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
		
		LibraryFactory libF = new LibraryFactory();
		/*
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
		
		Library library = new Library("library",20,20,20,20);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		CD CD1 =new CD("CD1","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		CD CD2 =new CD("CD2","Alexandre Rozier",2016,1,ConsultationType.borrowing,2,1,1,null);
		shelf.getListItems().add(CD1);
		shelf.getListItems().add(CD2);
		DVD DVD =new DVD("DVD","yolo",2015,1,ConsultationType.borrowing,1,1,1,null);
		shelfbis.getListItems().add(DVD);
		

		System.out.println(libF.list_items(library));

		
	}

}
