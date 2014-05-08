package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDAO extends DAO{

	public static boolean loginCheck(String id, String password) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			String sql = "SELECT PASSWORD FROM USERDATA WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			resultSet = prepStatement.executeQuery();
			password = encryptPassword(password);
			if (resultSet.next()) {
				if (password.equals(resultSet.getString("PASSWORD"))) {
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
				resultSet.close();
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		return false;
	}

	private static String encryptPassword(String password) {
		return DigestUtils.md5Hex(password);
	}

	public static boolean findUser(String id) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			String sql = "SELECT ID FROM USERDATA WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			resultSet = prepStatement.executeQuery();
			if (resultSet.next()) {
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
				resultSet.close();
				prepStatement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void addUser(String id, String password, String name) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		try {
			connection = getConnection();
			String sql = "INSERT INTO USERDATA (ID, PASSWORD, NAME) VALUES(?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPassword(password));
			prepStatement.setString(3, name);
			prepStatement.executeUpdate();
			prepStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("addUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("addUser Error : " + e);
		} 
	}

	public static void deleteUser(String id, String password) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		try {
			connection = getConnection();
			String sql = "DELETE FROM USERDATA WHERE ID = ? AND PASSWORD = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPassword(password));
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
