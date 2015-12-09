import java.util.Scanner;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		


		
		
		String itemType = "book";
		boolean testType = true;
		boolean testType1= true;
		boolean testType2 = true;
		
		
		
		if (itemType.equalsIgnoreCase("BOOK") || itemType.equalsIgnoreCase("DVD") || itemType.equalsIgnoreCase("CD")){
			
			ArrayList<String> listOfAttributes = new ArrayList<String>();
			
			/*
			 * We first ask the attributes in common for all the items
			 */
			
			//title
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Title of the item: ");

				String answer = sc.nextLine();
				//check that the user enters a title
				if (answer == ""){
					System.out.println("You did not enter a title");

				}
				else {
					listOfAttributes.add(0,answer);
					testType = false;
				}
			}

			//publisher
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("publisher: ");
				String answer = sc.nextLine();
				//check that the user enters an publisher
				if (answer == ""){
					System.out.println("You did not enter a publisher");
				}
				else {
					listOfAttributes.add(1,answer);
					testType = false;
				}
			}
			
			//publishingyear
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Publishing year: ");
				
				//check that the user enters an int	
				try{
					int year = sc.nextInt();
					listOfAttributes.add(2,Integer.toString(year));
					testType = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a year");
				}
			}
			
			//volumenumber
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Volume number (press 0 if there is only one volume): ");
				
				//check that the user enters an int
				try{
					int volnum = sc.nextInt();
					if (volnum ==0){
						listOfAttributes.add(3,"");
					}
					else{
						listOfAttributes.add(3,Integer.toString(volnum));
					}
					testType = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
			}
			
			//consultationType
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Consultation type (online consultation / borrowing): ");
				String answer = sc.nextLine();
				
				//check that the user enters a consultation type
				if(answer.equalsIgnoreCase("borrowing") || answer.equalsIgnoreCase("online consultation")){
					testType = false;
					if (answer.equalsIgnoreCase("online consultation")){
						listOfAttributes.add(4,"onlineConsultation");
					}
					else {
						listOfAttributes.add(4,"borrowing");
					}
				}
				
				else {
					System.out.println("You have to enter either 'online consultation' or 'borrowing' ");
				}
			}
			
			
			//borrowingdeadline

			//loop for day
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Borrowing deadline: \n \t Day:");
				
				try{
					int day = sc.nextInt();
					testType = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
			}
			//loop for month
			testType1 = true;
			while (testType1){
				Scanner sc1 = new Scanner(System.in);
				System.out.print("\t Month:");
					
				try{
					int month = sc1.nextInt();
					testType1 = false;
					}

				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
					
			}
				
			//loop for year
			testType2 = true;
			while (testType2){
				Scanner sc2 = new Scanner(System.in);
				System.out.print("\t Year:");
				
				try{
					int year = sc2.nextInt();
					testType2 = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
			}
			//The date will be stored in the form dd:MM:yyyy , and will be fetched with a regexp
			listOfAttributes.add(5,"day"+":"+"month"+":"+"year");
			
			
			
			
			//measures

			//loop for length
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Measure of the item in cm  /!\\ put a , and NOT a . before the decimals : \n \t Length:");
				
				try{
					double length = sc.nextDouble();
					testType = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
			}
			//loop for month
			testType1 = true;
			while (testType1){
				Scanner sc1 = new Scanner(System.in);
				System.out.print("\t Height:");
					
				try{
					double height = sc1.nextDouble();
					testType1 = false;
					}

				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
					
			}
				
			//loop for width
			testType2 = true;
			while (testType2){
				Scanner sc2 = new Scanner(System.in);
				System.out.print("\t Width:");
				
				try{
					double width = sc2.nextDouble();
					testType2 = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
			}
			//The measure will be stored in the form length:heigth:width , and will be fetched with a regexp
			listOfAttributes.add(5,"length"+":"+"height"+":"+"width");
			
			
			
			//location
			listOfAttributes.add(6,"endroit");		
			
			/*
			 * End of the attributes in common ! 
			 * Now for the attributes specific for a type of item:
			 * 
			 */
			
			//book
			if(itemType.equalsIgnoreCase("BOOK")){
				
				//ISBN
				testType = true;
				while (testType){
					Scanner sc = new Scanner(System.in);
					System.out.print("ISBN number");
					
					try{
						int isbn = sc.nextInt();
						testType = false;
						listOfAttributes.add(7,"isbn");
					}
					catch (java.util.InputMismatchException ime){
						System.out.println("You did not enter a number");
					}
				}
				
				
			}
			
		for (int i= 0; i<8; i++){
			System.out.println(listOfAttributes.get(i));
		}
			
			
			
			

		}

			
		


		




		
		
	}

}
