package nicebank.code;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nicebank.helpers.Helper;

public class DisplayBalanceServlet extends HttpServlet {

	private Helper helper;

	public DisplayBalanceServlet(Helper helper ) {
		this.helper=helper;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//At this point we should set the ATM's msg field to the user balance
		String userBalance = helper.getMyAccount().getBalance().toString();
		helper.getCashSlot().setMessage("User balance is: "+userBalance);
		
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<html><head><title>Nice Bank ATM Display Balance</title></head>"
				+ "<body>Display User Account Balance" 
						+ "<br>Account Balance: : " + helper.getCashSlot().getMessage()
						+ "</body>" + "</html>");
	}
}
