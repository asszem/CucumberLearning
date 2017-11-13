package src.main.java.nicebank;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.test.java.support.Helper;

public class DisplayBalanceServlet extends HttpServlet {

	private CashSlot cashSlot;
	private Account account;

	public DisplayBalanceServlet(CashSlot cashSlot, Account account) {	
		this.cashSlot=cashSlot;
		this.account=account;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//At this point we should set the ATM's msg field to the user balance
		String userBalance = account.getBalance().toString();
		cashSlot.setMessage("User balance is: "+userBalance);
		
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<html><head><title>Nice Bank ATM Display Balance</title></head>"
				+ "<body>Display User Account Balance" 
						+ "<br>Account Balance according to cashslot: : " + cashSlot.getMessage()
						+ "</body>" + "</html>");
	}
}
