import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Library{
	private ArrayList<Room> listRooms;
	// Parameters for frequent member
	private int N;
	private int M;
	private ArrayList<LibraryItem> storageRoom;
	//Limitation of borrowing
	private int Nbi;
	private String libraryName;
	private ArrayList<Member> listMembers;
	
	
	public ArrayList<Member> getListMembers() {
		return listMembers;
	}
	public void setListMembers(ArrayList<Member> listMembers) {
		this.listMembers = listMembers;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public ArrayList<Room> getListRooms() {
		return listRooms;
	}
	public void setListRooms(ArrayList<Room> listRooms) {
		this.listRooms = listRooms;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}
	public ArrayList<LibraryItem> getStorageRoom() {
		return storageRoom;
	}
	public void setStorageRoom(ArrayList<LibraryItem> storageRoom) {
		this.storageRoom = storageRoom;
	}
	public int getNbi() {
		return Nbi;
	}
	public void setNbi(int nbi) {
		Nbi = nbi;
	}
	public Library(String name, int n, int m, int nbi) {
		super();
		this.libraryName=name;
		this.listRooms = new ArrayList<Room>();
		N = n;
		M = m;
		this.storageRoom = new ArrayList<LibraryItem>();
		Nbi = nbi;
		this.listMembers= new ArrayList<Member>();
	}
	
	private static Date modifyDate(int numberOfDay)
	  {
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.DATE, numberOfDay);
		  return cal.getTime();
	  }

	
	public static void membersSuspended(Library library){
		for (Member member : library.getListMembers()){
			if (member.getEndingOfSuspension().equals(new Date())){
				member.setUnsuspended(true);
			}
		}
	}
	
	public static void checkCardType(Library library){
		for (Member member : library.getListMembers()){
			if (!member.getCard().getType().equals(CardType.golden)){
				int count =0;
				boolean bool=true;
				for (Borrowing borrowing : member.getHistory()){
					for(Date date: member.getPenalties()) {
						if(date.after(modifyDate(-library.getM()*7))){
							bool=false;
						}
					}
					if (bool && borrowing.getBorrowingDate().after(modifyDate(-library.getM()*7))){
							count=count+1;
						}
					
				}
				if (bool && count>=library.getN()){
					member.getCard().setType(CardType.frequent);
				}
			}
		}
	}
	
	public static void checkBorrowings(Library library){
		for (Member member : library.getListMembers()) {
			for (Borrowing borrowing : member.getCurrentItems()){
				if (borrowing.getBorrowingDate().equals(modifyDate(-7))){
					borrowing.notifyObserver();
					member.getPenalties().add(new Date());
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-21))){
					member.setEndingOfSuspension(modifyDate(member.lowerSuspensionTime()));
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-42))){
					member.setEndingOfSuspension(modifyDate(member.higherSuspensionTime()));
				}
			}
		}
	}
	
	public static void check(Library library){
		membersSuspended(library);
		checkBorrowings(library);
	}
}
