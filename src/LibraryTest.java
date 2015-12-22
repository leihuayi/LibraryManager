import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryTest {

	
	@Test
	public void testAdd_room() {
		Library library = new Library("library",20,20,20,20);
		library.add_room("room",10,10,10);
		Room room = new Room("room",10,10,10);
		assertTrue(library.getListRooms().contains(room));
	}

}
