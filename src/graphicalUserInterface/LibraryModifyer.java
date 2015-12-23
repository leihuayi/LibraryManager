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
		
		JPanel panelGeneral = new JPanel();
		tabbedPane.addTab("General information", panelGeneral);
		panelGeneral.add(new JLabel("General information"));
		this.setContentPane(tabbedPane);
		
		JPanel panelGeneral2 = new JPanel();
		tabbedPane.addTab("General information", panelGeneral2);
		panelGeneral2.add(new JLabel("General information"));
		this.setContentPane(tabbedPane);
		
		JPanel panelGeneral3 = new JPanel();
		tabbedPane.addTab("General information", panelGeneral3);
		panelGeneral3.add(new JLabel("General information"));
		this.setContentPane(tabbedPane);
		
		
		
		
		pack();
		setVisible(true);
	}

}
