package lms;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class BestBookcaseTest {

	@Test
	public void testStore() {
		Library library = new Library("library",10,10,10,10);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		Bookcase bookcasebis = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		room.getListBookcases().add(bookcasebis);
		Shelf shelf = new Shelf(3,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		Shelf shelfter=new Shelf(2,2,2);
		Shelf shelfquar=new Shelf(1,1,1);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		bookcasebis.getListShelves().add(shelfter);
		bookcasebis.getListShelves().add(shelfquar);
		Date date=new Date();
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		BestBookcase strategy = new BestBookcase();
		strategy.store(CD,library);
		assertTrue(shelf.getListItems().contains(CD)||shelfbis.getListItems().contains(CD));
	}

}
