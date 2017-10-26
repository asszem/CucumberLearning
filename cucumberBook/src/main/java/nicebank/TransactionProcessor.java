/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package src.main.java.nicebank;

	

/***
 *The TransactionProcesssor once it is started up, it then enters a loop
 * that tries to read a message off the transaction queue.
 * If it finds one, it pauses for a second, calculates the new balance, and then stores it on the BalanceStore.
 * */
public class TransactionProcessor {
	private TransactionQueue queue = new TransactionQueue();

	//TODO the BalanceStore instantiation is missing
//	private BalanceStore balanceStore = new BalanceStore();

	public void process() {
		System.out.println("Transaction processor started...");
		do {
			//Read the next message from the queue
			String message = queue.read(); //reading from the queue will also delete that msg.

			//Pause for a second
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}

			//If the message is not empty
			if (message.length() > 0) {
				Money balance = BalanceStore.getBalance();
				Money transactionAmount = new Money(message);

				if (isCreditTransaction(message)) {
					BalanceStore.setBalance(balance.add(transactionAmount));
					System.out.println("Transaction processor: "+message+" processed");
				} else {
					BalanceStore.setBalance(balance.minus(transactionAmount));
					System.out.println("Transaction processor: "+message+" processed");
				}
			}
		} while (true); //never stops
	}

	private boolean isCreditTransaction(String message) {
		return !message.startsWith("-"); // message starts with +$45.00
	}
}