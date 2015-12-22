import java.util.ArrayList;

public class BestShelf implements StoringStrategy {

	@Override
	public void store(LibraryItem item, Library library) {
		// TODO Auto-generated method stub
		ArrayList<Room> listRoom = library.getListRooms();
		ArrayList<Location> listPossibleLocation= new ArrayList<>();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					if (shelf.getFreeSpace()>=item.getLength() && shelf.getHeight() >= item.getHeight()){
						Location location = new Location(library,room,bookcase,shelf);
						listPossibleLocation.add(location);
					}
				}
			}
		}
		
		Location bestShelfLocation = listPossibleLocation.get(0);
		for (Location location : listPossibleLocation){
			if(location.getShelf().getFreeSpace()>=bestShelfLocation.getShelf().getFreeSpace()){
				bestShelfLocation = location;
			}
		}

		
		if (listPossibleLocation.isEmpty()){
			System.out.println("No place was found to store the item");
		}
		else{
			//we place the item at the location bestShelfLocation
			item.setLocation(bestShelfLocation);
			bestShelfLocation.getShelf().getListItems().add(item);
			bestShelfLocation.getShelf().setFreeSpace(bestShelfLocation.getShelf().getFreeSpace()-item.getLength());
			bestShelfLocation.getBookcase().setFreeSpace(bestShelfLocation.getBookcase().getFreeSpace()-item.getLength());
			bestShelfLocation.getRoom().setFreeSpace(bestShelfLocation.getRoom().getFreeSpace()-item.getLength());
		}
	}

}
