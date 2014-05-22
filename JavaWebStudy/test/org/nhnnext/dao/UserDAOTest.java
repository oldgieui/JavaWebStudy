package org.nhnnext.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.nhnnext.dao.UserDAO;

public class UserDAOTest {

	@Test
	public void loginSuccess() {
		assertTrue(UserDAO.getInstance().loginCheck("oldgieui", "1234"));
	}
	
	@Test
	public void loginFail() {
		assertFalse(UserDAO.getInstance().loginCheck("1", "1234"));
	}
	
	@Test
	public void findUser(){
		assertTrue(UserDAO.getInstance().findUser("oldgieui"));
	}
	
	@Test
	public void addUser(){
		UserDAO.getInstance().deleteUser("oldgieui", "1234");
		UserDAO.getInstance().addUser("oldgieui", "1234", "이건희", "flecher1@gmail.com", "01098740345", "student");
		assertTrue(UserDAO.getInstance().findUser("oldgieui"));
	}
	
	@Test
	public void deleteUser(){
		UserDAO.getInstance().deleteUser("newman", "goodman");
		assertFalse(UserDAO.getInstance().findUser("newman"));
	}
	
	@Test
	public void testFindId(){
		String id = UserDAO.getInstance().findId("flecher1@gmail.com");
		System.out.println(id);
		assertEquals("oldgieui", id);
	}
	
	@Test
	public void testChangePw(){
		String newPw = UserDAO.getInstance().changePw("junggyver", "flashscope@naver.com");
		System.out.println(newPw);
	}
	

}
