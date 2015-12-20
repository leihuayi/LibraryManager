import java.util.ArrayList;

public class Bookcase extends Cuboid{
	private String bcName;
	private double freeSpace;
	private ArrayList<Shelf> listShelves;
	

	public String getBcName() {
		return bcName;
	}
	public void setBcName(String bcName) {
		this.bcName = bcName;
	}
	
	
	public double getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(double freeSpace) {
		this.freeSpace = freeSpace;
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
		this.freeSpace = initFreeSpace();
	
	}
	
	//to calculate the initial free space in the whole bookcase
	public double initFreeSpace(){
		double sum= 0.0;
		for (Shelf sh : this.listShelves){
			sum = sum + sh.getFreeSpace();
		}
		return sum;		
	}
	
	
}
