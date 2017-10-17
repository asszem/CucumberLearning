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

	// do not use instance variables defined in step definiton
	// private Account myAccount; //Must be an instance variable to be available for each test steps

	// Use helper class
	KnowsMyDomain helper; // An instance variable for the step definition

	// Constructor to instantiate helper
	public CashWithdravalSD() {
		helper = new KnowsMyDomain();
	}

	class Account {
		private Money balance = new Money();

		public void deposit(Money amount) {
			balance = balance.add(amount);
		}

		public Money getBalance() {
			System.out.println("Account balance:\n" + balance);
			return balance;
		}
	}

	// Class that manages withdrawal from account. Also manages the ClashSlot
	class Teller {

		private CashSlot cashSlot;

		// Constructor
		public Teller(CashSlot cashSlot) {
			this.cashSlot = cashSlot;
		}

		public void withdrawFrom(Account account, Money amount) {
			cashSlot.dispense(amount);
		}
	}

	// Class that manages the ATM machine's clash slot behaviour
	class CashSlot {
		private Money inSlotContent;

		public Money getSlotContents() { // return the content (the actual money being in the cash slot of the machine)
			return inSlotContent;
		}

		public Money dispense(Money amount) {
			inSlotContent=amount;
			return amount;
		}
	}

	// Helper class
	class KnowsMyDomain {
		private Account myAccount;
		private CashSlot cashSlot;
		private Teller teller;

		public Account getMyAccount() { // To avoid returning null account
			if (myAccount == null) {
				myAccount = new Account();
			}
			return myAccount;
		}

		public CashSlot getCashSlot() {
			if (cashSlot == null) {
				cashSlot = new CashSlot();
			}
			return cashSlot;
		}
		
		public Teller getTeller(CashSlot cashSlot){
			if (teller==null){
				teller=new Teller(cashSlot);
			}
			return teller;
		}
	}

	// Use two capture groups
	// @Given("^I have deposited \\$(\\d+)\\.(\\d+) in my account$")

	// Use only one capture group for $100.10
	// @Given("^I have deposited \\$(\\d+\\.\\d+) in my account$") //The capture group will be converted to a Money object!

	// Use the @Transform annotation and move the $ sign inside the capture group
	@Given("^I have deposited (\\$\\d+\\.\\d+) in my account$")
	public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class) Money amount) throws Throwable {

		// Avoid instantiating account inside a step. Use the helper class instead
		// myAccount = new Account();
		// myAccount.deposit(amount);
		helper.getMyAccount().deposit(amount); // The helper account makes sure if myAccount is null, it will be created
		Assert.assertEquals("Incorrect account balance - ", amount, helper.getMyAccount().getBalance());
	}

	@When("^I withdraw (\\$\\d+\\.\\d+)$")
	public void i_request_$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
		helper.getTeller(helper.getCashSlot()).withdrawFrom(helper.getMyAccount(), amount);
	}

	@Then("^(\\$\\d+\\.\\d+) should be dispensed$")
	public void $_should_be_dispensed(@Transform(MoneyConverter.class) Money dispensedAmount) throws Throwable {
		Assert.assertEquals("Incorrect amount dispensed - ", dispensedAmount, helper.getCashSlot().getSlotContents());
	}
}
