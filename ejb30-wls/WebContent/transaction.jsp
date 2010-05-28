<HTML>
<HEAD>
<%@ page errorPage="exception.jsp" %>
<link href="sample.css" rel="stylesheet"></link>
</HEAD>
<BODY>
   <%@ include file="header.html" %>

     This example starts a local transaction and commits it. This is to just
     verify Persistence Provider Runtime is set up correctly.
     <HR>

     <%@ include file="common_service.jsp" %>

     <H2>Testing Transaction</H2><BR>

     <%
        out.print(new java.util.Date() + ": Starting a transaction...");
        service.getEntityManager().getTransaction().begin();
        out.println("started<p>");
        service.getEntityManager().getTransaction().commit();
        out.println(new java.util.Date() + ": Committed the transaction...");
     %>
  </BODY>
</HTML>     
