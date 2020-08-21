package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class FormPanel extends JPanel{
	
	private JButton addTaskButton;
	private JTextField addTaskField;
	private TaskPane taskPane;
	
	public FormPanel() {
		addTaskField = new JTextField();
		addTaskButton = new JButton("+");
		
		taskPane = new TaskPane();
		
		addTaskField.setColumns(14);
		
		setLayout(new FlowLayout(10));
		
		add(addTaskField);
		add(addTaskButton);
		add(taskPane);
	}

}
