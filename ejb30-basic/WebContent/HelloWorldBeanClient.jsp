<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="skillup.ejb30.basic.helloworld.HelloWorldLocal"%>
<%@page import="javax.naming.InitialContext"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello World Bean Client</title>
</head>
<body>
<%
	InitialContext itx = new InitialContext();
	HelloWorldLocal local = (HelloWorldLocal) itx
			.lookup("skillup_ejb30_basic/HelloWorldBean/local");
	out.print(local.sayHello("akira"));
%>
</body>
</html>