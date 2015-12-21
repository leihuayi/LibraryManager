import java.util.ArrayList;

public class AnyFit implements StoringStrategy {

	@Override
	public void store(LibraryItem item, Library library) {
		// TODO Auto-generated method stub
		Location location = null;
		ArrayList<Room> listRoom = library.getListRooms();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					//reminder: the length of an item is its thickness. We check that the item can enter in the shelf
					if (shelf.getFreeSpace()>=item.getLength()){
						//we place the item there and stop looking for a location
						location = new Location(library,room,bookcase,shelf);
						item.setLocation(location);
						shelf.getListItems().add(item);
						shelf.setFreeSpace(shelf.getFreeSpace()-item.getLength());
						bookcase.setFreeSpace(bookcase.getFreeSpace()-item.getLength());
						room.setFreeSpace(room.getFreeSpace()-item.getLength());
						break;
					}
				}
			}
		}
		if (item.getLocation().equals(null)){
			System.out.println("No place was found to put the item");
		}
	}

}
