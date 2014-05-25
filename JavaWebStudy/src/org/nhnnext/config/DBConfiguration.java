package org.nhnnext.config;

import org.nhnnext.framework.ConnectionManager;

public class DBConfiguration extends Configuration {
	public DBConfiguration() {
		loadFile("/Users/oldgieui/git/JavaWebStudy/JavaWebStudy/database.properties");
	}

	public void init() {
		ConnectionManager cm = new ConnectionManager();
		cm.init(getValue("db.className"), getValue("db.url"),
				getValue("db.id"), getValue("db.pwd"));
	}
}
