package src.test.java.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import src.main.java.nicebank.AtmServer;
import src.test.java.support.KnowsTheAccount;
import src.test.java.support.KnowsTheCashSlot;
import src.test.java.support.KnowsTheTeller;
import src.test.java.support.MyWebDriver;

public class ServerHooks {

	private MyWebDriver webDriver;
	private KnowsTheCashSlot clashSlotHelper;
	private KnowsTheAccount accountHelper;
	private KnowsTheTeller tellerHelper;

	public ServerHooks(MyWebDriver myWebDriverInjected, KnowsTheCashSlot knowsTheCashSlotInjected, KnowsTheAccount knowsTheAccountInjected,
			KnowsTheTeller knowsTheTellerInjected) {
		this.webDriver = myWebDriverInjected;
		this.clashSlotHelper=knowsTheCashSlotInjected;
		this.accountHelper=knowsTheAccountInjected;
		this.tellerHelper=knowsTheTellerInjected;
	}

	public static final int PORT = 8887;
	private AtmServer server;

	@Before
	public void startATMServer() throws Exception {
		server = new AtmServer(PORT, clashSlotHelper, accountHelper, tellerHelper);
		server.start();
	}

	@After
	public void stopATMServer() throws Exception {
		server.stop();
	}
}
