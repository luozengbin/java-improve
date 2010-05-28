<!-- ----------------------------------------------------------------------- -->
<!-- Adds a review to a randomly seleceted Book by a randomly selected -->
<!-- Reviewer. -->
<!-- Displays the review headers for the Book and all the reviewes by the -->
<!-- Reviewer before and after the operation. -->
<!-- -->
<!-- Request parameter -->
<!-- rating an integer between 1 and 5, both inclusive -->
<!-- comment a string as review comment -->
<!-- ----------------------------------------------------------------------- -->
<HTML>
<HEAD>
  <%@ page errorPage="exception.jsp" %>
  <link href="sample.css" rel="stylesheet"></link>
</HEAD>

<BODY>
<%@ include file="header.html" %>
<%@ include file="common_service.jsp" %>

This example creates a new Review. A review maintains two references: one to
the item being reviewed and other to the reviewer who created it. <p>
On the other hand, the same review is maintained both by the
Reviewer as the list of reviewes s/he has created as well as by the item as
the list of reviews.<p>

This example demonstrates how such relationship integrity is maintained when
creating an instance that is linked to multiple instances.
<HR>


<%

  List<Book>     books = service.getAll(Book.class);
  if (books == null || books.isEmpty()) {
    out.println("Found no book in the database");
    return;
  }

  List<Reviewer> reviewers = service.getAll(Reviewer.class);
  if (reviewers == null || reviewers.isEmpty()) {
    out.println("Found no book in the database");
    return;
  }

  java.util.Random random = new Random(System.currentTimeMillis());
  Book book = books.get(random.nextInt(books.size()));
  Reviewer reviewer = reviewers.get(random.nextInt(reviewers.size()));

  List<Review> reviewsOfBook = book.getReviews();
  List<Review> reviewsByReviewer = reviewer.getReviews();
%>
<HR>
Selected Reviewer <em><%=reviewer.getName()%></em> out of
<em><%=reviewers.size()%></em> existing reviewers<BR>
Selected Book <em><%=book.getTitle()%></em> out of
<em><%=books.size()%></em> existing books<BR>
<HR>

<HR>
<CENTER>Before adding this review, <em><%=book.getTitle()%></em> had
  <em><%=reviewsOfBook.size()%></em> reviews</CENTER><BR>

<%
  if (reviewsOfBook != null && !reviewsOfBook.isEmpty()) {
%>
<DIV ALIGN="CENTER">
  <TABLE class="table">
    <TR><TH>Reviewer</TH><TH>Date</TH><TH>Rating</TH>
      <%

               int i = 0;
               for (Review r:reviewsOfBook)
               {
                  i++;
                  String style = (i%2==0) ? "spec" : "specalt";

      %>
    <TR class=<%=style%>>
      <TD><%=r.getReviewer().getName()%></TD>
      <TD><%=dateFormat.format(r.getCreatedDate())%></TD>
      <TD><%=r.getRating()%></TD>
      <%

              }

      %>
  </TABLE>
</DIV>
<%
  }
%>


<BR>
<CENTER>Before adding this review, <em><%=reviewer.getName()%></em> had
  <em><%=reviewsByReviewer.size()%></em> reviews.</CENTER><BR>


<%
  if (reviewsByReviewer != null && !reviewsByReviewer.isEmpty()) {
%>
<DIV ALIGN="CENTER">
  <TABLE WIDTH="80%" TABLEBORDER=1>
    <TR><TH>Item</TH><TH>Date</TH><TH>Rating</TH>
      <%

               int i = 0;
               for (Review r:reviewsByReviewer)
               {
                  i++;
                  String style = (i%2==0) ? "spec" : "specalt";

      %>
    <TR class=<%=style%>>
      <TD><%=r.getReviewed().getTitle()%></TD>
      <TD><%=dateFormat.format(r.getCreatedDate())%></TD>
      <TD><%=r.getRating()%></TD>
      <%

              }

      %>
  </TABLE>
</DIV>
<%
  }
%>


<%
  String comment = request.getParameter("comment");
  int rating = Integer.parseInt(request.getParameter("rating"));
%>
<HR>
  Will add a review of <em><%=book.getTitle()%></em> by
  <em><%=reviewer.getName()%></em> with <p>
  rating <em><%=rating%></em> and comment "<em><%=comment%></em>".
</HR>
<%
  service.newReview(reviewer, book, rating, comment);

  List<Review> newReviewsOfBook = book.getReviews();
  List<Review> newReviewsByReviewer = reviewer.getReviews();
%>

<CENTER>New review has now been added</CENTER>
<BR>

<CENTER>After adding this review, <em><%=book.getTitle()%></em> has
  <em><%=newReviewsOfBook.size()%></em> reviews</CENTER><BR>

<DIV ALIGN="CENTER">
  <TABLE WIDTH="80%" TABLEBORDER=1>
    <TR><TH>Reviewer</TH><TH>Date</TH><TH>Rating</TH>
      <%

             int i = 0;
             for (Review r:newReviewsOfBook)
             {
                i++;
                  String style = (i%2==0) ? "spec" : "specalt";

      %>
    <TR class=<%=style%>>
      <TD><%=r.getReviewer().getName()%></TD>
      <TD><%=dateFormat.format(r.getCreatedDate())%></TD>
      <TD><%=r.getRating()%></TD>
      <%

        }

      %>
  </TABLE>
</DIV>
<BR>

<CENTER>After adding this review <em><%=reviewer.getName()%></em> has
</em><%=newReviewsByReviewer.size()%></em> reviews.</CENTER><BR>

<DIV ALIGN="CENTER">
  <TABLE WIDTH="80%" TABLEBORDER=1>
    <TR><TH>Item</TH><TH>Date</TH><TH>Rating</TH>
      <%

              i = 0;
             for (Review r:newReviewsByReviewer)
             {
                i++;
                  String style = (i%2==0) ? "spec" : "specalt";

      %>
    <TR class=<%=style%>>
      <TD><%=r.getReviewed().getTitle()%></TD>
      <TD><%=dateFormat.format(r.getCreatedDate())%></TD>
      <TD><%=r.getRating()%></TD>
      <%

        }

      %>
  </TABLE>
</DIV>
</BODY>
</HTML>
