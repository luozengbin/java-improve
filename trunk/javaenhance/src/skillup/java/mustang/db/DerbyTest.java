package skillup.java.mustang.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import skillup.java.basic.EnumAsSingletonTest.Person;

public class DerbyTest {

	private static final String DB_URL = "jdbc:derby:SampleDB;create=true";

	public static void main(String[] args) throws SQLException, InterruptedException {
		Connection connection = DriverManager.getConnection(DB_URL);
		createTable(connection);
		//someProcess(connection);
		
		//Thread.sleep(180000);
		
		dropTable(connection);
		connection.close();

	}

	private static void createTable(Connection connection) throws SQLException {
		System.out.println("--- createTable");
		Statement statement = connection.createStatement();
		statement.execute("CREATE TABLE Person (name VARCHAR(16), age INTEGER)");
		statement.close();
	}

	private static void dropTable(Connection connection) throws SQLException {
		System.out.println("--- dropTable");
		Statement statement = connection.createStatement();
		statement.execute("DROP TABLE Person");
		statement.close();
	}

//	private static void someProcess(Connection connection) throws SQLException {
//		PersonQueries queries = connection.createQueryObject(PersonQueries.class);
//
//		System.out.println("--- insert");
//		queries.insert("太郎", 10);
//		queries.insert("次郎", 20);
//		queries.insert("花子", 30);
//
//		System.out.println("--- select");
//		for (Person person : queries.select()) {
//			System.out.println(person);
//		}
//
//		System.out.println("--- update");
//		queries.update("太郎", 30);
//		queries.update("次郎", 20);
//		queries.update("花子", 10);
//
//		System.out.println("--- select");
//		for (Person person : queries.select()) {
//			System.out.println(person);
//		}
//
//		System.out.println("--- delete");
//		queries.delete();
//
//		System.out.println("--- select");
//		for (Person person : queries.select()) {
//			System.out.println(person);
//		}
//
//		queries.close();
//	}

}
