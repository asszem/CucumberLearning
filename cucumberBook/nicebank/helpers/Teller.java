package nicebank.helpers;

public class Teller {

	private CashSlot cashSlot;
	private Account account;

	//Constructor
	public Teller(CashSlot cashSlot, Account account) {
		this.cashSlot = cashSlot;
		this.account = account; //The teller must know about the account it handles
	}

	public void withdrawFrom(Account account, Money amount) {
		//Validate whether account has sufficient balance 
		//Validate whether the Cashslot has enough money to dispense

		cashSlot.dispense(amount);

		account.withdraw(amount);
	}

}
