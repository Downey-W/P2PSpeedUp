package dao;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AvaliableServer;

public class mainDao extends BaseDao{
	
	public void changeState(String ip,int port,int i)
	{
		inits();
		String sql = "update maininfo m set  m.state = "+i+" where m.ip = '"+ip+"' and m.myport = "+port;
		System.out.println("SQL:"+sql);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		free();
	}
	
	public List<AvaliableServer> findAvaliable(String ip ,int port, String movieName) throws UnsupportedEncodingException
	{
		inits();
		List<AvaliableServer> servers = new ArrayList<AvaliableServer>();
		String sql = "select * from maininfo where movieName = '"+movieName+"' and state = 1 and ip = '"+ip+"' and myport <> "+port;
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				AvaliableServer as = new AvaliableServer();
				as.setIp(rs.getString(1));
				as.setMyport(rs.getInt(2));
				System.out.println("2222222222222:"+rs.getString(3)+"\t"+rs.getString(4));
				as.setMovieName(rs.getString(3));
				as.setSavePath(rs.getString(4));
				as.setSize(rs.getLong(5));
				servers.add(as);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servers;
	}
	
	public void update(String ip,int port,String movieName,String savePath)
	{
		inits();
		String sql = "update maininfo set movieName = '"+movieName+"' ,savePath = '"+savePath+"' where ip = '"+ip+"' and myport = "+port;
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		free();
	}
	
	
}
