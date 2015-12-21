import java.util.ArrayList;

public class BestBookcase implements StoringStrategy {

	@Override
	public void store(LibraryItem item, Library library) {
		// TODO Auto-generated method stub
		ArrayList<Room> listRoom = library.getListRooms();
		ArrayList<Bookcase> listPossibleHostBookcase= new ArrayList<Bookcase>();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					if (shelf.getFreeSpace()>=item.getLength()){
						//this room contains a shelf big enough to host our book
						listPossibleHostBookcase.add(bookcase);
						break;
					}
				}
			}
		}
		
		Bookcase bestBk = listPossibleHostBookcase.get(0);
		for (Bookcase bookcase : listPossibleHostBookcase){
			if(bookcase.getFreeSpace()>bestBk.getFreeSpace()){
				bestBk = bookcase;
			}
		}
		//looking for the room of the best bookcase to be able to set the location
		Room roomOfBestBk = listRoom.get(0);
		for (Room room : listRoom){
			if(room.getListBookcases().contains(bestBk)){
				roomOfBestBk = room;
				break;
			}
		}
		
		Location oneLocationInBestBookcase = null;
		ArrayList<Shelf> listShelf = bestBk.getListShelves();
		for (Shelf shelf : listShelf){
			if (shelf.getFreeSpace()>=item.getLength()){
				//we place the item there and stop looking for a location
				oneLocationInBestBookcase = new Location(library,roomOfBestBk,bestBk,shelf);
				item.setLocation(oneLocationInBestBookcase);
				shelf.getListItems().add(item);
				shelf.setFreeSpace(shelf.getFreeSpace()-item.getLength());
				bestBk.setFreeSpace(bestBk.getFreeSpace()-item.getLength());
				roomOfBestBk.setFreeSpace(roomOfBestBk.getFreeSpace()-item.getLength());
				break;
			}
		}
		
		if (item.getLocation().equals(null)){
			System.out.println("No place was found to store the item");
		}

	}

}
