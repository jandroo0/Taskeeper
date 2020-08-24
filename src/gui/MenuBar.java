package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar {
	private JMenu preferencesMenu;
	private JCheckBoxMenuItem useDatabase;
	
	public MenuBar(MainFrame frame) {
		
		// sub menus
		preferencesMenu = new JMenu("Preferences");
		
		// preferences menu items
		useDatabase = new JCheckBoxMenuItem("Use Database");
		
		preferencesMenu.add(useDatabase);
		
		useDatabase.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setUseDatabase(useDatabase.getState());
			}
			
		});
		
		add(preferencesMenu);
	}

}
