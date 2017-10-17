package nicebank.stepDefinitionsForNiceBank;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import nicebank.helpers.Helper;
import nicebank.helpers.Money;
import nicebank.helpers.MoneyConverter;

public class AccountSteps {
	Helper helper;

	public AccountSteps(Helper helper) {
		this.helper=helper;
	}

	@Given("^I have deposited (\\$\\d+\\.\\d+) in my account$")
	public void i_have_deposited_$_in_my_account(@Transform(MoneyConverter.class) Money amount) throws Throwable {
		helper.getMyAccount().deposit(amount); // The helper account makes sure if myAccount is null, it will be created
		Assert.assertEquals("Incorrect account balance - ", amount, helper.getMyAccount().getBalance());
	}
	
	@Then("^my account balance should be updated to (\\$\\d+\\.\\d+)$")
	public void myAccountBalanceShouldBeUpdatedTo$(@Transform(MoneyConverter.class) Money expectedBalance) throws Throwable {
		Money actualBalance=helper.getMyAccount().getBalance();
		Assert.assertEquals("New balance is not correct - ", expectedBalance, actualBalance);
	}
}
