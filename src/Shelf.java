import java.util.ArrayList;

public class Shelf extends Cuboid {
	private static int totalShelfNumber=0;
	private int shelfNumber;
	private double freeSpace;
	private ArrayList<LibraryItem> listItems;
	
	
	public int getShelfNumber() {
		return shelfNumber;
	}
	public void setShelfNumber(int shelfNumber) {
		this.shelfNumber = shelfNumber;
	}
	public double getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(double freeSpace) {
		this.freeSpace = freeSpace;
	}
	public ArrayList<LibraryItem> getListItems() {
		return listItems;
	}
	public void setListItems(ArrayList<LibraryItem> listItems) {
		this.listItems = listItems;
	}
	
	public Shelf(double length, double height, double width) {
		//when we create a new shelf, it is empty
		super();
		this.setLength(length);
		this.setHeight(height);
		this.setWidth(width);
		this.listItems = new ArrayList<LibraryItem>();
		this.freeSpace = length;
		this.shelfNumber = totalShelfNumber;
		totalShelfNumber++;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(freeSpace);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((listItems == null) ? 0 : listItems.hashCode());
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
		Shelf other = (Shelf) obj;
		if (Double.doubleToLongBits(freeSpace) != Double.doubleToLongBits(other.freeSpace))
			return false;
		if (listItems == null) {
			if (other.listItems != null)
				return false;
		} else if (!listItems.equals(other.listItems))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Shelf number" + shelfNumber + ", containting the items: \n" + listItems + "]";
	}
	
	
	
}
