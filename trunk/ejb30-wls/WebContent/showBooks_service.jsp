<!-- ----------------------------------------------------------------------- -->
<!-- Shows all the Books in the database. -->
<!-- ----------------------------------------------------------------------- -->
<HTML>
<HEAD>
  <%@ page errorPage="exception.jsp" %>
  <link href="sample.css" rel="stylesheet"></link>
</HEAD>

<BODY>
<%@ include file="header.html" %>

This example issues query using Java Persistence Query Language to select
all books in the database.<p>
  The query is: <B><pre>SELECT i FROM Book i</pre></B>
<HR>

<%@ include file="common_service.jsp" %>
<CENTER><H2>List of books</H2></CENTER>
<HR>

<DIV ALIGN="CENTER">
  <TABLE ID="book_list" CELLPADDING="2" CELLSPACING="2" WIDTH="100%">
    <TR>
      <TH>UID</TH>
      <TH>Title</TH>
      <TH>Author</TH>
      <TH>Total Reviews</TH>
      <TH>Rating</TH>
    </TR>
    <!-- ---------------------------------------------------------------------- -->
    <!-- Calls the service to get the entire extent of Book.                    -->
    <!-- and then iterates through the listed books to display one per row.     -->
    <!-- ---------------------------------------------------------------------- -->
    <%
      List<Book> books = service.getAll(Book.class);
      int i = 0;
      for (Book book : books) {
        i++;
        String style = (i % 2 == 0) ? "spec" : "specalt";
        String author = (book.getArtist() == null)
            ? "" : book.getArtist().getName();
    %>
    <tr class="<%=style%>">
      <td> <%=book.getId()%>
      <td> <%=book.getTitle()%>
      <td> <%=author%>
      <td> <%=book.getReviews().size()%>
      <td>  <%=numberFormat.format(book.getRating())%>

        <%

         }

        %>
  </TABLE>
</DIV>

</BODY>
</HTML>
