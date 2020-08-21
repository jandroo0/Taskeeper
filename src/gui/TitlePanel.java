package gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitlePanel extends JPanel{
	
	private JLabel titleLabel;
	
	public TitlePanel() {
		
		titleLabel = new JLabel("Taskeeper");
		
		setLayout(new FlowLayout());
		
		add(titleLabel);
		
	}

}
