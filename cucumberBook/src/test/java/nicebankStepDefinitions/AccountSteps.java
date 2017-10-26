package src.test.java.nicebankStepDefinitions;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import src.main.java.nicebank.Money;
import src.test.java.support.Helper;
import src.test.java.transform.MoneyConverter;

public class AccountSteps {
	Helper helper;

	public AccountSteps(Helper helper) {
		this.helper = helper;
	}

	@Given("^my account has been credited with (\\$\\d+\\.\\d+)$")
	public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class) Money amount) throws Throwable {
		// Original balance
		Money balanceBefore = helper.getMyAccount().getBalance();

		// Actually update the balance
		helper.getMyAccount().credit(amount); // The helper account makes sure if myAccount is null, it will be created

		// System.out.println("Account credited step put to sleep...");
		// Thread.sleep(5000);
		// System.out.println("Account credited step wakes.");

		// Wait until the balance is updated correctly or timeout reached
		int timeoutMilliSecs = 3000;
		int pollIntervalMilliSecs = 100;
		while (!helper.getMyAccount().getBalance().equals(amount) && timeoutMilliSecs > 0) {
			System.out.println("Polling ..." + timeoutMilliSecs);
			Thread.sleep(pollIntervalMilliSecs);
			timeoutMilliSecs -= pollIntervalMilliSecs;
		}

		// New balance with credit
		Money balanceAfter = helper.getMyAccount().getBalance();

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
		while (!helper.getMyAccount().getBalance().equals(expectedBalance) && timeoutMilliSecs > 0) {
			Thread.sleep(pollIntervalMilliSecs);
			timeoutMilliSecs -= pollIntervalMilliSecs;
		}
		Money actualBalance = helper.getMyAccount().getBalance();
		Assert.assertEquals("New balance is not correct - ", expectedBalance, actualBalance);
	}
}
