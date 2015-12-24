package lms;

import java.util.Iterator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class LibraryFactory {

	public Library create_library(String name, int nbi, int n, int m, int mp){
			Library lib = new Library(name,nbi,n,m,mp);
			return lib;
	}
	
	public void add_room(Library lib, String roomName, double length, double height, double width){
		Room room = new Room(roomName,length,height,width);
		lib.getListRooms().add(room);
	}
	
	public void add_bookcase(Library library, Room room,Integer numShelves,String bcName,double length,double height,double width){
		if(library.getListRooms().contains(room)){
			double sumLength = 0;
			double sumWidth = 0;
			for(Bookcase bookcase:room.getListBookcases()){
				sumLength=sumLength+bookcase.getLength();
				sumWidth=sumWidth+bookcase.getWidth();
			}
			if(room.getLength()-sumLength>length&&room.getWidth()-sumWidth>width&&room.getHeight()>height){
				Bookcase bookcase=new Bookcase(bcName,length,height,width);				
				room.getListBookcases().add(bookcase);
				double new_height=(double)height/numShelves;
				for (int i = 1; i < numShelves+1; i++) {
					Shelf shelf = new Shelf(length,new_height,width);
					bookcase.getListShelves().add(shelf);
				}
			}
		
			else{
				System.out.println("There is an issue with your measures");
			}
		}
		else{
		System.out.println("That room is not in that library");	
		}
	}
	
	public void add_item(String author,String title,String itemType,int volumeNumber,String consultationType,String publisher,Library library,int publishingYear,double length,double width,double height){
		
		if (itemType.equalsIgnoreCase("BOOK")){
			if (consultationType.equalsIgnoreCase("ONLINECONSULTATION")){
				library.getStorageRoom().add(new Book(title,publisher,author,publishingYear,volumeNumber, ConsultationType.onlineConsultation,length,height,width,null,-1)); // -1 means it has no ISBN yet, but will be asked in the launcher
				}
			else if(consultationType.equalsIgnoreCase("BORROWING")){
				library.getStorageRoom().add(new Book(title,publisher,author,publishingYear,volumeNumber, ConsultationType.borrowing,length,height,width,null,-1));
			}
			else{
				System.out.println("You didn't enter a valid consultation type");
			}
		}
		else if (itemType.equalsIgnoreCase("CD")){
			if (consultationType.equalsIgnoreCase("ONLINECONSULTATION")){
				library.getStorageRoom().add(new CD(title,publisher,author,publishingYear,volumeNumber, ConsultationType.onlineConsultation,length,height,width,null));
				}
			else if(consultationType.equalsIgnoreCase("BORROWING")){
				library.getStorageRoom().add(new CD(title,publisher,author,publishingYear,volumeNumber, ConsultationType.borrowing,length,height,width,null));
			}
			else{
				System.out.println("You didn't enter a valid consultation type");
			}
		}
		else if (itemType.equalsIgnoreCase("DVD")){
			if (consultationType.equalsIgnoreCase("ONLINECONSULTATION")){
				library.getStorageRoom().add(new DVD(title,publisher,author,publishingYear,volumeNumber, ConsultationType.onlineConsultation,length,height,width,null));
				}
			else if(consultationType.equalsIgnoreCase("BORROWING")){
				library.getStorageRoom().add(new DVD(title,publisher,author,publishingYear,volumeNumber, ConsultationType.borrowing,length,height,width,null));
			}
			else{
				System.out.println("You didn't enter a valid consultation type");
			}			
		}
		else{
			System.out.println("This object cannot be added to the library");
		}
	}
		
	
	public void store_items(Library lib, String storing_strategy){
		switch(storing_strategy.toLowerCase())
		{
		case "anyfit": 
			AnyFit strategy1 = new AnyFit();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy1.store(item, lib);
			}
			lib.setStorageRoom(new ArrayList<LibraryItem>());
			break;
		
		case "bestbookcase":
			BestBookcase strategy2 = new BestBookcase();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy2.store(item, lib);
			}
			lib.setStorageRoom(new ArrayList<LibraryItem>());
			break;
		
		case "bestroom":
			BestRoom strategy3 = new BestRoom();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy3.store(item, lib);
			}
			lib.setStorageRoom(new ArrayList<LibraryItem>());
			break;
		
		case "bestshelf":
			BestShelf strategy4 = new BestShelf();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy4.store(item, lib);
			}
			lib.setStorageRoom(new ArrayList<LibraryItem>());
			break;
			
		default:
			System.out.println("You did not call a valid storing stategy. You can use AnyFit / BestShelf / BestBookcase / BestRoom");
		
		}
	}
	
	public void unstore_items(Library lib){
		ArrayList<Room> listRoom = lib.getListRooms();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					for (LibraryItem item : shelf.getListItems()){
						lib.getStorageRoom().add(item);
					}
					//we clear the shelf once we have stored all its items
					shelf.setListItems(new ArrayList<LibraryItem>());
					shelf.setFreeSpace(shelf.getLength());
				}
			}
		}
	}
	
	public String list_items(Library lib){
		String listItems = new String("List of the items :");
		ArrayList<Room> listRoom = lib.getListRooms();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					for (LibraryItem item : shelf.getListItems()){
						listItems = listItems + "\n" + item.toString();
					}
				}
			}
		}
		for (LibraryItem item : lib.getStorageRoom()){
			listItems=listItems+"\n"+item.toString();
		}
		return listItems;
	}
	
	public String list_room(Library lib, String roomName){
		boolean notRoom = true;

		ArrayList<Room> listRoom = lib.getListRooms();
		String listContent = new String();
		for (Room room : listRoom){
			if (room.getRoomName().equalsIgnoreCase(roomName)){
				//we have found the room : notRoom = false
				notRoom = false;
				listContent += "List of the content in room "+ roomName + " :\n------------------------------------------------\n";
				
				ArrayList<Bookcase> listBookcase = room.getListBookcases();
				for (Bookcase bookcase : listBookcase){
					listContent += "Bookcase named " + bookcase.getBcName()+ " containing :";
					
					ArrayList<Shelf> listShelf = bookcase.getListShelves();
					if(listShelf.isEmpty()){
						listContent += " nothing";
					}
					else {
						for (Shelf shelf : listShelf){
							ArrayList<LibraryItem> listItems = shelf.getListItems();
							
							listContent += "\n\tShelf number " + shelf.getShelfNumber()+ " containing :";
							if(listItems.isEmpty()){
								listContent += " nothing";
							}
							
							else {
								for (LibraryItem item : listItems){
									listContent = listContent + "\n\t\t" + item.toString();
								}
							}
						}
					}
					
					listContent += "\n\n";
				}
				//we get out of the loop if we have found the room
				break;
			}

		}
		
		if(notRoom){
			listContent += "No room with the name "+roomName+" was found.";
		}
		
		return listContent;
	}
	
	public String list_bookcase(Library lib, String roomName, String bookcaseName){
		boolean notRoom = true;

		ArrayList<Room> listRoom = lib.getListRooms();
		String listContent = new String();
		for (Room room : listRoom){
			
			if (room.getRoomName().equalsIgnoreCase(roomName)){
				//we have found the room : notRoom = false
				notRoom = false;
				
				boolean notBookcase = true;
				
				ArrayList<Bookcase> listBookcase = room.getListBookcases();
				for (Bookcase bookcase : listBookcase){
					
					if (bookcase.getBcName().equalsIgnoreCase(bookcaseName)){
						//we have found the bookcase : notBookcase = false
						notBookcase = false;
						
						
						listContent += "List of the content of bookcase "+bookcaseName+" in room "+ roomName + " :\n------------------------------------------------";
						
						ArrayList<Shelf> listShelf = bookcase.getListShelves();
						if(listShelf.isEmpty()){
							listContent += "\nno shelf in this bookcase";
						}
						else {
							for (Shelf shelf : listShelf){
								ArrayList<LibraryItem> listItems = shelf.getListItems();
								
								listContent += "\nShelf number " + shelf.getShelfNumber()+ " containing :";
								if(listItems.isEmpty()){
									listContent += " nothing";
								}
								
								else {
									for (LibraryItem item : listItems){
										listContent = listContent + "\n\t" + item.toString();
									}
								}
							}
						}
						
						
					}
					
					//we get out of the loop if we have found the bookcase
					break;
				}
				
				if(notBookcase){
					listContent += "No bookcase with the name "+bookcaseName+" was found.";
				}
				//we get out of the loop if we have found the room
				break;
			}

		}
		
		if(notRoom){
			listContent += "No room with the name "+roomName+" was found.";
		}
		
		return listContent;
	}
	
	
	public String find_items(Library lib, String author){
		String listItems = "";
		ArrayList<Room> listRoom = lib.getListRooms();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					for (LibraryItem item : shelf.getListItems()){
						if(item.getAuthor().equalsIgnoreCase(author)){
							listItems += item.toString()+"\n";
						}
					}
				}
			}
		}
		if(listItems.equals("")){
			listItems += "No item with publisher name "+author+" was found";
		}		
		return listItems;
	}
	
	
	public String search_title(Library lib, String title){
		String listItems = "";
		ArrayList<Room> listRoom = lib.getListRooms();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					for (LibraryItem item : shelf.getListItems()){
						if(item.getTitle().equalsIgnoreCase(title)){
							listItems += item.toString()+"\n";
						}
					}
				}
			}
		}
		if(listItems.equals("")){
			listItems += "No item with the title "+title+" was found";
		}		
		return listItems;
	}
	
	public void add_member(Library lib, String name, String surname, int ccNumber, String birthDate ) throws AlreadyExistsException {

		Date date = new Date();
	    try {
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	        date = formatter.parse(birthDate);
	      } catch (ParseException e) {
	        e.printStackTrace();
	      }

	    Member member = new Member(name, surname, date, ccNumber, lib);
	    if(lib.getListMembers().contains(member)){
	    	throw new AlreadyExistsException();
	    }
	    else{
		    lib.getListMembers().add(member);
	    }

		
	}
	
	public void borrow_item(Member member, LibraryItem item, Library library){
		if (member.isUnsuspended()&&member.getCurrentItems().size()>=library.getNbi()){
			boolean test=true;
			for (Room room: library.getListRooms()){
				for (Bookcase bookcase : room.getListBookcases()){
					for (Shelf shelf: bookcase.getListShelves()){
						for (LibraryItem libraryItem : shelf.getListItems()){
							if (libraryItem.getConsultationType().equals("borrowing")&&libraryItem.getHeight()==item.getHeight()&&libraryItem.getWidth()==item.getWidth()&&libraryItem.getLength()==item.getLength()&&libraryItem.getPublisher().equals(item.getPublisher())&&libraryItem.getPublishingYear()==item.getPublishingYear()&&libraryItem.getConsultationType()==item.getConsultationType()&&libraryItem.getVolumeNumber()==item.getVolumeNumber()&&libraryItem.getTitle().equals(item.getTitle())&&libraryItem.getAuthor().equals(item.getAuthor())){
								libraryItem.setLocation(null);
								libraryItem.setBorrowable(false);
								libraryItem.getBorrowingList().add(member);
								if (libraryItem instanceof Book){
									if(member.getCard().getType()==CardType.golden){
										member.getHistory().add(new Borrowing(libraryItem,member,modifyDate(-56),null));
										member.getCurrentItems().add(new Borrowing(libraryItem,member,modifyDate(-56),null));
									}
									else{
										member.getHistory().add(new Borrowing(libraryItem,member,modifyDate(-28),null));
										member.getCurrentItems().add(new Borrowing(libraryItem,member,modifyDate(-28),null));
									}
								}
								else if (libraryItem instanceof CD){
									if(member.getCard().getType()==CardType.golden){
										member.getHistory().add(new Borrowing(libraryItem,member,modifyDate(-21),null));
										member.getCurrentItems().add(new Borrowing(libraryItem,member,modifyDate(-21),null));
									}
									else{
										member.getHistory().add(new Borrowing(libraryItem,member,modifyDate(-7),null));
										member.getCurrentItems().add(new Borrowing(libraryItem,member,modifyDate(-7),null));
									}
								}
								else{
									if(member.getCard().getType()==CardType.golden){
										member.getHistory().add(new Borrowing(libraryItem,member,modifyDate(-28),null));
										member.getCurrentItems().add(new Borrowing(libraryItem,member,modifyDate(-28),null));
									}
									else{
										member.getHistory().add(new Borrowing(libraryItem,member,modifyDate(-14),null));
										member.getCurrentItems().add(new Borrowing(libraryItem,member,modifyDate(-14),null));
									}
								}
								test=false;
								break;
							}
						}
					}
				}
			}
			if(test){
				System.out.println("Your item currently isn't in the library, reserve it");
			}
		}
		else{
			System.out.println("You are currently not able to borrow any item");
		}
	}
	
	public void check_borrowed(Library library, Member member){
		// the work that method needs to do is partly done by the check method which launches when the program starts running from the class Library
		//Hence members are already penalised
		if (member.isUnsuspended()&&member.getCurrentItems().size()>=library.getNbi()){
			System.out.println(member.getName()+" is allowed to borrow items");
		}
		else if(member.getCurrentItems().size()>=library.getNbi()){
			System.out.println(member.getName()+" needs to return some items before borrowing again");
		}
		else{
			System.out.println(member.getName()+" is currently suspended");
		}
	}
	
	private static Date modifyDate(int numberOfDay)
	  {
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.DATE, numberOfDay);
		  return cal.getTime();
	  }
	
}
