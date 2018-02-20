package src.test.java.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import src.main.java.nicebank.AtmServer;
import src.main.java.nicebank.CashSlot;
import src.test.java.support.KnowsTheAccount;
import src.test.java.support.MyWebDriver;

public class ServerHooks {

	private MyWebDriver webDriver;
	private CashSlot cashSlot;
	private KnowsTheAccount accountHelper;

	public ServerHooks(MyWebDriver myWebDriverInjected, CashSlot cashSlotInjected, KnowsTheAccount knowsTheAccountInjected) { this.webDriver = myWebDriverInjected;
		this.cashSlot=cashSlotInjected;
		this.accountHelper=knowsTheAccountInjected;
	}

	public static final int PORT = 8887;
	private AtmServer server;

	@Before
	public void startATMServer() throws Exception {
		server = new AtmServer(PORT, cashSlot, accountHelper);
		server.start();
	}

	@After
	public void stopATMServer() throws Exception {
		server.stop();
	}
}
