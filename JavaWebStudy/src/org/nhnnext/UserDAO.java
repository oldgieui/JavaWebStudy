package org.nhnnext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDAO {
	Connection connection;
	PreparedStatement prepStatement;
	ResultSet resultset;
	String sql;

	public boolean loginCheck(String id, String password) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "SELECT PASSWORD FROM USERDATA WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			resultset = prepStatement.executeQuery();
			password = encryptPasswordMD5(password);
			if (resultset.next()) {
				if (password.equals(resultset.getString("PASSWORD"))) {
					return true;
				}
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("loginCheck DB Error : " + e.getMessage());
		} catch (Exception e) {
			System.err.println("loginCheck Error : " + e);
		} finally {
			try {
				resultset.close();
				prepStatement.close();
//				statement.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		return false;
	}

	private String encryptPasswordMD5(String password) {
		return DigestUtils.md5Hex(password);
	}

	public boolean findUser(String id) {
		try {
			connection = ConnectionManager.createConnection();
			sql = "SELECT ID FROM USERDATA WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			resultset = prepStatement.executeQuery();
			if (resultset.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			System.err.println("findUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("findUser Error : " + e);
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

	public void addUser(String id, String password, String name) {
		try {
			connection = ConnectionManager.createConnection();
//			sql = "INSERT INTO USERDATA (ID, PASSWORD, NAME) VALUES(?, password(?), ?)";
			sql = "INSERT INTO USERDATA (ID, PASSWORD, NAME) VALUES(?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPasswordMD5(password));
			prepStatement.setString(3, name);
			// sql 쿼리의 password()함수를 쓰고 싶은데 어떻게 해야 하는지 모르겠다
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
//			sql = "DELETE FROM USERDATA WHERE ID = ? AND PASSWORD = PASSWORD(?)";
			sql = "DELETE FROM USERDATA WHERE ID = ? AND PASSWORD = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPasswordMD5(password));
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
