package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	
	private static final int WIDTH = 800, HEIGHT = 500;
	
	private TitlePanel titlePanel;
	private FormPanel formPanel;
	private TaskPanel taskPanel;
	
	public MainFrame() {
		super("Taskeeper alpha");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		titlePanel = new TitlePanel();
		formPanel = new FormPanel();
		taskPanel = new TaskPanel();
		
		layoutComponents();
		
	}
	
	private void layoutComponents() {
		setLayout(new BorderLayout());
		
		add(titlePanel, BorderLayout.NORTH);
		add(formPanel, BorderLayout.CENTER);
	}

}
