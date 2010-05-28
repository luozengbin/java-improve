<%@ page import="examples.ejb.ejb30.service.SeedDatabase"%>
<HTML>
<HEAD>
<%@ page errorPage="exception.jsp" %>
<link href="sample.css" rel="stylesheet"></link>
</HEAD>
<BODY>
    <%@ include file="common_service.jsp" %>
    <%
		SeedDatabase populator = new SeedDatabase(emf);
		populator.ensureDatabaseInitialzed();
    %>
</BODY>
</HTML>
