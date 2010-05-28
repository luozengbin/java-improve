<!-- ----------------------------------------------------------------------- -->
<!--  Shows Musics that has artist name and duration                         -->
<!-- ----------------------------------------------------------------------- -->
<HTML>
<HEAD>
<%@ page errorPage="exception.jsp" %>
<link href="sample.css" rel="stylesheet"></link>
</HEAD>
<BODY>
    <%@ include file="header.html" %>
    <%@ include file="common_service.jsp" %>

    <%
      String jpql     = "SELECT i FROM Music i " +
                        "WHERE i.artist.name LIKE :artist " +
                        "AND i.duration >= :duration";
       String artist  = "John%";
       int duration   = 3;
    %>

    This example issues a query in Java Persistence Query Language to select
    any Music whose singer name matches regular expression <%=artist%> and its
    duration is more than <%=duration%> minutes.<p>
    The JPQL query is: <code><%=jpql%></code>
    <p>
    The parameters <code>:artist</code> and <code>:duration</code> are bound at
    runtime.
    <HR>
    <CENTER><H2>
                List of Music whose singer matches '<%=artist%>'
                and duration is greater than <%=duration%> minutes

    </H2></CENTER>
    <HR>

     <DIV ALIGN="CENTER">
     <TABLE CELLPADDING="2" CELLSPACING="2" WIDTH="100%">
	 <TR>
		<TH>Title</TH>
		<TH>Singer</TH>
		<TH>Duration</TH>
	 </TR>

     <%
         EntityManager em = service.getEntityManager();
         Query query      = em.createQuery(jpql);
         query.setParameter("artist", artist);
         query.setParameter("duration", duration);

         List<Music> selected   = query.getResultList();
         int i = 0;
         for (Music music:selected){
            i++;
            String style   = (i%2==0) ? "spec" : "specalt";
            String singer  = (music.getArtist()==null)
                           ? "" : music.getArtist().getName();
     %>
     <tr class="<%=style%>">
     <td>  <%=music.getTitle()%>
     <td>  <%=singer%>
     <td>  <%=music.getDuration()%>

     <%
      }
     %>
      </TABLE>
     </DIV>
  </BODY>
</HTML>
