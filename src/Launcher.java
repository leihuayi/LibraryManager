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
				System.out.println("You must have entered the wrong type of data.");
			}

			
		}
		
		else{
			System.out.println("You must have made a mistake in writing the method name. Please try again.");
		}
		

		
	}

}
