import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Library implements java.io.Serializable{
	private ArrayList<Room> listRooms;
	// Parameters for frequent member
	private int N;
	private int M;
	private ArrayList<LibraryItem> storageRoom;
	//Limitation of borrowing
	private int Nbi;
	private String libraryName;
	private ArrayList<Member> listMembers;
	private ArrayList <Borrowing> reservationList;
	
	
	public ArrayList<Borrowing> getReservationList() {
		return reservationList;
	}
	public void setReservationList(ArrayList<Borrowing> reservationList) {
		this.reservationList = reservationList;
	}
	public ArrayList<Member> getListMembers() {
		return listMembers;
	}
	public void setListMembers(ArrayList<Member> listMembers) {
		this.listMembers = listMembers;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public ArrayList<Room> getListRooms() {
		return listRooms;
	}
	public void setListRooms(ArrayList<Room> listRooms) {
		this.listRooms = listRooms;
	}
	public int getN() {
		return N;
	}
	public void setN(int n) {
		N = n;
	}
	public int getM() {
		return M;
	}
	public void setM(int m) {
		M = m;
	}
	public ArrayList<LibraryItem> getStorageRoom() {
		return storageRoom;
	}
	public void setStorageRoom(ArrayList<LibraryItem> storageRoom) {
		this.storageRoom = storageRoom;
	}
	public int getNbi() {
		return Nbi;
	}
	public void setNbi(int nbi) {
		Nbi = nbi;
	}
	public Library(String name, int n, int m, int nbi) {
		super();
		this.libraryName=name;
		this.listRooms = new ArrayList<Room>();
		N = n;
		M = m;
		this.storageRoom = new ArrayList<LibraryItem>();
		Nbi = nbi;
		this.listMembers= new ArrayList<Member>();
		this.reservationList= new ArrayList<Borrowing>();
	}
	
	
	
	@Override
	public String toString() {
		return "Library [N=" + N + ", M=" + M + ", Nbi="
				+ Nbi + ", libraryName=" + libraryName + ", listMembers=" + listMembers + "]";
	}
	
	
	private static Date modifyDate(int numberOfDay)
	  {
		  Calendar cal = Calendar.getInstance();
		  cal.add(Calendar.DATE, numberOfDay);
		  return cal.getTime();
	  }

	
	//this function check everything needed when the program launches
	public static void check(Library library){
		for (Member member : library.getListMembers()) {
			//check members suspended
			if (member.getEndingOfSuspension().equals(new Date())){
				member.setUnsuspended(true);
			}
			//check borrowings
			for (Borrowing borrowing : member.getCurrentItems()){
				if (borrowing.getBorrowingDate().equals(modifyDate(-7))){
					borrowing.notifyObserverDelay();
					member.getPenalties().add(new Date());
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-21))){
					member.setEndingOfSuspension(modifyDate(member.lowerSuspensionTime()));
				}
				else if (borrowing.getBorrowingDate().equals(modifyDate(-42))){
					member.setEndingOfSuspension(modifyDate(member.higherSuspensionTime()));
				}
			}
			//check of cards
			if (!member.getCard().getType().equals(CardType.golden)){
				int count =0;
				boolean bool=true;
				for (Borrowing borrowing : member.getHistory()){
					for(Date date: member.getPenalties()) {
						if(date.after(modifyDate(-library.getM()*7))){
							bool=false;
						}
					}
					if (bool && borrowing.getBorrowingDate().after(modifyDate(-library.getM()*7))){
							count=count+1;
						}
					
				}
				if (bool && count>=library.getN()){
					member.getCard().setType(CardType.frequent);
				}
				else{
					member.getCard().setType(CardType.standard);
				}
			}
		}
	}
	
	/*
	 * 
	 * Placement algorithms
	 * 
	 * 
	 */
	
	
	
	public void anyFit(LibraryItem item){
		Location location = null;
		ArrayList<Room> listRoom = this.getListRooms();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					//reminder: the length of an item is its thickness. We check that the item can enter in the shelf
					if (shelf.getFreeSpace()>=item.getLength()){
						//we place the item there and stop looking for a location
						location = new Location(this,room,bookcase,shelf);
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
	
	
	/*
	//method which gives the array of potential locations for an item... not needed for the new versions of the algorithms
	 * 
	public ArrayList<Location> potentialLocationForItem(LibraryItem item){
		ArrayList<Room> listRoom = this.getListRooms();
		ArrayList<Location> listLocation= new ArrayList<>();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					if (shelf.getFreeSpace()>=item.getLength()){
						Location location = new Location(this,room,bookcase,shelf);
						listLocation.add(location);
					}
				}
			}
		}
		return listLocation;
	}
	*/
	
	public void bestShelf(LibraryItem item){
		
		ArrayList<Room> listRoom = this.getListRooms();
		ArrayList<Location> listPossibleLocation= new ArrayList<>();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					if (shelf.getFreeSpace()>=item.getLength()){
						Location location = new Location(this,room,bookcase,shelf);
						listPossibleLocation.add(location);
					}
				}
			}
		}
		
		Location bestShelfLocation = null;
		for (Location location : listPossibleLocation){
			if(location.getShelf().getFreeSpace()>bestShelfLocation.getShelf().getFreeSpace()){
				bestShelfLocation = location;
			}
		}
		item.setLocation(bestShelfLocation);
		
		if (item.getLocation().equals(null)){
			System.out.println("There is no place");
		}
		else{
			//we place the item at the location bestShelfLocation
			bestShelfLocation.getShelf().getListItems().add(item);
			bestShelfLocation.getShelf().setFreeSpace(bestShelfLocation.getShelf().getFreeSpace()-item.getLength());
			bestShelfLocation.getBookcase().setFreeSpace(bestShelfLocation.getBookcase().getFreeSpace()-item.getLength());
			bestShelfLocation.getRoom().setFreeSpace(bestShelfLocation.getRoom().getFreeSpace()-item.getLength());
		}
	
	}
	
	
	public void bestBookcase (LibraryItem item){
		
		ArrayList<Room> listRoom = this.getListRooms();
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
		Room roomOfBestBk = this.listRooms.get(0);
		for (Room room : this.listRooms){
			if(room.getListBookcases().contains(bestBk)){
				roomOfBestBk = room;
				break;
			}
		}
		
		Location oneLocationInBestBookcase = null;
		ArrayList<Shelf> listShelf = bestBk.getListShelves();
		for (Shelf shelf : listShelf){
			if (shelf.getFreeSpace()>=item.getLength()){
				//we place the item there and stop searching for a location
				oneLocationInBestBookcase = new Location(this,roomOfBestBk,bestBk,shelf);
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
	
	
	
	public void bestRoom(LibraryItem item){
		
		ArrayList<Room> listPossibleHostRooms= new ArrayList<Room>();
		for (Room room : this.listRooms){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					if (shelf.getFreeSpace()>=item.getLength()){
						//this bookcase contains a shelf big enough to host our book
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
					//we place the item there ans stop looking for a location
					oneLocationInBestRoom = new Location(this,bestRoom,bookcase,shelf);
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
			System.out.println("No place was found to put the item");
		}
		

		
	}
}
