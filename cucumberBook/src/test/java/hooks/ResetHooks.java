package src.test.java.hooks;

import org.javalite.activejdbc.Base;

import cucumber.api.java.Before;
import src.main.java.nicebank.TransactionQueue;

public class ResetHooks {

	@Before
	public void resetData() {
		TransactionQueue.clear();
	}

}
