package org.nhnnext.dto;

public class User {
	private String userID;
	private String password;
	private String userName;
	private String email;
	private String phone;
	private String userType;

	public User(String userID, String password, String userName, String email,
			String phone, String userType) {
		this.userID = userID;
		this.password = password;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.userType = userType;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
}
