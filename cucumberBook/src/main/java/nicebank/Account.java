package src.main.java.nicebank;

import org.javalite.activejdbc.Model;

public class Account extends Model {

	private Money balance = new Money();
	private TransactionQueue queue = new TransactionQueue();

	/**
	 * Credit @param amount Money to Account identified by number
	 * Example: +$123.45,1
	 */
	public void credit(Money amount) {

		// balance = balance.add(amount); //Direct update of account
		// queue.write("+" + amount.toString()); // Asynchron update - TrxQ -> TrxProc
		// -> CreditStore
		queue.write("+" + amount.toString() + "," + getNumber()); // Asynchron update and activeJDBC method getNumber
	}

	/**
	 * Withdraw @param amount Money from Account identified by number
	 *
	 * Example: -$123.45,1
	 */
	public void debit(Money amount) {
		// balance = balance.minus(amount);
		// queue.write("-" + amount.toString());
		queue.write("-" + amount.toString() + "," + getNumber()); // Asynchron update and activeJDBC method getNumber
	}

	public Money getBalance() {
		// System.out.println("Account balance:\n" + balance);
		// return balance;
		// return BalanceStore.getBalance();

		// ActiveJDBC method getString
		return new Money(getString("balance")); // Returns the value of "balance" field for user
	}

	public void setBalance(Money amount) {
		setString("balance", amount.toString().substring(1)); // substring(1) removes the leading $ sign from balance
																// string
		saveIt(); // ActiveJDBC method to commit the balance change
	}

	public int getNumber() {
		return getInteger("number");
	}

}
