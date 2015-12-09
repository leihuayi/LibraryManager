import java.util.ArrayList;

public class Bookcase{
	private String bcName;
	private ArrayList<Shelf> listShelves;
	private Cuboid measures;
	
	
	public Cuboid getMeasures() {
		return measures;
	}
	public void setMeasures(Cuboid measures) {
		this.measures = measures;
	}
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
	public Bookcase(String bcName, Cuboid measures) {
		super();
		this.bcName = bcName;
		this.listShelves = new ArrayList<Shelf>();
		this.measures=measures;
	}
	
}
