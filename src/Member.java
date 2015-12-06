import java.util.ArrayList;
import java.util.Date;

public class Member implements Observer{
	private static int ID;
	private String name;
	private String surname;
	private Date birthDate;
	//Credit card number
	private int ccNumber;
	private ArrayList<Borrowing> currentItems;
	private ArrayList<Borrowing> history;
	private Card card;
	private Library library;
	private boolean unsuspended;
	private Date endingOfSuspension;
	private int lowerSuspensionTimeStandard =6;
	private int lowerSuspensionTimeFrequent =3;
	private int lowerSuspensionTimeGolden =2;
	private int higherSuspensionTimeStandard =10;
	private int higherSuspensionTimeFrequent =5;
	private int higherSuspensionTimeGolden =3;
	
	
	public int getLowerSuspensionTimeStandard() {
		return lowerSuspensionTimeStandard;
	}
	public void setLowerSuspensionTimeStandard(int lowerSuspensionTimeStandard) {
		this.lowerSuspensionTimeStandard = lowerSuspensionTimeStandard;
	}
	public int getLowerSuspensionTimeFrequent() {
		return lowerSuspensionTimeFrequent;
	}
	public void setLowerSuspensionTimeFrequent(int lowerSuspensionTimeFrequent) {
		this.lowerSuspensionTimeFrequent = lowerSuspensionTimeFrequent;
	}
	public int getLowerSuspensionTimeGolden() {
		return lowerSuspensionTimeGolden;
	}
	public void setLowerSuspensionTimeGolden(int lowerSuspensionTimeGolden) {
		this.lowerSuspensionTimeGolden = lowerSuspensionTimeGolden;
	}
	public int getHigherSuspensionTimeStandard() {
		return higherSuspensionTimeStandard;
	}
	public void setHigherSuspensionTimeStandard(int higherSuspensionTimeStandard) {
		this.higherSuspensionTimeStandard = higherSuspensionTimeStandard;
	}
	public int getHigherSuspensionTimeFrequent() {
		return higherSuspensionTimeFrequent;
	}
	public void setHigherSuspensionTimeFrequent(int higherSuspensionTimeFrequent) {
		this.higherSuspensionTimeFrequent = higherSuspensionTimeFrequent;
	}
	public int getHigherSuspensionTimeGolden() {
		return higherSuspensionTimeGolden;
	}
	public void setHigherSuspensionTimeGolden(int higherSuspensionTimeGolden) {
		this.higherSuspensionTimeGolden = higherSuspensionTimeGolden;
	}
	public Date getEndingOfSuspension() {
		return endingOfSuspension;
	}
	public void setEndingOfSuspension(Date endingOfSuspension) {
		this.endingOfSuspension = endingOfSuspension;
	}
	public boolean isUnsuspended() {
		return unsuspended;
	}
	public void setUnsuspended(boolean unsuspended) {
		this.unsuspended = unsuspended;
	}
	public Library getLibrary() {
		return library;
	}
	public void setLibrary(Library library) {
		this.library = library;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
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
	public Member(String name, String surname, Date birthDate, int ccNumber, Library library) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.ccNumber = ccNumber;
		this.history = new ArrayList<Borrowing>();
		this.currentItems= new ArrayList<Borrowing>();
		this.card= new Card();
		this.library= library;
		this.unsuspended=true;
		
	}
	
	public boolean checkBorrowings(){
		Integer size = (this.getCurrentItems()).size();
		if (size<this.library.getNbi()){
			return true;
		}
		else{
			return false;
		}
	}
	@Override
	public void update (String title) {
		// TODO Auto-generated method stub
		System.out.println("Vous n'avez toujours pas rendu le livre "+title);
	}
	
	public int lowerSuspensionTime(){
		if (this.getCard().getType().equals(CardType.standard)){
			return this.getLowerSuspensionTimeStandard();
		}
		else if (this.getCard().getType().equals(CardType.frequent)){
			this.getCard().setType(CardType.standard);
			return this.getLowerSuspensionTimeFrequent();
		}
		else{
			return 	this.getLowerSuspensionTimeGolden();
			}
		
	}
	public int higherSuspensionTime(){
		if (this.getCard().getType().equals(CardType.standard)){
			return this.getHigherSuspensionTimeStandard();
		}
		else if (this.getCard().getType().equals(CardType.frequent)){
			this.getCard().setType(CardType.standard);
			return this.getHigherSuspensionTimeFrequent();
		}
		else{
			return 	this.getHigherSuspensionTimeGolden();
			}
		
	}
}
