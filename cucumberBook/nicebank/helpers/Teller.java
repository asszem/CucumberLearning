package nicebank.helpers;

public class Teller {

	private CashSlot cashSlot;
	private Account account;

	// Constructor
	public Teller(CashSlot cashSlot, Account account) {
		this.cashSlot = cashSlot;
		this.account = account; // The teller must know about the account it handles
	}

	public String withdrawFrom(Account account, Money amount) {
		Double clientBalance = convertToDouble(account.getBalance());
		Double requestedWithdrawal = convertToDouble(amount);
		Double atmBalance = convertToDouble(cashSlot.getATMBalance());
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

		cashSlot.dispense(amount);
		account.withdraw(amount);
		msg = "Successfull withdrawal";
		return msg;
	}

	public Double convertToDouble(Money money) {
		Double result = (double) (money.dollars() + ((double) money.cents() / 100));
		return result;
	}
}
