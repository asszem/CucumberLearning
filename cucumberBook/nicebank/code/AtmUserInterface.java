package nicebank.code;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import helpers.KillAllDrivers;
import nicebank.helpers.Money;
import nicebank.helpers.webdrivers.FirefoxWebDriver;

public class AtmUserInterface implements Teller {

	private CashSlot cashSlotInterface;
	private Account accountHandledByAtm;
	private final EventFiringWebDriver webDriver;

	public AtmUserInterface(CashSlot cashSlot, Account account) {
		cashSlotInterface = cashSlot;
		accountHandledByAtm=account;
		webDriver= new EventFiringWebDriver(new FirefoxWebDriver().initializeFirefoxWebDriver());
	}
	
	@Override
	public String withdrawFrom(Account account, Money amount) {
		
	try {
	 webDriver.get("http://localhost:9988");	
	 webDriver.findElement(By.id("Amount")).sendKeys(amount.toString());
	 webDriver.findElement(By.id("Withdraw")).click();
	}
		finally {
//			KillAllDrivers.killFirefoxBrowsers();
	}
		return "ATMUserInterfaceTriedToReturn";
	}

}
