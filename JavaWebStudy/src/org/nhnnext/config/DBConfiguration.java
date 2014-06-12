package org.nhnnext.config;

import java.io.InputStream;

import org.nhnnext.framework.ConnectionManager;

public class DBConfiguration extends Configuration {
	public DBConfiguration() {
	}

	public void init() {
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream("database.properties");
		loadFile(is);
		ConnectionManager cm = new ConnectionManager();
		cm.init(getValue("db.className"), getValue("db.url"),
				getValue("db.id"), getValue("db.pwd"));
	}
}
