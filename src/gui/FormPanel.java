package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class FormPanel extends JPanel {

	private JButton addTaskButton;
	private JTextField addTaskField;
	private TaskPane taskPane;

	private JPanel topPanel;
	private JPanel bottomPanel;

	private UtilDateModel dueDateModel;
	private JDatePanelImpl dueDatePanel;
	private JDatePickerImpl dueDatePicker;

	private FormListener formListener;

	public FormPanel() {

		// initialization

		addTaskField = new JTextField();
		addTaskButton = new JButton("+");

		taskPane = new TaskPane();

		topPanel = new JPanel();
		bottomPanel = new JPanel();

		addTaskField.setColumns(14); // text field size

		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");

		dueDateModel = new UtilDateModel();
		dueDatePanel = new JDatePanelImpl(dueDateModel, p);
		dueDatePicker = new JDatePickerImpl(dueDatePanel, new DateLabelFormatter());

		dueDatePicker.getComponent(0).setPreferredSize(new Dimension(145, 27)); // JFormattedTextField
		dueDatePicker.getComponent(1).setPreferredSize(new Dimension(30, 33)); // JButton

		// action listener
		addTaskButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addTaskAction();
			}

		});

		// enter listener
		addTaskField.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					addTaskAction();
				}
			}
		});

		layoutComponents();
	}

	public void setFormListener(FormListener listener) {
		this.formListener = listener;
	}

	public void setTaskPaneText(List<Task> tasks) {
		taskPane.updateTaskDisplay(tasks); // update task display list with new task list
	}

	public void addTaskAction() {
		String addedTask = addTaskField.getText();
		LocalDate dueDate = (LocalDate) ((Date) dueDatePicker.getModel().getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // converts date from date picker to local date
		
		String dueDateString = dueDate.getDayOfWeek().toString().substring(0, 3) + " " + dueDate.getMonth().toString().substring(0, 3) + " " + dueDate.getDayOfMonth(); // converts local date to readable string format: "TUE AUGUST 24"

		
		Task task = new Task(addedTask, dueDateString); // creates new task

		if (formListener != null) {
			formListener.inputTask(task); // sends new task to form listener

		}

		addTaskField.setText(""); // sets add taskfield blank
		JTextField dueDateTextField = (JTextField) dueDatePicker.getComponent(0);
		dueDateTextField.setText("");
	}

	private void layoutComponents() {
		// top panel

		topPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();

		// first row
		gc.anchor = GridBagConstraints.LINE_START;

		gc.gridy = 0;
		gc.gridx = 0;

		// add task field
		topPanel.add(addTaskField, gc);

		// next row
		gc.gridy++;
		gc.gridx = 0;

		// due date picker
		topPanel.add(dueDatePicker, gc);

		// next row
		gc.gridy++;
		gc.gridx = 0;

		// add task button
		gc.anchor = GridBagConstraints.CENTER;
		topPanel.add(addTaskButton, gc);

		// bottom panel

		bottomPanel.setLayout(new BorderLayout());
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,25,25,25));
		bottomPanel.add(taskPane, BorderLayout.CENTER);

		// form panel

		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(topPanel);
		add(Box.createVerticalGlue());
		add(bottomPanel);
	}

}
