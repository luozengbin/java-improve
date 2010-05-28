<%@ page import="examples.ejb.ejb30.ejb21client.ItemManagerHome" %>
<%@ page import="examples.ejb.ejb30.ejb21client.ItemManagerLocal" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="examples.ejb.ejb30.web.Constants" %>

<!-- ----------------------------------------------------------------------- -->
<!-- Add a book with Artists to the database using ejb21 client. -->
<!-- ----------------------------------------------------------------------- -->
<HTML>
<HEAD>
  <%@ page errorPage="exception.jsp" %>
  <link href="sample.css" rel="stylesheet">
</HEAD>

<BODY>
<%@ include file="header.html" %>
This example shows how to provide a Home and Component interface for EJB30 Session Bean so that
the Session Bean can be invoked by the EJB21 client.<p>

  This example defines a Home Interface <a href="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManagerHome.html">
  ItemManagerHome</a> and a Component Interface <a
    href="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManagerLocal.html">
  ItemManagerLocal </a> in addition to Business Interface <a
    href="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManager.html">
  ItemManager</a>. The session bean <a href="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManagerBean.html">ItemManagerBean</a>
  is
  annotated with Home Interface as well as the Business Interface.

<p>

<HR>
<table>

  <%

    String title = request.getParameter("title");
    String artistName = request.getParameter("name");
    boolean toAdd = true;

    if (title == null || title.trim().equals("")) {
      out.println("<tr><td colSpan='2'><font color=\"#FF0000\">Please input the title of the Book</font></td></tr>");
      toAdd = false;
    }

    if (artistName == null || artistName.trim().equals("")) {
      out.println("<tr><td colSpan='2'><font color=\"#FF0000\">Please input the artist name of the Book</font></td></tr>");
      toAdd = false;
    }

    if (toAdd) {

      InitialContext ctx = new InitialContext();
      ItemManagerHome home = (ItemManagerHome) ctx.lookup("java:comp/env/ejb/ItemManagerHome");
      ItemManagerLocal itemManagerIF = home.create();
      itemManagerIF.addBook(title, artistName);
      out.println("<CENTER><H2>The book <em>" + title + "</em> is added.</H2></CENTER>");

    } else {
  %>

  <CENTER>
    <FORM action="" method="post">
      <tr>
        <td>Book Title: </td>
        <td><input type="text" name="title" size="20"></td>
      </tr>
      <tr>
        <td>Artist Name: </td>
        <td><input type="text" name="name" size="20"></td>
      </tr>
      <tr>
        <td><input type="submit" value="Add Book"></td>
        <td><input type="reset"></td>
      </tr>
    </FORM>
  </CENTER>
</table>
<%
  }
%>

</BODY>
</HTML>
