package src.main.java.nicebank;

public class Account {

	private Money balance = new Money();
	private TransactionQueue queue = new TransactionQueue();

	// Add money to Account
	public void credit(Money amount) {

		// balance = balance.add(amount); //Direct update of account
		queue.write("+" + amount.toString()); // Asynchron update - TrxQ -> TrxProc -> CreditStore
	}

	// Withdraw money from Account
	public void debit(Money amount) {
		// balance = balance.minus(amount);
		queue.write("-" + amount.toString());
	}

	public Money getBalance() {
		// System.out.println("Account balance:\n" + balance);
		// return balance;
		return BalanceStore.getBalance();
	}

}
