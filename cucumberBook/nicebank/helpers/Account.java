package nicebank.helpers;

public class Account {
	private Money balance = new Money();

	public void deposit(Money amount) {
		balance = balance.add(amount);
	}

	public Money getBalance() {
		System.out.println("Account balance:\n" + balance);
		return balance;
	}

}
