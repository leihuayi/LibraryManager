import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryFactoryTest {

	@Test
	public void testCreate_library() {
		LibraryFactory libF = new LibraryFactory();
		Library lib = libF.create_library("coucou",2,1,5,6);
		Library libTest = new Library("coucou",2,1,5,6);
		assertTrue(lib.equals(libTest));
	}

		/*
	@Test
	public void testAdd_room() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd_bookcase() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd_item() {
		fail("Not yet implemented");
	}

	@Test
	public void testStore_items() {
		fail("Not yet implemented");
	}

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
