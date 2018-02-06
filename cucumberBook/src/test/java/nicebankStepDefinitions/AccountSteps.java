package src.test.java.nicebankStepDefinitions;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Money;
import src.main.java.nicebank.Teller;
import src.test.java.support.TestAccount;
import src.test.java.transform.MoneyConverter;

public class AccountSteps {
	private CashSlot clashSlotInjected;
	private TestAccount accountInjected;
	private Teller tellerInjected;

	private static int counter=1;

	public AccountSteps(CashSlot knowsTheClashSlotInjected, TestAccount accountInjected,
			AutomatedTeller tellerInjected ) {
		this.clashSlotInjected = knowsTheClashSlotInjected;
		this.accountInjected = accountInjected;
		this.tellerInjected = tellerInjected;
		System.out.println("Account steps constructor called: " + counter++);
	} 

	@Given("^my account has been credited with (\\$\\d+\\.\\d+)$")
	public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class) Money amount) throws Throwable {


		// Original balance
		Money balanceBefore = accountInjected.getBalance();

		// Actually update the balance - this method to be updated to use the db not the
		// file
		accountInjected.credit(amount); // The helper account makes sure if myAccount is null, it will be created
		Thread.sleep(5000);

		// MAKE SURE BALANCE IS UPDATED ONLY ONCE
		// To set directly an initial account balance
		// helper.getMyAccount().setBalance(amount);

		// System.out.println("Account credited step put to sleep to give enough time
		// for transaction processor...");
		// Thread.sleep(5000);
		// System.out.println("Account credited step wakes.");

		// Wait until the balance is updated correctly or timeout reached
		int timeoutMilliSecs = 3000;
		int pollIntervalMilliSecs = 100;
		System.out.println("Polling until balance update completed");
		while (!accountInjected.getBalance().equals(amount) && timeoutMilliSecs > 0) {
			System.out.print(".");
			Thread.sleep(pollIntervalMilliSecs);
			timeoutMilliSecs -= pollIntervalMilliSecs;
		}

		// New balance with credit
		Money balanceAfter = accountInjected.getBalance();

		// Original balance + amount credited
		Money balanceWithCredit = balanceBefore.add(amount);

		// Validation: the account balance should be increased exactly with the amount
		// credited. This verification is independent of actual scenarios
		Assert.assertEquals("Incorrect account balance - ", balanceWithCredit, balanceAfter);
	}

	@Then("^my account balance should be equal to (\\$\\d+\\.\\d+)$")
	public void myAccountBalanceShouldBeUpdatedTo$(@Transform(MoneyConverter.class) Money expectedBalance)
			throws Throwable {
		// Workaround: make sure the Cucumber step definition execution always loses the
		// race
		// System.out.println("Account balance verification step put to sleep...");
		// Thread.sleep(5000);
		// System.out.println("Account balance verification step wakes.");

		// Wait until the balance is updated correctly or timeout reached
		int timeoutMilliSecs = 3000;
		int pollIntervalMilliSecs = 100;
		while (!accountInjected.getBalance().equals(expectedBalance) && timeoutMilliSecs > 0) {
			Thread.sleep(pollIntervalMilliSecs);
			timeoutMilliSecs -= pollIntervalMilliSecs;
		}
		Money actualBalance = accountInjected.getBalance();
		Assert.assertEquals("New balance is not correct - ", expectedBalance, actualBalance);
	}
}
