package src.test.java.support;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import src.main.java.nicebank.Account;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Teller;

public class Helper {
	private Account myAccount;
	private CashSlot cashSlot;
	private Teller teller;
	private EventFiringWebDriver webDriver;

	public Account getMyAccount() { // To avoid returning null account
		if (myAccount == null) {
			myAccount = new Account();
		}
		return myAccount;
	}

	public CashSlot getCashSlot() {
		if (cashSlot == null) {
			cashSlot = new CashSlot();
		}
		return cashSlot;
	}

	public Teller getTeller(CashSlot cashSlot, Account account) {
		if (teller == null) {
			// If we want the automated teller
			teller = new AutomatedTeller(cashSlot, account);
//			teller = new AtmUserInterface(getWebDriver()); // pass the webdriver only
			// object to ATM
		}
		return teller;
	}

	public EventFiringWebDriver getWebDriver() {
		if (webDriver == null) {
			webDriver = new EventFiringWebDriver(new NicebankFirefoxWebDriver().initializeFirefoxWebDriver());
		}
		return webDriver;
	}
}
