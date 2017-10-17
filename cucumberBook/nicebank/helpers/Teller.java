package nicebank.helpers;

public class Teller {

	private CashSlot cashSlot;

	// Constructor
	public Teller(CashSlot cashSlot) {
		this.cashSlot = cashSlot;
	}

	public void withdrawFrom(Account account, Money amount) {
		//Validate whether account has sufficient balance 
		//Validate whether the Cashslot has enough money to dispense
		cashSlot.dispense(amount);
	}

}
