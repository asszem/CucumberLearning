package nicebank.code;

import nicebank.helpers.Money;

public interface Teller {
	public String withdrawFrom(Account account, Money money);

}
