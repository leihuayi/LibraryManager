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

	    		JOptionPane.showMessageDialog(LibraryModifyer.this,"saved!");
	             
	            }
	        });
	    
	    panelGeneral2.add(buttonSave);
		
		
		this.setContentPane(tabbedPane);
		
		
		
		
		
		
		//Tab : Add
		
		JPanel panelAdd = new JPanel();
		tabbedPane.addTab("Add panel", panelAdd);
		panelAdd.add(new JLabel("Add information"));
		this.setContentPane(tabbedPane);
		
		
		
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
