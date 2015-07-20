package net;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jsonObj.AvaliableServerObj;

import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;



import UI.mainFrame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.AvaliableServer;

public class Utils {
	
	
	/**
	 * 
	 * @param ip
	 * @param port
	 * @param movieName
	 * @return
	 * 
	 * request the server that return all the avaliable servers which have the same resource
	 */
	public static List<AvaliableServer> findAvaliableServer(String ip,int port,String movieName){
		
		List<AvaliableServer> servers = null;
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("movieName", movieName);
		map.put("ip", ip);
		map.put("port", ""+port);
		String url  = "http://127.0.0.1:8080/P2PSpeedUpServlet/servlet/FindAvaliableServlet";
		Document doc;
		try {
			doc = Jsoup.connect(url).data(map).method(Method.POST).get();
			System.out.println(doc);
			servers = parseAvaliableServer(doc.text());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servers;
	}
	
	//parse
	public static List<AvaliableServer> parseAvaliableServer(String json)
	{
		Gson gson = new Gson();  
		Type type = new TypeToken<AvaliableServerObj>() {}.getType();  
		AvaliableServerObj serversObj = gson.fromJson(json, type);
		return serversObj.getServers();
	}
	
//	public static void main(String[] args) {
//		findAvaliableServer("192.168.1.105",22222,"¹è¹È.Silicon.Valley.S02E01.ÖÐÓ¢×ÖÄ».HDTVrip.1024X576.mp4");
//	}
}
