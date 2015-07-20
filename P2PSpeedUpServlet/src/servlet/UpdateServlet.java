package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mainDao;

public class UpdateServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String ip = (String)req.getParameter("ip");
		int port = Integer.parseInt((String)req.getParameter("port"));
		String movieName = (String)req.getParameter("movieName");
		String savePath = (String)req.getParameter("savePath");
		try {
			movieName = new String(movieName.getBytes("ISO-8859-1"),"UTF-8");
			savePath = new String(savePath.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(ip+port+movieName+savePath);
		mainDao md = new mainDao();
		md.update(ip, port, movieName, savePath);
	}

}
