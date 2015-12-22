
public class LibraryFactory {

	public Library create_library(String name, int nbi, int n, int m, int mp){
			Library lib = new Library(name,nbi,n,m,mp);
			return lib;
	}
	
	public void add_room(Library lib, String roomName, double length, double height, double width){
	}
	
	public void add_bookcase(){
	}
	
	public void add_item(){
	}
	
	public void store_items(Library lib, String storing_strategy){
		switch(storing_strategy.toLowerCase())
		{
		case "anyfit": 
			AnyFit strategy1 = new AnyFit();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy1.store(item, lib);
			}
			break;
		
		case "bestbookcase":
			BestBookcase strategy2 = new BestBookcase();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy2.store(item, lib);
			}
			break;
		
		case "bestroom":
			BestRoom strategy3 = new BestRoom();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy3.store(item, lib);
			}
			break;
		
		case "bestshelf":
			BestShelf strategy4 = new BestShelf();
			for (LibraryItem item : lib.getStorageRoom()){
				strategy4.store(item, lib);
			}
			break;
			
		default:
			System.out.println("You did not call a valid storing stategy. You can use AnyFit / BestShelf / BestBookcase / BestRoom");
		
		}
	}
	
	public void unstore_items(){
	}
	
	public String list_items(){
		return "";
	}
	
	public String list_room(){
		return "";
	}
	
	public String list_bookcase(){
		return "";
	}
	
	public String find_items(){
		return "";
	}
	
	public String search_title(){
		return "";
	}
	
	public void add_member(){
	}
	
	public void borrow_item(){
	}
	
	public String check_borrowed(){
		return "";
	}
	
}
