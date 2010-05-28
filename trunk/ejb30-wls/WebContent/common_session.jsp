<!-- ======================================================================  -->
<!-- Common functionality and package declaration for all JSP that uses      -->
<!-- Session Bean                                                            -->
<!-- ======================================================================  -->
<%@ page import="java.util.*,
                 javax.naming.*,
                 javax.persistence.*,
                 examples.ejb.ejb30.domain.*,
                 examples.ejb.ejb30.session.*,
                 java.text.*,
                 javax.servlet.*" 
%>
<%@ page import="examples.ejb.ejb30.service.ReviewService"%>

<%! 
  private ReviewService serviceImpl;
  
  SimpleDateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
  NumberFormat numberFormat   = new DecimalFormat("0.0");
  
%>

<%
    String SESSION_BEAN_JNDI_NAME = "java:comp/env/ejb/ReviewManager";
    InitialContext ctx = new InitialContext();
    serviceImpl = (ReviewService) ctx.lookup(SESSION_BEAN_JNDI_NAME);
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
    Class[] itemClasses     = new Class[]{Book.class, Music.class, Movie.class};
    String[] itemClassNames = new String[]{"Book","Music","Movie"};
	public Class getItemClass(String itemType) {
	    for (int i=0; i<itemClasses.length; i++) {
	       if (itemClassNames[i].equalsIgnoreCase(itemType)) {
	          return itemClasses[i];
	       }
	    }
	    return itemClasses[0];
	}
%>

