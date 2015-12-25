package lms;
import java.io.*;

public class Serialization {
	
	public Serialization() {};
	
	//i=0 if we do not want to save a library over another of the same name, 1 if we don't care if we save over
	public void saveLibrary(Library library, boolean over) throws AlreadyExistsException, IOException{
		
		if (new File("savedLibraries/"+library.getLibraryName()+".ser").exists() && !over){
			throw new AlreadyExistsException();
		}
		
		else{
		
			try{
				
				/* Creation of the directory if needed. However writing new FileOutputStream("savedLibraries/library.ser") is enough to create it.
				//on crée le dossier au cas où il n'y en aurait pas
				new File("savedLibraries").mkdir();
				File libraryFile = new File("savedLibraries/library.ser");
				//Si le fichier n'existe pas, on le crée
				if (!libraryFile.exists()){
					try{
						libraryFile.createNewFile();
					}
					catch (IOException i){
						i.printStackTrace();
					}
				}*/
				
				
				FileOutputStream fileOut = new FileOutputStream("savedLibraries/"+library.getLibraryName()+".ser");
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(library);
				out.close();
				fileOut.close();
	
			}
		
		
			catch(IOException i){
				throw new IOException();
			}
		}
	}
	
	public Library fetchLibrary(String name) throws IOException {
		Library lib = null;
		try
		{			
			FileInputStream fileIn = new FileInputStream("savedLibraries/"+name+".ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			lib = (Library) in.readObject();
			in.close();
			fileIn.close();
			return lib;
		}
		catch(IOException i)
		{
			throw new IOException();
		}
		catch(ClassNotFoundException c)
		{
			throw new IOException();
		}
	}

}
