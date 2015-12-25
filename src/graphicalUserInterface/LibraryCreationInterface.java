package graphicalUserInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.*;

public class LibraryCreationInterface extends JFrame {
	
	LibraryCreationInterface(){
		super("Creation of a Library");
		
		
		setLayout(new GridLayout(4,1));
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2,2));
		panel.setBorder(BorderFactory.createTitledBorder("General settings of the Library"));
		add(panel);
		
		JLabel labelLibName = new JLabel("Library name :");
		panel.add(labelLibName);
		JTextField textLibName = new JTextField("",20);
		panel.add(textLibName);
		
		JLabel labelLibNbi = new JLabel("Maximum items a member can borrow :");
		panel.add(labelLibNbi);
		JTextField textLibNbi = new JTextField("",20);
		panel.add(textLibNbi);
		
		
		
		JPanel panelbis = new JPanel();
		panelbis.setLayout(new BoxLayout(panelbis,BoxLayout.PAGE_AXIS));
		add(panelbis);
		panelbis.add(Box.createVerticalStrut(20)); //A little space for a more pretty window
		JLabel labelExplication1 = new JLabel("In your library, members have different fidelity cards.");
		JLabel labelExplication2 = new JLabel("If they borrow more than N books over M months, they become frequent members and therefore have privileges.");
		JLabel labelExplication3 = new JLabel("If they borrow fewer than N books over M' months, they lose their frequent membership and become standard members again.");
		panelbis.add(labelExplication1);
		panelbis.add(labelExplication2);
		panelbis.add(labelExplication3);
		
		
		JPanel panelter = new JPanel();
		panelter.setLayout(new GridLayout(3,2));
		panelter.setBorder(BorderFactory.createTitledBorder("Fidelity settings of the Library"));
		add(panelter);
		
		JLabel labelLibN = new JLabel("N :");
		panelter.add(labelLibN);
		JTextField textLibN = new JTextField("",20);
		panelter.add(textLibN);
		
		JLabel labelLibM = new JLabel("M :");
		panelter.add(labelLibM);
		JTextField textLibM = new JTextField("",20);
		panelter.add(textLibM);
		
		JLabel labelLibMp = new JLabel("M' :");
		panelter.add(labelLibMp);
		JTextField textLibMp = new JTextField("",20);
		panelter.add(textLibMp);
		
		
		
		JPanel panelButton = new JPanel();
		add(panelButton);
		JButton buttonCreate = new JButton("Create");
	    buttonCreate.addActionListener(new ActionListener(){
	    	

	  
	    	@Override
	        public void actionPerformed(ActionEvent ae) {
				//checks that the user enters the right parameters
				boolean validAnswer = false;
	    		
	    		String libName = textLibName.getText();
	    		if(libName.equals("")){
	    			JOptionPane.showMessageDialog(LibraryCreationInterface.this, "You need to enter a library name");
	    		}
	    		else{
	    			validAnswer = true;
	    		}
	    		
	    		String libNbi = textLibNbi.getText();
	    		int nbi = 0;
	    		try{
	    			nbi = Integer.parseInt(libNbi);
	    			if(nbi < 0){
	    				JOptionPane.showMessageDialog(LibraryCreationInterface.this, "The number of maximum items must be positive");
	    				validAnswer=false;
	    			}
	    		}
				catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(LibraryCreationInterface.this, "You did not enter an integer in the field : maximum items");
					validAnswer=false;
				}
	    		
	    		
	    		String libN = textLibN.getText();
	    		int n=0;
	    		try{
	    			n = Integer.parseInt(libN);
	    			if(n < 0){
	    				JOptionPane.showMessageDialog(LibraryCreationInterface.this, "The value of N must be positive");
	    				validAnswer=false;
	    			}
	    		}
				catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(LibraryCreationInterface.this, "You did not enter an integer in the field : N");
					validAnswer=false;
				}
	    		
	            
	    		String libM = textLibM.getText();
	    		int m = 0;
	    		try{
	    			m = Integer.parseInt(libM);
	    			if(m < 0){
	    				JOptionPane.showMessageDialog(LibraryCreationInterface.this, "The  value of M must be positive");
	    				validAnswer=false;
	    			}
	    		}
				catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(LibraryCreationInterface.this, "You did not enter an integer in the field : M");
					validAnswer=false;
				}
	    		
	    		
	    		String libMp = textLibMp.getText();
	    		int mp=0;
	    		try{
	    			mp = Integer.parseInt(libMp);
	    			if(mp < 0){
	    				JOptionPane.showMessageDialog(LibraryCreationInterface.this, "The  value of M' must be positive");
	    				validAnswer=false;
	    			}
	    		}
				catch(Exception NumberFormatException){
					JOptionPane.showMessageDialog(LibraryCreationInterface.this, "You did not enter an integer in the field : M'");
					validAnswer=false;
				}
	               
	    		if(validAnswer){
	    			LibraryFactory libF = new LibraryFactory();
	    			Library library = libF.create_library(libName, nbi, n, m, mp);
	    			JOptionPane.showMessageDialog(LibraryCreationInterface.this, "The creation of the Library "+libName+" was successful !");
	    			new LibraryModifyer(library);
	    			dispose();
	    		}
	    		
	             
	            }
	        });
	    
	    panelButton.add(buttonCreate);
		
		
		pack();
		setVisible(true);
	}
	
	
}
