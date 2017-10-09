package implementations;

public class Checkout {

private int checkoutTotal=0;

	public void add(int count, int price){
		checkoutTotal+=count*price;
		//System.out.println("checkout total:"+checkoutTotal);
	}

	public int total(){
		return checkoutTotal;
	}
}
