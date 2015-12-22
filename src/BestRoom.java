import java.util.ArrayList;

public class BestRoom implements StoringStrategy {

	@Override
	public void store(LibraryItem item, Library library) {
		// TODO Auto-generated method stub

		ArrayList<Room> listPossibleHostRooms= new ArrayList<Room>();
		for (Room room : library.getListRooms()){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					if (shelf.getFreeSpace()>=item.getLength() && shelf.getHeight() >= item.getHeight()){
						//this room contains a shelf big enough to host our book
						listPossibleHostRooms.add(room);
						break;
					}
				}
				
				//if we have found a shelf in this room able to host the book, no need to check the other bookcases of this room
				if (listPossibleHostRooms.contains(room)){
					break;
				}
			}
		}
		
		Room bestRoom = listPossibleHostRooms.get(0);
		for (Room room : listPossibleHostRooms){
			if(room.getFreeSpace()>bestRoom.getFreeSpace()){
				bestRoom = room;
			}
		}
		
		Location oneLocationInBestRoom = null;
		ArrayList<Bookcase> listBookcase = bestRoom.getListBookcases();
		for (Bookcase bookcase : listBookcase){
			ArrayList<Shelf> listShelf = bookcase.getListShelves();
			for (Shelf shelf : listShelf){
				if (shelf.getFreeSpace()>=item.getLength()){
					//we place the item there and stop looking for a location
					oneLocationInBestRoom = new Location(library,bestRoom,bookcase,shelf);
					item.setLocation(oneLocationInBestRoom);
					shelf.getListItems().add(item);
					shelf.setFreeSpace(shelf.getFreeSpace()-item.getLength());
					bookcase.setFreeSpace(bookcase.getFreeSpace()-item.getLength());
					bestRoom.setFreeSpace(bestRoom.getFreeSpace()-item.getLength());
					break;
				}
			}
		}

		
		if (item.getLocation().equals(null)){
			System.out.println("No place was found to store the item");
		}
		

		
	}

}
