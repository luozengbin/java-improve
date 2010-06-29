<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.transaction.UserTransaction"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JDBC Over JTA Demo</title>
</head>
<body>

<%
	Context initCtx = new InitialContext();
	Context envCtx = (Context) initCtx.lookup("java:comp/env");
	DataSource ds = (DataSource) envCtx.lookup("jdbc/framework");
	UserTransaction ut = (UserTransaction) initCtx.lookup("java:comp/UserTransaction");

	// データベース接続の取得
	java.sql.Connection con = ds.getConnection();

	// トランザクションの開始。この後の処理が、一連の不可分な処理になる。
	ut.begin();

	// JDBC を使った SQL の実行
	Statement stmt = con.createStatement();

	String sql = "SELECT COUNT(*) FROM PRODUCT";
	ResultSet rs = stmt.executeQuery(sql);

	if (rs.next()) {
		int count = rs.getInt(1);

		out.println("count = " + count);
	}

	// トランザクションの終了（コミット）。ここまでの一連の処理を確定させる。
	ut.commit();

	// データベース接続を閉じる（コネクションプールに返す）
	con.close();
%>
</body>
</html>