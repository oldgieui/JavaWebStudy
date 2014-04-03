package org.nhnnext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	Connection connection;
	Statement statement;
	PreparedStatement prepStatement;
	ResultSet resultset;
	String sql;
	
	public boolean loginCheck(String id, String password) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "SELECT rawPW FROM USERDATA WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			resultset = prepStatement.executeQuery();
			if (resultset.next()) {
				if (password.equals(resultset.getString("rawPW"))) {
					return true;
				}
			} else {
				return false;
				//close()가 어느 시점에 되어야 하는가? try가 실패하면?
			}
		} catch (SQLException e) {
			System.err.println("loginCheck DB Error : " + e.getMessage());
		} catch (Exception e) {
			System.err.println("loginCheck Error : " + e);
		} finally {
			try {
				resultset.close();
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean findUser(String id) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "SELECT ID FROM USERDATA WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			resultset = prepStatement.executeQuery();
			if (resultset.next()) {
				resultset.close();
				prepStatement.close();
				connection.close();
				return true;
			} else {
				resultset.close();
				prepStatement.close();
				connection.close();
				return false;
			}
		} catch (SQLException e) {
			System.err.println("findUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("findUser Error : " + e);
		}
		return false;
	}

	public void addUser(String id, String password) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "INSERT INTO USERDATA VALUES(?, ?, password(?))";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, password);
			prepStatement.setString(3, password);
//			sql 쿼리의 password()함수를 쓰고 싶은데 어떻게 해야 하는지 모르겠다
			prepStatement.executeUpdate();
			prepStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("addUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("addUser Error : " + e);
		}
	}

	public void deleteUser(String id, String password) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "DELETE FROM USERDATA WHERE ID = ? AND rawPW = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, password);
			prepStatement.executeUpdate();
			prepStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("deleteUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("deleteUser Error : " + e);
		}
	}

}
