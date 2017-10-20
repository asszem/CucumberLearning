package nicebank.code;

import nicebank.helpers.Money;

public class AtmUserInterface implements Teller {

	private CashSlot cashSlotInterface;
	private Account accountHandledByAtm;

	public AtmUserInterface(CashSlot cashSlot, Account account) {
		cashSlotInterface = cashSlot;
		accountHandledByAtm=account;
	}
	
	@Override
	public String withdrawFrom(Account account, Money amount) {
		return null;
	}

}
