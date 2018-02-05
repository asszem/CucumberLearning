package src.test.java.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.AtmServer;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Teller;
import src.test.java.support.MyWebDriver;

public class ServerHooks {

	private MyWebDriver webDriver;
	private CashSlot cashSlot;
	private Account accountInjected;
	private Teller tellerInjected;

	public ServerHooks(MyWebDriver myWebDriverInjected, CashSlot cashSlotInjected,
			Account accountInjected, AutomatedTeller tellerInjected) {
		this.webDriver = myWebDriverInjected;
		this.cashSlot = cashSlotInjected;
		this.tellerInjected = tellerInjected;
		this.accountInjected = accountInjected;
	}

	public static final int PORT = 8887;
	private AtmServer server;

	@Before
	public void startATMServer() throws Exception {
		//cast to AutomatedTeller or AtmUserInterface depending on teller type
		server = new AtmServer(PORT, cashSlot, accountInjected, (AutomatedTeller) tellerInjected);
		server.start();
	}

	@After
	public void stopATMServer() throws Exception {
		server.stop();
	}
}
