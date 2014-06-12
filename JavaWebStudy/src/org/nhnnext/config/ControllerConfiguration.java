package org.nhnnext.config;

import java.io.InputStream;

import org.nhnnext.framework.Controller;
import org.nhnnext.framework.ControllerMap;

public class ControllerConfiguration extends Configuration {
	public ControllerConfiguration() {
	}

	@Override
	public void init() {
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("controllers.properties");
			loadFile(is);
			ControllerMap.addController(getValue("login.uri"), (Controller)Class.forName(getValue("login.class")).newInstance());
			ControllerMap.addController(getValue("logout.uri"), (Controller)Class.forName(getValue("logout.class")).newInstance());
			ControllerMap.addController(getValue("bbs.uri"), (Controller)Class.forName(getValue("bbs.class")).newInstance());
			ControllerMap.addController(getValue("timetable.uri"), (Controller)Class.forName(getValue("timetable.class")).newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
