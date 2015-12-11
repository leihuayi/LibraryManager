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
	
	public Location anyFit(LibraryItem item){
		ArrayList<Room> listRoom = this.getListRooms();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					ArrayList<LibraryItem> listItem = shelf.getListItems();
					Integer sum = new Integer(0);
					for (LibraryItem libraryItem : listItem){	
						sum= (int) (sum+libraryItem.getMeasures().getLength());
					}
					//reminder: the length of an item is its thickness. We check that the item can enter in the shelf
					if (shelf.getMeasures().getHeight()>=item.getMeasures().getHeight()&&shelf.getMeasures().getLength()-sum>=item.getMeasures().getLength()&&shelf.getMeasures().getWidth()>=item.getMeasures().getWidth()){
						shelf.getListItems().add(item);
						Location location = new Location(this,room,bookcase,shelf);
						item.setLocation(location);
						return location;
					}
				}
			}
		}
		if (item.getLocation().equals(null)){
			System.out.println("No place was found to put the item");
		}
		return null;
	}
	
	//method which gives the array of potential locations for an item
	public ArrayList<Location> potentialLocationForItem(LibraryItem item){
		ArrayList<Room> listRoom = this.getListRooms();
		ArrayList<Location> listLocation= new ArrayList<>();
		for (Room room : listRoom){
			ArrayList<Bookcase> listBookcase = room.getListBookcases();
			for (Bookcase bookcase : listBookcase){
				ArrayList<Shelf> listShelf = bookcase.getListShelves();
				for (Shelf shelf : listShelf){
					ArrayList<LibraryItem> listItem = shelf.getListItems();
					Integer sum = new Integer(0);
					for (LibraryItem libraryItem : listItem){	
						sum= (int) (sum+libraryItem.getMeasures().getLength());
					}
					if (shelf.getMeasures().getHeight()>=item.getMeasures().getHeight()&&shelf.getMeasures().getLength()-sum>=item.getMeasures().getLength()&&shelf.getMeasures().getWidth()>=item.getMeasures().getWidth()){
						Location location = new Location(this,room,bookcase,shelf);
						listLocation.add(location);
					}
				}
			}
		}
		return listLocation;
	}
	
	
	public Location bestShelf(LibraryItem item){
		
		ArrayList<Location> listLocation= this.potentialLocationForItem(item);
		
		Location bestLocation = listLocation.get(0);
		for (Location locat : listLocation){
			Double sumLoop = new Double(0.0);
			//we calculate how much space is used in the Location locat and then deduct the remaining space
			for (LibraryItem libraryItem : locat.getShelf().getListItems()){	
				sumLoop= (sumLoop+libraryItem.getMeasures().getLength());
			}
			double remainingSpaceLoop = locat.getShelf().getMeasures().getLength() - sumLoop;
			//we calculate how much space is used in the Location bestLocation and then deduct the remaining space
			Double sumBest = new Double(0.0);
			for (LibraryItem libraryItem : bestLocation.getShelf().getListItems()){	
				sumBest= (sumBest+libraryItem.getMeasures().getLength());
			}
			double remainingSpaceBest = bestLocation.getShelf().getMeasures().getLength() - sumLoop;
			
			//the best shelf is the one with the biggest remaining space
			if(remainingSpaceLoop>remainingSpaceBest){
				bestLocation=locat;
			}
		}
		item.setLocation(bestLocation);
		bestLocation.getShelf().getListItems().add(item);
		
		if (item.getLocation().equals(null)){
			System.out.println("No place was found to put the item");
			return null;
		}
		else{
			return bestLocation;
		}
	}
	
	
	public Location bestBookcase (LibraryItem item){
		ArrayList<Location> listLocation= this.potentialLocationForItem(item);
		
		//instanciation
		double bestAvaibleSpace = 0.0;
		Bookcase bestBookcaseInAvaibleSpace = listLocation.get(0).getBookcase();
		
		/*
		 * We store the bookcases we have already seen in order not to calculate several times
		 * the bookcase with the most remaining space if there are several potential locations in it
		 * 
		 */
		ArrayList<Bookcase> alreadySeenBookcase = new ArrayList<Bookcase>();
		
		
		for (Location locat : listLocation){
			
			
			
			//We check that we didn't calculate this bookcase before
			if (!alreadySeenBookcase.contains(locat.getBookcase())){
				
				Double sumLoopBookcase = new Double(0.0);
				
				//for each shelf of the bookcase where locat is located
				for (Shelf shelf : locat.getBookcase().getListShelves()){
					
					//if the shelf is big enough to welcome the book (= if this location is inside listLocation)
					if (listLocation.contains(new Location(this,locat.getRoom(),locat.getBookcase(), shelf))){
						
						//We calculate the remaining space for the shelf
						Double sumLoopShelf = new Double(0.0);
						for (LibraryItem libraryItem : shelf.getListItems()){
							sumLoopShelf= (sumLoopShelf+libraryItem.getMeasures().getLength());				
						}
						double remainingSpaceLoop = shelf.getMeasures().getLength() - sumLoopShelf;
						
						//and then we sum to know the whole place avaible in the entire bookcase
						sumLoopBookcase = sumLoopBookcase + remainingSpaceLoop;
						
					}

				}
				
				
				//We inform that we have calculated this bookcase
				alreadySeenBookcase.add(locat.getBookcase());
			
			
				//We compare to the best remaining space we have found up to now for a bookcase
				if (sumLoopBookcase>bestAvaibleSpace){
					bestBookcaseInAvaibleSpace = locat.getBookcase();
				}

				
				
			}

		}
		
		
		//I fetch the room where is located our best bookshelf
		Room roomOfBestShelf = new Room("bestRoom",new Cuboid(0.0,0.0,0.0));
		for (Room room : this.listRooms){
			if(room.getListBookcases().contains(bestBookcaseInAvaibleSpace)){
				roomOfBestShelf = room;
			}
			break;
		}
			
		
		
		//We put our item in any Shelf big enough to welcome it inside our best Bookcase
		Location oneLocationInBestBookcase = null;
		
		for (Shelf shelf : bestBookcaseInAvaibleSpace.getListShelves()){
			oneLocationInBestBookcase = new Location(this,roomOfBestShelf,bestBookcaseInAvaibleSpace, shelf);
			if (listLocation.contains(oneLocationInBestBookcase)){
				item.setLocation(oneLocationInBestBookcase);
				oneLocationInBestBookcase.getShelf().getListItems().add(item);
				//we stop as soon as we have found a convenient location
				break;
			}


		}
		
		if (item.getLocation().equals(null)){
			System.out.println("There is no place");
			return null;
		}
		else{
			return oneLocationInBestBookcase;
		}
	}
	
	
	
	public Location bestRoom(LibraryItem item){
		ArrayList<Location> listLocation= this.potentialLocationForItem(item);
		
		//instanciation
		double bestAvaibleSpace = 0.0;
		Room bestRoomInAvaibleSpace = listLocation.get(0).getRoom();
		
		/*
		 * We store the bookcases we have already seen in order not to calculate several times
		 * the bookcase with the most remaining space if there are several potential locations in it
		 * 
		 */
		ArrayList<Room> alreadySeenRoom = new ArrayList<Room>();
		
		
		for (Location locat : listLocation){
			
			
			
			//We check that we didn't calculate this bookcase before
			if (!alreadySeenRoom.contains(locat.getRoom())){
				
				Double sumLoopRoom = new Double(0.0);
				
				//for each bookcase of the room where locat is located
				for (Bookcase bookcase : locat.getRoom().getListBookcases()){
					
					Double sumLoopBookcase = new Double(0.0);
					
					//we calculate the whole remaining space in this bookcase : code of the previous algorithm				
					for (Shelf shelf : bookcase.getListShelves()){
						
						//if the shelf is big enough to welcome the book (= if this location is inside listLocation)
						if (listLocation.contains(new Location(this,locat.getRoom(),bookcase, shelf))){
							
							//We calculate the remaining space for the shelf
							Double sumLoopShelf = new Double(0.0);
							for (LibraryItem libraryItem : shelf.getListItems()){
								sumLoopShelf= (sumLoopShelf+libraryItem.getMeasures().getLength());				
							}
							double remainingSpaceLoop = shelf.getMeasures().getLength() - sumLoopShelf;
							
							//and then we sum to know the whole place avaible in the entire bookcase
							sumLoopBookcase = sumLoopBookcase + remainingSpaceLoop;
							
						}

					}
				
					
					//To get the remaining space in the whole room, we get the whole remaining space of each bookcase
					sumLoopRoom = sumLoopRoom + sumLoopBookcase;
					
					
				}
				
				
				//We inform that we have calculated this bookcase
				alreadySeenRoom.add(locat.getRoom());
			
			
				//We compare to the best remaining space we have found up to now for a bookcase
				if (sumLoopRoom>bestAvaibleSpace){
					bestRoomInAvaibleSpace = locat.getRoom();
				}
				
			}
	
		}
			
		
		
		//We put our item in any Shelf big enough to welcome it inside our best Room
		Location oneLocationInBestRoom = null;
		
		for (Bookcase bookcase : bestRoomInAvaibleSpace.getListBookcases()){
			for (Shelf shelf : bookcase.getListShelves()){
				oneLocationInBestRoom = new Location(this,bestRoomInAvaibleSpace,bookcase, shelf);
				if (listLocation.contains(oneLocationInBestRoom)){
					item.setLocation(oneLocationInBestRoom);
					oneLocationInBestRoom.getShelf().getListItems().add(item);
					//we stop as soon as we have found a convenient location
					break;
				}
			}
			
			//we stop as soon as we have found a convenient location
			if(item.getLocation() != null){
				break;
			}
		}
		
		if (item.getLocation().equals(null)){
			System.out.println("No place was found to put the item");
			return null;
		}
		else{
			return oneLocationInBestRoom;
		}
	}
}
