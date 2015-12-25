package lms;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + M;
		result = prime * result + Mp;
		result = prime * result + N;
		result = prime * result + Nbi;
		result = prime * result + ((libraryName == null) ? 0 : libraryName.hashCode());
		return result;
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

	
}
