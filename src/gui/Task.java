package gui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Task {
	
	private String task, teacher;
	private int priority;
	private LocalDate dueDate;
	
	public Task(String task, LocalDate dueDate) {
		this.task = task;
		this.dueDate = dueDate;
//		this.teacher = teacher;
//		this.due = due;
//		this.priority = priority;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public String toString() {
		return task + " - " + dueDate.getDayOfWeek() + " " + dueDate.getMonth() + " " + dueDate.getDayOfMonth();
	}
	
	

}
