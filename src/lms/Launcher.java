package lms;
import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.io.*;
import java.lang.reflect.Array;

public class Launcher {
	

	public static void main(String[] args) throws ParseException{
		// TODO Auto-generated method stub	
		
		boolean correctAnswer = false;

		LibraryFactory libF = new LibraryFactory();
		Library library= new Library("empty_library",0,0,0,0);
		System.out.println("Welcome to the Library Management System (LMS), you will have to enter the corresponding number and then press Enter");
		System.out.println("Type (1) if you want to use the already saved library or (2) if you want to create a new one");
		while(!correctAnswer){
			try {
				Scanner sc = new Scanner(System.in);
				int libraryChoice = sc.nextInt();
				
				while (!(libraryChoice==1||libraryChoice==2)){
					System.out.println("That is not a possible choice, please try again");
					libraryChoice = sc.nextInt();
				}
				if (libraryChoice==1){
					System.out.println("Please enter the name of the library you would like to fetch. \n This file must be located in the savedLibraries directory, in the same directory as this LMS.");
					Scanner sc2 = new Scanner(System.in);
					String libraryName = sc2.nextLine();
					Serialization ser = new Serialization();
					try{
						library=ser.fetchLibrary(libraryName);
						correctAnswer = true;
					}
					catch(IOException e){
						System.out.println("Sorry, no library of the name "+libraryName+" exists, you must have spelled it wrong. \n Please write again either 1 or 2.");
					}
					libF.check(library);
				}
				else if (libraryChoice==2){
					System.out.println("You decided to create a new library, respect the following syntax:\ncreate_library(libraryName,NBI,N,M,MP)\nNBI:a registered member can borrow at most NBI items at the same time\nN and M :a member who has borrowed at least N items over the last M months should be automatically granted the frequent member card\nMP:a frequent member who has borrowed less N items over the last MP months will lose frequent membership ");
					Scanner sc2 = new Scanner(System.in);
					String libraryCreation = sc2.nextLine();
					
					String nameOfMethod0 = libraryCreation.substring(0,libraryCreation.indexOf('('));
					String listOfArguments0 = libraryCreation.substring(libraryCreation.indexOf('(')+1,libraryCreation.indexOf(')'));
					String tabArguments0[] = listOfArguments0.split(",");
					try{
						int int1 = Integer.parseInt(tabArguments0[1]);
						int int2 = Integer.parseInt(tabArguments0[2]);
						int int3 = Integer.parseInt(tabArguments0[3]);
						int int4 = Integer.parseInt(tabArguments0[4]);
						library =libF.create_library(tabArguments0[0],int1,int2,int3,int4);
						correctAnswer = true;
						System.out.println("The creation of the library was successful");
					}
					catch(Exception NumberFormatException){
						System.out.println("You must have entered the wrong type of data or the wrong number of arguments.");
					}
				}
			}
			catch(Exception InputMismatchException){
				System.out.println("That is not a possible choice, please try again");
			}
			
		}
		boolean loop=true;
		while(loop==true){
			System.out.println("Now you can choose the method you want to use by entering a number and pressing the Enter key.\n(1) add_room\n(2) add_bookcase\n(3) add_item\n(4) store_items\n(5) unstore_items\n(6) list_items\n(7) list_room\n(8) list_bookcase\n(9) find_items\n(10) search_title\n(11) add_member\n(12) borrow_item\n(13) check_borrowed\n(14) Quit the program ");
			try {
				Scanner sc = new Scanner(System.in);
				int libraryChoice = sc.nextInt();
				switch (libraryChoice) {
				case 1:
					System.out.println("The syntax is add_room(roomName,length,height,width) (you can't have two rooms with the same name)");
					Scanner sc1 = new Scanner(System.in);
					String addRoom = sc1.nextLine();					
					String nameOfMethod1 = addRoom.substring(0,addRoom.indexOf('('));
					String listOfArguments1 = addRoom.substring(addRoom.indexOf('(')+1,addRoom.indexOf(')'));
					String tabArguments1[] = listOfArguments1.split(",");
					
					try{
							double double1 = Double.parseDouble(tabArguments1[1]);
							double double2 = Double.parseDouble(tabArguments1[2]);
							double double3 = Double.parseDouble(tabArguments1[3]);
							try{
								libF.add_room(library,tabArguments1[0],double1,double2,double3);
								System.out.println("The room "+tabArguments1[0]+" was successfully added to the library");
							}
							
							catch(AlreadyExistsException e){
								System.out.println("A room with that name already exists. Please create another one.");
							}
					}
					catch(Exception NumberFormatException){
						System.out.println("You must have entered the wrong type of data or the wrong number of arguments.");
					}
					
					
					
					break;
				case 2:
					System.out.println("The syntax is add_bookcase(roomNameWhereAdded,numberOfShelves,bookcaseName,length,height,width)");
					Scanner sc2 = new Scanner(System.in);
					String addBookcase = sc2.nextLine();					
					String nameOfMethod2 = addBookcase.substring(0,addBookcase.indexOf('('));
					String listOfArguments2 = addBookcase.substring(addBookcase.indexOf('(')+1,addBookcase.indexOf(')'));
					String tabArguments2[] = listOfArguments2.split(",");
					try{
						int int1 = Integer.parseInt(tabArguments2[1]);
						double double1 = Double.parseDouble(tabArguments2[3]);
						double double2 = Double.parseDouble(tabArguments2[4]);
						double double3 = Double.parseDouble(tabArguments2[5]);

						try{
							libF.add_bookcase(library,tabArguments2[0],int1,tabArguments2[2],double1,double2,double3);
							System.out.println("The addition of the Bookcase was successful");
						}
						catch(AlreadyExistsException e){
							System.out.println("A bookcase of this name already exits in the room. Please create another one.");
						}
						catch(NoSuchFieldException e){
							System.out.println("That room is not in that library");
						}
						catch(IndexOutOfBoundsException e){
							System.out.println("There is an issue with your measures, your bookcase is too big for the room");
						}
									
						
					}
					
					catch(Exception NumberFormatException){
						System.out.println("You must have entered the wrong type of data or the wrong number of arguments.");
					}
					break;
				case 3:
					System.out.println("The syntax is add_item(author,title,itemType,volumeNumber,consultationType,publisher,publishingYear,length,height,width)\nitemType: Book, CD or DVD\nconsultationType: borrowing or onlineConsultation");
					Scanner sc3 = new Scanner(System.in);
					String addItem = sc3.nextLine();
					
					String nameOfMethod3 = addItem.substring(0,addItem.indexOf('('));
					String listOfArguments3 = addItem.substring(addItem.indexOf('(')+1,addItem.indexOf(')'));
					String tabArguments3[] = listOfArguments3.split(",");
					if (tabArguments3[2].equalsIgnoreCase("BOOK")||tabArguments3[2].equalsIgnoreCase("CD")||tabArguments3[2].equalsIgnoreCase("DVD")){
						try{
							int int1 = Integer.parseInt(tabArguments3[3]);
							int int2 = Integer.parseInt(tabArguments3[6]);
							double double1 = Double.parseDouble(tabArguments3[7]);
							double double2 = Double.parseDouble(tabArguments3[8]);
							double double3 = Double.parseDouble(tabArguments3[9]);
							try{
									libF.add_item(tabArguments3[0],tabArguments3[1],tabArguments3[2],int1,tabArguments3[4],tabArguments3[5],library,int2,double1,double2,double3);
									if (tabArguments3[2].equalsIgnoreCase("BOOK")){
										System.out.println("That item is a book, please enter the ISBN Code");
										Scanner sc31 = new Scanner(System.in);
										int ISBN = sc.nextInt();
										for (LibraryItem item : library.getStorageRoom()){
											if (item instanceof Book){
												Book book =(Book)item;
												if(book.getISBNCode()==-1){
													book.setISBNCode(ISBN);
													
													break;
												}
											}
										}
										
									
									}
									System.out.println("The addition of the item was successful");
							}
							catch(IllegalArgumentException e){
								System.out.println("You did not enter a valid consultation type. You have to enter either borrowing or online consultation.");
							}
							
						}
						catch(Exception NumberFormatException){
							System.out.println("You must have entered the wrong type of data or the wrong number of arguments.");
						}
					}
					else{
						System.out.println("The object "+tabArguments3[2]+" is not a valid item. Pleae chose a book, CD or DVD.");
					}
					
					break;
				case 4:
					System.out.println("The syntax is store_items(storingStrategy)\nstoringStrategy can be anyfit, bestshelf, bestbookcase, bestroom");
					Scanner sc4 = new Scanner(System.in);
					String storeItems = sc4.nextLine();
					
					String nameOfMethod4 = storeItems.substring(0,storeItems.indexOf('('));
					String listOfArguments4 = storeItems.substring(storeItems.indexOf('(')+1,storeItems.indexOf(')'));
					String tabArguments4[] = listOfArguments4.split(",");
					try{
						libF.store_items(library,tabArguments4[0]);
						System.out.println("The storage was successful");
						}
					catch(IllegalArgumentException e){
						System.out.println("You did not call a valid storing stategy. You can use AnyFit / BestShelf / BestBookcase / BestRoom");
					}
					break;
				case 5:
					System.out.println("The syntax is unstore_items()");
					Scanner sc5 = new Scanner(System.in);
					String unstoreItems = sc5.nextLine();
					
					String nameOfMethod5 = unstoreItems.substring(0,unstoreItems.indexOf('('));
					String listOfArguments5 = unstoreItems.substring(unstoreItems.indexOf('(')+1,unstoreItems.indexOf(')'));
					String tabArguments5[] = listOfArguments5.split(",");
					libF.unstore_items(library);
					System.out.println("Unstorage was successful");
					break;
				case 6:
					System.out.println("The syntax is list_items()");
					Scanner sc6 = new Scanner(System.in);
					String listItems = sc6.nextLine();
					
					String nameOfMethod6 = listItems.substring(0,listItems.indexOf('('));
					String listOfArguments6 = listItems.substring(listItems.indexOf('(')+1,listItems.indexOf(')'));
					String tabArguments6[] = listOfArguments6.split(",");					
					System.out.println(libF.list_items(library));
					break;
				case 7:
					System.out.println("The syntax is list_room(roomName)");
					Scanner sc7 = new Scanner(System.in);
					String listRoom = sc7.nextLine();
						
					String nameOfMethod7 = listRoom.substring(0,listRoom.indexOf('('));
					String listOfArguments7 = listRoom.substring(listRoom.indexOf('(')+1,listRoom.indexOf(')'));
					String tabArguments7[] = listOfArguments7.split(",");
					System.out.println(libF.list_room(library,tabArguments7[0]));			
					break;
				case 8:
					System.out.println("The syntax is list_bookcase(roomName,bookcaseName)\nNote:roomName is the name of the room where the bookcase is located");
					Scanner sc8 = new Scanner(System.in);
					String listBookcase = sc8.nextLine();
					
					String nameOfMethod8 = listBookcase.substring(0,listBookcase.indexOf('('));
					String listOfArguments8 = listBookcase.substring(listBookcase.indexOf('(')+1,listBookcase.indexOf(')'));
					String tabArguments8[] = listOfArguments8.split(",");
					System.out.println(libF.list_bookcase(library, tabArguments8[0], tabArguments8[1]));
					break;
				case 9:
					System.out.println("The syntax is find_items(authorName)");
					Scanner sc9 = new Scanner(System.in);
					String findItems = sc9.nextLine();
					
					String nameOfMethod9 = findItems.substring(0,findItems.indexOf('('));
					String listOfArguments9 = findItems.substring(findItems.indexOf('(')+1,findItems.indexOf(')'));
					String tabArguments9[] = listOfArguments9.split(",");
					System.out.println(libF.find_items(library,tabArguments9[0]));	
					break;
				case 10:
					System.out.println("The syntax is search_title(titleOfItem)");
					Scanner sc10 = new Scanner(System.in);
					String searchTitle = sc10.nextLine();
					
					String nameOfMethod10 = searchTitle.substring(0,searchTitle.indexOf('('));
					String listOfArguments10 = searchTitle.substring(searchTitle.indexOf('(')+1,searchTitle.indexOf(')'));
					String tabArguments10[] = listOfArguments10.split(",");
					System.out.println(libF.search_title(library,tabArguments10[0]));	
					break;
				case 11:
					System.out.println("The syntax is add_member(name,surname,creditCardNumber,birthDate)\nbirthDate:yyyy/MM/dd (ex : 1999/09/01)\ncreditCardNumber:without spaces");
					Scanner sc11 = new Scanner(System.in);
					String addMember = sc11.nextLine();
					
					String nameOfMethod11 = addMember.substring(0,addMember.indexOf('('));
					String listOfArguments11 = addMember.substring(addMember.indexOf('(')+1,addMember.indexOf(')'));
					String tabArguments11[] = listOfArguments11.split(",");						
					try{
						int int1 = Integer.parseInt(tabArguments11[2]);
						libF.add_member(library,tabArguments11[0],tabArguments11[1],int1,tabArguments11[3]);
						System.out.println("The member was successfully added to the library");
					}
					
					catch(AlreadyExistsException e){
						System.out.println("A member with this information already exists. Please create another one.");
					}
					catch(IllegalArgumentException e){
						System.out.println("You did not enter the date in the right format.");
					}
					break;
				case 12:
					System.out.println("The syntax is borrow_item(name,creditCardNumber,itemTitle,volumeNumber,author)\ncreditCardNumber:without spaces");
					Scanner sc12 = new Scanner(System.in);
					String borrowItem = sc12.nextLine();
					
					String nameOfMethod12 = borrowItem.substring(0,borrowItem.indexOf('('));
					String listOfArguments12 = borrowItem.substring(borrowItem.indexOf('(')+1,borrowItem.indexOf(')'));
					String tabArguments12[] = listOfArguments12.split(",");	
					try{
						int int1 = Integer.parseInt(tabArguments12[1]);
						int int2 = Integer.parseInt(tabArguments12[3]);
						Member member = new Member(null,null,null,0,library);
						for (Member memberbis : library.getListMembers()){
							if(memberbis.getCcNumber()==int1&&memberbis.getName().equalsIgnoreCase(tabArguments12[0])){
								member=memberbis;
								break;
							}
						}
						boolean test=true;
						for(Room room:library.getListRooms()){
							for(Bookcase bookcase:room.getListBookcases()){
								for(Shelf shelf:bookcase.getListShelves()){
									for(LibraryItem item:shelf.getListItems()){
										if(item.getAuthor().equalsIgnoreCase(tabArguments12[4])&&item.getTitle().equalsIgnoreCase(tabArguments12[2])&&item.getVolumeNumber()==int2){
											libF.borrow_item(member, item, library);
											test=false;
											break;
										}
									}
								}
							}
						}
					}
					catch(NullPointerException e){
						System.out.println("The item currently isn't in the library, reserve it");
					}
					catch(IllegalStateException e){
						System.out.println("That member is currently not able to borrow any item");
					}
					break;
				case 13:
					System.out.println("The syntax is check_borrowed(name,creditCardNumber)\ncreditCardNumber:without spaces");
					Scanner sc13 = new Scanner(System.in);
					String checkBorrowed = sc13.nextLine();
					
					String nameOfMethod13 = checkBorrowed.substring(0,checkBorrowed.indexOf('('));
					String listOfArguments13 = checkBorrowed.substring(checkBorrowed.indexOf('(')+1,checkBorrowed.indexOf(')'));
					String tabArguments13[] = listOfArguments13.split(",");
					int int1=Integer.parseInt(tabArguments13[1]);
					boolean memberNotInLibrary=true;
					for (Member member:library.getListMembers()){
						if(member.getName().equalsIgnoreCase(tabArguments13[0])&&int1==member.getCcNumber()){
							System.out.println(libF.check_borrowed(library, member));
							memberNotInLibrary=false;
							break;
							}						
					}
					if(memberNotInLibrary){
						System.out.println("That member is not in the library");
					}
					break;
				case 14:	
					loop=false;
					boolean yesOrNo = false;
					System.out.println("You have decided to quit the program. Do you want to save your changes? (y/n) ");
					while(!yesOrNo){
						Scanner scYN = new Scanner(System.in);
						String answer = scYN.nextLine();
						if(answer.equalsIgnoreCase("y")){
							Serialization ser = new Serialization();
							try{
								ser.saveLibrary(library, false);
							}
							catch(AlreadyExistsException e){
								boolean oOrc = false;
								System.out.println("A library of this name already exists. Enter: \n\t (o) if you want to save over it \n\t (c) if you want to change your library name");
								while (!oOrc){
									Scanner scOC = new Scanner(System.in);
									String answerOC = scOC.nextLine();
									if(answerOC.equalsIgnoreCase("o")){
										ser.saveLibrary(library,true);
										oOrc = true;
									}
									else if(answerOC.equalsIgnoreCase("c")){
									System.out.println("Write your new library name");
									Scanner scLibN = new Scanner(System.in);
									library.setLibraryName(scLibN.nextLine());
									System.out.println("You library name was successfully changed to "+library.getLibraryName()+". Try to save again with (14).");
									oOrc = true;
									}
									else{
										System.out.println("Please enter either o or c .");
									}
								}
							}
							catch(IOException e){
								System.out.println("Whoops there must have been a bug in the saving. Please try again.");
							}
							yesOrNo = true;
						}
						else if(answer.equalsIgnoreCase("n")){
							yesOrNo = true;
						}
						else{
							System.out.println("Please enter either y or n .");
						}
					}
					break;
					

				default:
					System.out.println("This is not a number between (1) and (12)");
					break;
				}
			} catch(Exception InputMismatchException){
				System.out.println("That is not a possible choice, please try again");
			}
		}
		
		
		
	


	}

}
