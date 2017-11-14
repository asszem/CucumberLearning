package src.test.java.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.AtmServer;
import src.main.java.nicebank.CashSlot;
import src.test.java.support.Helper;

public class ServerHooks {

	private Helper helper;

	public ServerHooks(Helper helper) {
		this.helper = helper;
	}

	public static final int PORT = 8887;
	private AtmServer server;

	@Before
	public void startATMServer() throws Exception {
		server = new AtmServer(PORT, helper);
		server.start();
	}

	@After
	public void stopATMServer() throws Exception {
		server.stop();
	}
}
