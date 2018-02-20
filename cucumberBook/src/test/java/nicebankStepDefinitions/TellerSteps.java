package src.test.java.nicebankStepDefinitions;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Money;
import src.main.java.nicebank.Teller;
import src.test.java.support.AtmUserInterface;
import src.test.java.support.KnowsTheAccount;
import src.test.java.transform.MoneyConverter;

public class TellerSteps {

	CashSlot cashSlotInjected;
	KnowsTheAccount accountHelper;
	Teller teller;

	// Inject BOTH type of tellers and comment the appropriate in the assignment
	public TellerSteps(CashSlot cashSlotInjected, KnowsTheAccount knowsTheAccountInjected,
			AutomatedTeller automatedTellerInjected) {
		this.cashSlotInjected = cashSlotInjected;
		this.accountHelper = knowsTheAccountInjected;

		// Only one should be selected
		this.teller = automatedTellerInjected;
		// this.teller = atmUITellerInjected;
	}

	@When("^I withdraw (\\$\\d+\\.\\d+)$")
	public void i_request_$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
//		CashSlot cashSlot = cashSlotInjected;
		Account account = accountHelper.getMyAccount();
//		Teller teller = tellerHelper.getTeller(cashSlot, account);

		teller.withdrawFrom(account, amount);

		// Thread.sleep(10000);
		// System.out.println("Waking after I withdraw");
		// Money dispensedMoney = cashSlot.getSlotContents();

		// The cashSlot message is set by the withdrawnFrom method
		// cashSlot.setMessage(resultMsg);
		// There is no validation at this step
	}
}
