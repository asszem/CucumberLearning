package nicebank.code;

import nicebank.helpers.Money;

public class Account {
	private Money balance = new Money();

	//Add money to Account
	public void credit(Money amount) {
		balance = balance.add(amount);
	}

	//Withdraw money from Account
	public void debit(Money amount) {
		balance = balance.minus(amount);
	}

	public Money getBalance() {
		// System.out.println("Account balance:\n" + balance);
		return balance;
	}

}
