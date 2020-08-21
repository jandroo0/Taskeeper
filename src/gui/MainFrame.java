package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.Controller;

public class MainFrame extends JFrame {
	
	private static final int WIDTH = 600, HEIGHT = 500;
	
	private TitlePanel titlePanel;
	private FormPanel formPanel; TaskPanel taskPanel;
	
	private Controller controller;
	
	public MainFrame() {
		super("Taskeeper alpha");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(WIDTH-450, HEIGHT-150));
		setVisible(true);
		
		titlePanel = new TitlePanel();
		formPanel = new FormPanel();
		taskPanel = new TaskPanel();
		
		controller = new Controller(); // controller for database 

		
		layoutComponents();
		
		formPanel.setFormListener(new FormListener() {

			@Override
			public void inputTask(Task task) {
				controller.addTask(task);
				formPanel.setTaskPaneText(controller.getTasks());
				
			}
			
		});
		
	}
	
	private void layoutComponents() {
		setLayout(new BorderLayout());
		
		add(titlePanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
	}

}
