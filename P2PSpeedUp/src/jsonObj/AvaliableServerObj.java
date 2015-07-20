package jsonObj;

import java.util.List;

import model.AvaliableServer;

/**
 * 
 * @author Downey
 *  Use Gson to parson json data
 */
public class AvaliableServerObj {
	
	private List<AvaliableServer> servers;

	public List<AvaliableServer> getServers() {
		return servers;
	}

	public void setServers(List<AvaliableServer> servers) {
		this.servers = servers;
	}
}
