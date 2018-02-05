package src.test.java.nicebankStepDefinitions;

import cucumber.api.Transform;
import cucumber.api.java.en.When;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.AutomatedTeller;
import src.main.java.nicebank.CashSlot;
import src.main.java.nicebank.Money;
import src.main.java.nicebank.Teller;
import src.test.java.support.TestAccount;
import src.test.java.transform.MoneyConverter;

public class TellerSteps {

	CashSlot cashSlotInjected;
	TestAccount accountInjected;
	Teller tellerInjected;

	//Define here which implementation of teller interface to be injected
	//AutomatedTeller:
	//AutomatedTeller tellerInjected
	//ATMUserInterface:
	//AtmUserInterface tellerInjected
	public TellerSteps(CashSlot cashSlotInjected, TestAccount accountInjected,
			AutomatedTeller tellerInjected) {
		this.cashSlotInjected = cashSlotInjected;
		this.accountInjected = accountInjected;
		this.tellerInjected = tellerInjected;
	}

	@When("^I withdraw (\\$\\d+\\.\\d+)$")
	public void i_request_$(@Transform(MoneyConverter.class) Money amount) throws Throwable {
//		CashSlot cashSlot = cashSlotInjected;
		tellerInjected.withdrawFrom(accountInjected, amount);

//		Thread.sleep(10000);
//		System.out.println("Waking after I withdraw");
//		Money dispensedMoney = cashSlot.getSlotContents();
		
		//The cashSlot message is set by the withdrawnFrom method
//		cashSlot.setMessage(resultMsg);
		//There is no validation at this step
	}
}
