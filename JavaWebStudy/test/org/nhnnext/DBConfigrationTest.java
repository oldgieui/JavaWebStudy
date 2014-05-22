package org.nhnnext;

import static org.junit.Assert.*;

import org.junit.Test;

public class DBConfigrationTest {

	@Test
	public void testDBConfigration() {
		fail("Not yet implemented");
	}

	@Test
	public void testInit() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetProperty() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValue() {
		DBConfiguration dbc = new DBConfiguration();
		System.out.println(dbc.getValue("db.className"));
		System.out.println(dbc.getValue("db.url"));
		System.out.println(dbc.getValue("db.id"));
		System.out.println(dbc.getValue("db.pwd"));
	}

}
