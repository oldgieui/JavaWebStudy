package org.nhnnext.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.nhnnext.dao.UserDAO;

public class UserDAOTest {

	@Test
	public void loginSuccess() {
		assertTrue(UserDAO.loginCheck("oldgieui", "1234"));
	}
	
	@Test
	public void loginFail() {
		assertFalse(UserDAO.loginCheck("1", "1234"));
	}
	
	@Test
	public void findUser(){
		assertTrue(UserDAO.findUser("oldgieui"));
	}
	
	@Test
	public void addUser(){
		UserDAO.deleteUser("newman2", "goodman");
		UserDAO.addUser("newman2", "goodman", "newman2name");
		assertTrue(UserDAO.findUser("newman2"));
	}
	
	@Test
	public void deleteUser(){
		UserDAO.deleteUser("newman", "goodman");
		assertFalse(UserDAO.findUser("newman"));
	}
	
	

}
