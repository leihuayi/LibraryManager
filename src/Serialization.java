import java.io.*;

public class Serialization {
	
	public Serialization() {};
	
	public void saveLibrary(Library library) {
		try{
			
			
			/* Creation of the directory if needed. However writing new FileOutputStream("tmp/library.ser") is enough to create it.
			//on crée le dossier au cas où il n'y en aurait pas
			new File("tmp").mkdir();
			File libraryFile = new File("tmp/library.ser");
			//Si le 
			if (!libraryFile.exists()){
				try{
					libraryFile.createNewFile();
				}
				catch (IOException i){
					i.printStackTrace();
				}
			}*/
			FileOutputStream fileOut = new FileOutputStream("tmp/library.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(library);
			out.close();
			fileOut.close();
			System.out.println("Serialized library "+library.getLibraryName()+" in tmp/library.ser");
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}
	
	public Library fetchLibrary() {
		Library lib = null;
		try
		{

			FileInputStream fileIn = new FileInputStream("tmp/library.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			lib = (Library) in.readObject();
			in.close();
			fileIn.close();
			return lib;
		}
		catch(IOException i)
		{
			i.printStackTrace();
			return null;
		}
		catch(ClassNotFoundException c)
		{
			System.out.println("Library class not found");
			c.printStackTrace();
			return null;
		}
	}

}
