/***
 * Excerpted from "The Cucumber for Java Book",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/srjcuc for more book information.
***/
package src.main.java.nicebank;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.javalite.activejdbc.Base;

import src.test.java.hooks.ServerHooks;
import src.test.java.support.KnowsTheAccount;

public class AtmServer {
	private final Server server;
	CashSlot cashSlotInjected;
	KnowsTheAccount accountHelper;

	// Constructor 1 - cashslot and account retrieved from injected KnowsTheXxx objects
	public AtmServer(int port, CashSlot cashSlotInjected, KnowsTheAccount knowsTheAccountInjected) {
		this.cashSlotInjected = cashSlotInjected;
		this.accountHelper = knowsTheAccountInjected;
		server = new Server(ServerHooks.PORT);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new AtmServlet()), "/*");

		// Servlets now handle new CashSlot and Account instances
		context.addServlet(new ServletHolder(new WithdrawalServlet(cashSlotInjected, accountHelper.getMyAccount())),
				"/withdraw");
		context.addServlet(new ServletHolder(new DisplayBalanceServlet(cashSlotInjected, accountHelper.getMyAccount())),
				"/displayBalance");
	}

	// Constructor 2 - cashSlot and account insantiated in the main method
	public AtmServer(int port, CashSlot cashSlot, Account account) {
		server = new Server(ServerHooks.PORT);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new AtmServlet()), "/*");

		// Servlets now handle new CashSlot and Account instances
		context.addServlet(new ServletHolder(new WithdrawalServlet(cashSlot, account)), "/withdraw");
		context.addServlet(new ServletHolder(new DisplayBalanceServlet(cashSlot, account)), "/displayBalance");
	}

	public void start() throws Exception {
		server.start();
		System.out.println("ATM Server Started. Listening on " + server.getURI());
	}

	public void stop() throws Exception {
		server.stop();
		System.out.println("ATM Server stopped");
	}

	public static void main(String[] args) throws Exception {
		// Javalite (activeJDBC) - Base class is used to ask ActiveJDBC to open a connection to MySql database called "bank"
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/bank", "teller", "password");

		// This creates a new server instance with new CashSlot and Account instances
		int portForServer = 9988;
		int accountNumber = 5678;
		new AtmServer(portForServer, new CashSlot(), new Account(accountNumber)).start();

		// Starts the server with a new Helper instance
		// new AtmServer(9988, new Helper()).start();

		/*
		 * <!-- Liquibase - Database management --> <groupId>org.liquibase</groupId> <artifactId>liquibase-maven-plugin</artifactId> <!-- 3.0.5 -->
		 * <version>3.5.3</version> <configuration> <changeLogFile>src/main/resources/bank_schema.xml</changeLogFile> <driver>com.mysql.jdbc.Driver</driver>
		 * <url>jdbc:mysql://localhost/bank</url> <username>teller</username> <password>password</password> </configuration>
		 */

	}
	// public static void main(String[] args) throws Exception {
	// new AtmServer(ServerHooks.PORT, cashSlot, account).start();
	// }
}
