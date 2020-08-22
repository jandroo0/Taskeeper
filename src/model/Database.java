package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import gui.Task;

public class Database {
	private List<Task> tasks;
	private String filePath = "tasks.csv";

	public Database() {
		tasks = new ArrayList<Task>();

	}

	public List<Task> getTasks() {
		return tasks;
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
	public void loadFromFile() throws FileNotFoundException{
		tasks.clear();
		
		File file = new File(filePath);
		
		try {
			Scanner reader = new Scanner(file);
			String row;
			
			while(reader.hasNextLine()) {
				row = reader.nextLine();
				
				String[] tasksString = row.split(",");
				
				
				Task task = new Task(tasksString[0], tasksString[1]);
				
				tasks.add(task);
			}
			
			reader.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	// add task to tasks file
	public void addToFile(Task task) throws FileNotFoundException{
		File file = new File(filePath); // write to tasks file
		
		try {
			FileWriter writer = new FileWriter(file, true);
			
			String appendedTask = task.getTask() + "," + task.getDueDate() + "\n";
			
			writer.append(appendedTask);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// remove task from tasks file
	public void removeFromFile(Task task) throws FileNotFoundException{
		tasks.remove(task);
		
		File file = new File(filePath);
		
		try {
			FileWriter writer = new FileWriter(file);
			
			for(Task tsk : tasks) {
				String appendedTask = tsk.getTask() + "," + tsk.getDueDate() + "\n";
				
				writer.append(appendedTask);
			}
			writer.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
