package org.nhnnext.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserDAOTest {

	@Test
	public void loginSuccess() {
		//ConnectionManager의 createConnectionPool이 동작하지 않아서 테스트케이스가 먹통이 되었다. 어떻게 해야 할까 
		//ConnectionManager의 getConnection()에서 connectionPool 크기가 일정 수치 이하면 자동으로 connectionPool에 새 객체들을 넣도록 했더니 해결됨 
		assertTrue(UserDAO.getInstance().checkLogin("oldgieui", "1234"));
	}
	
	@Test
	public void loginFail() {
		assertFalse(UserDAO.getInstance().checkLogin("1", "1234"));
	}
	
	
	@Test
	public void addUser(){
//		UserDAO.getInstance().deleteUser("oldgieui", "1234");
//		UserDAO.getInstance().addUser("admin", "admin", "admin", "flecher1@gmail.com", "01098740345", "admin");
		UserDAO.getInstance().addUser("oldgieui", "1234", "이건희", "oldgieui@gmail.com", "01098740345", "student");
//		assertTrue(UserDAO.getInstance().findUser("oldgieui"));
	}
	
	@Test
	public void deleteUser(){
		UserDAO.getInstance().deleteUser("newman", "goodman");
	}
	
	@Test
	public void testFindId(){
		String id = UserDAO.getInstance().findId("flecher1@gmail.com");
		System.out.println(id);
		assertEquals("oldgieui", id);
	}
	
	@Test
	public void testChangePw(){
		String newPw = UserDAO.getInstance().setNewPw("junggyver", "flashscope@naver.com");
		System.out.println(newPw);
	}
	

}
