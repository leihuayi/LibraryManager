import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Library implements java.io.Serializable{
	private ArrayList<Room> listRooms;
	// Parameters for frequent member
	private int N;
	private int M;
	private int Mp;
	private ArrayList<LibraryItem> storageRoom;
	//Limitation of borrowing
	private int Nbi;
	private String libraryName;
	private ArrayList<Member> listMembers;
	private ArrayList <Borrowing> reservationList;
	
	
	public ArrayList<Borrowing> getReservationList() {
		return reservationList;
	}
	public void setReservationList(ArrayList<Borrowing> reservationList) {
		this.reservationList = reservationList;
	}
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
	
	public int getMp() {
		return Mp;
	}
	public void setMp(int mp) {
		Mp = mp;
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
	public Library(String name, int nbi, int n, int m, int mp) {
		super();
		this.libraryName=name;
		this.listRooms = new ArrayList<Room>();
		N = n;
		M = m;
		this.storageRoom = new ArrayList<LibraryItem>();
		Nbi = nbi;
		this.listMembers= new ArrayList<Member>();
		this.reservationList= new ArrayList<Borrowing>();
	}
	
	
	
	@Override
	public String toString() {
		return "Library [N=" + N + ", M=" + M + ", Nbi="
				+ Nbi + ", libraryName=" + libraryName + ", listMembers=" + listMembers + "]";
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Library other = (Library) obj;
		if (M != other.M)
			return false;
		if (Mp != other.Mp)
			return false;
		if (N != other.N)
			return false;
		if (Nbi != other.Nbi)
			return false;
		if (libraryName == null) {
			if (other.libraryName != null)
				return false;
		} else if (!libraryName.equals(other.libraryName))
			return false;
		return true;
	}
	
	private static Date modifyDate(int numberOfDay)
	  {
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.DATE, numberOfDay);
		  return cal.getTime();
	  }

	
	//this function check everything needed when the program launches
	public static void check(Library library){
		for (Member member : library.getListMembers()) {
			//check members suspended
			if (member.getEndingOfSuspension().equals(new Date())){
				member.setUnsuspended(true);
			}
			//check borrowings
			for (Borrowing borrowing : member.getCurrentItems()){
				if (borrowing.getBorrowingDate().equals(modifyDate(-7))){
					borrowing.notifyObserverDelay();
					member.getPenalties().add(new Date());
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-21))){
					member.setEndingOfSuspension(modifyDate(member.lowerSuspensionTime()));
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-42))){
					member.setEndingOfSuspension(modifyDate(member.higherSuspensionTime()));
				}
			}
			//check of cards
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
				else{
					member.getCard().setType(CardType.standard);
				}
			}
		}
	}
	
	
	
}
