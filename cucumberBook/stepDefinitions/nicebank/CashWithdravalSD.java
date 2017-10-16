package stepDefinitions.nicebank;

import org.junit.Assert;

import cucumber.api.PendingException;
import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import helpers.Money;
import helpers.MoneyConverter;

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

	class Teller {
		public void withdrawFrom(Account account, int amount){
			
		}
	}


	//Use two capture groups
	//@Given("^I have deposited \\$(\\d+)\\.(\\d+) in my account$")

	//Use only one capture group for $100.10
	//	@Given("^I have deposited \\$(\\d+\\.\\d+) in my account$")		//The capture group will be converted to a Money object!
	
	//Use the @Transform annotation and move the $ sign inside the capture group
	@Given("^I have deposited (\\$\\d+\\.\\d+) in my account$")		
	public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class) Money amount) throws Throwable {
		Account myAccount = new Account();
		myAccount.deposit(amount);
		Assert.assertEquals("Incorrect account balance - ", amount, myAccount.getBalance());
	}

	@When("^I withdraw \\$(\\d+)$")
	public void i_request_$(int amount) throws Throwable {
		Teller teller = new Teller();
//		teller.withdrawFrom(myAccount, amount);
	}

	@Then("^\\$(\\d+) should be dispensed$")
	public void $_should_be_dispensed(int arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		throw new PendingException();
	}
}
