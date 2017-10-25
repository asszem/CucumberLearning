package nicebank.code;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import nicebank.helpers.Helper;
import nicebank.helpers.Money;
import nicebank.hooks.ServerHooks;

public class AtmUserInterface implements Teller {

	private final EventFiringWebDriver webDriver;

	// Only the webdriver was passed when the Helper class instantiated this object
	public AtmUserInterface(EventFiringWebDriver webDriver) {
		this.webDriver = webDriver;
	}

	@Override
	public String withdrawFrom(Account account, Money amount) {

		try {
			webDriver.get("http://localhost:" + ServerHooks.PORT);
			webDriver.findElement(By.id("Amount")).sendKeys(amount.toString());
			webDriver.findElement(By.id("Withdraw")).click();
		} finally {
			// webDriver.close();
		}

		// The return message will be set in the WithdrawalServlet by calling 
		// The withdrawFrom method of a NEW AutomatedTeller instance
		// Which will handle the withdrawal and set the message
		return "AtmUserInterface returned.";
	}

}
