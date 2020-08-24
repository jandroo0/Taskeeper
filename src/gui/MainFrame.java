package gui;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame {

	private static final int WIDTH = 500, HEIGHT = 550;
	
	private MenuBar menuBar;

	private TitlePanel titlePanel;
	private FormPanel formPanel;
	TaskPanel taskPanel;

	private Controller controller;
	
	private Boolean useDatabase;

	public MainFrame() {
		super("Taskeeper alpha");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
		
		menuBar = new MenuBar(this);
		titlePanel = new TitlePanel();
		formPanel = new FormPanel();
		taskPanel = new TaskPanel();

		controller = new Controller(); // controller for database

		setJMenuBar(menuBar);
		layoutComponents();

		formPanel.setFormListener(new FormListener() {

			@Override
			public void inputTaskEvent(Task task) {
				addTask(task); // add task to file and db
			}

			@Override
			public void removeTaskEvent(String task) {
				Task removedTask = null;

				for (Task tsk : controller.getTasks()) {
					if (task.equals(tsk.getTask())) {
						removedTask = tsk;
					}
				}

				removeTask(removedTask);
			}
		});
		
		connect();
		loadTasks();

	}

	// connect to database
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
//			showOptionPane() 
//			return;
			try {
				controller.createDatabase();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	// load tasks
	private void loadTasks() {
		// load from database
		try {
			controller.loadFromDB();
		} catch (Exception e) {
			// showOptionPane()
			try {
				controller.createDatabase();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return;
		}

		// load from file
		try {
			controller.loadFromFile();
		} catch (FileNotFoundException e) {
			controller.createFile();
		}

		formPanel.updateTaskDisplay(controller.getTasks());
	}

	// add task to tasks file
	private void addTask(Task task) {
		connect(); // connect to database

		// add to database
		try {
			controller.addTaskToDB(task);
		} catch (SQLException e) { // Likely due to no database being found, if case create database and table.
									// then add task
			try {
				controller.createDatabase();
				controller.addTaskToDB(task);
			} catch (Exception e1) {
				e.printStackTrace();
			}
		} 
		// mysql syntax error exception

		// add task to file
		try {
			controller.addTaskToFile(task);
		} catch (FileNotFoundException e) {
			controller.createFile();
		}

		controller.getTasks().add(task);
		formPanel.updateTaskDisplay(controller.getTasks()); // update text pane and remove task box
	}

	// remove task from tasks file
	private void removeTask(Task task) {
		
		// remove task from file
		try {
			controller.removeTaskFromFile(task);
		} catch (FileNotFoundException e) {
			controller.createFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// remove task from database
		try {
			controller.removeTaskFromDB(task);
		} catch(SQLException e) {
			// showOptionPane
			e.printStackTrace();
			return;
		}
		
		formPanel.updateTaskDisplay(controller.getTasks()); // update text pane and remove task box
	}

	private void layoutComponents() {
		setLayout(new BorderLayout());

		add(titlePanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
	}
	
	public void setUseDatabase(Boolean useDatabase) {
		this.useDatabase = useDatabase;
	}

}
