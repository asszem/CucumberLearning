package src.test.java.support;

import org.javalite.activejdbc.annotations.Table;

import src.main.java.nicebank.Account;

@Table("accounts")
public class TestAccount extends Account {

	public TestAccount() {
		super(1234); // constructor of Account to create a new account with number 1234
		saveIt();
	}
}
