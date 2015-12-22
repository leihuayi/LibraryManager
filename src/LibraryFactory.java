
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
	
	
	

	
}
