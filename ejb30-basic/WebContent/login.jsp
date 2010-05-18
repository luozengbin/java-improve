<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Jaas Test Login</title>
</head>
<body>
<center><h2>Jaas Test Login</h2></center>

<% if ("true".equals(request.getParameter("failed"))){ %>
	<font color="red" >ログイン失敗しました！</font>
<% } %>

<br />
ユーザIDとパスワードを入力してください。
<br />
<form method="post" action="j_security_check">
<p>ユーザID<input type="text" name="j_username" /></p>
<p>パスワード<input type="password" name="j_password" /></p>

<input type="submit" name="ログイン" />
</form>
</body>
</html>