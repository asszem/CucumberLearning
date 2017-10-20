package nicebank.helpers;

import nicebank.code.Account;
import nicebank.code.AutomatedTeller;
import nicebank.code.CashSlot;
import nicebank.code.Teller;

public class Helper {
	private Account myAccount;
	private CashSlot cashSlot;
	// private AutomatedTeller teller;
	private Teller teller;

	public Account getMyAccount() { // To avoid returning null account
		if (myAccount == null) {
			myAccount = new Account();
		}
		return myAccount;
	}

	public CashSlot getCashSlot() {
		if (cashSlot == null) {
			cashSlot = new CashSlot();
		}
		return cashSlot;
	}

	// public AutomatedTeller getTeller(CashSlot cashSlot, Account account) {
	// if (teller == null) {
	// teller = new AutomatedTeller(cashSlot, account);
	// }
	// return teller;
	// }

	public Teller getTeller(CashSlot cashSlot, Account account) {
		if (teller == null) {
			teller = new AutomatedTeller(cashSlot, account);
		}
		return teller;
	}

}
