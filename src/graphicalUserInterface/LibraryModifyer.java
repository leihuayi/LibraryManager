package graphicalUserInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

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
	    			ser.saveLibrary(library,false);
	    			JOptionPane.showMessageDialog(LibraryModifyer.this,"The Library "+library.getLibraryName()+" was successfully saved in the directory savedLibraries in the same directory as this application.");
	    		}
	    		catch(AlreadyExistsException e){
	    			
	    			/*
	    			boolean oOrc = false;	    		
					System.out.println("A library of this name already exists. Enter: \n\t (o) if you want to save over it \n\t (c) if you want to change your library name");
					while (!oOrc){
						Scanner scOC = new Scanner(System.in);
						String answerOC = scOC.nextLine();
						if(answer.equalsIgnoreCase("o")){
							ser.saveLibrary(library,true);
							oOrc = true;
						}
						else if(answer.equalsIgnoreCase("c")){
						System.out.println("Write your new library name");
						Scanner scLibN = new Scanner(System.in);
						library.setLibraryName(scLibN.nextLine());
						System.out.println("You library name was successfully changed to "+library.getLibraryName()+". Try to save again with (14).");
						oOrc = true;
						}
						else{
							System.out.println("Please enter either o or c .");
						}
					}
					*/
	    		}
	    		catch(IOException i){
	    			JOptionPane.showMessageDialog(LibraryModifyer.this,"Whoops there must have been a bug in the saving. Please try again.");
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
		panelAdd.add(Box.createVerticalStrut(50)); //Space for a more pretty window
		final String addRoom = "add room : to add a room to the library";
		final String addBc = "add bookcase : to add a bookcase with num shelves to a given room of the library";
		final String addItem = "add item : to add a library item with given parameters to the temporary storage box of library";
		final String addMember = "add member : to add a new member to the library";
		final String[] addChoice = {addRoom,addBc,addItem,addMember};
		JComboBox<String> comboBox = new JComboBox<String>(addChoice);
		comboBox.setMaximumSize(new Dimension(1000, 30));
		panelAdd.add(comboBox);
		panelAdd.add(Box.createVerticalStrut(50));
		JButton buttonNextAdd = new JButton("Next");
	    buttonNextAdd.addActionListener(new ActionListener(){
	    	

	  	  
	    	@Override
	        public void actionPerformed(ActionEvent ae) {
	    		
	    		String selectedAdd = (String) comboBox.getSelectedItem();
	    		
	    		switch(selectedAdd){
	    		
	    		//addRoom
	    		case addRoom :
	    			boolean validAnswer = false;
	    			JPanel myPanel = new JPanel();
	    			myPanel.setLayout(new BoxLayout(myPanel,BoxLayout.PAGE_AXIS));
	    			JTextField roomName = new JTextField(20);
	    		    JTextField length = new JTextField(5);
	    		    JTextField height = new JTextField(5);
	    		    JTextField width = new JTextField(5);
	    			myPanel.add(new JLabel("Name of the room:"));
	    		    myPanel.add(roomName);
	    		    myPanel.add(new JLabel("Length of the room (in cm):"));
	    		    myPanel.add(length);
	    		    myPanel.add(new JLabel("Height of the room (in cm):"));
	    		    myPanel.add(height);
	    		    myPanel.add(new JLabel("Width of the room (in cm):"));
	    		    myPanel.add(width);
	    		    
    	    		double l2 = 0.0;
    	    		double h2 = 0.0;
    	    		double w2 = 0.0;

	    		    int result = JOptionPane.showConfirmDialog(null, myPanel, 
	    		               "Please enter the parameters of the room", JOptionPane.OK_CANCEL_OPTION);
	    		    if (result == JOptionPane.OK_OPTION) {
	    		    		    		    	
	    		    	//length
	    	    		String l1 = length.getText();
	    	    		if(roomName.getText().equals("")){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to enter a room name");
	    		    	}
	    	    		else{
	    	    			validAnswer = true;
	    	    		}

	    	    		try{
	    	    			l2 = Double.parseDouble(l1);
	    	    			if(l2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of length must be positive");
	    	    				validAnswer=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : length");
	    					validAnswer=false;
	    				}
	    	    		
	    	    		//height
	    	    		String h1 = height.getText();

	    	    		try{
	    	    			h2 = Double.parseDouble(h1);
	    	    			if(h2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of height must be positive");
	    	    				validAnswer=false;
	    	    			}
	    	    			
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : height");
	    					validAnswer=false;
	    				}
	    	    		
	    	    		//width
	    	    		String w1 = width.getText();

	    	    		try{
	    	    			w2 = Double.parseDouble(w1);
	    	    			if(w2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of width must be positive");
	    	    				validAnswer=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : width");
	    					validAnswer=false;
	    				}
	    		    }
	    		    
	    		    if(validAnswer){
	    		    	
	    		    	LibraryFactory libF = new LibraryFactory();
	    		    	try{
			    			libF.add_room(library, roomName.getText(),l2, h2, w2);
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "The room "+roomName.getText()+" was successful added to the Library");
				    		new LibraryModifyer(library);
				    		dispose();
			    		}
			    		catch(AlreadyExistsException e){
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "A room of the name "+roomName.getText()+" already exists in this library.");
			    		}
	    		    	
		    			
		    		}
	    		    
	    			break;
	    			//end addRoom
	    			
	    			
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
