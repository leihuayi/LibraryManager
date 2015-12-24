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
					catch(AlreadyExistsException e){
						System.out.println("Sorry, no library of the name "+libraryName+" exists, you must have spelled it wrong. \n Please write again either 1 or 2.");
					}
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
		boolean goON=true;
		while(goON=true){
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
					boolean nonExistingRoom=true;
					for (Room room : library.getListRooms()){
						if (room.getRoomName()==tabArguments1[0]){
							nonExistingRoom=false;
						}
					}
					if(nonExistingRoom){
						try{
							double double1 = Double.parseDouble(tabArguments1[1]);
							double double2 = Double.parseDouble(tabArguments1[2]);
							double double3 = Double.parseDouble(tabArguments1[3]);
							libF.add_room(library,tabArguments1[0],double1,double2,double3);
							System.out.println("The addition of the room was successful");
						}
						catch(Exception NumberFormatException){
							System.out.println("You must have entered the wrong type of data or the wrong number of arguments.");
						}
					}
					else{
						System.out.println("A room with that name already exists");
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
						boolean existingRoom=false;
						for (Room room : library.getListRooms()){
							if (room.getRoomName()==tabArguments2[0]){
								libF.add_bookcase(library,room,int1,tabArguments2[2],double1,double2,double3);
								existingRoom=true;
								break;
							}
						}
						if(existingRoom){
							System.out.println("The addition of the Bookcase was successful");
						}
						else{
							System.out.println("There is no such room");
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
					if (tabArguments3[2].equalsIgnoreCase("BOOK")||tabArguments3[2].equalsIgnoreCase("CD")||tabArguments3[2].equalsIgnoreCase("DVD")||tabArguments3[4].equalsIgnoreCase("BORROWING")||tabArguments3[4].equalsIgnoreCase("ONLINECONSULTATION")){
						try{
							int int1 = Integer.parseInt(tabArguments3[3]);
							int int2 = Integer.parseInt(tabArguments3[6]);
							double double1 = Double.parseDouble(tabArguments3[7]);
							double double2 = Double.parseDouble(tabArguments3[8]);
							double double3 = Double.parseDouble(tabArguments3[9]);
							libF.add_item(tabArguments3[0],tabArguments3[1],tabArguments3[2],int1,tabArguments3[4],tabArguments3[5],library,int2,double1,double2,double3);
							System.out.println("The addition of the item was successful");
						}
						catch(Exception NumberFormatException){
							System.out.println("You must have entered the wrong type of data or the wrong number of arguments.");
						}
					}
					
					break;
				case 4:
					System.out.println("The syntax is store_items(storingStrategy)\nsotringStrategy can be anyfit, bestshelf, bestbookcase, bestroom");
					Scanner sc4 = new Scanner(System.in);
					String storeItems = sc4.nextLine();
					
					String nameOfMethod4 = storeItems.substring(0,storeItems.indexOf('('));
					String listOfArguments4 = storeItems.substring(storeItems.indexOf('(')+1,storeItems.indexOf(')'));
					String tabArguments4[] = listOfArguments4.split(",");
					if (tabArguments4[0].equalsIgnoreCase("ANYFIT")||tabArguments4[0].equalsIgnoreCase("BESTSHELF")||tabArguments4[0].equalsIgnoreCase("BESTROOM")||tabArguments4[0].equalsIgnoreCase("BESTBOOKCASE")){
						libF.store_items(library,tabArguments4[0]);
						System.out.println("The storage was successful");
						}
					else{
						System.out.println("This algorithm doesn't exist");
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
					
					break;
				case 7:
					
					break;
				case 8:
					
					break;
				case 9:
					
					break;
				case 10:
					
					break;
				case 11:
					
					break;
				case 12:
					
					break;
					

				default:
					System.out.println("This is not a number between (1) and (12)");
					break;
				}
			} catch(Exception InputMismatchException){
				System.out.println("That is not a possible choice, please try again");
			}
		}
		
		
		/*
		LibraryFactory libF = new LibraryFactory();
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Please call a method :");
		String calledMethod = sc.nextLine();
		
		String nameOfMethod = calledMethod.substring(0,calledMethod.indexOf('('));
		String listOfArguments = calledMethod.substring(calledMethod.indexOf('(')+1,calledMethod.indexOf(')'));
		String tabArguments[] = listOfArguments.split(",");
		
		
		if(nameOfMethod.equals("create_library")){
			try{
				int int1 = Integer.parseInt(tabArguments[1]);
				int int2 = Integer.parseInt(tabArguments[2]);
				int int3 = Integer.parseInt(tabArguments[3]);
				int int4 = Integer.parseInt(tabArguments[4]);
				libF.create_library(tabArguments[0],int1,int2,int3,int4);
				System.out.println("the creation of the library was successful");
			}
			catch(Exception NumberFormatException){
				System.out.println("You must have entered the wrong type of data or the wrong number of arguments.");
			}

			
		}
		
		else{
			System.out.println("You must have made a mistake in writing the method name. Please try again.");
		}
		*/
	


	}

}
