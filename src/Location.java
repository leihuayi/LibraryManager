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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookcase == null) ? 0 : bookcase.hashCode());
		result = prime * result + ((library == null) ? 0 : library.hashCode());
		result = prime * result + ((room == null) ? 0 : room.hashCode());
		result = prime * result + ((shelf == null) ? 0 : shelf.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		if (bookcase == null) {
			if (other.bookcase != null)
				return false;
		} else if (!bookcase.equals(other.bookcase))
			return false;
		if (library == null) {
			if (other.library != null)
				return false;
		} else if (!library.equals(other.library))
			return false;
		if (room == null) {
			if (other.room != null)
				return false;
		} else if (!room.equals(other.room))
			return false;
		if (shelf == null) {
			if (other.shelf != null)
				return false;
		} else if (!shelf.equals(other.shelf))
			return false;
		return true;
	}

	
	
	
	
}
