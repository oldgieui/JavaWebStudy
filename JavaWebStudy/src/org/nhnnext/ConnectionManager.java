package org.nhnnext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error : " + e);
		}
		try {
			String databaseUrl = "jdbc:mysql://localhost:3306/webDB";
			String databaseID = "root";
			String databasePW = "";
			return DriverManager.getConnection(databaseUrl, databaseID, databasePW);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
