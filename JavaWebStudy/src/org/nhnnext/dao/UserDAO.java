package org.nhnnext.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import org.apache.commons.codec.digest.DigestUtils;
import org.nhnnext.dto.User;

public class UserDAO extends DAO<User> {

	private UserDAO() {
	}

	private static UserDAO dao = null;

	public static UserDAO getInstance() {
		if (dao == null) {
			dao = new UserDAO();
		}
		return dao;
	}

	public boolean checkLogin(String id, String pw) {
		String sql = "SELECT PASSWORD FROM USER WHERE USERID = ?";
		pw = encryptPassword(pw);
		if (pw.equals(getString(sql, id))) {
			return true;
		}
		return false;
	}

	private String encryptPassword(String password) {
		return DigestUtils.md5Hex(password);
	}

	public User getUser(String id) {
		String sql = "SELECT * FROM USER WHERE USERID = ?";
		RowMapper<User> rm = new RowMapper<User>() {
			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("USERID"), rs.getString("PASSWORD"), rs.getString("USERNAME"), rs.getString("EMAIL"), rs.getString("PHONE"), rs.getString("USERTYPE"));
			}
		};
		return getDTO(sql, rm, id);
	}

	public String findId(String email) {
		String sql = "SELECT USERID FROM USER WHERE EMAIL = ?";
		return getString(sql, email);
	}

	public String setNewPw(String id, String email) {
		if (findId(email).equals(id) == false) {
			return "changePw failed";
		}
		// 나중에 랜덤 스트링 생성 기능이 필요한 곳이 또 생기면 분리하자
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		char chars[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
				'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		for (int i = 0; i < 12; i++) {
			sb.append(chars[random.nextInt(chars.length)]);
		}
		String newPassword = sb.toString();

		String sql = "UPDATE USER SET PASSWORD = ? WHERE USERID = ?";
		executeUpdate(sql, encryptPassword(newPassword), id);
		return newPassword;
	}

	public void addUser(String id, String pw, String name, String mail, String phone, String type) {
		String sql = "INSERT INTO USER (USERID, PASSWORD, USERNAME, EMAIL, PHONE, USERTYPE) VALUES(?, ?, ?, ?, ?, ?)";
		executeUpdate(sql, id, encryptPassword(pw), name, mail, phone, type);
	}

	public void deleteUser(String id, String pw) {
		String sql = "DELETE FROM USER WHERE USERID = ? AND PASSWORD = ?";
		executeUpdate(sql, id, encryptPassword(pw));
	}

}
