/**
 *Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
 */
package examples.ejb.ejb30.web;

import examples.ejb.ejb30.ejbref.*;
import examples.ejb.ejb30.domain.Book;
import examples.ejb.ejb30.exceptions.BadCommentsException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.naming.InitialContext;
import javax.ejb.EJB;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.DecimalFormat;

/**
 * This is the client view of EJB Interceptor sample.
 */
public class InterceptorSampleServlet extends HttpServlet {
  @EJB
  private examples.ejb.ejb30.interceptors.InterceptedProcess service_;


  /**
   * Print the header of the HTML page
   *
   * @param request
   * @param out
   */
  private void printPageHeader(HttpServletRequest request, HttpServletResponse response, PrintWriter out) {
    try {
      request.getRequestDispatcher("/header.html").include(request, response);
    } catch (Exception e) {
      printErrorMsgs(out, e);
      return;
    }
    out.println("<link href=\"sample.css\" rel=\"stylesheet\"></link>");
    String tips = "This example demostrates the usage of EJB30 interceptors.<p>" +
        "The <a href='" + Constants.JAVA_SOURCE_BASE_DIR + "/web/InterceptorSampleServlet.html'>InterceptorSampleClientServlet</a> " +
        "references a simple stateless session bean <a href='" + Constants.JAVA_SOURCE_BASE_DIR + "/interceptors/InterceptedProcessBean.html'>InterceptedProcessBean</a>" +
        " which is annatoted with a interceptor <a href='" + Constants.JAVA_SOURCE_BASE_DIR + "/interceptors/AuditInterceptor.html'>AuditInterceptor<a/><p>" +
        "The InterceptedProcessBean selects a book for the guest reviewer to add comments randomly. When comments are" +
        " going to be persisted, the AuditInterceptor will make sure empty and impolite comments are filtered out.<HR>";
    out.println(tips);
  }

  /**
   * Print the table of the Books and input area
   *
   * @param out
   */
  private void printBookTable(PrintWriter out) {

    out.println("<form method='POST'><p>");
    String table = "<HR><DIV ALIGN='CENTER'>" +
        "<TABLE id='book_table' CELLPADDING='2' CELLSPACING='2' WIDTH='100%'>" +
        "<TR><TH>UID</TH><TH>Title</TH><TH>Author</TH><TH>Total Reviews</TH><TH>Rating</TH></TR>";
    out.println(table);

    Book book = service_.getRandomBook();

    int i = 0;
    NumberFormat numberFormat = new DecimalFormat("0.00");
    String author = (book.getArtist() == null) ? "" : book.getArtist().getName();
    out.println("<tr class='spec'>");
    out.println("<td>" + book.getId());
    out.println("<td>" + book.getTitle());
    out.println("<td>" + author);
    out.println("<td>" + book.getReviews().size());
    out.println("<td>" + numberFormat.format(book.getRating()));
    out.println("</tr>");
    out.println("<hr>");

    out.println("<tr>");
    out.println("<td colSpan='2'>");
    // write rating area
    out.println("Rating: " +
        "<select name='rating' size='1'>");
    for (int j = 1; j < 6; j++) {
      out.println("<option value='" + j + "'>" + j + "</option>");
    }
    out.println("</select>&nbsp;&nbsp;");
    out.println("</td>");
    out.println("<td colSpan='3'>");
    // write comments area
    out.println("Review Comments: (leave it empty or enter 'bitch' to see the validation made by the interceptor)" +
        "<textarea rows='5' name='comments' cols='40'></textarea> &nbsp;&nbsp;&nbsp;&nbsp;" +
        "<input type='submit' value='Submit' name='button'>");
    out.println("<input type='hidden' name='selectedID' value='" + book.getId() + "'>");
    out.println("</p></form>");
    out.println("</td>");
    out.println("</tr>");
    out.println("</TABLE>");
    out.println("<hr>");
    out.println("</body>");
    out.println("</html>");

  }


  private void printErrorMsgs(PrintWriter out, Throwable ex) {
    out.println("<p align='center'><font color='#FF0000'>" + ex.getMessage() + "</font></p>");
    out.println("You can use your browsers \"View Page Source\" to see the error stack trace.<BR>");
    out.println("<!--");
    ex.printStackTrace(out);
    out.println("-->");
  }

  /**
   * Get method
   *
   * @param request
   * @param response
   * @throws java.io.IOException
   * @throws javax.servlet.ServletException
   */
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {

    PrintWriter out = response.getWriter();
    response.setContentType("text/html");

    printPageHeader(request, response, out);
    printBookTable(out);
  }

  /**
   * Post method
   *
   * @param request
   * @param response
   * @throws java.io.IOException
   * @throws javax.servlet.ServletException
   */
  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    response.setContentType("text/html");
    String errorMsg = null;
    int rate = 0;
    int uid = 0;

    String bookid = request.getParameter("selectedID");
    if (bookid == null || bookid.trim().equals("")) {
      errorMsg = "Please select the book you want to review";
    } else {
      uid = Integer.parseInt(bookid);
    }

    String rating = request.getParameter("rating");
    String comments = request.getParameter("comments");

    //doGet(request, response);
    PrintWriter out = response.getWriter();
    response.setContentType("text/html");
    printPageHeader(request, response, out);

    try {
      rate = Integer.parseInt(rating);
      if (rate < 0 || rate > 5) {
        errorMsg = "Please input rating as a number between 0 and 5";
      }
    } catch (Exception e) {
      errorMsg = "Please input rating as a number";
    }

    // there is no error
    if (errorMsg == null) {
      try {
        service_.addReview(uid, "guest", rate, comments);
        out.println("<p align='center'>Your comments have been saved. Please review another randomly selected book.</p>");
      } catch (Exception ex) {
        printErrorMsgs(out, ex);
      }
    } else
      out.println("<p align='center'><font color='#FF0000'>" + errorMsg + "</font></p>");

    printBookTable(out);
  }


}
