package model;

/**
 * 
 * @author Downey
 * A model for keeping all neccessary attribute for an avaliable server
 */
public class AvaliableServer {
	
	
	private String ip ;
	private int myport;
	private String movieName;
	private String savePath;
	private long size;
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public int getMyport() {
		return myport;
	}
	public void setMyport(int myport) {
		this.myport = myport;
	}
	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	
	
	
}
