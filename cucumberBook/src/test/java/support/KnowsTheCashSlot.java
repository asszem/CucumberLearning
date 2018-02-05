package src.test.java.support;

import src.main.java.nicebank.CashSlot;

public class KnowsTheCashSlot {
	private CashSlot cashSlot;
	public CashSlot getCashSlot() {
		if (cashSlot == null) {
			cashSlot = new CashSlot();
		}
		return cashSlot;
	}

}
