import java.util.Scanner;

public class LibraryFactory {

	public Library create_library(String name){
		boolean testType = true;
		
		Library library = new Library(name,0,0,0,0);

		while (testType){
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the maximum number of items a member can borrow");
			
			//check that the user enters an int	
			try{
				int nbi = sc.nextInt();
				if(nbi>=0){
					library.setNbi(nbi);
					testType = false;
				}
				else{
					System.out.println("You have to enter a positive integer");
				}
				
			}
			catch (java.util.InputMismatchException ime){
				System.out.println("You have to enter a positive integer");
			}
		}
		
		
		System.out.println("Please enter the N, M and M' numbers so that a member of the library \n"
				+ "- becomes a frequent member only if he has borrowed N items over the last M months \n"
				+ "- loses its frequent membership and becomes a normal member if he has borrowed less than N items over the last M' months");		
		
		testType = true;
		while (testType){
			Scanner sc = new Scanner(System.in);
			System.out.print("\t N: ");
			
			//check that the user enters an int	
			try{
				int n = sc.nextInt();
				if(n>=0){
					library.setN(n);
					testType = false;
				}
				else{
					System.out.println("You have to enter a positive integer");
				}
				
			}
			catch (java.util.InputMismatchException ime){
				System.out.println("You have to enter a positive integer");
			}
		}
		
		testType = true;
		while (testType){
			Scanner sc = new Scanner(System.in);
			System.out.print("\t M: ");
			
			//check that the user enters an int	
			try{
				int m = sc.nextInt();
				if(m>=0){
					library.setM(m);
					testType = false;
				}
				else{
					System.out.println("You have to enter a positive integer");
				}
				
			}
			catch (java.util.InputMismatchException ime){
				System.out.println("You have to enter a positive integer");
			}
		}
		
		testType = true;
		while (testType){
			Scanner sc = new Scanner(System.in);
			System.out.print("\t M': ");
			
			//check that the user enters an int	
			try{
				int mP = sc.nextInt();
				if(mP>=0){
					library.setMp(mP);
					testType = false;
				}
				else{
					System.out.println("You have to enter a positive integer");
				}
				
			}
			catch (java.util.InputMismatchException ime){
				System.out.println("You have to enter a positive integer");
			}
		}

		
		return library;
	}
	
}
