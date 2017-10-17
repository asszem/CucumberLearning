package nicebank.stepDefinitionsForNiceBank;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import nicebank.helpers.Helper;
import nicebank.helpers.Money;
import nicebank.helpers.MoneyConverter;

public class CashSlotSteps {
	Helper helper;

	public CashSlotSteps(Helper helper) {
		this.helper = helper;
	}

	//This sets the ATM balance equal to the balance that the user has
	@Given("^ATM has sufficient money to dispose$")
	public void atmHasSufficientMoneyToDispose() throws Throwable {
		helper.getCashSlot().setATMBalance(helper.getMyAccount().getBalance());
	}

	@Given("^ATM does not have sufficient money to dispose$")
	public void atm_does_not_have_sufficient_money_to_dispose() throws Throwable {
		//At this point it is not known how much user wants to withdraw
		helper.getCashSlot().setATMBalance(new Money(helper.getMyAccount().getBalance().dollars()-1,0));
	}	
	
	@Then("^(\\$\\d+\\.\\d+) should be dispensed$")
	public void $_should_be_dispensed(@Transform(MoneyConverter.class) Money dispensedAmount) throws Throwable {
		Assert.assertEquals("Incorrect amount dispensed - ", dispensedAmount, helper.getCashSlot().getSlotContents());
	}
}
