/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package src.main.java.nicebank;

import org.javalite.activejdbc.Base;

import src.test.java.support.TestAccount;

/***
 * The TransactionProcesssor once it is started up, it then enters a loop that
 * tries to read a message off the transaction queue. If it finds one, it pauses
 * for a second, calculates the new balance, and then stores it on the
 * BalanceStore.
 */
public class TransactionProcessor {
	private TransactionQueue queue = new TransactionQueue();


	public void process() {
		System.out.println("Transaction processor thread started...");

		// Establish a connection to the database so it can access it to read
		if (!Base.hasConnection()) {
			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/bank", "teller", "password");
			System.out.println("Transaction processor: Connection to database established");
		}

		do {
			// Read the next message from the queue
			String message = queue.read(); // reading from the queue will also delete that msg.

			// Pause for a second to create a race condition
			 try {
			 Thread.sleep(1000);
			 } catch (InterruptedException e) {
			
			 }

			// If the message is not empty it will read the data from the DATABASE
			if (message.length() > 0) {
//				System.out.println("Transaction processor: message found: " + message);
				String[] parts = message.split(","); // value,accountnumber example: 123.45,1 - the $ sign is removed
														// when writing to DB
				TestAccount account = TestAccount.findFirst("number = ?", parts[1]);
				if (account == null) {
					throw new RuntimeException("Account number not found: " + parts[1]);
				}
				Money transactionAmount = new Money(parts[0]);

				if (isCreditTransaction(message)) {
					// Sets the new balance by adding the new transactionAmount
//					System.out.println("Account to CREDIT: " + parts[1]);
					account.setBalance(account.getBalance().add(transactionAmount));
//					System.out.println("Transaction processor: " + message + " processed\n");
				} else {
//					System.out.println("Account to DEBIT: " + parts[1]);
					account.setBalance(account.getBalance().minus(transactionAmount));
//					System.out.println("Transaction processor: " + message + " processed\n");
				}
			}
		} while (true); // never stops

	}

	private boolean isCreditTransaction(String message) {
		return !message.startsWith("-"); // message starts with +$45.00
	}
}
