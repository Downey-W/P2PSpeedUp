package jsonObj;

import TaskPool.Task;

/**
 * 
 * @author Downey
 * User Gson to parse Json
 */
public class TaskInfoObj {
	
	private Task task;
	private String path;
	
	
	public TaskInfoObj(Task task,String path)
	{
		this.task = task;
		this.path = path;
	}


	public Task getTask() {
		return task;
	}


	public void setTask(Task task) {
		this.task = task;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}
	
	
}

