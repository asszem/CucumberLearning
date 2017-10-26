package src.test.java.nicebankStepDefinitions;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Money;
import src.main.java.nicebank.Teller;
import src.test.java.support.Helper;
import src.test.java.transform.MoneyConverter;

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
