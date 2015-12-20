import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class LibraryTest {

	@Test
	public void testAnyFit() {
		Library library = new Library("library",10,10,10);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(2,2,2);
		bookcase.getListShelves().add(shelf);
		Date date=new Date();
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,date,1,1,1,null);
		library.anyFit(CD);
		assertTrue(shelf.getListItems().contains(CD));
	}
	
	
	@Test
	public void testBestShelf() {
		Library library = new Library("library",10,10,10);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 8,8,8);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(2,2,2);
		Shelf shelfbis=new Shelf(3,2,2);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		Date date=new Date();
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,date,1,1,1,null);
		library.bestShelf(CD);
		assertTrue(shelfbis.getListItems().contains(CD));
	}

		/*
	@Test
	public void testBestBookcase() {
		Library library = new Library("library",10,10,10);
		Cuboid cuboid = new Cuboid(10,10,10);
		Room room = new Room("room",cuboid);
		library.getListRooms().add(room);
		Cuboid cuboid2 = new Cuboid (5,5,5);
		Cuboid cuboid2bis = new Cuboid (5,5,5);
		Bookcase bookcase = new Bookcase("bookcase", cuboid2);
		Bookcase bookcasebis = new Bookcase("bookcase", cuboid2bis);
		room.getListBookcases().add(bookcase);
		Cuboid cuboid3=new Cuboid(3,3,3);
		Cuboid cuboid3bis=new Cuboid(2,2,2);
		Cuboid cuboid3ter = new Cuboid(2,2,2);
		Cuboid cuboid3quar = new Cuboid(1,1,1);
		Shelf shelf = new Shelf(cuboid3);
		Shelf shelfbis=new Shelf(cuboid3bis);
		Shelf shelfter=new Shelf(cuboid3ter);
		Shelf shelfquar=new Shelf(cuboid3quar);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		bookcasebis.getListShelves().add(shelfter);
		bookcasebis.getListShelves().add(shelfquar);
		Date date=new Date();
		Cuboid cuboid4=new Cuboid(1,1,1);
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,date,cuboid4,null);
		Location location = library.bestBookcase(CD);
		assertTrue(shelf.getListItems().contains(CD)||shelfbis.getListItems().contains(CD));
	}

	@Test
	public void testBestRoom() {
		Library library = new Library("library",20,20,20);
		Cuboid cuboid = new Cuboid(10,10,10);
		Room room = new Room("room",cuboid);
		library.getListRooms().add(room);
		Cuboid cuboid2 = new Cuboid (5,5,5);
		Cuboid cuboid2bis = new Cuboid (5,5,5);
		Bookcase bookcase = new Bookcase("bookcase", cuboid2);
		Bookcase bookcasebis = new Bookcase("bookcase", cuboid2bis);
		room.getListBookcases().add(bookcase);
		Cuboid cuboid3=new Cuboid(3,3,3);
		Cuboid cuboid3bis=new Cuboid(2,2,2);
		Cuboid cuboid3ter = new Cuboid(2,2,2);
		Cuboid cuboid3quar = new Cuboid(1,1,1);
		Shelf shelf = new Shelf(cuboid3);
		Shelf shelfbis=new Shelf(cuboid3bis);
		Shelf shelfter=new Shelf(cuboid3ter);
		Shelf shelfquar=new Shelf(cuboid3quar);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		bookcasebis.getListShelves().add(shelfter);
		bookcasebis.getListShelves().add(shelfquar);
		Date date=new Date();
		Cuboid cuboid4=new Cuboid(1,1,1);
		Cuboid measures=new Cuboid(5,5,5);
		Room roombis=new Room("room",measures);
		library.getListRooms().add(roombis);
		Cuboid measures2=new Cuboid(4,4,4);
		Bookcase bookcase2=new Bookcase("bookcase",measures2);
		roombis.getListBookcases().add(bookcase2);
		Cuboid measures3=new Cuboid(2,2,2);
		Shelf shelf2=new Shelf(measures3);
		bookcase2.getListShelves().add(shelf2);
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,date,cuboid4,null);
		Location location = library.bestRoom(CD);
		assertTrue(shelf.getListItems().contains(CD)||shelfter.getListItems().contains(CD)||shelfquar.getListItems().contains(CD)||shelfbis.getListItems().contains(CD));
	}
	*/
}
