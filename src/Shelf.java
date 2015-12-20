import java.util.ArrayList;

public class Shelf extends Cuboid {
	private static int shelfNumber=0;
	private double freeSpace;
	private ArrayList<LibraryItem> listItems;
	public static int getShelfNumber() {
		return shelfNumber;
	}
	public static void setShelfNumber(int shelfNumber) {
		Shelf.shelfNumber = shelfNumber;
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
		shelfNumber++;
	}
	
	
}
