<%@ page isErrorPage="true" import="java.io.*" %>
<html>
<head>
	<title>Exceptions</title>
	<style>
	body, p { font-family:Tahoma; font-size:10pt; padding-left:30; }
	pre { font-size:8pt; }
	</style>
</head>
<body>
<BR>
<BR>
<CENTER>
<%-- Exception Handler --%>
<font color="red">
<%= exception.toString() %><br>
</font>
<BR>
<BR>
You can use your browsers "View Page Source" to see the error stack trace.<BR>
<A HREF="index.jsp">Back</A>
</CENTER>

<%
out.println("<!--");
StringWriter sw = new StringWriter();
PrintWriter pw  = new PrintWriter(sw);
exception.printStackTrace(pw);
out.print(sw);
out.println("-->");
sw.close();
pw.close();
%>

</body>
</html>
