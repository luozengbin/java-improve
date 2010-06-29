package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class TestCase001 {

	public static String runTest() throws NamingException, SQLException, IllegalStateException, SecurityException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException {

		StringBuffer result = new StringBuffer();

		Context initCtx = null;
		Context envCtx = null;

		Connection produceConn = null;
		Connection scottConn = null;

		UserTransaction ut = null;

		try {

			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			produceConn = ((DataSource) envCtx.lookup("jdbc/produce"))
					.getConnection();
			scottConn = ((DataSource) envCtx.lookup("jdbc/scott"))
					.getConnection();
			ut = (UserTransaction) initCtx.lookup("java:comp/UserTransaction");

			// トランザクションの開始。この後の処理が、一連の不可分な処理になる。
			ut.begin();

			Statement stmt  = null;
			
			result.append("Executing SCOTT SQL ...<br/>");
			stmt = scottConn.createStatement();
			
			stmt.executeUpdate("INSERT INTO JOBS VALUES('xxx01', 'vvvv01', 20, 100)");
			
			stmt.close();
			
			// JDBC を使った SQL の実行
			stmt = produceConn.createStatement();

			String sql = "SELECT COUNT(*) FROM PRODUCT";
			ResultSet rs = stmt.executeQuery(sql);
			
			result.append("Executing PRODUCT SQL ...<br/>");

			if (rs.next()) {
				int count = rs.getInt(1);
				result.append("Result = ").append(count).append("<br/>");
			}
			
			stmt.executeUpdate("INSERT INTO BASKET (BASKET_NO, OBPM_INSTANCE_ID,PRODUCT_ID,CREATE_PERSON,CREATE_DATE,MODIFY_PERSON,MODIFY_DATE) VALUES('bk001', 'test001', 99, 'akira', sysdate, 'akira', sysdate)");
			
			stmt.close();
			
			result.append("UPDATE SUCCESS<br/>");
			
			// トランザクションの終了（コミット）。ここまでの一連の処理を確定させる。
			ut.commit();
			produceConn.close();
			scottConn.close();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			if(ut != null) ut.rollback();
			
		} finally {

			if (produceConn != null && !produceConn.isClosed()) produceConn.close();
			if (scottConn != null && !scottConn.isClosed()) scottConn.close();
			
			initCtx = null;
			envCtx = null;
			produceConn = null;
			scottConn = null;
			ut = null;
		}

		return result.toString();
	}
}
