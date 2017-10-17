package nicebank.stepDefinitionsForNiceBank;

import org.junit.Assert;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import nicebank.helpers.Account;
import nicebank.helpers.CashSlot;
import nicebank.helpers.Helper;
import nicebank.helpers.Money;
import nicebank.helpers.MoneyConverter;
import nicebank.helpers.Teller;

public class TellerSteps {
	Helper helper;

	public TellerSteps(Helper helper) {
		this.helper = helper;
	}

	@When("^I withdraw (\\$\\d+\\.\\d+)$")
	public void i_request_$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
		CashSlot cashSlot = helper.getCashSlot();
		Account account = helper.getMyAccount();
		Teller teller = helper.getTeller(cashSlot, account);
		String resultMsg=teller.withdrawFrom(account, amount);
		//There is no validation at this step
	}
}
