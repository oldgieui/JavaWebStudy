package org.nhnnext.initializer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.nhnnext.config.DBConfiguration;

public class ServerInitializer implements ServletContextListener {
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Map Initialize!!");
		ControllerInitializer ci = new ControllerInitializer();
		ci.init();
		DBConfiguration dbc = new DBConfiguration();
		dbc.init();
	}
}
