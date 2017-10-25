/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package nicebank.code;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import nicebank.helpers.Helper;
import nicebank.hooks.ServerHooks;

public class AtmServer {
	private final Server server;

	public AtmServer(int port, Helper helper) { // Pass the cashSholt and Account info to the
																		// server
		server = new Server(ServerHooks.PORT);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new AtmServlet()), "/*");
		context.addServlet(new ServletHolder(new WithdrawalServlet(helper)), "/withdraw");
		context.addServlet(new ServletHolder(new DisplayBalanceServlet(helper)), "/displayBalance");
	}

	public void start() throws Exception {
		server.start();
		System.out.println("ATM Server Started. Listening on " + server.getURI());
	}

	public void stop() throws Exception {
		server.stop();
		System.out.println("ATM Server stopped");
	}

	// public static void main(String[] args) throws Exception {
	// new AtmServer(ServerHooks.PORT, cashSlot, account).start();
// }
}
