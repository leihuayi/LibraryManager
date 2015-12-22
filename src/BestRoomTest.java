import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class BestRoomTest {

	@Test
	public void testStore() {
		Library library = new Library("library",20,20,20,20);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		Bookcase bookcasebis = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		Shelf shelfter=new Shelf(2,2,2);
		Shelf shelfquar=new Shelf(1,1,1);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		bookcasebis.getListShelves().add(shelfter);
		bookcasebis.getListShelves().add(shelfquar);
		Date date=new Date();
		Room roombis=new Room("room",5,5,5);
		library.getListRooms().add(roombis);;
		Bookcase bookcase2=new Bookcase("bookcase",4,4,4);
		roombis.getListBookcases().add(bookcase2);
		Shelf shelf2=new Shelf(2,2,2);
		bookcase2.getListShelves().add(shelf2);
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		BestRoom strategy = new BestRoom();
		strategy.store(CD,library);
		assertTrue(shelf.getListItems().contains(CD)||shelfter.getListItems().contains(CD)||shelfquar.getListItems().contains(CD)||shelfbis.getListItems().contains(CD));
	}

}
