package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mainDao;

public class ChangeStateServlet extends HttpServlet
{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		int state = Integer.parseInt((String)req.getParameter("state"));
		String ip = (String)req.getParameter("ip");
		int port = Integer.parseInt((String)req.getParameter("port"));
		
		mainDao md = new mainDao();
		System.out.println("Servlet ip:"+ip+"\t"+port+"\t"+state);
		md.changeState(ip, port, state);
	}
	
}
