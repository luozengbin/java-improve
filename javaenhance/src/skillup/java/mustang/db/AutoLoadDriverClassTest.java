package skillup.java.mustang.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class AutoLoadDriverClassTest {
	
	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
		
		Properties prpos = new Properties();
		
		prpos.load(AutoLoadDriverClassTest.class.getResourceAsStream("jdbc.properties"));
		
		Class.forName("oracle.jdbc.OracleDriver");

		
		Connection _conn = DriverManager.getConnection(prpos.getProperty("url"), prpos.getProperty("user"), prpos.getProperty("password"));
		_conn.setAutoCommit(false);

		_conn.close();
	}
}
