package model;

import java.util.ArrayList;
import java.util.List;

import gui.Task;

public class Database {
	private List<Task> tasks;
	
	public Database() {
		tasks = new ArrayList<Task>();
		
	}
	
	public List<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	public void removeTask(Task task) {
		tasks.remove(task);
	}

}
