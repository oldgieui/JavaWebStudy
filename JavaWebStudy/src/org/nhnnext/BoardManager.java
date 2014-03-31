package org.nhnnext;

import java.sql.*;

public class BoardManager {
	public static void main(String[] args) {
		
		Connection conn;
		Statement statement;
		ResultSet resultSet;
		String sql;
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/webDB";
		String userID = "root";
		String userPW = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error : " + e.getMessage() );
			return;
		}
		System.out.println("JDBC Driver is found. OK.");
		
		try {
			conn = DriverManager.getConnection(jdbcUrl, userID, userPW);
			System.out.println("connection success");
			statement = conn.createStatement();
			sql = "SELECT * FROM WEBBOARD";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString("NAME");
				String title = resultSet.getString("TITLE");
				String content = resultSet.getString("CONTENT");
				System.out.println(id + " / " + name + " / " + title + " / " + content);
			}
			statement.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("DB Error : " + e.getMessage());
			return;
		}
		
	}
}
