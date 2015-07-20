package ServerThread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import jsonObj.AvaliableServerObj;
import jsonObj.TaskInfoObj;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import constants.Constant;

public class Server extends Thread{
	
	Gson gson = new Gson(); 
	private int port;
	private ServerSocket ss ;
	private Socket s;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	private RandomAccessFile raf = null;
	
	public Server(int port)
	{
		this.port = port;
		start();
	}
	
	public void run()
	{
		try {
			ss = new ServerSocket(port);
			while(true)
			{
				//accept client constantly
				s = ss.accept();
				//handle a clienet
				handleClient(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			try {
				ss.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void handleClient(Socket s)
	{
		try {
			dis = new DataInputStream(s.getInputStream());
			//read a task util Done
			while(true)
			{
				//begin to read a task from stream 
				String taskInfo = dis.readUTF();
				if(!taskInfo.equals("Done"))
				{
					//parse task json
					Type type = new TypeToken<TaskInfoObj>() {}.getType();  
					TaskInfoObj taskInfoObj = gson.fromJson(taskInfo, type);
					
					long start = taskInfoObj.getTask().getStart();
					long end = taskInfoObj.getTask().getEnd();
					String path = taskInfoObj.getPath();
					
					//find the resource path in PC
					File target = new File(path);
					if(target.exists())
					{
						dos = new DataOutputStream(s.getOutputStream());
						raf = new RandomAccessFile(target, "rw");
						raf.seek(start);
						byte[] buffer = new byte[1024];
						long amount = (end - start)/1024;
						int count = 0;
						int size = 0;
						while(count < amount && (size = raf.read(buffer, 0, buffer.length)) > 0)
						{
							count++;
							dos.write(buffer, 0, size);
							dos.flush();
						}
						
						if((amount*1024) != (end-start))
						{	
							//the bytes left
							raf.read(buffer, 0, buffer.length);
							dos.write(buffer, 0, (int)((end-start)-(amount*1024)));
							dos.flush();
						}
						//send to client end mark
						byte[] endMark = new String("end").getBytes();
						dos.write(endMark, 0, endMark.length);
						dos.flush();
						raf.close();
					}else
					{
						//may refresh db in centrl server 
						break;
					}
				}else
				{	
					// finish a task
					break;
				}
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
