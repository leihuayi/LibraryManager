import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class AnyFitTest {

	@Test
	public void testStore() {
		Library library = new Library("library",10,10,10,10);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(2,2,2);
		bookcase.getListShelves().add(shelf);
		Date date=new Date();
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,date,1,1,1,null);
		AnyFit strategy = new AnyFit();
		strategy.store(CD,library);
		assertTrue(shelf.getListItems().contains(CD));
	}

}
