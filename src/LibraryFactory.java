
public class LibraryFactory {

	public Library create_library(String name, int nbi, int n, int m, int mp){
			return (new Library(name,nbi,n,m,mp));
	}

	
}
