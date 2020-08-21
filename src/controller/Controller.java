package controller;

import java.util.List;

import gui.Task;
import model.Database;

public class Controller {
	
	Database db = new Database();
	
	public List<Task> getTasks() {
		 return db.getTasks();
	}
	
	public void addTask(Task task) {
		db.addTask(task);
	}
	
	public void removeTask(Task task) {
		db.removeTask(task);
	}
	
	

}
