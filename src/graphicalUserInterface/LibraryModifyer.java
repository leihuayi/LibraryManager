package graphicalUserInterface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import lms.*;

public class LibraryModifyer extends JFrame {
	
	LibraryModifyer(Library library){
		super("Action panel for the library "+library.getLibraryName());
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		
		//Tab : General Information
		JPanel panelGeneral = new JPanel();
		tabbedPane.addTab("General information", panelGeneral);
		panelGeneral.setLayout(new BoxLayout(panelGeneral,BoxLayout.PAGE_AXIS));
		panelGeneral.add(new JLabel("Welcome to the panel which enabled you to make actions on your Library !"));
		panelGeneral.add(new JLabel("You have 3 different tabs where you can execute different kinds of actions:"));
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
