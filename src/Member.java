import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Member implements Observer , java.io.Serializable{
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
	private int fineSuspensionStandard=50;
	private int fineSuspensionFrequent=30;
	private int fineSuspensionGolden=20;
	//Amount of money due to the library
	private int amount=0;
	//list of dates when the member took penalties
	private ArrayList<Date> penalties;
	
	
	
	public ArrayList<Date> getPenalties() {
		return penalties;
	}
	public void setPenalties(ArrayList<Date> penalties) {
		this.penalties = penalties;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getFineSuspensionStandard() {
		return fineSuspensionStandard;
	}
	public void setFineSuspensionStandard(int fineSuspensionStandard) {
		this.fineSuspensionStandard = fineSuspensionStandard;
	}
	public int getFineSuspensionFrequent() {
		return fineSuspensionFrequent;
	}
	public void setFineSuspensionFrequent(int fineSuspensionFrequent) {
		this.fineSuspensionFrequent = fineSuspensionFrequent;
	}
	public int getFineSuspensionGolden() {
		return fineSuspensionGolden;
	}
	public void setFineSuspensionGolden(int fineSuspensionGolden) {
		this.fineSuspensionGolden = fineSuspensionGolden;
	}
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
		this.penalties=new ArrayList<Date>();
		
	}
	
		
	@Override
	public String toString() {
		return "Member [name=" + name + ", surname=" + surname + ", birthDate=" + (new SimpleDateFormat ("dd/MM/yyyy")).format(birthDate) + ", ccNumber=" + ccNumber
				+ ", currentItems=" + currentItems + ", history=" + history + ", card=" + card + ", library=" + library
				+ ", unsuspended=" + unsuspended + "]";
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
	
	//method which suspends the member and returns the lower suspension time
	public int lowerSuspensionTime(){
		this.setUnsuspended(false);
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
	
	//method which suspends the member, creates the fine and returns the higher suspension time
	public int higherSuspensionTime(){
		this.setUnsuspended(false);
		if (this.getCard().getType().equals(CardType.standard)){
			this.setAmount(this.getFineSuspensionStandard());
			return this.getHigherSuspensionTimeStandard();
		}
		else if (this.getCard().getType().equals(CardType.frequent)){
			this.getCard().setType(CardType.standard);
			this.setAmount(this.getFineSuspensionFrequent());
			return this.getHigherSuspensionTimeFrequent();
		}
		else{
			this.setAmount(this.getFineSuspensionGolden());
			return 	this.getHigherSuspensionTimeGolden();
			}
		
	}
}
