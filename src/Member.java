import java.util.ArrayList;

public class Member{
	private static int ID;
	private String name;
	private String surname;
	private Date birthDate;
	//Credit card number
	private int ccNumber;
	private ArrayList<Borrowing> currentItems;
	private ArrayList<Borrowing> history;
	public static int getID() {
		return ID;
	}
	public static void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public int getCcNumber() {
		return ccNumber;
	}
	public void setCcNumber(int ccNumber) {
		this.ccNumber = ccNumber;
	}
	public ArrayList<Borrowing> getCurrentItems() {
		return currentItems;
	}
	public void setCurrentItems(ArrayList<Borrowing> currentItems) {
		this.currentItems = currentItems;
	}
	public ArrayList<Borrowing> getHistory() {
		return history;
	}
	public void setHistory(ArrayList<Borrowing> history) {
		this.history = history;
	}
	public Member(String name, String surname, Date birthDate, int ccNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.ccNumber = ccNumber;
		this.history = new ArrayList<Borrowing>();
		this.currentItems= new ArrayList<Borrowing>();
	}
	
	
}
