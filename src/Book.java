import java.lang.reflect.Array;
import java.util.Date;

public class Book extends LibraryItem {
	
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
			double length, double height, double width, Location location, int iSBNCode) {
		super(title, publisher, publishingYear, volumeNumber, consultationType, length, height, width, location);
		ISBNCode = iSBNCode;
		this.borrowingDuration=4;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ISBNCode;
		result = prime * result + borrowingDuration;
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
		Book other = (Book) obj;
		if (ISBNCode != other.ISBNCode)
			return false;
		if (borrowingDuration != other.borrowingDuration)
			return false;
		return true;
	}
	
	

	

	
		
	
	
}
