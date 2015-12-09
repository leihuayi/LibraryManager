import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public abstract class LibraryItem implements java.io.Serializable {

	private String title;
	private String publisher;
	private int publishingYear;
	private int volumeNumber;
	private ConsultationType consultationType;
	private Date borrowingDeadline;
	private Cuboid measures;
	private Location location;
	private ArrayList<Member> borrowingList;
	
	
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
	public Cuboid getMeasures() {
		return measures;
	}
	public void setMeasures(Cuboid measures) {
		this.measures = measures;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public LibraryItem(String title, String publisher, int publishingYear, int volumeNumber,
			ConsultationType consultationType, Date borrowingDeadline, Cuboid measures, Location location) {
		super();
		this.title = title;
		this.publisher = publisher;
		this.publishingYear = publishingYear;
		this.volumeNumber = volumeNumber;
		this.consultationType = consultationType;
		this.borrowingDeadline = borrowingDeadline;
		this.measures = measures;
		this.location = location;
		this.borrowingList=new ArrayList<Member>();
	}

	
}
