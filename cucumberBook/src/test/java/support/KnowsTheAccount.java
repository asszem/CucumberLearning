package src.test.java.support;

import org.javalite.activejdbc.Base;

import src.main.java.nicebank.Account;

public class KnowsTheAccount {
	private Account myAccount;

	public Account getMyAccount() { // To avoid returning null account
		if (myAccount == null) {
			if (!Base.hasConnection()) {
				Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/bank", "teller", "password");

				// Turning off transaction auto completion
				// This will not work if multiple connections are used to the database
				// try {
				// Base.connection().setAutoCommit(false);
				// } catch (Exception se) {
				// // ignore exception
				// }
			}
			Account.deleteAll();
			System.out.println("Previous accounts deleted");
			myAccount = new Account(1234); /// 1234 is the account number to create the account with
			myAccount.saveIt();
			System.out.println("KnowsTheAccount completed account number 1234 creation");
		}
		return myAccount;
	}

}
