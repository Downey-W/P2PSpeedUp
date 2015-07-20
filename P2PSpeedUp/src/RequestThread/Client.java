package RequestThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.UnknownHostException;

import net.ChangeState;
import jsonObj.TaskInfoObj;

import com.google.gson.Gson;

import TaskPool.Task;
import constants.Constant;

/**
 * 
 * @author Downey
 * According to each avaliable server,new a Client thread to request the resouce of the avaliable sever
 */
public class Client extends Thread{
	
	Gson gson = new Gson();
	private String ip;
	private int port;
	private String savePath;
	private String movieName;
	private Socket s = null;
	private DataOutputStream dos = null;
	private DataInputStream dis = null;
	private RandomAccessFile raf = null;
	
	public Client(String ip,int port,String savePath,String movieName)
	{
		this.ip = ip;
		this.port = port;
		this.savePath = savePath;
		this.movieName = movieName;
		start();

	}
	
	public void run()
	{
		long start = 0;
		long end = 0;
		long receiveBytes = 0;
		try {
			//request a server
			s = new Socket(ip,port);
			while(true)
			{
				//get a task from taskPool util null.
				Task task = Constant.taskPool.getTask();
				if(task != null)
				{
					//start ,end of the task
					start = task.getStart();
					end = task.getEnd();
					dos = new DataOutputStream(s.getOutputStream());
					dis = new DataInputStream(s.getInputStream());
					//tell the server what to do 
					dos.writeUTF(gson.toJson(new TaskInfoObj(task,savePath+movieName)));
					
					//save resource
					File download = new File(Constant.path+movieName);
					raf = new RandomAccessFile(download, "rw");
					raf.seek(start);
					byte[] buffer = new byte[1024];
					int size = 0;
					while((size = dis.read(buffer, 0, buffer.length)) > 0)
					{
						if(size < 1024)
						{	
							//when the each transportation is done,send Client the end mark
							String endMark = new String(buffer,0,size);
							if(endMark.equals("end")){
								break;
							}
						}
						raf.write(buffer, 0, size);
						receiveBytes += size;
					}
					//reset receiveBytes
					receiveBytes = 0;
					raf.close();
				}else
				{
					//tell the server that there is no more task,and server can release
					dos.writeUTF("Done");
					//reset the taskPool
					Constant.taskPool = null;
					break;
				}
				
				
			}
		
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		} catch (IOException e) {
			// server may disconnect suddenly,record the receiveBytes, and add 
			//into taskPool as a new Task 
			//(with new star)
			if(receiveBytes != (end - start))
			{
				Constant.taskPool.addTask(new Task(start+receiveBytes,end));
			}
		}finally
		{
			try {
				if(dis != null)
				{
					dis.close();
				}
				if(dos != null)
				{
					dos.close();
				}
				if(s != null)
				{
					s.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
