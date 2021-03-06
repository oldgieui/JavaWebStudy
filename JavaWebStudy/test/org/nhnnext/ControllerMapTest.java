package org.nhnnext;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.nhnnext.framework.ControllerMap;
import org.nhnnext.initializer.ControllerInitializer;

public class ControllerMapTest {

	@Test
	public void testMap() {
		ControllerInitializer ci = new ControllerInitializer();
		ci.init();
		System.out.println(ControllerMap.getController("/login.do"));
		System.out.println(ControllerMap.getController("/logout.do"));
		System.out.println(ControllerMap.getController("/bbs.do"));
		assertFalse(ControllerMap.getControllerMap().isEmpty());
	}

}
