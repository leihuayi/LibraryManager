package graphicalUserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;
import lms.*;




public class GraphicalInterface extends JFrame{
	
	
	
	
	GraphicalInterface(){
		
		super("Library Management System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new GridLayout(3,1));
		
		JPanel panelExp = new JPanel();
		panelExp.setLayout(new BoxLayout(panelExp,BoxLayout.PAGE_AXIS));
		add(panelExp);
		JLabel explicationContent1 = new JLabel("Welcome to Cocher&Gross' Library Management System.");
		JLabel explicationContent2 = new JLabel("With this graphical interface, you will be able to have access to a library and make many modifications to it !");
		panelExp.add(explicationContent1);
		panelExp.add(explicationContent2);
		
		
		JPanel panelLib = new JPanel();
		panelLib.setLayout(new BoxLayout(panelLib,BoxLayout.PAGE_AXIS));
		add(panelLib);
		JLabel questionLibrary = new JLabel("On which Library would you like to work?");
		JRadioButton radioButtonFetch = new JRadioButton("Fetch an already existing Library");
		JRadioButton radioButtonNew = new JRadioButton("Create a new Library", true);
		
	    ButtonGroup buttonGroup  = new ButtonGroup();
	    buttonGroup.add(radioButtonFetch);
        buttonGroup.add(radioButtonNew);
        
		panelLib.add(questionLibrary);
		panelLib.add(radioButtonFetch);
		panelLib.add(radioButtonNew);
		
		
		JPanel panelButton = new JPanel();
		add(panelButton);
		
		JButton buttonNext = new JButton("Next");
	    buttonNext.addActionListener(new ActionListener(){
	  
	    	@Override
	        public void actionPerformed(ActionEvent ae) {
	                 
	                if (radioButtonFetch.isSelected()){
	                	
	                	//open a file chooser window
	                	JFrame dialogFrame = new JFrame();
	                	JFileChooser fileChooser = new JFileChooser();
	                	int returnVal = fileChooser.showOpenDialog(dialogFrame);
	                	
	                	if (returnVal == JFileChooser.APPROVE_OPTION) {
	                		//we get the name of the file in order to use fetchLibrary from Serialization class
	                        String libraryName = fileChooser.getSelectedFile().getName();
	                        libraryName = libraryName.substring(0,libraryName.lastIndexOf("."));
	                        
	                        Serialization ser = new Serialization();
	                        //fetching of the serialized library
	                        try{
	                        	Library library = ser.fetchLibrary(libraryName);
		                        JOptionPane.showMessageDialog(GraphicalInterface.this,"The fetching of library "+libraryName+" was a success.");
		                        new LibraryModifyer(library);
	                        }
	                        catch(IOException i){
	                        	JOptionPane.showMessageDialog(GraphicalInterface.this,"Whoops there must have been a bug in the retrieval. Please try again.");
	                        }
	                        
	    	    			
	                    } else {
	                    	JOptionPane.showMessageDialog(GraphicalInterface.this,"Open command cancelled by user.");
	                    }
	                	
	                	
	            		pack();
	            		setVisible(true);
	                }
	                
	                
	                
	                if (radioButtonNew.isSelected()){
	                	new LibraryCreationInterface();
	                }
	                 
	             
	            }
	        });
	    
	    panelButton.add(buttonNext);
        
		pack();
		setVisible(true);
	}
	


	public static void main(String[] arguments){
		new GraphicalInterface();
		System.out.println("FIN");
	}
}
