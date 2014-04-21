package org.nhnnext;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.nhnnext.controller.BbsController;

public class InstanceTest {

	@Test
	public void test() throws Exception {
		assertTrue(Class.forName("org.nhnnext.controller.BbsController").newInstance() instanceof BbsController);
	}

}
