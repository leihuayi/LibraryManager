
public class LibraryFactory {

	public Library create_library(String name, int nbi, int n, int m, int mp){
			return (new Library(name,nbi,n,m,mp));
	}
	
	public void add_room(Library lib, String roomName, double length, double height, double width){
	}
	
	public void add_bookcase(){
	}
	
	public void add_item(){
	}
	
	public void store_items(){
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
