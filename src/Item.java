import java.lang.reflect.Array;

public abstract class Item {

	private String title;
	private String publisher;
	private int publishingYear;
	private int volumeNumber;
	private ConsultationType consultationType;
	private Date borrowingDeadline;
	private Cuboid measures;
	private Array location;
	
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
	public Array getLocation() {
		return location;
	}
	public void setLocation(Array location) {
		this.location = location;
	}
	public Item(String title, String publisher, int publishingYear, int volumeNumber, ConsultationType consultationType,
			Date borrowingDeadline, Cuboid measures, Array location) {
		super();
		this.title = title;
		this.publisher = publisher;
		this.publishingYear = publishingYear;
		this.volumeNumber = volumeNumber;
		this.consultationType = consultationType;
		this.borrowingDeadline = borrowingDeadline;
		this.measures = measures;
		this.location = location;
	}
	
	
	
}
