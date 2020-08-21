package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JPanel;

public class TaskPanel extends JPanel{
	
	private TaskPane taskPane;

	
	public TaskPanel() {
		taskPane = new TaskPane();
		
		setLayout(new FlowLayout());
		
		add(taskPane);
	}
}
