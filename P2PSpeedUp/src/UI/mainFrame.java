package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import constants.Constant;
import RequestThread.Client;
import ServerThread.Server;
import TaskPool.TaskPool;
import model.AvaliableServer;
import net.ChangeState;
import net.Utils;

/**
 * 
 * @author Downey
 * a simple UI
 */
public class mainFrame extends JFrame implements ActionListener{
	
	JButton jb=new JButton("Download");
	JLabel jlab=new JLabel("Movie Name:");
	JTextField jtf=new JTextField(10);
	JTextArea jta = new JTextArea();
	JPanel jp=new JPanel();
	
	public static int port;
	String ip;
	
	public mainFrame(String ip,int port)
	{
		setTitle("Speed Up! Download movies base P2P. ");
		jb.addActionListener(this);
		jp.add(jlab);
		jp.add(jtf);
		jp.add(jb);
		this.ip = ip;
		this.port = port;
		
		//update db state,as a server which is online
		new ChangeState(1,port);
		new Server(port);
		
		this.add(jp);
		setSize(500,530);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				
				new ChangeState(0,mainFrame.port);
				System.exit(0);
			}
			
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//press download
		if(e.getSource() == jb)
		{
			String movieName = jtf.getText();
			//request the central serever to find avaliable server which have the same resource
			List<AvaliableServer> servers = Utils.findAvaliableServer(ip,port,movieName);
			if(servers.size() > 0)
			{
				//initilize taskPool
				Constant.taskPool = new TaskPool(servers.get(0).getSize(),servers.size());
				//new a thread to request each avaliable server
				for(int i=0;i<servers.size();i++)
				{
					AvaliableServer as = servers.get(i);
					new Client(as.getIp(),as.getMyport(),as.getSavePath(),as.getMovieName());
				}
			}else
			{
				System.out.println("Can not set up P2P");
			}
		}
	}
	
	
	
}
