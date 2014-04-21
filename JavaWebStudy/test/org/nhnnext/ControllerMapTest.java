package org.nhnnext;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.nhnnext.framework.ControllerMap;

public class ControllerMapTest {

	@Test
	public void testMap() {
		CmapInitializer.mapInit();
		System.out.println(ControllerMap.getController("/login.do"));
		System.out.println(ControllerMap.getController("/logout.do"));
		System.out.println(ControllerMap.getController("/bbs.do"));
		assertFalse(ControllerMap.getControllerMap().isEmpty());
	}

}
