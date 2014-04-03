package org.nhnnext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BbsDAO {
	Connection connection;
	Statement statement;
	PreparedStatement prepStatement;
	ResultSet resultset;
	String sql;

	public boolean addArticle(String title, String content) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "INSERT INTO WEBBOARD (name, title, content) VALUES (?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, "TEMP");
			prepStatement.setString(2, title);
			prepStatement.setString(3, content);
			prepStatement.execute();
//			execute(), executeQuery(), executeUpdate() 등의 차이
			prepStatement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			System.err.println("SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
		return false;
	}

	public boolean updateArticle(int id, String name, String title,
			String content) {

		return false;
	}

	public boolean deleteArticle(int id) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "DELETE FROM WEBBOARD WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, id);
			prepStatement.execute();
			prepStatement.close();
			connection.close();
			return true;
		} catch (SQLException e) {
			System.err.println("SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
		System.out.println("Article is not in DB");
		return false;
	}

	public BbsArticle findArticle(int id) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "SELECT * FROM WEBBOARD WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, id);
			resultset = prepStatement.executeQuery();
			BbsArticle article;
			if (resultset.next()) {
				article = new BbsArticle(resultset.getString("NAME"), resultset.getString("TITLE"), resultset.getString("CONTENT"));
			} else {
				article = null;
			}
			resultset.close();
			prepStatement.close();
			connection.close();
			return article;
		} catch (SQLException e) {
			System.err.println("findArticle SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
		return null;		
	}

	public ArrayList<BbsArticle> showBoard() {

		try {
			connection = ConnectionManager.createConnection();
			sql = "SELECT * FROM WEBBOARD";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			ArrayList<BbsArticle> articleList = new ArrayList<BbsArticle>();
			while (resultset.next()) {
				BbsArticle bbs = new BbsArticle(resultset.getString("NAME"),
						resultset.getString("TITLE"),
						resultset.getString("CONTENT"));
				articleList.add(bbs);
			}
			statement.close();
			connection.close();
			return articleList;
		} catch (SQLException e) {
			System.err.println("SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("Error : " + e);
		}
		return new ArrayList<BbsArticle>();
	}
}
