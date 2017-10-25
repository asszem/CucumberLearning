package nicebank.code;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
//			webDriver.findElement(By.id("Withdraw")).click();
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			webDriver.findElement(By.id("WithdrawFixed")).click();

			// Wait until the ATM user interface loaded before continuing to next step

			WebDriverWait waiter = new WebDriverWait(webDriver, 5); // timeout in seconds
			waiter.until(ExpectedConditions.titleContains("Nice Bank ATM WITHDRAWAL"));
			System.out.println("...waiter finished waiting, ATM withdrawal title found");
		} finally {
			// webDriver.close();
		}

		// The return message will be set in the WithdrawalServlet by calling
		// The withdrawFrom method of a NEW AutomatedTeller instance
		// Which will handle the withdrawal and set the message
		return "AtmUserInterface returned.";
	}
	
	@Override
	public String displayBalance(Account account) {
			webDriver.get("http://localhost:" + ServerHooks.PORT);
			webDriver.findElement(By.id("displayBalance")).click();
			WebDriverWait waiter = new WebDriverWait(webDriver, 5); // timeout in seconds
			waiter.until(ExpectedConditions.titleContains("Nice Bank ATM Display Balance"));
			System.out.println("...waiter finished waiting, ATM Display Balance title found");
			
			//The return message for the correct display balance must be set in the DisplayBalance interface
			return "AtmUserInterface - display balance returned";
	}

}
