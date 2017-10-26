package src.test.java.nicebankStepDefinitions;

import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Money;
import src.main.java.nicebank.Teller;
import src.test.java.support.Helper;
import src.test.java.transform.MoneyConverter;

public class CashSlotSteps {
	Helper helper;

	public CashSlotSteps(Helper helper) {
		this.helper = helper;
	}

	// This sets the ATM balance equal to the balance that the user has
	@Given("^ATM has sufficient money to dispose$")
	public void atmHasSufficientMoneyToDispose() throws Throwable {
		helper.getCashSlot().setATMBalance(helper.getMyAccount().getBalance());
	}

	@Given("^ATM does not have sufficient money to dispose$")
	public void atm_does_not_have_sufficient_money_to_dispose() throws Throwable {
		helper.getCashSlot().setATMBalance(new Money (0,0));
	}

	@Then("^(\\$\\d+\\.\\d+) should be dispensed$")
	public void $_should_be_dispensed(@Transform(MoneyConverter.class) Money expectedDispensed) throws Throwable {
		Money actualDispensed = helper.getCashSlot().getSlotContents();
		Assert.assertEquals("Incorrect amount dispensed - ", expectedDispensed, actualDispensed);
	}

	@Then("^\"([^\"]*)\" message should be displayed$")
	public void messageShouldBeDisplayed(String expectedMSG) throws Throwable {
		CashSlot cashSlot = helper.getCashSlot();
		String actualMsg = cashSlot.getMessage();
		Assert.assertEquals("ATM message is not correct - ", expectedMSG.trim(), actualMsg.trim());
	}

	@When("^I click on the Display Balance button$")
	public void iClickOnTheDisplayBalanceButton() throws Throwable {
		CashSlot cashSlot = helper.getCashSlot();
		Account account = helper.getMyAccount();
		Teller teller = helper.getTeller(cashSlot, account);
		teller.displayBalance(account);
	}

	@Then("^My account balance of (\\$\\d+\\.\\d+) should be displayed$")
	public void myAccountBalanceOf$ShouldBeDisplayed(@Transform(MoneyConverter.class) Money expectedBalance) throws Throwable {
		String expectedBalanceMsg = "User balance is: " + expectedBalance.toString().trim();
		String actualBalanceMsg = helper.getCashSlot().getMessage().trim();
		Assert.assertEquals("ATM Balance msg is not correct - ", expectedBalanceMsg, actualBalanceMsg);
	}

}
