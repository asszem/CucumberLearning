package nicebank.stepDefinitionsForNiceBank;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import nicebank.helpers.Helper;
import nicebank.helpers.Money;
import nicebank.helpers.MoneyConverter;

public class TellerSteps {
	Helper helper;

	public TellerSteps(Helper helper) {
		this.helper = helper;
	}

	@When("^I withdraw (\\$\\d+\\.\\d+)$")
	public void i_request_$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
		helper.getTeller(helper.getCashSlot(), helper.getMyAccount()).withdrawFrom(helper.getMyAccount(), amount);
	}
}
