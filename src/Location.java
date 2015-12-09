import java.io.Serializable;

public class Location implements Serializable {
	private Library library;
	private Room room;
	private Bookcase bookcase;
	private Shelf shelf;
	
	
	public Library getLibrary() {
		return library;
	}


	public void setLibrary(Library library) {
		this.library = library;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public Bookcase getBookcase() {
		return bookcase;
	}


	public void setBookcase(Bookcase bookcase) {
		this.bookcase = bookcase;
	}


	public Shelf getShelf() {
		return shelf;
	}


	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}


	public Location(Library library, Room room, Bookcase bookcase, Shelf shelf) {
		super();
		this.library = library;
		this.room = room;
		this.bookcase = bookcase;
		this.shelf = shelf;
	}


	
	
	
}
