import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.*;
import java.util.regex.*;

public class Launcher {
	
	
	
	public static void main(String[] args) throws ParseException{
		// TODO Auto-generated method stub	
		Library library = new Library("LibrairieDeSassa",0,0,0);
		Member member= new Member("Cocher","Thomas",new Date(),123,library);
		library.getListMembers().add(member);
		Date actuelle = new Date();
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String dat = dateFormat.format(actuelle);
		 System.out.println("======================");
		 System.out.println(dat);
		 

		//library.toString();
		Serialization ser = new Serialization();
		ser.saveLibrary(library);
		Library lib = new Library("Library",1,2,3);
		lib = ser.fetchLibrary();
		System.out.println((lib.getListMembers()).get(0).getName());
		System.out.println(lib.getLibraryName());
		//lib.toString();
		
		
		//utilisation de regex pour reformer la date à partir des strings
		Pattern p = Pattern.compile(":");
		String[] items = p.split("23:10:1994");
		System.out.println("jour: "+items[0]+ ", mois: "+items[1]+", année: "+items[2]);
		
		boolean testType = true;

		//volumenumber
		testType = true;
		while (testType){
			Scanner sc = new Scanner(System.in);
			System.out.print("Title of the item: ");

			String answer = sc.nextLine();
			//check that the user enters a title
			if (answer == ""){
				System.out.println("You did not enter a title");

			}
			
			
			/*
			Scanner sc = new Scanner(System.in);
			System.out.print("Volume number (press enter if there is only one volume): ");
			
			//check that the user enters an int
			if (!sc.hasNextLine()){
				System.out.println("entrée vide");
			}
			else if (sc.hasNextInt()==false){
				System.out.println("bouh");
			}
			else{
				testType = false;
			}
			System.out.println("fini!");
		*/
		}
		

		




		
		
	}

}
