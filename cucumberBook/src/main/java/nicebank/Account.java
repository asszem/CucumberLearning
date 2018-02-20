package src.main.java.nicebank;

import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.Model;

//by inheriting from ActiveJDBC::Model it will act on the 'accounts' table
public class Account extends Model {

	// private Money balance = new Money();
	private TransactionQueue queue = new TransactionQueue();

	// A default constructor needed, othervise activeJDBC throws error:
	// org.javalite.activejdbc.InitException: Failed to create a new instance of:
	// class src.main.java.nicebank.Account, are you sure this class has a default
	// constructor?
	public Account() {
	}

	// Constructor to set an initial balance for the account
	public Account(int accountNumber) {
		setInteger("number", accountNumber);
		setString("balance", "0.00");
		System.out.println("Acccount " + accountNumber + " with " + getString("balance") + " balance created");
		System.out.println("Account number from database: " + getString("number"));
	}

	/**
	 * Credit @param amount Money to Account identified by number Example: +$123.45,1
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
		// To refresh the DB data before retrieveing....
		refresh();

		// ActiveJDBC method getString to get the string value of balance field
		return new Money(getString("balance")); // Returns the value of "balance" field for user
	}

	public void setBalance(Money amount) {
		setString("balance", amount.toString().substring(1)); // substring(1) removes the leading $ sign from balance string
		boolean result = saveIt(); // ActiveJDBC method to commit the balance change
	}

	public int getNumber() {
		return getInteger("number");
	}

}
