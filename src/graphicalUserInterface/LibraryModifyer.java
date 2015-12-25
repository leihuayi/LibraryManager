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
		
		/*
		 * Tab : General Information
		 */
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
	    			JPanel myPanel = new JPanel();
	    			myPanel.setLayout(new BoxLayout(myPanel,BoxLayout.PAGE_AXIS));
	    			myPanel.add(new JLabel("A Library of this name already exists. You can change you library name below, enter nothing if you want to save over it"));
	    			JTextField libName = new JTextField(20);
	    			myPanel.add(libName);
	    		    if(libName.getText().equals("")){
	    		    	try{
	    		    		ser.saveLibrary(library, true);
	    		    	}
	    		    	catch(AlreadyExistsException i){
	    		    	}
	    		    	catch(IOException i){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this,"Whoops there must have been a bug in the saving. Please try again.");
	    		    	}
	    		    }
	    		    else{
	    		    	library.setLibraryName(libName.getText());
	    		    	JOptionPane.showMessageDialog(LibraryModifyer.this,"Your library name was successfully changed to "+". Try again to save.");
	    		    }
	    		
	    		}
	    		catch(IOException i){
	    			JOptionPane.showMessageDialog(LibraryModifyer.this,"Whoops there must have been a bug in the saving. Please try again.");
	    		}
	             
	            }
	        });
	    
	    panelGeneral2.add(buttonSave);
		
		
		this.setContentPane(tabbedPane);
		
		
		
		
		
		
		
		
		/*
		 * Tab : Add
		 */
		
		
		
		
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
	    			boolean validAnswerRoom = false;
	    			JPanel myPanelRoom = new JPanel();
	    			myPanelRoom.setLayout(new BoxLayout(myPanelRoom,BoxLayout.PAGE_AXIS));
	    			JTextField roomName = new JTextField(20);
	    		    JTextField length = new JTextField(5);
	    		    JTextField height = new JTextField(5);
	    		    JTextField width = new JTextField(5);
	    			myPanelRoom.add(new JLabel("Name of the room:"));
	    		    myPanelRoom.add(roomName);
	    		    myPanelRoom.add(new JLabel("Length of the room (in cm):"));
	    		    myPanelRoom.add(length);
	    		    myPanelRoom.add(new JLabel("Height of the room (in cm):"));
	    		    myPanelRoom.add(height);
	    		    myPanelRoom.add(new JLabel("Width of the room (in cm):"));
	    		    myPanelRoom.add(width);
	    		    
    	    		double l2 = 0.0;
    	    		double h2 = 0.0;
    	    		double w2 = 0.0;

	    		    int result = JOptionPane.showConfirmDialog(null, myPanelRoom, 
	    		               "Please enter the parameters of the room", JOptionPane.OK_CANCEL_OPTION);
	    		    if (result == JOptionPane.OK_OPTION) {
	    		    		    		    	
	    		    	
	    	    		//Room name
	    	    		if(roomName.getText().equals("")){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to enter a room name");
	    		    	}
	    	    		else{
	    	    			validAnswerRoom = true;
	    	    		}
	    	    		
	    	    		//length
	    	    		String l1 = length.getText();
	    	    		try{
	    	    			l2 = Double.parseDouble(l1);
	    	    			if(l2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of length must be positive");
	    	    				validAnswerRoom=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : length");
	    					validAnswerRoom=false;
	    				}
	    	    		
	    	    		//height
	    	    		String h1 = height.getText();

	    	    		try{
	    	    			h2 = Double.parseDouble(h1);
	    	    			if(h2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of height must be positive");
	    	    				validAnswerRoom=false;
	    	    			}
	    	    			
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : height");
	    					validAnswerRoom=false;
	    				}
	    	    		
	    	    		//width
	    	    		String w1 = width.getText();

	    	    		try{
	    	    			w2 = Double.parseDouble(w1);
	    	    			if(w2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of width must be positive");
	    	    				validAnswerRoom=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : width");
	    					validAnswerRoom=false;
	    				}
	    		    }
	    		    
	    		    if(validAnswerRoom){
	    		    	
	    		    	LibraryFactory libF = new LibraryFactory();
	    		    	try{
			    			libF.add_room(library, roomName.getText(),l2, h2, w2);
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "The room "+roomName.getText()+" was successful added to the Library");
				    		
			    		}
			    		catch(AlreadyExistsException e){
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "A room of the name "+roomName.getText()+" already exists in this library.");
			    		}
	    		    	
		    			
		    		}
	    		    
	    			break;
	    			//end addRoom
	    			
	    		//beginning addBc
	    		case addBc :
	    			boolean validAnswerBc = false;
	    			JPanel myPanelBc = new JPanel();
	    			myPanelBc.setLayout(new BoxLayout(myPanelBc,BoxLayout.PAGE_AXIS));
	    			JTextField roomNameBc = new JTextField(20);
	    			JTextField bcNameBc = new JTextField(20);
	    			JTextField numberShelvesBc = new JTextField(5);
	    		    JTextField lengthBc = new JTextField(5);
	    		    JTextField heightBc = new JTextField(5);
	    		    JTextField widthBc = new JTextField(5);
	    		    myPanelBc.add(new JLabel("Name of the room:"));
	    		    myPanelBc.add(roomNameBc);
	    			myPanelBc.add(new JLabel("Name of the bookcase:"));
	    		    myPanelBc.add(bcNameBc);
	    		    myPanelBc.add(new JLabel("Number of shelves:"));
	    		    myPanelBc.add(numberShelvesBc);
	    		    myPanelBc.add(new JLabel("Length of the bookcase (in cm):"));
	    		    myPanelBc.add(lengthBc);
	    		    myPanelBc.add(new JLabel("Height of the bookcase (in cm):"));
	    		    myPanelBc.add(heightBc);
	    		    myPanelBc.add(new JLabel("Width of the bookcase (in cm):"));
	    		    myPanelBc.add(widthBc);
	    		    
	    		    int numS2 = 0;
    	    		double l2Bc = 0.0;
    	    		double h2Bc = 0.0;
    	    		double w2Bc = 0.0;

	    		    int resultBc = JOptionPane.showConfirmDialog(null, myPanelBc, 
	    		               "Please enter the parameters of the bookcase", JOptionPane.OK_CANCEL_OPTION);
	    		    if (resultBc == JOptionPane.OK_OPTION) {
	    		    		    		    	
	    		    	
	    	    		//Room Name
	    	    		if(roomNameBc.getText().equals("")){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to enter a room name");
	    		    	}
	    	    		else{
	    	    			validAnswerBc = true;
	    	    		}
	    	    		
	    	    		//Bookcase Name
	    	    		if(roomNameBc.getText().equals("")){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to enter a bookcase name");
	    		    		validAnswerBc=false;
	    		    	}
	    	    		
	    	    		//NumShelves
	    	    		String numS = numberShelvesBc.getText();
	    	    		try{
	    	    			numS2 = Integer.parseInt(numS);
	    	    			if(numS2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of the number of shelves must be positive");
	    	    				validAnswerBc=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter an integer in the field : number of shelves");
	    					validAnswerBc=false;
	    				}
	    	    		
	    	    		//length
	    	    		String l1Bc = lengthBc.getText();
	    	    		try{
	    	    			l2Bc = Double.parseDouble(l1Bc);
	    	    			if(l2Bc < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of length must be positive");
	    	    				validAnswerBc=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : length");
	    					validAnswerBc=false;
	    				}
	    	    		
	    	    		//heightBc
	    	    		String h1Bc = heightBc.getText();

	    	    		try{
	    	    			h2Bc = Double.parseDouble(h1Bc);
	    	    			if(h2Bc < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of height must be positive");
	    	    				validAnswerBc=false;
	    	    			}
	    	    			
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : height");
	    					validAnswerBc=false;
	    				}
	    	    		
	    	    		//widthBc
	    	    		String w1Bc = widthBc.getText();

	    	    		try{
	    	    			w2Bc = Double.parseDouble(w1Bc);
	    	    			if(w2Bc < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of width must be positive");
	    	    				validAnswerBc=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : width");
	    					validAnswerBc=false;
	    				}
	    		    }
	    		    
	    		    if(validAnswerBc){
	    		    	
	    		    	LibraryFactory libF = new LibraryFactory();
	    		    	try{
			    			libF.add_bookcase(library, roomNameBc.getText(), numS2, bcNameBc.getText(),l2Bc, h2Bc, w2Bc);
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "The bookcase "+bcNameBc.getText()+" was successful added to the room "+roomNameBc.getText());
			    		}
			    		catch(AlreadyExistsException e){
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "A bookcase of the name "+roomNameBc.getText()+" already exists in this room.");
			    		}
	    		    	catch(NoSuchFieldException e){
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "No room of the name "+roomNameBc.getText()+" exists in this library");
	    		    	}
	    		    	
		    		}
	    			
	    			break;
	    		//end addBc
	    			
	    			
	    			
	    		//beginning addItem
	    		case addItem :
	    			boolean validAnswerItem = false;
	    			JPanel myPanelItem = new JPanel();
	    			myPanelItem.setLayout(new GridLayout(10,2));

	    			JTextField title = new JTextField(20);
	    			JTextField author = new JTextField(20);
	    			JTextField publisher = new JTextField(20);
	    			JTextField publishingYear = new JTextField(5);
	    			JTextField consultationType = new JTextField(20);
	    		    JTextField volumeNumber = new JTextField(5);
	    		    JTextField lengthItem = new JTextField(5);
	    		    JTextField heightItem = new JTextField(5);
	    		    JTextField widthItem = new JTextField(5);

	    		    myPanelItem.add(new JLabel("Item type:"));
	    			final String[] addChoice = {"Book","CD","DVD"};
	    			JComboBox<String> comboBox = new JComboBox<String>(addChoice);
	    			comboBox.setMaximumSize(new Dimension(1000, 30));
	    			myPanelItem.add(comboBox);
	    		    
	    			myPanelItem.add(new JLabel("Title:"));
	    		    myPanelItem.add(title);
	    		    myPanelItem.add(new JLabel("Author:"));
	    		    myPanelItem.add(author);
	    		    myPanelItem.add(new JLabel("Publisher:"));
	    		    myPanelItem.add(publisher);
	    		    myPanelItem.add(new JLabel("Publishing year:"));
	    		    myPanelItem.add(publishingYear);
	    		    myPanelItem.add(new JLabel("Consultation type:"));
	    		    myPanelItem.add(consultationType);
	    		    myPanelItem.add(new JLabel("Volume number (put 0 if unique volume):"));
	    		    myPanelItem.add(volumeNumber);
	    		    myPanelItem.add(new JLabel("Length of the item (in cm):"));
	    		    myPanelItem.add(lengthItem);
	    		    myPanelItem.add(new JLabel("Height of the item (in cm):"));
	    		    myPanelItem.add(heightItem);
	    		    myPanelItem.add(new JLabel("Width of the item (in cm):"));
	    		    myPanelItem.add(widthItem);
	    		    
	    		    int publishY2 = 0;
	    		    int volNum2 = 0;
    	    		double l2Item = 0.0;
    	    		double h2Item = 0.0;
    	    		double w2Item = 0.0;

	    		    int resultItem = JOptionPane.showConfirmDialog(null, myPanelItem, 
	    		               "Please enter the parameters of the item", JOptionPane.OK_CANCEL_OPTION);
	    		    if (resultItem == JOptionPane.OK_OPTION) {
	    		    		    		    	
	    		    	
	    
	    	    		//Title
	    	    		if(title.getText().equals("")){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to fill all the fields");
	    		    	}
	    	    		else{
	    	    			validAnswerItem=true;
	    	    		}
	    	    		
	    	    		//Other string results
	    	    		if(author.getText().equals("") || publisher.getText().equals("") || consultationType.getText().equals("") ){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to fill all the fields");
	    		    		validAnswerItem=false;
	    		    	}
	    	    		
	    	    		
	    	    		//Publishing year
	    	    		String publishY1 = publishingYear.getText();
	    	    		try{
	    	    			publishY2 = Integer.parseInt(publishY1);
	    	    			if(publishY2 > 2016){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "An item published in the future? wow. Try again");
	    	    				validAnswerItem=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter an integer in the field : year");
	    					validAnswerItem=false;
	    				}
	    	    		
	    	    		
	    	    		//Volume number
	    	    		String volNum1 = volumeNumber.getText();
	    	    		try{
	    	    			volNum2 = Integer.parseInt(volNum1);
	    	    			if(volNum2 < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The volume number must be positive.");
	    	    				validAnswerItem=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter an integer in the field : volume number");
	    					validAnswerItem=false;
	    				}
	    	    		
	    	    		
	    	    		//length
	    	    		String l1Item = lengthItem.getText();
	    	    		try{
	    	    			l2Item = Double.parseDouble(l1Item);
	    	    			if(l2Item < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of length must be positive");
	    	    				validAnswerItem=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : length");
	    					validAnswerItem=false;
	    				}
	    	    		
	    	    		//heightItem
	    	    		String h1Item = heightItem.getText();

	    	    		try{
	    	    			h2Item = Double.parseDouble(h1Item);
	    	    			if(h2Item < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of height must be positive");
	    	    				validAnswerItem=false;
	    	    			}
	    	    			
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : height");
	    					validAnswerItem=false;
	    				}
	    	    		
	    	    		//widthItem
	    	    		String w1Item = widthItem.getText();

	    	    		try{
	    	    			w2Item = Double.parseDouble(w1Item);
	    	    			if(w2Item < 0){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "The  value of width must be positive");
	    	    				validAnswerItem=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter a number in the field : width");
	    					validAnswerItem=false;
	    				}
	    		    }
	    		    
	    		    if(validAnswerItem){
	    		    	
	    		    	LibraryFactory libF = new LibraryFactory();
	    		    	
	    	    		//Item Type
	    		    	String selectedItem = (String) comboBox.getSelectedItem();
	    	    		
	    	    		
	    	    		
		    		    try{
				    		libF.add_item(author.getText(),title.getText(),selectedItem,volNum2,consultationType.getText(),publisher.getText(),library,publishY2,l2Item,w2Item,h2Item);
				    		JOptionPane.showMessageDialog(LibraryModifyer.this, "The item "+title.getText()+" was successfully added to the Library.");
		    		    }
		    		   	catch(IllegalArgumentException e){
		    		    	JOptionPane.showMessageDialog(LibraryModifyer.this, "The consultation type is either borrowing or online consultation .");
		    		    	validAnswerItem = false;
		    		    }
	    	    				  		    	
	    		    	
	    		    }
	    			break;	
	    		//end addItem
	    		
	    			
	    			
	    		//beginning add member
	    		case addMember :
	    			boolean validAnswerMember = false;
	    			JPanel myPanelMember = new JPanel();
	    			myPanelMember.setLayout(new BoxLayout(myPanelMember,BoxLayout.PAGE_AXIS));
	    			JTextField nameMember = new JTextField(20);
	    		    JTextField surnameMember = new JTextField(20);
	    		    JTextField ccNumber = new JTextField(16);
	    		    JTextField birthdate = new JTextField(10);
	    			myPanelMember.add(new JLabel("Name:"));
	    		    myPanelMember.add(nameMember);
	    		    myPanelMember.add(new JLabel("Surname:"));
	    		    myPanelMember.add(surnameMember);
	    		    myPanelMember.add(new JLabel("Credit Card number:"));
	    		    myPanelMember.add(ccNumber);
	    		    myPanelMember.add(new JLabel("Birthdate (dd/mm/yyyy):"));
	    		    myPanelMember.add(birthdate);
	    		    
    	    		int ccNum2 = 0;


	    		    int resultMember = JOptionPane.showConfirmDialog(null, myPanelMember, 
	    		               "Please enter the parameters of the room", JOptionPane.OK_CANCEL_OPTION);
	    		    if (resultMember == JOptionPane.OK_OPTION) {
	    		    		    		    	
	    		    	
	    	    		//Member name
	    	    		if(nameMember.getText().equals("")){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to fill all the fields.");
	    		    	}
	    	    		else{
	    	    			validAnswerMember = true;
	    	    		}
	    	    		
	    	    		//Other text fields
	    	    		if(surnameMember.getText().equals("")||birthdate.getText().equals("")){
	    		    		JOptionPane.showMessageDialog(LibraryModifyer.this, "You have to fill all the fields.");
	    		    		validAnswerMember = false;
	    		    	}

	    	    		
	    	    		//length
	    	    		String ccNum1 = ccNumber.getText();
	    	    		try{
	    	    			ccNum2 = Integer.parseInt(ccNum1);
	    	    			if(ccNum1.length() != 10){
	    	    				JOptionPane.showMessageDialog(LibraryModifyer.this, "A credit card number has 10 numbers");
	    	    				validAnswerMember=false;
	    	    			}
	    	    		}
	    				catch(Exception NumberFormatException){
	    					JOptionPane.showMessageDialog(LibraryModifyer.this, "You did not enter an integer in the field : credit card");
	    					validAnswerMember=false;
	    				}
	    	    		

	    		    }
	    		    
	    		    if(validAnswerMember){
	    		    	
	    		    	LibraryFactory libF = new LibraryFactory();
	    		    	try{			    			
			    			libF.add_member(library, nameMember.getText(), surnameMember.getText(), ccNum2, birthdate.getText());
				   			JOptionPane.showMessageDialog(LibraryModifyer.this, "The member "+nameMember.getText()+" was successful added to the Library");
					
	    		    	}	
			    		catch(AlreadyExistsException e){
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "A member of these parameters already exists in this library.");
			    		}
	    		    	catch(IllegalArgumentException e){
			    			JOptionPane.showMessageDialog(LibraryModifyer.this, "Your date does not respect the pattern dd/mm/yyyy.");
		    			}
	    		    	
		    			
		    		}
	    			break;
	    			
	    		}
	    		//end add member
	    
	            }
	        });
	    
	    panelAdd.add(buttonNextAdd);

	    
	    
		/*
		 * Tab : List Panel
		 */
		
		
		JPanel panelList = new JPanel();
		tabbedPane.addTab("List panel", panelList);
		panelList.add(new JLabel("List information"));
		this.setContentPane(tabbedPane);
		
		/*
		 * Tab : Move items
		 */
		
		JPanel panelMove = new JPanel();
		tabbedPane.addTab("Move items", panelMove);
		panelMove.add(new JLabel("Move information"));
		this.setContentPane(tabbedPane);
		
		
		
		pack();
		setVisible(true);
	}

}
