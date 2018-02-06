package src.main.java.nicebank;

public class AutomatedTeller implements Teller {

	private CashSlot cashSlotHandledByTeller;
	private Account accountHandledByTeller;

	// Constructor
	public AutomatedTeller(CashSlot cashSlot, Account account) {
		this.cashSlotHandledByTeller = cashSlot;
		this.accountHandledByTeller = account; // The teller must know about the account it handles
//		System.out.println("Automated teller constructor called");
//		System.out.println("Account Handled by teller in cosntructor:" + account);
	}

	@Override
	public String withdrawFrom(Account account, Money amount) {
		Double clientBalance = convertToDouble(account.getBalance());
		Double requestedWithdrawal = convertToDouble(amount);
		Double atmBalance = convertToDouble(cashSlotHandledByTeller.getATMBalance()); // The Teller must know about the
																						// CashSlot that is being used
		String msg;

		// Validate whether account has sufficient balance

		if (clientBalance < requestedWithdrawal) {
			msg = "Error: Insufficient funds on account balance";
			cashSlotHandledByTeller.setMessage(msg);
			return msg;
		}
		// Validate whether the Cashslot has enough money to dispense
		if (requestedWithdrawal > atmBalance) {
			msg = "Error: ATM does not have enough money	";
			cashSlotHandledByTeller.setMessage(msg);
			return msg;
		}

		// When this is reached, the withdrawal request is valid. Now the money should
		// be put in to the ATM and removed from Account
		cashSlotHandledByTeller.dispense(amount);
		System.out.println("**********************\n");
		System.out.println("Amount to withdraw " + amount);
		System.out.println("accounthandled by teller in withdraw: " + accountHandledByTeller);
		System.out.println("accounthandled by teller balance: " + accountHandledByTeller.getString("balance"));
		System.out.println("**********************\n");
		accountHandledByTeller.debit(amount);
		// Set the ATM message
		msg = "Successfull withdrawal";
		cashSlotHandledByTeller.setMessage(msg);
		return msg;
	}

	@Override
	public String displayBalance(Account account) {
		cashSlotHandledByTeller.setMessage("User balance is: " + account.getBalance().toString());
//		return accountHandledByTeller.getBalance().toString();
		return "Automated Teller display balance returns";
	}

	public Double convertToDouble(Money money) {
		Double result = (double) (money.dollars() + ((double) money.cents() / 100));
		return result;
	}
}
