package lms;
import java.lang.reflect.Array;
import java.util.Date;

public class DVD extends LibraryItem{
	private int borrowingDuration;

	public int getBorrowingDuration() {
		return borrowingDuration;
	}

	public void setBorrowingDuration(int borrowingDuration) {
		this.borrowingDuration = borrowingDuration;
	}

	public DVD(String title, String publisher, int publishingYear, int volumeNumber, ConsultationType consultationType,
			 double length, double height, double width, Location location) {
		super(title, publisher, publishingYear, volumeNumber, consultationType, length, height, width, location);
		this.borrowingDuration=2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		DVD other = (DVD) obj;
		if (borrowingDuration != other.borrowingDuration)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DVD ["+ super.toString() + ", borrowingDuration=" + borrowingDuration + "]";
	}
	

	
	
	
	
}
