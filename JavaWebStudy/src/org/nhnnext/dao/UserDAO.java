package org.nhnnext.dao;

import java.sql.SQLException;

import org.apache.commons.codec.digest.DigestUtils;

public class UserDAO extends DAO{

	public boolean loginCheck(String id, String password) {
		try {
			connection = getConnection();
			sql = "SELECT PASSWORD FROM USERDATA WHERE ID = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			resultset = prepStatement.executeQuery();
			password = encryptPassword(password);
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
				closeConnection();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		return false;
	}

	private String encryptPassword(String password) {
		return DigestUtils.md5Hex(password);
	}

	public boolean findUser(String id) {
		try {
			connection = getConnection();
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
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public void addUser(String id, String password, String name) {
		try {
			connection = getConnection();
			sql = "INSERT INTO USERDATA (ID, PASSWORD, NAME) VALUES(?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPassword(password));
			prepStatement.setString(3, name);
			prepStatement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {
			System.err.println("addUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("addUser Error : " + e);
		} 
	}

	public void deleteUser(String id, String password) {
		try {
			connection = getConnection();
			sql = "DELETE FROM USERDATA WHERE ID = ? AND PASSWORD = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPassword(password));
			prepStatement.executeUpdate();
			closeConnection();
		} catch (SQLException e) {
			System.err.println("deleteUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("deleteUser Error : " + e);
		}
	}

}
