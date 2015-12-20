import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public abstract class LibraryItem extends Cuboid implements java.io.Serializable{

	private String title;
	private String publisher;
	private int publishingYear;
	private int volumeNumber;
	private ConsultationType consultationType;
	private Date borrowingDeadline;
	//location set as null when the item is borrowed and calculated again when the item is returned
	private Location location;
	private ArrayList<Member> borrowingList;
	private boolean borrowable;

	
	

	public boolean isBorrowable() {
		return borrowable;
	}
	public void setBorrowable(boolean borrowable) {
		this.borrowable = borrowable;
	}
	public ArrayList<Member> getBorrowingList() {
		return borrowingList;
	}
	public void setBorrowingList(ArrayList<Member> borrowingList) {
		this.borrowingList = borrowingList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public int getPublishingYear() {
		return publishingYear;
	}
	public void setPublishingYear(int publishingYear) {
		this.publishingYear = publishingYear;
	}
	public int getVolumeNumber() {
		return volumeNumber;
	}
	public void setVolumeNumber(int volumeNumber) {
		this.volumeNumber = volumeNumber;
	}
	public ConsultationType getConsultationType() {
		return consultationType;
	}
	public void setConsultationType(ConsultationType consultationType) {
		this.consultationType = consultationType;
	}
	public Date getBorrowingDeadline() {
		return borrowingDeadline;
	}
	public void setBorrowingDeadline(Date borrowingDeadline) {
		this.borrowingDeadline = borrowingDeadline;
	}
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public LibraryItem(String title, String publisher, int publishingYear, int volumeNumber,
			ConsultationType consultationType, Date borrowingDeadline, double length, double height, double width, Location location) {

		super();
		this.title = title;
		this.publisher = publisher;
		this.publishingYear = publishingYear;
		this.volumeNumber = volumeNumber;
		this.consultationType = consultationType;
		this.borrowingDeadline = borrowingDeadline;
		this.setLength(length);
		this.setHeight(height);
		this.setWidth(width);
		this.location = location;
		this.borrowingList=new ArrayList<Member>();
		this.borrowable=true;
	}
	
	public void borrow_item(Member member, LibraryItem item){
		//will be made in part 2
		//if the item is under reservation u can't borrow it
	}
	
	public void returnItem(Library library, LibraryItem item, Member member){
		item.setBorrowable(true);
		ArrayList<Borrowing> list = member.getCurrentItems();
		ArrayList<Borrowing> list2= new ArrayList<>();
		for (Borrowing borrowing : list){
			//one member can borrow one copy of each item
			if (borrowing.getItem().equals(item)){
				list.remove(borrowing);
				for (Borrowing borrow : library.getReservationList()){
					if (borrow.getItem().equals(borrowing.getItem())){
						list2.add(borrow);
					}
				}
			}
		}
		if(!list2.isEmpty()){
			//getting the first member who wanted to borrow
			Borrowing borrowing = list2.get(0);
			for (Borrowing borrow : list2){
				if(borrow.getBorrowingDate().before(borrowing.getBorrowingDate())){
					borrowing=borrow;
				}
			}
			borrowing.notifyObserverItemBorrowable();
			//research of the location with one of the four algorithms
		}
		
	}
	
}
