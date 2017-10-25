package nicebank.stepDefinitionsForNiceBank;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import nicebank.code.Account;
import nicebank.code.CashSlot;
import nicebank.code.Teller;
import nicebank.code.AutomatedTeller;
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
		CashSlot cashSlot = helper.getCashSlot();
		Account account = helper.getMyAccount();
		Teller teller = helper.getTeller(cashSlot, account);
		teller.withdrawFrom(account, amount);
//		Money dispensedMoney = cashSlot.getSlotContents();
		
		//The cashSlot message is set by the withdrawnFrom method
//		cashSlot.setMessage(resultMsg);
		//There is no validation at this step
	}
}
