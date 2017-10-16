package stepDefinitions.nicebank;

import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Money;

public class CashWithdravalSD {

	class Account {
		private Money balance = new Money();

		public void deposit(Money amount) {
			balance=balance.add(amount);
		}

		public Money getBalance() {
			System.out.println("Account balance:\n" + balance);
			return balance;
		}
	}

	@Given("^I have deposited \\$(\\d+)\\.(\\d+) in my account$")
	public void i_have_deposited_$_in_my_account(int dollars, int cents) throws Throwable {
		Account myAccount = new Account();
		Money toBeDeposited = new Money(dollars, cents);
		myAccount.deposit(toBeDeposited);
		Assert.assertEquals("Incorrect account balance - ", toBeDeposited, myAccount.getBalance());
	}

	@When("^I request \\$(\\d+)$")
	public void i_request_$(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}

	@Then("^\\$(\\d+) should be dispensed$")
	public void $_should_be_dispensed(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}
