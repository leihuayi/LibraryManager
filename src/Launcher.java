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
		

		Library library = new Library("LibrairieDeSassa",0,0,0,0);
		Member member= new Member("Cocher","Thomas",new Date(),123,library);
	
		library.getListMembers().add(member);

		/*
		ItemFactory itemFactory = new ItemFactory();
		
		Scanner scType = new Scanner(System.in);
		System.out.print("Enter the type of item you want to create (book/CD/DVD): ");
		String itemType = scType.nextLine();
		
		
		// ask the factory to create the item
		itemFactory.createItem(itemType,library);
		*/
		
		LibraryFactory libF = new LibraryFactory();
		libF.create_library("Librairie_De_Tomate");
		
		

		
	}

}
