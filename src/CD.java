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
			 double length, double height, double width, Location location) {
		super(title, publisher, publishingYear, volumeNumber, consultationType, length, height, width, location);
	
	this.borrowingDuration=1;
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
		CD other = (CD) obj;
		if (borrowingDuration != other.borrowingDuration)
			return false;
		return true;
	}
	
	

	
}
