import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Launcher {

	public void updateCards(Date date){
		Scanner sc = new Scanner(System.in);
		System.out.println("Which day are we ? (DD/MM/YY expected)");
		String s=sc.nextLine();
		
	}
	
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String string = "January 2, 2010";
		DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
		Date date = format.parse(string);
		System.out.println(date.getMonth());
		
	}

}
