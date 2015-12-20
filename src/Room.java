import java.util.ArrayList;

public class Room extends Cuboid{
	private String roomName;
	private double freeSpace;
	private ArrayList<Bookcase> listBookcases;
	

	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	
	
	public double getFreeSpace() {
		return freeSpace;
	}
	public void setFreeSpace(double freeSpace) {
		this.freeSpace = freeSpace;
	}
	
	
	public ArrayList<Bookcase> getListBookcases() {
		return listBookcases;
	}
	public void setListBookcases(ArrayList<Bookcase> listBookcases) {
		this.listBookcases = listBookcases;
	}
	

	public Room(String roomName, double length, double height, double width) {
		super();
		this.roomName = roomName;
		this.setLength(length);
		this.setHeight(height);
		this.setWidth(width);
		this.listBookcases = new ArrayList<Bookcase>();
		this.freeSpace = initFreeSpace();
		
	}
	
	//to calculate the initial free space in the whole room
	public double initFreeSpace(){
		double sum= 0.0;
		for (Bookcase bk  : this.listBookcases){
			sum = sum + bk.getFreeSpace();
		}
		return sum;		
	}
	
}
