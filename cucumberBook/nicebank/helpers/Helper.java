package nicebank.helpers;

public class Helper {
	private Account myAccount;
	private CashSlot cashSlot;
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

	public Teller getTeller(CashSlot cashSlot) {
		if (teller == null) {
			teller = new Teller(cashSlot);
		}
		return teller;
	}

}
