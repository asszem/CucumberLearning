package nicebank.code;

import nicebank.helpers.Money;

public class CashSlot {
	private Money inSlotContent=new Money();
	private Money atmBalance = new Money(5555,55);
	private String atmMessage="ATM is currently operational";

	public Money getSlotContents() { // return the content (the actual money being in the cash slot of the machine)
		return inSlotContent;
	}

	public void dispense(Money amount) {
		inSlotContent = amount;
		atmBalance=atmBalance.minus(amount);
	}
	
	public void setATMBalance(Money amount){
		atmBalance=amount;
	}

	public Money getATMBalance(){
		return atmBalance;
	}
	
	public void setMessage(String message){
		this.atmMessage=message;
	}
	public String getMessage(){
		return atmMessage;
	}
	

}
