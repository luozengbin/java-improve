<!-- ======================================================================  -->
<!-- Common functionality and package declaration for all JSP that uses      -->
<!-- JPA Service directly                                                    -->
<!-- ======================================================================  -->
<%@ page import="examples.ejb.ejb30.service.ReviewServiceImpl"%>
<%@ page import="java.text.DecimalFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.text.NumberFormat"%>
<%@ page import="examples.ejb.ejb30.domain.*"%>
<%@ page import="javax.persistence.EntityManager"%>
<%@ page import="javax.persistence.Query"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Random"%>
<%@ page import="javax.persistence.EntityManagerFactory"%>

<%!
  SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
  NumberFormat numberFormat   = new DecimalFormat("0.0");

%>

<%
  //EntityManagerFactory has been stored during context initialization.
  EntityManagerFactory emf = (EntityManagerFactory) application.getAttribute("reviewService");
  ReviewServiceImpl service = new ReviewServiceImpl(emf);
%>

<%!
  public int getIntegerParameter(ServletRequest request, String param,
                                 int defaultValue) {
         String value = request.getParameter(param);
         if (value==null)
            return defaultValue;
         try {
             return Integer.parseInt(value);
         } catch (NumberFormatException e) {
             return defaultValue;
         }
  }
%>

<%!
  public String getStringParameter(ServletRequest request, String param,
                                  String defaultValue) {
         String value = request.getParameter(param);
         return (value==null) ? defaultValue : value;
  }
%>

<%!
  Class[] itemClasses = new Class[]{Book.class, Music.class, Movie.class};
  String[] itemClassNames = new String[]{"Book", "Music", "Movie"};

  public Class getItemClass(String itemType) {
    for (int i = 0; i < itemClasses.length; i++) {
      if (itemClassNames[i].equalsIgnoreCase(itemType)) {
        return itemClasses[i];
      }
    }
    return itemClasses[0];
  }
%>

