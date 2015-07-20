package servlet;

import gsonObj.AvaliableServerObj;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import com.google.gson.Gson;

import model.AvaliableServer;
import dao.mainDao;

public class FindAvaliableServlet extends HttpServlet{
	
	PrintWriter out = null;
	Gson gson = new Gson();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		String movieName = (String)req.getParameter("movieName");
		String ip = (String)req.getParameter("ip");
		int port = Integer.parseInt((String)req.getParameter("port"));
		try {
			movieName = new String(movieName.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Find movieName"+movieName);
		mainDao md = new mainDao();
		AvaliableServerObj serversObj = new AvaliableServerObj();
		List<AvaliableServer> servers = md.findAvaliable(ip,port,movieName);
		serversObj.setServers(servers);
		
		out = resp.getWriter();
		System.out.println("JSON:"+gson.toJson(serversObj));
		out.print(gson.toJson(serversObj));
	}

}
