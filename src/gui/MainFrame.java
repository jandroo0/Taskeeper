package gui;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame {
	
	private static final int WIDTH = 500, HEIGHT = 550;
	
	private TitlePanel titlePanel;
	private FormPanel formPanel; TaskPanel taskPanel;
	
	private Controller controller;
	
	public MainFrame() {
		super("Taskeeper alpha");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		titlePanel = new TitlePanel();
		formPanel = new FormPanel();
		taskPanel = new TaskPanel();
		
		controller = new Controller(); // controller for database 

		
		layoutComponents();
		
		formPanel.setFormListener(new FormListener() {

			@Override
			public void inputTask(Task task) {
				addTask(task); // add task to file and db
				
				
				formPanel.setTaskPaneText(controller.getTasks()); // update text pane
				
			}
			
		});
		
		loadTasks();
		
	}
	
	private void loadTasks() {
		try {
			controller.loadFromFile();
		} catch(FileNotFoundException e) {
			controller.createFile();
		}
		
		formPanel.setTaskPaneText(controller.getTasks());
	}
	
	// add task to tasks file
	private void addTask(Task task) {
		
		try {
			controller.addTaskToFile(task);
		} catch(FileNotFoundException e) {
			controller.createFile();
		}
		
		controller.getTasks().add(task);
		
		System.out.println("Task added to file");
	}
	
	// remove task from tasks file
	private void removeTask(Task task) {
		try {
			controller.removeTaskFromFile(task);
		} catch(FileNotFoundException e) {
			controller.createFile();
		} catch(Exception e) {
			e.printStackTrace();
		}
		formPanel.setTaskPaneText(controller.getTasks());
	}
	
	private void layoutComponents() {
		setLayout(new BorderLayout());
		
		add(titlePanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
	}

}
