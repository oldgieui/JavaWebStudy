package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.nhnnext.dto.BbsArticle;

//동일 인스턴스에서도 각 스레드에서 메소드를 사용할 때 참조하는 주소가 다르므로 connection 등을 로컬로 사용해야 추가적인 인스턴스 생성을 방지할 수 있다 
public class BbsDAO extends DAO<BbsArticle> {
	private BbsDAO() {
	}
	
	private static BbsDAO dao = null;
	public static BbsDAO getInstance(){
		if (dao == null) {
			dao = new BbsDAO();
		}
		return dao;
	}
	
	public boolean addArticle(String name, String title, String content) {
		Connection connection;
		PreparedStatement prepStatement;
		try {
			connection = getConnection();
			String sql = "INSERT INTO WEBBOARD (USER_USERID, title, content) VALUES (?, ?, ?)";
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

	// public static boolean updateArticle(int id, String name, String title,
	// String content) {
	//
	// return false;
	// }

	public boolean deleteArticle(String name) {
		// id값을 기준으로 Article 삭제
		Connection connection;
		PreparedStatement prepStatement;
		try {
			connection = getConnection();
			String sql = "DELETE FROM WEBBOARD WHERE USER_USERID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, name);
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
		Connection connection;
		PreparedStatement prepStatement;
		ResultSet resultSet;
		try {
			connection = getConnection();
			String sql = "SELECT * FROM WEBBOARD WHERE USER_USERID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setInt(1, id);
			resultSet = prepStatement.executeQuery();
			BbsArticle article;
			if (resultSet.next()) {
				article = new BbsArticle(resultSet.getString("USER_USERID"),
						resultSet.getString("TITLE"),
						resultSet.getString("CONTENT"));
			} else {
				article = null;
			}
			resultSet.close();
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
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			String sql = "SELECT * FROM WEBBOARD";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			ArrayList<BbsArticle> articleList = new ArrayList<BbsArticle>();
			while (resultSet.next()) {
				BbsArticle bbs = new BbsArticle(resultSet.getString("USER_USERID"),
						resultSet.getString("TITLE"),
						resultSet.getString("CONTENT"));
				articleList.add(bbs);
			}
			return articleList;
		} catch (SQLException e) {
			System.err.println("showBoard SQL Error : " + e);
		} catch (Exception e) {
			System.err.println("showBoard Error : " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println("showBoard finally Error : " + e);
			}
		}
		return new ArrayList<BbsArticle>();
	}
}
