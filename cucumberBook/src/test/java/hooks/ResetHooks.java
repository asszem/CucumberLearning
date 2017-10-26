package src.test.java.hooks;

import cucumber.api.java.Before;
import src.main.java.nicebank.BalanceStore;
import src.main.java.nicebank.TransactionQueue;

public class ResetHooks {

	@Before
	public void resetData() {
		TransactionQueue.clear();
		BalanceStore.clear();
	}
}
