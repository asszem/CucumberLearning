package nicebank.helpers;

public class Teller {

	private CashSlot cashSlot;

	// Constructor
	public Teller(CashSlot cashSlot) {
		this.cashSlot = cashSlot;
	}

	public void withdrawFrom(Account account, Money amount) {
		cashSlot.dispense(amount);
	}

}
