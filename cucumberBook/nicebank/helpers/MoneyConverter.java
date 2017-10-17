/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package nicebank.helpers;

import cucumber.api.Transformer;

//Converts the String input to dollar and cent value and returns a new Money class instance with the dollar/cent values
public class MoneyConverter extends Transformer<Money> {
	public Money transform(String amount) {
		
		//This ignores the first character (the $ sign) that is also included in the capture group
		String[] numbers = amount.substring(1).split("\\.");

		int dollars = Integer.parseInt(numbers[0]);
		int cents = Integer.parseInt(numbers[1]);

		return new Money(dollars, cents);
	}
}
