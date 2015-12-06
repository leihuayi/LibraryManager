import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Launcher {
	
	private static Date modifyDate(int numberOfDay)
	  {
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.DATE, numberOfDay);
		  return cal.getTime();
	  }

	public static void updateCards(){
		 Date date = modifyDate(2);
		 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 String dat = dateFormat.format(date);
		 System.out.println(dat);
		
	}
	
	public static void checkBorrowings(Library library){
		ArrayList<Borrowing> borrowingsList = new ArrayList<>();
		
		for (Member member : library.getListMembers()) {
			for (Borrowing borrowing : member.getCurrentItems()){
				if (borrowing.getBorrowingDate().equals(modifyDate(-7))){
					borrowing.notifyObserver();
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-21))){
					member.setUnsuspended(false);
					member.setEndingOfSuspension(modifyDate(member.lowerSuspensionTime()));
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-42))){
					member.setUnsuspended(false);
					member.setEndingOfSuspension(modifyDate(member.higherSuspensionTime()));
				}
			}
		}
	}
	public static void main(String[] args) throws ParseException{
		// TODO Auto-generated method stub	
		Library library = new Library("Hey",0,0,0);
		Member member= new Member("Cocher","Thomas",new Date(),123,library);
		library.getListMembers().add(member);
		System.out.println(member.getHigherSuspensionTimeFrequent());
		
	}

}
