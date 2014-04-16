package org.nhnnext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	static String databaseUrl = "jdbc:mysql://localhost:3306/webDB";
	static String databaseID = "root";
	static String databasePW = "";
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error : " + e);
		}
		try {
			return DriverManager.getConnection(databaseUrl, databaseID, databasePW);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static void changeDB(String db){
		databaseUrl = db;
	}
	
	public static void changeID(String id){
		databaseID = id;
	}
	
	public static void changePW(String pw){
		databasePW = pw;
	}
	
	public static void changeDB(String db, String id, String pw) {
		if (db != null && db != "") {
			databaseUrl = db;
		}
		if (id != null && id != "") {
			databaseID = id;
		}
		if (pw != null && pw!= "") {
			databasePW = pw;
		}
	}
}
