package graphicalUserInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.awt.*;
import javax.swing.*;






public class GraphicalInterface extends JFrame implements ActionListener {
	
	GraphicalInterface(){
		Library library = new Library("yo",2,2,2,2);
		
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	
	}

	public static void main(String[] arguments){
		GraphicalInterface g = new GraphicalInterface();
		System.out.println("FIN");
	}
}
