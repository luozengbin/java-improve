<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="skillup.ejb30.basic.security.SecurityAccess"%>
<%@page import="skillup.ejb30.basic.web.util.ContextUtils"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jaas Test Login</title>
</head>
<body>
<h3>Jaas Test</h3>
あなたは
<%
	SecurityAccess bean = ContextUtils.lookup("skillup_ejb30_basic/SecurityAccessImpl/remote");
	out.print(bean.cunsumerMethods());
%>
</body>
</html>