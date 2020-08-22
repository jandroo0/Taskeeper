package controller;

import java.io.FileNotFoundException;
import java.util.List;

import gui.Task;
import model.Database;

public class Controller {
	
	Database db = new Database();
	
	public List<Task> getTasks() {
		 return db.getTasks();
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
