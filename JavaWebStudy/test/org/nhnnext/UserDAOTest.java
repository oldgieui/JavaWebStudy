package org.nhnnext;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserDAOTest {

	@Test
	public void loginSuccess() {
		UserDAO dao = new UserDAO();
		assertTrue(dao.loginCheck("oldgieui", "1234"));
	}
	
	@Test
	public void loginFail() {
		UserDAO dao = new UserDAO();
		assertFalse(dao.loginCheck("1", "1234"));
	}
	
	@Test
	public void findUser(){
		UserDAO dao = new UserDAO();
		assertTrue(dao.findUser("oldgieui"));
	}
	
	@Test
	public void addUser(){
		UserDAO dao = new UserDAO();
		dao.deleteUser("newman2", "goodman");
		dao.addUser("newman2", "goodman", "newman2");
		assertTrue(dao.findUser("newman2"));
	}
	
	@Test
	public void deleteUser(){
		UserDAO dao = new UserDAO();
		dao.deleteUser("newman", "goodman");
		assertFalse(dao.findUser("newman"));
	}
	
	

}
