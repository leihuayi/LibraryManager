package lms;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.Test;

public class LibraryFactoryTest {
	
	LibraryFactory Test = new LibraryFactory();
	
	@Test
	public void testCreate_library() {
		Library lib = Test.create_library("coucou",2,1,5,6);
		Library libTest = new Library("coucou",2,1,5,6);
		assertTrue(lib.equals(libTest));
	}

		
	@Test
	public void testAdd_room() {
		
		Library library = new Library("library",20,20,20,20);
		Test.add_room(library,"room",10,10,10);
		boolean bool = false;
		for (Room room : library.getListRooms()){
			if (room.getRoomName().equals("room")&&room.getHeight()==10&&room.getLength()==10&&room.getWidth()==10){
				bool=true;
				break;
			}
		}
		assertTrue(bool);
	}
	
	@Test
	public void testAdd_bookcase() {
		Library library = new Library("library",20,20,20,20);
		Test.add_room(library,"room",10,10,10);
		Room room=library.getListRooms().get(0);
		Test.add_bookcase(library, room,3, "bookcase", 5, 5,5);
		boolean bool = false;
		for (Bookcase bookcase:room.getListBookcases()){
			if (bookcase.getBcName().equals("bookcase")&&bookcase.getHeight()==5&&bookcase.getLength()==5&&bookcase.getWidth()==5){
				bool=true;
				break;
			}
		}
		assertTrue(bool);
	}
	
	
	@Test
	public void testAdd_item() {
		Library library = new Library("library",20,20,20,20);
		Test.add_room(library,"room",10,10,10);
		Test.add_item("Alexandre Prot","Book of JAVA","Book",1,"borrowIng","ECP",library,2015,5,5,5);
		assertTrue(!library.getStorageRoom().isEmpty());
	}
 	
	
	/*
	@Test
	public void testStore_items() {
		Library library = new Library("library",20,20,20,20);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		Bookcase bookcasebis = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		room.getListBookcases().add(bookcasebis);
		//shelf, shelfbis and shelfter have more space than shelf2
		Shelf shelf = new Shelf(2.5,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		Shelf shelfter=new Shelf(2,2,2);
		Shelf shelfquar=new Shelf(1,1,1);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(0,shelfbis);
		bookcasebis.getListShelves().add(3,shelfter);
		bookcasebis.getListShelves().add(shelfquar);
		Room roombis=new Room("room",5,5,5);
		/*
		 * but the room of shelf2 is placed before the room of the other shelves => shelf2
		 *  is the first shelf to be found with enough space
		 
		library.getListRooms().add(0,roombis);;
		Bookcase bookcase2=new Bookcase("bookcase",4,4,4);
		roombis.getListBookcases().add(bookcase2);
		Shelf shelf1 = new Shelf(0.5,1,1);
		Shelf shelf2=new Shelf(1,1,1);
		//the first shelf in this room is shelf1 but is is too small to host the CD
		bookcase2.getListShelves().add(shelf1);
		bookcase2.getListShelves().add(1,shelf2);
		CD CD1 =new CD("CD1","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		CD CD2 =new CD("CD2","Alexandre Rozier",2016,1,ConsultationType.borrowing,1,1,1,null);
		library.getStorageRoom().add(CD1);
		library.getStorageRoom().add(CD2);
		Test.store_items(library, "bestshelf");
		assertTrue(shelf.getListItems().contains(CD1));
		
	}
	*/

	/*
	@Test
	public void testUnstore_items() {
		Library library = new Library("library",20,20,20,20);
		Room room = new Room("room",10,10,10);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		CD CD1 =new CD("CD1","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		CD CD2 =new CD("CD2","Alexandre Rozier",2016,1,ConsultationType.borrowing,2,1,1,null);
		shelf.getListItems().add(CD1);
		shelf.getListItems().add(CD2);
		DVD DVD =new DVD("DVD","yolo",2015,1,ConsultationType.borrowing,1,1,1,null);
		shelfbis.getListItems().add(DVD);

		Test.unstore_items(library);
		assertTrue(library.getStorageRoom().contains(CD1));
		
	}
	*/

	
	@Test
	public void testList_items() {
		Library library = new Library("library",20,20,20,20);
		Room room = new Room("room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		CD CD1 =new CD("CD1","ECP","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		CD CD2 =new CD("CD2","ECP","Alexandre Rozier",2016,1,ConsultationType.borrowing,2,1,1,null);
		shelf.getListItems().add(CD1);
		shelf.getListItems().add(CD2);
		DVD DVD =new DVD("DVD","ECP","yolo",2015,1,ConsultationType.borrowing,1,1,1,null);
		shelfbis.getListItems().add(DVD);
		
		String result = "List of the items :\n"+
				"CD [title=CD1, publisher=Alexandre Prot, publishingYear=2015, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]\n"+
				"CD [title=CD2, publisher=Alexandre Rozier, publishingYear=2016, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]\n"+
				"DVD [title=DVD, publisher=yolo, publishingYear=2015, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=2]";
		
		assertTrue(Test.list_items(library).equals(result));
	}
	

	@Test
	public void testList_room() {
		Library library = new Library("library",20,20,20,20);
		Room room1 = new Room("room",10,10,10);
		Room room = new Room("Beautiful Room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase 1", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		Shelf shelfbis=new Shelf(2,2,2);
		bookcase.getListShelves().add(shelf);
		bookcase.getListShelves().add(shelfbis);
		CD CD1 =new CD("CD1","ECP","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		shelf.getListItems().add(CD1);


		String result = "List of the content in room beautiful room :\n------------------------------------------------\n"+
		"Bookcase named bookcase 1 containing :\n"+
			 "\tShelf number 0 containing :\n"+
				"\t\tCD [title=CD1, publisher=Alexandre Prot, publishingYear=2015, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]"+
			 "\n\tShelf number 1 containing : nothing\n\n";
	 		
		assertTrue(Test.list_room(library,"beautiful room").equals(result));
	}

	/*
	@Test
	public void testList_bookcase() {
		Library library = new Library("library",20,20,20,20);
		Room room = new Room("Beautiful Room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase 1", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		bookcase.getListShelves().add(shelf);
		CD CD1 =new CD("CD1","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		shelf.getListItems().add(CD1);
		
		String result = "List of the content of bookcase bookcase 1 in room beautiful room :\n------------------------------------------------"+
				"\nShelf number 0 containing :"+
				"\n\tCD [title=CD1, publisher=Alexandre Prot, publishingYear=2015, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]";
		
		assertTrue(Test.list_bookcase(library,"beautiful room", "bookcase 1").equals(result));
	}
	*/
	
	@Test
	public void testFind_items() {
				Library library = new Library("library",20,20,20,20);
		Room room = new Room("Beautiful Room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase 1", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		bookcase.getListShelves().add(shelf);
		CD CD1 =new CD("CD1","ECP","Alexandre",2015,1,ConsultationType.borrowing,1,1,1,null);
		CD CD2 =new CD("CD2","ECP","Alexandre",2016,1,ConsultationType.borrowing,2,1,1,null);
		shelf.getListItems().add(CD2);
		shelf.getListItems().add(CD1);
		
		String result = "CD [title=CD2, publisher=Alexandre, publishingYear=2016, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]"
				+"\nCD [title=CD1, publisher=Alexandre, publishingYear=2015, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]\n";
		
		assertTrue(Test.find_items(library,"alexandre").equals(result));
	}
	

	@Test
	public void testSearch_title() {
				Library library = new Library("library",20,20,20,20);
		Room room = new Room("Beautiful Room",10,10,10);
		library.getListRooms().add(room);
		Bookcase bookcase = new Bookcase("bookcase 1", 5,5,5);
		room.getListBookcases().add(bookcase);
		Shelf shelf = new Shelf(3,3,3);
		bookcase.getListShelves().add(shelf);
		CD CD1 =new CD("CD","ECP","Alexandre Prot",2015,1,ConsultationType.borrowing,1,1,1,null);
		CD CD2 =new CD("CD","ECP","Alexandre Rozier",2016,1,ConsultationType.borrowing,2,1,1,null);
		shelf.getListItems().add(CD2);
		shelf.getListItems().add(CD1);
		
		String result = "CD [title=CD, publisher=Alexandre Rozier, publishingYear=2016, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]"
				+"\nCD [title=CD, publisher=Alexandre Prot, publishingYear=2015, volumeNumber=1, consultationType=online consultation, location=null, borrowingList=[], borrowable=true, borrowingDuration=1]\n";
	
		assertTrue(Test.search_title(library,"CD").equals(result));
	
	}
	
	/*

	@Test
	public void testAdd_member() {
		fail("Not yet implemented");
	}

	@Test
	public void testBorrow_item() {
		fail("Not yet implemented");
	}

	@Test
	public void testCheck_borrowed() {
		fail("Not yet implemented");
	}

	*/
}
