package src.test.java.support;

import src.main.java.nicebank.Account;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Teller;

public class KnowsTheTeller {
	private Teller teller;
	public Teller getTeller(CashSlot cashSlot, Account account) {
		if (teller == null) {
			// If we want the automated teller
			teller = new AutomatedTeller(cashSlot, account);
			// teller = new AtmUserInterface(getWebDriver()); // pass the webdriver only
			// object to ATM
		}
		return teller;
	}

}
