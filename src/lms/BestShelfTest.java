package lms;
import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class BestShelfTest {

	@Test
	public void testStore() {
		Library library = new Library("library",20,20,20,20);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		Bookcase bookcasebis = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		room.getListBookcases().add(bookcasebis);
		//shelf has the most space. It is the second shelf of the first bookcase of the second room.
		Shelf shelf = new Shelf(3,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		Shelf shelfter=new Shelf(2,2,2);
		Shelf shelfquar=new Shelf(1,1,1);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(0,shelfbis);
		bookcasebis.getListShelves().add(shelfter);
		bookcasebis.getListShelves().add(shelfquar);
		Room roombis=new Room("room",5,5,5);
		library.getListRooms().add(0,roombis);;
		Bookcase bookcase2=new Bookcase("bookcase",4,4,4);
		roombis.getListBookcases().add(bookcase2);
		Shelf shelf1 = new Shelf(0.5,1,1);
		Shelf shelf2=new Shelf(1,1,1);
		bookcase2.getListShelves().add(shelf1);
		bookcase2.getListShelves().add(1,shelf2);
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		BestShelf strategy = new BestShelf();
		strategy.store(CD,library);
		assertTrue(shelf.getListItems().contains(CD));
	}

}
