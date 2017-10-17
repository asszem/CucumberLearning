package nicebank.helpers;

public class CashSlot {
	private Money inSlotContent;

	public Money getSlotContents() { // return the content (the actual money being in the cash slot of the machine)
		return inSlotContent;
	}

	public Money dispense(Money amount) {
		inSlotContent = amount;
		return amount;
	}

}
