package step_definitions;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import implementations.Checkout; //So that the Checkout class can be instantiated
import static org.junit.Assert.*;	//for the Junit AssertEqual method

public class CheckoutSteps{
	int bananaPrice; //Making it an instance variable so all methods can use it
	int applePrice;

	// Creating object here so every When call will update 
	Checkout checkout=new Checkout(); 

@Given("^the price of a \"([^\"]*)\" is (\\d+)c$")
public void thePriceOfAIsC(String name, int price) throws Throwable {
	if (name.equals("banana")){
		bananaPrice=price;
	}
	else {
		applePrice=price;
	}
		
    //throw new PendingException();
}

@When("^I checkout (\\d+) \"([^\"]*)\"$")
public void iCheckout(int itemCount, String itemName) throws Throwable {
	//checkout = new Checkout(); //instantiate checkout variable here
	//checkout.add(itemCount, itemPrice);
	if (itemName.equals("banana")){
	checkout.add(itemCount, bananaPrice);
	}
	else {
	checkout.add(itemCount, applePrice);
	}
    //throw new PendingException();
}

@Then("^the total price should be (\\d+)c$")
public void theTotalPriceShouldBeC(int total) throws Throwable {
	assertEquals(total, checkout.total());
    //throw new PendingException();
}	

}//end class
