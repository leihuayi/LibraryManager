import java.util.ArrayList;

public class Room{
	private String roomName;
	private ArrayList<Bookcase> listBookcases;
	private Cuboid measures;
	
	
	public Cuboid getMeasures() {
		return measures;
	}
	public void setMeasures(Cuboid measures) {
		this.measures = measures;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public ArrayList<Bookcase> getListBookcases() {
		return listBookcases;
	}
	public void setListBookcases(ArrayList<Bookcase> listBookcases) {
		this.listBookcases = listBookcases;
	}
	public Room(String roomName, Cuboid measures) {
		super();
		this.roomName = roomName;
		this.listBookcases = new ArrayList<Bookcase>();
		this.measures=measures;
	}
	
	
}
