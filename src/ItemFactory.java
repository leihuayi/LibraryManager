import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ItemFactory {
	
	public LibraryItem createItem(String itemType, Library library){
		
		//booleans which will help us to make tests to know whether the user enters the right type of data
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
					listOfAttributes.add(3,Integer.toString(volnum));
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

			int day=0, month=0, year=0;
			//loop for day
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Borrowing deadline: \n \t Day:");
				
				try{
					day = sc.nextInt();
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
					month = sc1.nextInt();
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
					year = sc2.nextInt();
					testType2 = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
			}
			//The date will be stored in the form yyyy/MM/dd , and will be stored as a Date with SimpleDateFormat
			listOfAttributes.add(5,Integer.toString(year)+"/"+Integer.toString(month)+"/"+Integer.toString(day));
			

			
			
			
			
			//measures
			double length=0.0,height=0.0,width=0.0;
			//loop for length
			testType = true;
			while (testType){
				Scanner sc = new Scanner(System.in);
				System.out.print("Measure of the item in cm  /!\\ put a , and NOT a . before the decimals : \n \t Length (thickness):");
				
				try{
					length = sc.nextDouble();
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
					height = sc1.nextDouble();
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
					width = sc2.nextDouble();
					testType2 = false;
				}
				catch (java.util.InputMismatchException ime){
					System.out.println("You did not enter a number");
				}
			}
			//The measure will be stored in the form length:heigth:width , and will be fetched with a regexp
			listOfAttributes.add(6,Double.toString(length)+":"+Double.toString(height)+":"+Double.toString(width));
			
			
			
			//location
			listOfAttributes.add(7,"");		
			
			/*
			 * End of the attributes in common ! 
			 * Now for the attributes specific for a type of item and the creation of the Object !
			 * 
			 */
			
			
			
			/*
			 * Transformation of the Strings in Objects in order to fill in the attributes of the item
			 */
			
			//ConsultationType
			ConsultationType ct;
			if(listOfAttributes.get(4)=="onlineConsultation"){
				ct = ConsultationType.onlineConsultation;
			}
			else{
				ct = ConsultationType.borrowing;
			}
			
			//BorrowingDeadline
			Date date = new Date();
		    try {
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		        date = formatter.parse(listOfAttributes.get(5));
		      } catch (ParseException e) {
		        e.printStackTrace();
		      }
		    
		    //Measures: use a regexp to get each side
			Pattern p = Pattern.compile(":");
			String[] arrayCuboid = p.split(listOfAttributes.get(6));
			Cuboid cuboid = new Cuboid(Double.parseDouble(arrayCuboid[0]),Double.parseDouble(arrayCuboid[1]),Double.parseDouble(arrayCuboid[2]));
		    
			
			
			/*
			 * Now the implementation regarding the type of item requested!
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
						listOfAttributes.add(8,"isbn");
					}
					catch (java.util.InputMismatchException ime){
						System.out.println("You did not enter a number");
					}
				}
				

				
				/*
				 * Creation of the object
				 */
				Book book= new Book(listOfAttributes.get(0),listOfAttributes.get(1),Integer.parseInt(listOfAttributes.get(2)),
						Integer.parseInt(listOfAttributes.get(3)),ct, date, cuboid, null , Integer.parseInt(listOfAttributes.get(8)) );
				// we can use any algorithm for the location
				Location location = library.anyFit(book);
				book.setLocation(location);
				return book;
				
			}
			
			else if(itemType.equalsIgnoreCase("CD")){
				
				/*
				 * Creation of the object
				 */
				CD CD = new CD(listOfAttributes.get(0),listOfAttributes.get(1),Integer.parseInt(listOfAttributes.get(2)),
						Integer.parseInt(listOfAttributes.get(3)),ct, date, cuboid, null );
				Location location = library.anyFit(CD);
				CD.setLocation(location);
				return CD;
				
			}
			
			else{
				
				/*
				 * Creation of the object
				 */
				DVD DVD= new DVD(listOfAttributes.get(0),listOfAttributes.get(1),Integer.parseInt(listOfAttributes.get(2)),
						Integer.parseInt(listOfAttributes.get(3)),ct, date, cuboid, null );
				Location location = library.anyFit(DVD);
				DVD.setLocation(location);
				return DVD;
				
			}
			
										
			
		}
		
		
		//If the user did not enter: book, cd or dvd
		else {
			System.out.println("You did not enter a valid item type");
			return null;
		}
		
		
		

	}
}
