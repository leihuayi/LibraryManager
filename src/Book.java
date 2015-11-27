import java.lang.reflect.Array;

public class Book extends LibraryItem implements Item {
	
	private int ISBNCode;
	private int borrowingDuration;

	public int getISBNCode() {
		return ISBNCode;
	}

	public void setISBNCode(int iSBNCode) {
		ISBNCode = iSBNCode;
	}

	public int getBorrowingDuration() {
		return borrowingDuration;
	}

	public void setBorrowingDuration(int borrowingDuration) {
		this.borrowingDuration = borrowingDuration;
	}

	public Book(String title, String publisher, int publishingYear, int volumeNumber, ConsultationType consultationType,
			Date borrowingDeadline, Cuboid measures, Array location, int iSBNCode) {
		super(title, publisher, publishingYear, volumeNumber, consultationType, borrowingDeadline, measures, location);
		ISBNCode = iSBNCode;
		this.borrowingDuration=4;
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("Book created");
		
	}

		
	
	
}
