package nicebank.code;

import nicebank.helpers.Money;

public class Teller {

	private CashSlot cashSlotHandledByTeller;
	private Account account;

	// Constructor
	public Teller(CashSlot cashSlot, Account account) {
		this.cashSlotHandledByTeller = cashSlot;
		this.account = account; // The teller must know about the account it handles
	}

	public String withdrawFrom(Account account, Money amount) {
		Double clientBalance = convertToDouble(account.getBalance());
		Double requestedWithdrawal = convertToDouble(amount);
		Double atmBalance = convertToDouble(cashSlotHandledByTeller.getATMBalance());  //The Teller must know about the CashSlot that is being used
		String msg;

		// Validate whether account has sufficient balance

		if (clientBalance < requestedWithdrawal) {
			msg = "Error: Insufficient funds on account balance";
			return msg;
		}
		// Validate whether the Cashslot has enough money to dispense
		if (requestedWithdrawal > atmBalance) {
			msg = "Error: ATM does not have enough money	";
			return msg;
		}
		
		// When this is reached, the withdrawal request is valid. Now the money should be put in to the ATM and removed from Account
		cashSlotHandledByTeller.dispense(amount);
		account.debit(amount);
		msg = "Successfull withdrawal";
		return msg;
	}

	public Double convertToDouble(Money money) {
		Double result = (double) (money.dollars() + ((double) money.cents() / 100));
		return result;
	}
}
