package src.test.java.hooks;

import org.javalite.activejdbc.Base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import src.main.java.nicebank.Account;
import src.main.java.nicebank.TransactionQueue;

public class ResetHooks {

	@Before(order=1) //to make sure this runs before eveything
	public void resetData() {
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
		TransactionQueue.clear();
//		System.out.println("ResetHook called\ntransaction queue cleared\ndatabase cleared");
	}

}
