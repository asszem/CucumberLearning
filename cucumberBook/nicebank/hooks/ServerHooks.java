package nicebank.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import nicebank.code.AtmServer;

public class ServerHooks {
	
	public static final int  PORT=8887;
	private AtmServer server;

	@Before
	public void startATMServer() throws Exception{
	server=new AtmServer(PORT);	
	server.start();
	}
	
	@After
	public void stopATMServer() throws Exception {
		server.stop();
	}
}
