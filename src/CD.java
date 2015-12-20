import java.lang.reflect.Array;
import java.util.Date;

public class CD extends LibraryItem{
	private int borrowingDuration;

	public int getBorrowingDuration() {
		return borrowingDuration;
	}

	public void setBorrowingDuration(int borrowingDuration) {
		this.borrowingDuration = borrowingDuration;
	}


	public CD(String title, String publisher, int publishingYear, int volumeNumber, ConsultationType consultationType,
			Date borrowingDeadline, double length, double height, double width, Location location) {
		super(title, publisher, publishingYear, volumeNumber, consultationType, borrowingDeadline, length, height, width, location);
	
	this.borrowingDuration=1;


	}

	
}
