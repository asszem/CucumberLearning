package src.test.java.nicebankStepDefinitions;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Money;
import src.main.java.nicebank.Teller;
import src.test.java.support.TestAccount;
import src.test.java.transform.MoneyConverter;

public class CashSlotSteps {
	CashSlot cashSlot;
	TestAccount accountInjected;
	Teller tellerInjected;

	public CashSlotSteps(CashSlot cashSlotInjected, TestAccount accountInjected,
			AutomatedTeller tellerInjected) {
		this.cashSlot = cashSlotInjected;
		this.accountInjected = accountInjected;
		this.tellerInjected = tellerInjected;
	}

	// This sets the ATM balance equal to the balance that the user has
	@Given("^ATM has sufficient money to dispose$")
	public void atmHasSufficientMoneyToDispose() throws Throwable {
		cashSlot.setATMBalance(accountInjected.getBalance());
	}

	@Given("^ATM does not have sufficient money to dispose$")
	public void atm_does_not_have_sufficient_money_to_dispose() throws Throwable {
		cashSlot.setATMBalance(new Money(0, 0));
	}

	@Then("^(\\$\\d+\\.\\d+) should be dispensed$")
	public void $_should_be_dispensed(@Transform(MoneyConverter.class) Money expectedDispensed) throws Throwable {
		Money actualDispensed = cashSlot.getSlotContents();
		Assert.assertEquals("Incorrect amount dispensed - ", expectedDispensed, actualDispensed);
	}

	@Then("^\"([^\"]*)\" message should be displayed$")
	public void messageShouldBeDisplayed(String expectedMSG) throws Throwable {
		String actualMsg = cashSlot.getMessage();
		Assert.assertEquals("ATM message is not correct - ", expectedMSG.trim(), actualMsg.trim());
	}

	@When("^I click on the Display Balance button$")
	public void iClickOnTheDisplayBalanceButton() throws Throwable {
		Account account = accountInjected;
		tellerInjected.displayBalance(account);
	}

	@Then("^My account balance of (\\$\\d+\\.\\d+) should be displayed$")
	public void myAccountBalanceOf$ShouldBeDisplayed(@Transform(MoneyConverter.class) Money expectedBalance)
			throws Throwable {
		String expectedBalanceMsg = "User balance is: " + expectedBalance.toString().trim();
		String actualBalanceMsg = cashSlot.getMessage().trim();
		Assert.assertEquals("ATM Balance msg is not correct - ", expectedBalanceMsg, actualBalanceMsg);
	}

}
