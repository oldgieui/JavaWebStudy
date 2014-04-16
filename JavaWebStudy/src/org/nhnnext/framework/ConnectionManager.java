package org.nhnnext.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	static String databaseUrl;
	static String databaseID;
	static String databasePW;

	public static void initDB(String db, String id, String pw) {
		databaseUrl = db;
		databaseID = id;
		databasePW = pw;
	}

	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error : " + e);
		}
		try {
			return DriverManager.getConnection(databaseUrl, databaseID,	databasePW);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static void changeDB(String db) {
		databaseUrl = db;
	}

	public static void changeID(String id) {
		databaseID = id;
	}

	public static void changePW(String pw) {
		databasePW = pw;
	}

}
