<%@ page import="examples.ejb.ejb30.web.Constants"%>
<HTML>
<BODY>
<TABLE WIDTH="100%">
<TR>
  <TD COLSPAN=6 ALIGN="left"><H1>EJB3 Sample Application</H1></TD>
  <TD ALIGN="right"><IMG SRC="images/bea_logo.jpg"></TD>
<TR>
    <TD><A HREF="index.jsp">Home</A></TD>
    <TD><A HREF="guideJPA.html">EJB3+JPA </A><TD>
    <TD><A HREF="guideSession.html">EJB3+Session Bean</A></TD>
    <TD><A HREF="guideMDB.html">Message Driven Bean</A></TD>
    <TD><A HREF="guideAJAX.html">EJB3+JPA+AJAX</A></TD>
    <TD><A HREF="viewCode.jsp">See code/resources</A></TD>
</TABLE>
<HR>
<CENTER><H2>Browse example <A HREF="#CodeTable">code</A> or
                           <A HREF="#Resources">resources</A>
</H2></CENTER><BR>
<DIV ALIGN="CENTER">
<A name="CodeTable"/>
<TABLE CELLSPACING=2>
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>JSPs that invoke JPA directly</H3></TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/common_service.html">common_service.jsp</A><TD>Obtains a EntityManagerFactory reference by direct construction</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/transaction.html">transaction.jsp</A><TD>Starting a explicit transaction with direct JPA</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/createArtist_service.html">createArtist_service.jsp</A><TD>Create a new Artist via direct JPA</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/addReview_service.html">addReview_service.jsp</A><TD>Adds a new Review via direct JPA</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/showBooks_service.html">showBooks_service.jsp</A><TD>JPQL queries via direct JPA</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/showSpecificMusic_service.html">showSpecificMusic_service.jsp</A><TD>JPQL queries with predicate via direct JPA</TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>JSPs that invoke Session Bean</H3></TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/common_session.html">common_session.jsp</A><TD>Obtains a Session Bean injected with a EntityManagerFactory from JNDI</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/createArtist_session.html">createArtist_session.jsp</A><TD>Create a new Artist via Session Bean</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/addReview_session.html">addReview_session.jsp</A><TD>Adds a new Review via Session Bean</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/showBooks_session.html">showBooks_session.jsp</A><TD>JPQL queries via Session Bean</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/showSpecificMusic_session.html">showSpecificMusic_session.jsp</A><TD>JPQL queries with predicate via Session Bean</TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/addBooks_session_ejb21.html">addBooks_session_ejb21.jsp</A><TD>Add a new book via Session Bean with a ejb21 client view. </TD>
<TR><TD><A HREF="<%=Constants.JSP_SOURCE_BASE_DIR%>/mdb.html">mdb.jsp</A><TD>Trigger the Message Driven Bean to be invoked. </TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Persistent EJBs</H3></TD> 
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Item.html">Item.java</A><TD>Defines persistent and abstract Item EJB. A root of inheritance hierarchy with SINGLE_TABLE mapping</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Book.html">Book.java</A><TD>Defines persistent Book EJB</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Music.html">Music.java</A><TD>Defines persistent Music EJB</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Movie.html">Movie.java</A><TD>Defines persistent Movie EJB</TD>
<TR><TD>&nbsp;
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Person.html">Person.java</A><TD>Defines persistent Person EJB. Another root of inheritance hierarchy with different mapping strategy</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Artist.html">Artist.java</A><TD>Defines persistent Artist EJB</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Reviewer.html">Reviewer.java</A><TD>Defines persistent Reviewer EJB</TD>
<TR><TD>&nbsp;
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/domain/Review.html">Review.java</A><TD>Defines persistent Review EJB. Shows examples of relationship mapping</TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Functions on Persistent EJBs using direct JPA</H3></TD> 
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/service/PersistenceService.html">PersistenceService.java</A><TD>wraps EntityManagerFactory to provide EntityManager per-thread</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/service/ReviewService.html">ReviewService.java</A><TD>Defines business functions on EJB domain model</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/service/ReviewServiceImpl.html">ReviewServiceImpl.java</A><TD>Implements business functions on EJB domain model</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/service/SeedDatabase.html">SeedDatabase.java</A><TD>Populates a database with EJB domain model via direct JPA</TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Session Bean on Persistent EJBs injected with EntityManager</H3></TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/session/ReviewManagerBean.html">ReviewManagerBean.java</A><TD>A Stateless Session Bean that implements a business interface functions on EJB domain model</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/session/ReviewStatManager.html">ReviewStatManager.java</A><TD>Interface defines function calulating the items and reviews added during one session.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/session/ReviewStatManagerBean.html">ReviewStatManagerBean.java</A><TD>A stateful Session Bean that implements ReviewStatManager interface </TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Session Bean with the usage of EJB Reference </H3></TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejbref/Process.html">Process.java</A><TD>Defines a business interface adding reviews on a randomly selected Book.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejbref/ProcessBean.html">ProcessBean.java</A><TD>A Stateless Session Bean which references EJB <a href="<%=Constants.JAVA_SOURCE_BASE_DIR%>/mdb/ReviewListener.html">ReviewListenerBean</a> to broadcast that a new review is added. </TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Message Driven Bean</H3></TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/mdb/WatchProcessMDB.html">WatchProcessMDB.java</A><TD>A Message Driven Bean invoked when a new review is added.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/mdb/SubscriptionService.html">SubscriptionService.java</A><TD>A POJO is used by the WatchProcessMDB to feed reviews into subscriber.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/mdb/ReviewListener.html">ReviewListener.java</A><TD>Listener interface for new added reviews.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/mdb/ReviewListenerBean.html">ReviewListenerBean.java</A><TD>A stateless Session Bean which send a jms message carring new review's id into exmaple jms queue.</TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Application Execeptions</H3></TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/exceptions/ObjectExistedException.html">ObjectExistedException.java</A><TD>unchecked RuntimeException annoatated as application execption.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/exceptions/ObjectNotFoundException.html">ObjectNotFoundException.java</A><TD>unchecked RuntimeException annoatated as application execption.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/exceptions/NotLoggedInException.html">NotLoggedInException.java</A><TD>Checked Exception.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/exceptions/BadCommentsException.html">BadCommentsException.java</A><TD>unchecked RuntimeException annoatated as application execption.</TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>EJB Interceptors</H3></TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/interceptors/AuditInterceptor.html">AuditInterceptor.java</A><TD>Interceptor class including a business method interceptor methoda and lifecycle callback interceptor method annotated with @PreDestroy.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/interceptors/InterceptedProcess.html">InterceptedProcess.java</A><TD>Business interface for the EJB interceptor sample.</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/interceptors/InterceptedProcessBean.html">InterceptedProcessBean.java</A><TD>A stateless Session Bean annotated with a interceptor to valid comments being added.</TD>
<TR><TD>&nbsp;
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Session Bean with EJB21 client view.</H3></TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManager.html">ItemManager.java</A><TD>Business Interface</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManagerHome.html">ItemManagerHome.java</A><TD>Home Interface</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManagerLocal.html">ItemManagerLocal.java</A><TD>Component Interface</TD>
<TR><TD><A HREF="<%=Constants.JAVA_SOURCE_BASE_DIR%>/ejb21client/ItemManagerBean.html">ItemManagerBean.java</A><TD>A stateless session bean annotated with Home and Business interface.</TD>
<TR><TD>&nbsp;
<A name="Resources"/>
<TR><TD COLSPAN=2 ALIGN="CENTER" BGCOLOR="#CCCCCC"><H3>Deployment Descriptors</H3></TD>
<TR><TD><A HREF="<%=Constants.RESOURCE_SOURCE_BASE_DIR%>/application.xml">application.xml</A><TD>Deployment Descriptor for the application</TD>
<TR><TD><A HREF="<%=Constants.RESOURCE_SOURCE_BASE_DIR%>/web.xml">web.xml</A>                <TD>Deployment Descriptor for the web module</TD>
<TR><TD><A HREF="<%=Constants.RESOURCE_SOURCE_BASE_DIR%>/persistence.xml">persistence.xml</A><TD>Descriptor for the Persistence Provider Runtime</TD>
<TR><TD><A HREF="<%=Constants.RESOURCE_SOURCE_BASE_DIR%>/dwr.xml">dwr.xml</A>                <TD>Descriptor for Direct Web Remoting</TD>
<TR><TD><A HREF="<%=Constants.RESOURCE_SOURCE_BASE_DIR%>/data.properties">data.properties</A>       <TD>Sample data to populate the database</TD>
</TABLE>
</DIV>

</BODY>
</HTML>
