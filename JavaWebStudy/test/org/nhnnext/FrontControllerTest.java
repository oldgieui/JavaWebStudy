package org.nhnnext;

import static org.junit.Assert.*;

import org.junit.Test;
import org.nhnnext.controller.BbsController;
import org.nhnnext.controller.account.Login;
import org.nhnnext.controller.account.Logout;
import org.nhnnext.framework.ControllerMap;
import org.nhnnext.framework.FrontController;

public class FrontControllerTest {

	FrontController ctrl = new FrontController();
	
	@Test
	public void testControllerMap() {
		assertTrue(ControllerMap.getController("/login.do").getClass().equals(new Login().getClass()));
		assertTrue(ControllerMap.getController("/logout.do").getClass().equals(new Logout().getClass()));
		assertTrue(ControllerMap.getController("/bbs.do").getClass().equals(new BbsController().getClass()));
	}
	
	@Test
	public void getURI() throws Exception {
		
	}
	
	

}
