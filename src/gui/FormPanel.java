package gui;

import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.Controller;

public class FormPanel extends JPanel {

	private JButton addTaskButton;
	private JTextField addTaskField;
	private TaskPane taskPane;

	private JPanel topPanel;
	private JPanel bottomPanel;
	
	private FormListener formListener;
	
	private Controller controller;

	public FormPanel() {
	
		// initialization
		
		addTaskField = new JTextField();
		addTaskButton = new JButton("+");

		taskPane = new TaskPane();

		topPanel = new JPanel();
		bottomPanel = new JPanel();

		addTaskField.setColumns(14); // text field size
		
		controller = new Controller(); // controller for database 

		// action listener
		addTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String addedTask = addTaskField.getText();

				Task task = new Task(addedTask);

				if (formListener != null) {
					formListener.inputTask(task);

				}
			}

		});

		layoutComponents();
	}
	
	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}
	
	public void setTaskPaneText(Task task) {
		controller.addTask(task); // add task to current task list
		taskPane.updateTaskDisplay(controller.getTasks()); // update task display list with new task list 
	}

	private void layoutComponents() {

		// top panel

		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(addTaskField);
		topPanel.add(addTaskButton);

		// bottom panel

		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		bottomPanel.add(taskPane);

		bottomPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// form panel

		setLayout(new FlowLayout());
		add(topPanel);
		add(bottomPanel);
	}

}
