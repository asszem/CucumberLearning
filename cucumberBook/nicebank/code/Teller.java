package nicebank.code;

import nicebank.helpers.Money;

public interface Teller {
	String withdrawFrom(Account account, Money amount);

	public String displayBalance(Account account);

}
