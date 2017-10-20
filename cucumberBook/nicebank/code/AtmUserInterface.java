package nicebank.code;

import org.openqa.selenium.support.events.EventFiringWebDriver;

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
		return null;
	}

}
