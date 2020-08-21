package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JTextPane;

public class TaskPane extends JTextPane {
	
	public TaskPane( ) {
		setPreferredSize(new Dimension(700,200));
		setEditable(false);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),BorderFactory.createEmptyBorder(0, 0, 0, 0)));
		
	}

}
