package src.main.java.nicebank;

public interface Teller {
	String withdrawFrom(Account account, Money amount);

	public String displayBalance(Account account);

}
