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
 
public class AtmServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
                                                throws ServletException, IOException
    {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(
        "<html><head><title>ATM</title></head>" +
        "<body>"
        + "<form action=\"/withdraw\" method=\"post\">" +
        "<label for=\"amount\">Amount</label>" +
        "<input type=\"text\" id=\"amount\" name=\"amount\">" +
        "<button type=\"submit\" id=\"withdraw\">Withdraw</button>" +
        "<button type=\"submit\" id=\"withdrawFixed\" fixedvalue=\"$25.00\">Withdraw $25.00</button>" +
       "</form>"+
        "<form action=\"displayBalance\" method=\"post\">"+
        "<button type=\"submit\" id=\"displayBalance\" >Display your balance</button>" +
        "</form></body></html>");

    }
}