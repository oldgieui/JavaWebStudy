package org.nhnnext.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.nhnnext.dto.BbsArticle;

public class BbsDAO extends DAO {
	public boolean addArticle(String name, String title, String content) {
		try {
			connection = getConnection();
			sql = "INSERT INTO WEBBOARD (name, title, content) VALUES (?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, name);
			prepStatement.setString(2, title);
			prepStatement.setString(3, content);
			prepStatement.execute();
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
		// id값을 기준으로 Article 삭제
		try {
			connection = getConnection();
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
		// 게시판 DB의 ID값을 기준으로 Article을 찾아 리턴함
		try {
			connection = getConnection();
			sql = "SELECT * FROM WEBBOARD WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, id);
			resultset = prepStatement.executeQuery();
			BbsArticle article;
			if (resultset.next()) {
				article = new BbsArticle(resultset.getString("NAME"),
						resultset.getString("TITLE"),
						resultset.getString("CONTENT"));
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
		// 게시판 DB에 보관되어 있는 모든 내용을 BbsArticle 객체에 담아 ArrayList에 넣어 리턴해줌
		try {
			connection = getConnection();
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
			return articleList;
		} catch (SQLException e) {
			System.err.println("showBoard SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("showBoard Error : " + e);
		} finally {
			try {
				resultset.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println("showBoard finally Error : " + e);
			}
		}
		return new ArrayList<BbsArticle>();
	}
}
