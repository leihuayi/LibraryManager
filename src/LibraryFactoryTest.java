import static org.junit.Assert.*;
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
	/*
	@Test
	public void testAdd_bookcase() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd_item() {
		fail("Not yet implemented");
	}
 
	*/
	@Test
	public void testStore_items() {
		Library library = new Library("library",20,20,20,20);
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
		Room roombis=new Room("room",5,5,5);
		library.getListRooms().add(0,roombis);;
		Bookcase bookcase2=new Bookcase("bookcase",4,4,4);
		roombis.getListBookcases().add(bookcase2);
		Shelf shelf2=new Shelf(2,2,2);
		bookcase2.getListShelves().add(shelf2);
		CD CD =new CD("CD","Alexandre Prot",2015,1,ConsultationType.borrowing,date,1,1,1,null);
		BestRoom strategy = new BestRoom();
		Test.store_items(library, "anyfit");
		assertTrue(shelf2.getListItems().contains(CD));
		//assertTrue(shelf.getListItems().contains(CD)||shelfter.getListItems().contains(CD)||shelfquar.getListItems().contains(CD)||shelfbis.getListItems().contains(CD));
	}

	/*
	@Test
	public void testUnstore_items() {
		fail("Not yet implemented");
	}

	@Test
	public void testList_items() {
		fail("Not yet implemented");
	}

	@Test
	public void testList_room() {
		fail("Not yet implemented");
	}

	@Test
	public void testList_bookcase() {
		fail("Not yet implemented");
	}

	@Test
	public void testFind_items() {
		fail("Not yet implemented");
	}

	@Test
	public void testSearch_title() {
		fail("Not yet implemented");
	}

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

	@Test
	public void testObject() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClass() {
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testEquals() {
		fail("Not yet implemented");
	}

	@Test
	public void testClone() {
		fail("Not yet implemented");
	}

	@Test
	public void testToString() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotify() {
		fail("Not yet implemented");
	}

	@Test
	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testWait() {
		fail("Not yet implemented");
	}

	@Test
	public void testFinalize() {
		fail("Not yet implemented");
	}
	*/
}
