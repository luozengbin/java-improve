<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="match.Game001"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JNDI Demo</title>
</head>
<body>
<%
	Context initCtx = new InitialContext();
	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	Game001 game001 = (Game001) envCtx.lookup("game/FootballFactory");
	out.println(game001.getGameName());
%>
</body>
</html>