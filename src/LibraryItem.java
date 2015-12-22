import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public abstract class LibraryItem extends Cuboid implements java.io.Serializable{

	private String title;
	private String publisher;
	private int publishingYear;
	private int volumeNumber;
	private ConsultationType consultationType;
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
	
	
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public LibraryItem(String title, String publisher, int publishingYear, int volumeNumber,
			ConsultationType consultationType,double length, double height, double width, Location location) {

		super();
		this.title = title;
		this.publisher = publisher;
		this.publishingYear = publishingYear;
		this.volumeNumber = volumeNumber;
		this.consultationType = consultationType;
		this.setLength(length);
		this.setHeight(height);
		this.setWidth(width);
		this.location = location;
		this.borrowingList=new ArrayList<Member>();
		this.borrowable=true;
	}
	
	
	
	
	@Override
	public String toString() {
		return "title=" + title + ", publisher=" + publisher + ", publishingYear=" + publishingYear
				+ ", volumeNumber=" + volumeNumber + ", consultationType=" + consultationType + ", location=" + location
				+ ", borrowingList=" + borrowingList + ", borrowable=" + borrowable;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + (borrowable ? 1231 : 1237);
		result = prime * result + ((borrowingList == null) ? 0 : borrowingList.hashCode());
		result = prime * result + ((consultationType == null) ? 0 : consultationType.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((publisher == null) ? 0 : publisher.hashCode());
		result = prime * result + publishingYear;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + volumeNumber;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryItem other = (LibraryItem) obj;
		if (borrowable != other.borrowable)
			return false;
		if (borrowingList == null) {
			if (other.borrowingList != null)
				return false;
		} else if (!borrowingList.equals(other.borrowingList))
			return false;
		if (consultationType != other.consultationType)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (publisher == null) {
			if (other.publisher != null)
				return false;
		} else if (!publisher.equals(other.publisher))
			return false;
		if (publishingYear != other.publishingYear)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (volumeNumber != other.volumeNumber)
			return false;
		return true;
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
