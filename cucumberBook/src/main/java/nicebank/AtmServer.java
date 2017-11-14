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
import src.test.java.support.Helper;

public class AtmServer {
	private final Server server;
	private Helper helper;

	public AtmServer(int port, Helper helper) { // Pass the cashSholt and Account info to the
																		// server
		this.helper=helper;
		server = new Server(ServerHooks.PORT);

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new AtmServlet()), "/*");

		// Servlets now handle new CashSlot and Account instances
		context.addServlet(new ServletHolder(new WithdrawalServlet(helper.getCashSlot(), helper.getMyAccount())), "/withdraw");
		context.addServlet(new ServletHolder(new DisplayBalanceServlet(helper.getCashSlot(),helper.getMyAccount())), "/displayBalance");
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
		// Javalite - Base class is used to ask ActiveJDBC to open a connection to MySql
		// database called "bank"
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/bank", "teller", "password");

		// This creates a new server instance and creates new CashSlot and Account
		// instances
		new AtmServer(9988, new Helper()).start();

		/*
		 * <!-- Liquibase - Database management --> <groupId>org.liquibase</groupId>
		 * <artifactId>liquibase-maven-plugin</artifactId> <!-- 3.0.5 -->
		 * <version>3.5.3</version> <configuration>
		 * <changeLogFile>src/main/resources/bank_schema.xml</changeLogFile>
		 * <driver>com.mysql.jdbc.Driver</driver> <url>jdbc:mysql://localhost/bank</url>
		 * <username>teller</username> <password>password</password> </configuration>
		 */

	}
	// public static void main(String[] args) throws Exception {
	// new AtmServer(ServerHooks.PORT, cashSlot, account).start();
	// }
}
