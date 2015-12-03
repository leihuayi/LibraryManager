import java.util.ArrayList;

public class Room{
	private String roomName;
	private ArrayList<Bookcase> listBookcases;
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
	public Room(String roomName) {
		super();
		this.roomName = roomName;
		this.listBookcases = new ArrayList<Bookcase>();
	}
	
	
}
