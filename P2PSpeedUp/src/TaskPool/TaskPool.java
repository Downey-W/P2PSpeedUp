package TaskPool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskPool {
	
	public List<Task> tasks = new ArrayList<Task>();
	
	//try to equal each task , or the last task is less
	public TaskPool(long size,int amount)
	{
		long average = size/amount;
		if((average*amount) == size)
		{
			for(int i=0;i<amount;i++)
			{
				tasks.add(new Task(i*average,(i+1)*average));
			}
		}else
		{
			for(int i=0;i<amount;i++)
			{
				tasks.add(new Task(i*average,(i+1)*average));
			}
			tasks.add(new Task(average*amount,size+1));
		}
	}
	
	//get a random task from taskPool
	public synchronized Task getTask()
	{
		int task_amount = tasks.size();
		if(task_amount != 0)
		{
			Random r = new Random();
			int position = r.nextInt(task_amount);
			Task task = tasks.remove(position);
			return task;
		}else
		{
			return null;
		}
	}
	
	//add a task
	public synchronized void addTask(Task task)
	{
		tasks.add(task);
	}
}
