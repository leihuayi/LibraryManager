	import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class LibraryTest {

	
	
	@Test
	public void testCreate_library() {
		LibraryFactory libF = new LibraryFactory();
		Library lib = libF.create_library("coucou",2,1,5,6);
		Library libTest = new Library("coucou",2,1,5,6);
		assertTrue(lib.equals(libTest));
	}
	
}
