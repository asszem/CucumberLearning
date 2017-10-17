package nicebank.stepDefinitionsForNiceBank;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.en.Then;
import nicebank.helpers.Helper;
import nicebank.helpers.Money;
import nicebank.helpers.MoneyConverter;

public class CashSlotSteps {
	Helper helper;

	public CashSlotSteps(Helper helper) {
		this.helper = helper;
	}

	@Then("^(\\$\\d+\\.\\d+) should be dispensed$")
	public void $_should_be_dispensed(@Transform(MoneyConverter.class) Money dispensedAmount) throws Throwable {
		Assert.assertEquals("Incorrect amount dispensed - ", dispensedAmount, helper.getCashSlot().getSlotContents());
	}
}
