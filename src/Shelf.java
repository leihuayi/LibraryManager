import java.util.ArrayList;

public class Shelf {
	private static int shelfNumber=0;
	private Cuboid measures;
	private ArrayList<LibraryItem> listItems;
	public static int getShelfNumber() {
		return shelfNumber;
	}
	public static void setShelfNumber(int shelfNumber) {
		Shelf.shelfNumber = shelfNumber;
	}
	public Cuboid getMeasures() {
		return measures;
	}
	public void setMeasures(Cuboid measures) {
		this.measures = measures;
	}
	public ArrayList<LibraryItem> getListItems() {
		return listItems;
	}
	public void setListItems(ArrayList<LibraryItem> listItems) {
		this.listItems = listItems;
	}
	public Shelf(Cuboid measures) {
		super();
		this.measures = measures;
		this.listItems = new ArrayList<LibraryItem>();
	}
	
	
}
