import java.util.Scanner;

public class LibraryFactory {

	public Library create_library(String name){
		boolean testType = true;
		
		Library library = new Library(name,0,0,0,0);

		return library;
	}
	
}
