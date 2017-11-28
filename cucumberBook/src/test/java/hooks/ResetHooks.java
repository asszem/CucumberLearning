package src.test.java.hooks;

import org.javalite.activejdbc.Base;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import src.main.java.nicebank.TransactionQueue;

public class ResetHooks {

	@Before
	public void resetData() {
		TransactionQueue.clear();
	}
	
	//To roll back JDBC transaction after each scenario
	@After
	public void rollback() {
		Base.rollbackTransaction();
	}
}
