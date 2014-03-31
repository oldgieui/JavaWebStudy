package org.nhnnext;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BbsDAO {
	public void addArticle(String title, String content) {
		Connection connection;
		Statement statement;
		String sql;
		String jdbcUrl = "jdbc:mysql://localhost:3306/webDB";
		String databaseID = "root";
		String databasePW = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found : " + e);
		} 
		try {
			sql = "INSERT INTO WEBBOARD (name, title, content) VALUES ('TEMP','" + title + "','" + content +"')" ;
			connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
			statement.close();
			connection.close();
		} catch (SQLException e){
			System.err.println("SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
	}
	
	public ArrayList<Bbs> showArticles() {
		Connection connection;
		Statement statement;
		ResultSet resultset;
		String sql;
		String jdbcUrl = "jdbc:mysql://localhost:3306/webDB";
		String databaseID = "root";
		String databasePW = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found : " + e);
		} 
		try {
			sql = "SELECT * FROM WEBBOARD" ;
			connection = DriverManager.getConnection(jdbcUrl, databaseID, databasePW);
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			ArrayList<Bbs> bbsList = new ArrayList<Bbs>();
			while (resultset.next()) {
				Bbs bbs = new Bbs(resultset.getString("NAME"), resultset.getString("TITLE"), resultset.getString("CONTENT"));
				bbsList.add(bbs);
			}
//			writer.println(resultset.getString("NAME") + " | " + resultset.getString("TITLE") + " | " + resultset.getString("CONTENT") + "<br>");
			statement.close();
			connection.close();
			return bbsList;
		} catch (SQLException e){
			System.err.println("SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
		return new ArrayList<Bbs>();
	}
}
