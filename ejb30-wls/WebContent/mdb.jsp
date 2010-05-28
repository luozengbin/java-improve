<!-- ----------------------------------------------------------------------- -->
<!-- MDB sample page -->
<!-- ----------------------------------------------------------------------- -->
<HTML>
<HEAD>
  <%@ page import="examples.ejb.ejb30.domain.Book,
                   examples.ejb.ejb30.domain.Review,
                   examples.ejb.ejb30.domain.Reviewer,
                   examples.ejb.ejb30.service.ReviewService,
                   javax.naming.Context,
                   javax.naming.InitialContext"
           errorPage="exception.jsp" %>
  <%@ page import="java.util.List" %>
  <%@ page import="java.util.Random" %>
  <%@ page import="examples.ejb.ejb30.web.Constants"%> <link href="sample.css" rel="stylesheet">
</HEAD>

<BODY>
<%@ include file="header.html" %>

<%
  String SERVICE_NAME = "ReviewManagerBean";
  ReviewService service = (ReviewService) session.getAttribute(SERVICE_NAME);
  if (service == null) {
    Context ctx = new InitialContext();
    service = (ReviewService) ctx.lookup("java:comp/env/ejb/ReviewManager");
    session.setAttribute(SERVICE_NAME, service);
  }
%>


<HR>


<%
  java.util.Random random = new Random(System.currentTimeMillis());
  // randomly select a book.
  List<Book> books = service.getAll(Book.class);
  if (books == null || books.isEmpty()) {
    out.println("Found no book in the database");
    return;
  }
  Book book = books.get(random.nextInt(books.size()));

  //randomly select a subscriber for the current session.
  List<Reviewer> reviewers = service.getAll(Reviewer.class);
  if (reviewers == null || reviewers.isEmpty()) {
    out.println("Found no book in the database");
    return;
  }
  String watcherName = (String) session.getAttribute("watcher");
  Reviewer watcher = null;
  if (watcherName == null) {
    watcher = reviewers.get(random.nextInt(reviewers.size()));
    session.setAttribute("watcher", watcher.getName());
  } else {
    watcher = service.getReviewer(watcherName,false);
  }

  //randomly select a reviewer differnt from the subscriber.
  int index = random.nextInt(reviewers.size());
  Reviewer reviewer = reviewers.get(index);
  if(reviewer.getName().equals(watcherName)){
     reviewer = reviewers.get((index+1)/reviewers.size());
  }

  //let the subscriber watchs new reivews on the given item.
  service.subscribe(watcher.getName(), book.getId());
  //let the review make a review on the same book.
  Review newReview = service.newReview(reviewer, book, 3, "it's a good book ( a review only for testing made by "+reviewer.getName()+" ).");


  Thread.sleep(1000 * 10);
  List<Review> myReviews = service.getFeededReviews(watcher.getName());
%>
<p>

  This example demonstrates the simplied Message Driven Bean programming model in EJB3.0.<BR/> <BR/>
  A randomly selected reviewer '<%=watcher.getName()%>'  first subsribes new reviews on a randomly selectd book '<%=book.getTitle()%>';
  Another randomly reviewer <%=reviewer.getName()%> adds a review on the selected book '<%=book.getTitle()%>'.<BR/><BR/>
  ReviewerManagerBean persists the Review into database and sends a JMS message carrying the review id into JMS queue.<BR/><BR/>
  The <a href="<%=Constants.JAVA_SOURCE_BASE_DIR%>/mdb/WatchProcessMDB.html">WatchProcessMDB</a> feeds the newly added reviews into all subscribers of '<%=book.getTitle()%>'. <BR/><BR/>
  Then all <%=watcher.getName()%>'s feeds is listed below.The new feed is highlighted. <BR/>
<HR>
<CENTER>
  <BR>

  <TABLE  id="feeds-table" CELLPADDING="2" CELLSPACING="2" WIDTH="100%">
    <TR>
      <TH ALIGN="LEFT" WIDTH="20%">Reviwer</TH>
      <TH ALIGN="LEFT" WIDTH="25%">Title</TH>
      <TH ALIGN="LEFT" WIDTH="15%">Score</TH>
      <TH ALIGN="LEFT" WIDTH="40%">Comments</TH>
    </TR>

    <%
      int k = 0;
      for (Review myRev : myReviews) {
        StringBuffer sb = new StringBuffer();
        String style   = (k++%2==0) ? "spec" : "specalt";
        if(myRev.getId()==newReview.getId()) {
          style = "highlighted";
        }
        sb.append("<TR CLASS='").append(style).append("'><TD>").append(myRev.getReviewer().getName());
        sb.append("</TD><TD>").append(myRev.getReviewed().getTitle());
        sb.append("</TD><TD ALIGN='RIGHT'>").append(myRev.getRating());
        sb.append("</TD><TD>").append(myRev.getComment()).append("</TD></TR>");
        out.println(sb);
      }
    %>

  </TABLE>

</CENTER>
</BODY>
</HTML>
