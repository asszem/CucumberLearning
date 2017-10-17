package nicebank.stepDefinition;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nicebank.helpers.*;

public class CashWithdravalSD {

	Helper helper;

	public CashWithdravalSD() {
		helper = new Helper();
	}

	@Given("^I have deposited (\\$\\d+\\.\\d+) in my account$")
	public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class) Money amount) throws Throwable {
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
