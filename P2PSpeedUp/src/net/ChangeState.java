package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ChangeState extends Thread{
	
	private String ip;
	private int state;
	private int port;
	
	/**
	 * 
	 * @param state
	 * @param port
	 *  request for server to change the state (is online?) of all the server record in db
	 */
	public ChangeState(int state,int port)
	{
		this.state = state;
		this.port = port;
		try {
			InetAddress addr = InetAddress.getLocalHost();
			ip=addr.getHostAddress().toString();
			System.out.println("IP:"+ip);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		start();
	}
	
	public void run()
	{
		Map<String,String> map = new HashMap<String, String>();
		map.put("state", state+"");
		map.put("ip", ip);
		map.put("port", port+"");
		System.out.println("Here");
		String url  = "http://127.0.0.1:8080/P2PSpeedUpServlet/servlet/ChangeStateServlet";
		Document doc;
		try {
			doc = Jsoup.connect(url).data(map).method(Method.POST).get();
			System.out.println(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
