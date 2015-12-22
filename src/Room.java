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
	
	
	
	
	@Override
	public String toString() {
		return "Room [roomName=" + roomName + ", length=" + getLength() + ", height=" + getHeight() + ", width=" + getWidth() +", freeSpace=" + freeSpace + ", listBookcases=" + listBookcases + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(freeSpace);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((listBookcases == null) ? 0 : listBookcases.hashCode());
		result = prime * result + ((roomName == null) ? 0 : roomName.hashCode());
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
		Room other = (Room) obj;
		if (Double.doubleToLongBits(freeSpace) != Double.doubleToLongBits(other.freeSpace))
			return false;
		if (listBookcases == null) {
			if (other.listBookcases != null)
				return false;
		} else if (!listBookcases.equals(other.listBookcases))
			return false;
		if (roomName == null) {
			if (other.roomName != null)
				return false;
		} else if (!roomName.equals(other.roomName))
			return false;
		return true;
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
