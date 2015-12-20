import java.util.ArrayList;

public class Bookcase extends Cuboid{
	private String bcName;
	private ArrayList<Shelf> listShelves;
	

	public String getBcName() {
		return bcName;
	}
	public void setBcName(String bcName) {
		this.bcName = bcName;
	}
	public ArrayList<Shelf> getListShelves() {
		return listShelves;
	}
	public void setListShelves(ArrayList<Shelf> listShelves) {
		this.listShelves = listShelves;
	}
	public Bookcase(String bcName, double length, double height, double width) {
		super();
		this.bcName = bcName;
		this.setLength(length);
		this.setHeight(height);
		this.setWidth(width);
		this.listShelves = new ArrayList<Shelf>();

	}
	
}
