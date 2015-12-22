
import java.util.Iterator;

import java.util.ArrayList;


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
	
	public void add_item(){
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
						if(item.getPublisher().equalsIgnoreCase(author)){
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
	
	
	public String search_title(){
		return "";
	}
	
	public void add_member(){
	}
	
	public void borrow_item(){
	}
	
	public String check_borrowed(){
		return "";
	}
	
}
