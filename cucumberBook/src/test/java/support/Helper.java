package src.test.java.support;

import org.javalite.activejdbc.Base;
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

	// Constructor
	public Helper() {
		System.out.println("Helper instance created");
		if (!Base.hasConnection()) {
			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/bank", "teller", "password");

			// Turning off transaction auto completion
			// This will not work if multiple connections are used to the database
			// try {
			// Base.connection().setAutoCommit(false);
			// } catch (Exception se) {
			// // ignore exception
			// }
		}
		Account.deleteAll();
	}

	public Account getMyAccount() { // To avoid returning null account
		if (myAccount == null) {
			System.out.println("Helper initiates account creation with account number 1234");
			myAccount = new Account(1234); /// 1234 is the account number to create the account with
			myAccount.saveIt();
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
			// teller = new AtmUserInterface(getWebDriver()); // pass the webdriver only
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
