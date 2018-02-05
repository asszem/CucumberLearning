package src.test.java.nicebankStepDefinitions;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Money;
import src.main.java.nicebank.Teller;
import src.test.java.support.KnowsTheAccount;
import src.test.java.support.KnowsTheCashSlot;
import src.test.java.support.KnowsTheTeller;
import src.test.java.transform.MoneyConverter;

public class TellerSteps {

	KnowsTheCashSlot cashSlotHelper;
	KnowsTheAccount accountHelper;
	KnowsTheTeller tellerHelper;

	public TellerSteps(KnowsTheCashSlot knowsTheClashSlotInjected, KnowsTheAccount knowsTheAccountInjected,
			KnowsTheTeller knowsTheTellerInjected) {
		this.cashSlotHelper = knowsTheClashSlotInjected;
		this.accountHelper = knowsTheAccountInjected;
		this.tellerHelper = knowsTheTellerInjected;
	}

	@When("^I withdraw (\\$\\d+\\.\\d+)$")
	public void i_request_$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
		CashSlot cashSlot = cashSlotHelper.getCashSlot();
		Account account = accountHelper.getMyAccount();
		Teller teller = tellerHelper.getTeller(cashSlot, account);
		teller.withdrawFrom(account, amount);

//		Thread.sleep(10000);
//		System.out.println("Waking after I withdraw");
//		Money dispensedMoney = cashSlot.getSlotContents();
		
		//The cashSlot message is set by the withdrawnFrom method
//		cashSlot.setMessage(resultMsg);
		//There is no validation at this step
	}
}
