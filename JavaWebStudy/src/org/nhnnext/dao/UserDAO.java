package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.nhnnext.framework.ConnectionManager;

public class UserDAO extends DAO {

	private UserDAO() {
	}

	private static UserDAO dao = null;

	public static UserDAO getInstance() {
		if (dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}

	public boolean loginCheck(String id, String password) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			String sql = "SELECT PASSWORD FROM USER WHERE USERID = ?";
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
			System.err.println("loginCheck Error : " + e + " : "
					+ e.getMessage());
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

	private String encryptPassword(String password) {
		return DigestUtils.md5Hex(password);
	}

	public boolean findUser(String id) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			String sql = "SELECT USERID FROM USER WHERE USERID = ?";
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
	
	public String findId(String email){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "SELECT USERID FROM USER WHERE EMAIL = ?";
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getString("USERID");
			}
		} catch (Exception e) {
			System.err.println("findId error : " + e);
		} finally{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return "Not in DB";
	}
	
	public String changePw(String id, String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String failed = "changePw failed";
		try {
			if (findId(email).equals(id) == false) {
				return failed;
			}
			// 나중에 랜덤 스트링 생성 기능이 필요한 곳이 또 생기면 분리하자
			StringBuffer sb = new StringBuffer();
			Random random = new Random();
			char chars[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
					'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
					'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
					'8', '9' };
			for (int i = 0; i < 12; i++) {
				sb.append(chars[random.nextInt(chars.length)]);
			}
			String newPassword = sb.toString();
			String sql = "UPDATE USER SET PASSWORD = ? WHERE USERID = ?";
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, encryptPassword(newPassword));
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			return newPassword;

		} catch (Exception e) {
			System.err.println("changePw error : " + e);
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return failed;
	}

	public void addUser(String id, String pw, String name, String mail,String phone, String type) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		try {
			connection = getConnection();
			String sql = "INSERT INTO USER (USERID, PASSWORD, USERNAME, EMAIL, PHONE, USERTYPE) VALUES(?, ?, ?, ?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPassword(pw));
			prepStatement.setString(3, name);
			prepStatement.setString(4, mail);
			prepStatement.setString(5, phone);
			prepStatement.setString(6, type);
			prepStatement.executeUpdate();
			prepStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("addUser DB Error : " + e);
		} catch (Exception e) {
			System.err.println("addUser Error : " + e);
			e.printStackTrace();
		}
	}

	public void deleteUser(String id, String pw) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		try {
			connection = getConnection();
			String sql = "DELETE FROM USER WHERE USERID = ? AND PASSWORD = ?";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, id);
			prepStatement.setString(2, encryptPassword(pw));
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
