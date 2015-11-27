import java.lang.reflect.Array;

public class DVD extends LibraryItem implements Item {
	private int borrowingDuration;

	public int getBorrowingDuration() {
		return borrowingDuration;
	}

	public void setBorrowingDuration(int borrowingDuration) {
		this.borrowingDuration = borrowingDuration;
	}

	public DVD(String title, String publisher, int publishingYear, int volumeNumber, ConsultationType consultationType,
			Date borrowingDeadline, Cuboid measures, Array location) {
		super(title, publisher, publishingYear, volumeNumber, consultationType, borrowingDeadline, measures, location);
		this.borrowingDuration=2;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("DVD created");
	}
	
}
