/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package nicebank.code;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nicebank.helpers.Helper;
import nicebank.helpers.Money;
import nicebank.helpers.MoneyConverter;

//This class is called by the withdrawFrom method of AtmUserInterface class
//By clicking on the Submit button on the webpage (AtmServlet)
//That will load the WithdrawalServlet (this class)
public class WithdrawalServlet extends HttpServlet {

	private CashSlot cashSlot;
	private Account account;

	public WithdrawalServlet(CashSlot cashSlot, Account account ) {
		this.cashSlot = cashSlot;
		this.account = account;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//A new teller instance is required, because the Helper class will return the AtmUserInterface teller
		Teller automatedTeller = new AutomatedTeller(cashSlot, account);
		String amountEnteredToATM = request.getParameter("amount");
		Money withdrawnAmount = new MoneyConverter().transform(amountEnteredToATM);
		
		//The automated teller's withdrawFrom method is called which will do the actual withdrawal
		automatedTeller.withdrawFrom(account, withdrawnAmount);

		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<html><head><title>Nice Bank ATM WITHDRAWAL</title></head>"
				+ "<body>Please take your " + withdrawnAmount.toString() + " Thank you! " 
						+ "<br>Cashslot message: "+ cashSlot.getMessage()
						+ "<br>Dispensed amount: " + cashSlot.getSlotContents()
						+ "<br>User account balance : " + account.getBalance().toString()
						+ "</body>" + "</html>");
	}
}