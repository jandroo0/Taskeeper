package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gui.Task;

public class Database {
	private List<Task> tasks;

	private String filePath = "tasks.csv";

	private Connection con;

	private int port;
	private String hostName = "127.0.0.1";
	private String databaseUser = "root";
	private String databasePassword = "sparkles32";

	public Database() {
		tasks = new ArrayList<Task>();

	}

	public List<Task> getTasks() {
		return tasks;
	}

// database

	// create database
	public void createDatabase() throws Exception {
		connect();

		String createDatabaseSQL = "create database taskeeper";
		String createTableSQL = "create table tasks(task text, dueDate text)";

		Statement statement = con.createStatement();
		statement.execute(createDatabaseSQL);
		selectDatabase();
		statement.execute(createTableSQL);

		statement.close();
		
		System.out.println("Database Created");
	}

	// connect to database
	public void connect() throws SQLException {
		if (con != null) {
			// mesg
			return;
		} else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			// String conUrl = "jdbc:mysql://" + hostName + ":" + port + "/" + "?autoReconnect=true&useSSL=false";
			// con = DriverManager.getConnection(conUrl, databaseUser, databasePassword);

			con = DriverManager.getConnection(
					"jdbc:mysql://" + hostName + ":" + 3306 + "/?user=" + databaseUser + "&password=" + databasePassword
							+ "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		}

		System.out.println("Connected : " + con);
	}

	// disconnect from database
	public void disconnect() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Could not close connections");
			}
			System.out.println("Disconnected...");
		}
	}

	// load tasks from database
	public void loadFromDB() throws SQLException {
		// clear tasks list
		tasks.clear();

		selectDatabase();

		String selectSQL = "select * from tasks";
		Statement selectStatement = con.createStatement();

		ResultSet results = selectStatement.executeQuery(selectSQL);

		while (results.next()) {
			String task = results.getString("task");
			String dueDate = results.getString("dueDate");

			Task newTask = new Task(task, dueDate);

			tasks.add(newTask);
		}

		results.close();

		selectStatement.close();
		
		System.out.println("Tasks loaded from DB");

	}

	// add task to database
	public void addTaskDB(Task task) throws SQLException {
		selectDatabase();

		// sql insert
		String insertSQL = "insert into tasks(task, dueDate) values (?,?)";
		PreparedStatement insertStatement = con.prepareStatement(insertSQL);

		String taskString = task.getTask();
		String dueDateString = task.getDueDate();

		insertStatement.setString(1, taskString);
		insertStatement.setString(2, dueDateString);

		insertStatement.executeUpdate();

		insertStatement.close();
		
		System.out.println("DB + " + task);
	}

	// remove task from database
	public void removeTaskDB(Task task) throws SQLException {
		selectDatabase();

		String removeSQL = "delete from tasks where task=?";
		PreparedStatement removeStatement = con.prepareStatement(removeSQL);

		String taskString = task.getTask();

		removeStatement.setString(1, taskString);

		removeStatement.executeUpdate();

		removeStatement.close();
		
		System.out.println("DB - " + task);
	}

	// select database sql statement
	private void selectDatabase() throws SQLException {
		String useDatabaseSQL = "use taskeeper";
		Statement statement = con.createStatement();
		statement.execute(useDatabaseSQL);

		statement.close();
	}

// from file

	// create tasks file
	public void createFile() {
		try {
			File newFile = new File(filePath);

			if (newFile.createNewFile()) {
				System.out.println("File Created " + newFile.getName());
			} else {
				System.out.println("File Creation Failed");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	 load tasks from file
	public void loadFromFile() throws FileNotFoundException {
		tasks.clear();

		File file = new File(filePath);

		try {
			Scanner reader = new Scanner(file);
			String row;

			while (reader.hasNextLine()) {
				row = reader.nextLine();

				String[] tasksString = row.split(",");

				Task task = new Task(tasksString[0], tasksString[1]);

				tasks.add(task);
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("Tasks loaded from file");
	}

	// add task to tasks file
	public void addToFile(Task task) throws FileNotFoundException {
		File file = new File(filePath); // write to tasks file

		try {
			FileWriter writer = new FileWriter(file, true);

			String appendedTask = task.getTask() + "," + task.getDueDate() + "\n";

			writer.append(appendedTask);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("File + " + task);
	}

	// remove task from tasks file
	public void removeFromFile(Task task) throws FileNotFoundException {
		tasks.remove(task);

		File file = new File(filePath);

		try {
			FileWriter writer = new FileWriter(file);

			for (Task tsk : tasks) {
				String appendedTask = tsk.getTask() + "," + tsk.getDueDate() + "\n";

				writer.append(appendedTask);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("File - " + task);
	}

}
