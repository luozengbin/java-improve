<!-- ----------------------------------------------------------------------- -->
<!-- Adds a new Artist to the database. -->
<!-- After creating the new instance, all Artists are fetched and newly -->
<!-- created artist is highlighted. -->
<!-- -->
<!-- Request parameter -->
<!-- name a string as the artist's name -->
<!-- ----------------------------------------------------------------------- -->
<HTML>
<HEAD>
  <%@ page errorPage="exception.jsp" %>
  <link href="sample.css" rel="stylesheet"></link>
</HEAD>

<BODY>
<%@ include file="header.html" %>
<%@ include file="./common_service.jsp" %>

This example creates a new Artist. The only parameter required to create an
Artist is his/her name. After the artist has been persisted, all the artists
are listed with the newly created Artist highlighted.

<H2>Create an artist to store in the database</H2>

<BR>


<%
  String name = request.getParameter("name");
  Artist artist = service.newArtist(name);
  List<Artist> artists = service.getAll(Artist.class);
%>
<TABLE class="table" width="100%">
  <TR>
    <TH>Name</TH>
  </TR>
  <%

    for (int i = 0; i < artists.size(); i++) {
      Artist row = artists.get(i);
      String style = (i % 2 == 0) ? "spec" : "specalt";
      if (row.getName().equals(name))
        style = "highlighted";
  %>
  <tr class="<%=style%>"><td><%=row.getName()%></td></tr>
  <%
    }
  %>
</TABLE>


</BODY>
</HTML>
