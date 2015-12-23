package lms;
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
	
	
	
	@Override
	public String toString() {
		return "Bookcase named" + bcName + ", containing the shelves=" + listShelves + "]";
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((bcName == null) ? 0 : bcName.hashCode());
		long temp;
		temp = Double.doubleToLongBits(freeSpace);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((listShelves == null) ? 0 : listShelves.hashCode());
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
		Bookcase other = (Bookcase) obj;
		if (bcName == null) {
			if (other.bcName != null)
				return false;
		} else if (!bcName.equals(other.bcName))
			return false;
		if (Double.doubleToLongBits(freeSpace) != Double.doubleToLongBits(other.freeSpace))
			return false;
		if (listShelves == null) {
			if (other.listShelves != null)
				return false;
		} else if (!listShelves.equals(other.listShelves))
			return false;
		return true;
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
