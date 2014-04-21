package org.nhnnext;

import static org.junit.Assert.*;

import org.junit.Test;
import org.nhnnext.controller.BbsController;
import org.nhnnext.controller.LoginController;
import org.nhnnext.controller.LogoutController;
import org.nhnnext.framework.FrontController;

public class FrontControllerTest {

	FrontController ctrl = new FrontController();
	
	@Test
	public void testControllerMap() {
		assertTrue(ControllerMap.getController("/login.do").getClass().equals(new LoginController().getClass()));
		assertTrue(ControllerMap.getController("/logout.do").getClass().equals(new LogoutController().getClass()));
		assertTrue(ControllerMap.getController("/bbs.do").getClass().equals(new BbsController().getClass()));
	}
	
	@Test
	public void getURI() throws Exception {
		
	}
	
	

}
