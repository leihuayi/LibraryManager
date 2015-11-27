import java.util.ArrayList;

public class Bookcase {
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
	public Bookcase(String bcName, ArrayList<Shelf> listShelves) {
		super();
		this.bcName = bcName;
		this.listShelves = listShelves;
	}
	
}
