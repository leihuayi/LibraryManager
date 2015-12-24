package graphicalUserInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import lms.*;

public class LibraryModifyer extends JFrame {
	
	LibraryModifyer(Library library){
		super("Action panel for the library "+library.getLibraryName());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
		//Tab : General Information
		JPanel panelGeneral = new JPanel();
		tabbedPane.addTab("General information", panelGeneral);
		panelGeneral.setLayout(new GridLayout(2,1));
		
		JPanel panelGeneral1 = new JPanel();
		panelGeneral.add(panelGeneral1);
		panelGeneral1.setLayout(new BoxLayout(panelGeneral1,BoxLayout.PAGE_AXIS));
		panelGeneral1.add(new JLabel("Welcome to the panel which enabled you to make actions on your Library !"));
		panelGeneral1.add(new JLabel("You have 3 different tabs where you can execute different kinds of actions:"));
		panelGeneral1.add(new JLabel("	* Add panel: to add any component to your Library (member, item, bookcase...)"));
		panelGeneral1.add(new JLabel("	* List panel: to obtain the list of components of your library respecting a criteria (for instance list of rooms named 'Paolini')"));
		panelGeneral1.add(new JLabel("	* Move items: to move items inside the library (store/unstore from the storage box) or outside (borrow/return a book)"));

		
		JPanel panelGeneral2 = new JPanel();
		panelGeneral2.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0)); ;
		panelGeneral.add(panelGeneral2);
		panelGeneral2.setLayout(new BoxLayout(panelGeneral2,BoxLayout.PAGE_AXIS));
		panelGeneral2.add(new JLabel("You can also save your library in order to work on it later:"));
		JButton buttonSave = new JButton("Save");
	    buttonSave.addActionListener(new ActionListener(){
	    	

	  	  
	    	@Override
	        public void actionPerformed(ActionEvent ae) {
				
	    		Serialization ser = new Serialization();
	    		try{
	    			ser.saveLibrary(library);
	    			JOptionPane.showMessageDialog(LibraryModifyer.this,"The Library "+library.getLibraryName()+" was successfully saved in the directory savedLibraries in the same directory as this application.");
	    		}
	    		catch(AlreadyExistsException e){
	    			
	    		}
	    		catch(IOException i){
	    			JOptionPane.showMessageDialog(LibraryModifyer.this,"Whoops there must have been a bug in the retrieval. Please try again.");
	    		}
	             
	            }
	        });
	    
	    panelGeneral2.add(buttonSave);
		
		
		this.setContentPane(tabbedPane);
		
		
		
		
		
		
		//Tab : Add
		
		JPanel panelAdd = new JPanel();
		panelAdd.setLayout(new BoxLayout(panelAdd,BoxLayout.PAGE_AXIS));
		tabbedPane.addTab("Add panel", panelAdd);
		this.setContentPane(tabbedPane);
		panelAdd.add(new JLabel("Chose which kind of element you want to add to your library"));
		final String addRoom = "add room <library name> : to add a room to a library with given name";
		final String addBc = "add bookcase <num shelves, room name> : to add a bookcase with num shelves to a given room of the library";
		final String addItem = "add item <list of parameters>: to add a library item with given parameters to the temporary storage box of library";
		final String addMember = "add member <member name, ccard num, email, membership type> : to add a new member to the library";
		final String[] addChoice = {addRoom,addBc,addItem,addMember};
		JComboBox<String> comboBox = new JComboBox<String>(addChoice);
		panelAdd.add(comboBox);
		JButton buttonNextAdd = new JButton("Next");
	    buttonNextAdd.addActionListener(new ActionListener(){
	    	

	  	  
	    	@Override
	        public void actionPerformed(ActionEvent ae) {
	    		String selectedAdd = (String) comboBox.getSelectedItem();
	    		
	    		switch(selectedAdd){
	    		case addRoom :
	    			JFrame frame = new JFrame();
	    			String input = JOptionPane.showInputDialog(frame,"what ?","Parameters of add room",
	    					JOptionPane.QUESTION_MESSAGE);
	    			break;
	    			
	    		case addBc :
	    			
	    			break;
	    		
	    		case addItem :
	    			
	    			break;	
	    			
	    		case addMember :
	    			
	    			break;
	    			
	    		}
	    
	            }
	        });
	    
	    panelAdd.add(buttonNextAdd);

		
		
		
		JPanel panelList = new JPanel();
		tabbedPane.addTab("List panel", panelList);
		panelList.add(new JLabel("List information"));
		this.setContentPane(tabbedPane);
		
		
		JPanel panelMove = new JPanel();
		tabbedPane.addTab("Move items", panelMove);
		panelMove.add(new JLabel("Move information"));
		this.setContentPane(tabbedPane);
		
		
		
		pack();
		setVisible(true);
	}

}
