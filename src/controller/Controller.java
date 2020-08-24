package controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import gui.Task;
import model.Database;

public class Controller {
	
	Database db = new Database();
	
	public List<Task> getTasks() {
		 return db.getTasks();
	}
	
	// from database--
	public void createDatabase() throws Exception {
		db.createDatabase();
	}
	
	public void loadFromDB() throws SQLException {
		db.loadFromDB();
	}
	
	public void connect() throws SQLException {
		db.connect();
	}
	
	public void disconnect() {
		db.disconnect();
	}
	
	public void addTaskToDB(Task task) throws SQLException {
		db.addTaskDB(task);
	}
	
	public void removeTaskFromDB(Task task) throws SQLException {
		db.removeTaskDB(task);
	}
	
	// from file--
	public void createFile() {
		db.createFile();
	}
	
	public void loadFromFile() throws FileNotFoundException{
		db.loadFromFile();
	}
	
	public void addTaskToFile(Task task) throws FileNotFoundException {
		db.addToFile(task);
	}
	
	public void removeTaskFromFile(Task task) throws FileNotFoundException {
		db.removeFromFile(task);
	}
	
}
