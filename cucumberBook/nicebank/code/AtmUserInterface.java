package nicebank.code;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import drivers.FirefoxWebDriver;
import helpers.KillAllDrivers;
import nicebank.helpers.Money;

public class AtmUserInterface implements Teller {

	private CashSlot cashSlotHandledByAtm;
	private Account account;
	private final EventFiringWebDriver webDriver;

	// Constructor
	public AtmUserInterface(CashSlot cashSlot, Account account) {
		this.cashSlotHandledByAtm = cashSlot;
		this.account = account; // The teller must know about the account it handles
		this.webDriver = new EventFiringWebDriver(new FirefoxWebDriver().getFirefoxWebDriver());
	}

	@Override
	public String withdrawFrom(Account account, Money money) {
		try {
			webDriver.get("http://localhost:9988");
			webDriver.findElement(By.id("Amount")).sendKeys(money.toString());
			webDriver.findElement(By.id("Withdraw")).click();
		} finally {
			//This freezes Firefox
//			webDriver.close();
			KillAllDrivers.killFirefoxBrowsers();
		}
		return "ATMUserInterfaceTriedToReturn";
	}

}
