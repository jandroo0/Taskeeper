package gui;

public class Task {
	
	private String task, teacher, due;
	private int priority;
	
	public Task(String task) {
		this.task = task;
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

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	

}
