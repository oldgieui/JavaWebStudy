package org.nhnnext.initializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.nhnnext.config.DBConfiguration;

public class ServerInitializer extends HttpServlet {
	private static final long serialVersionUID = 3228199840908472764L;

	@Override
	public void init() throws ServletException {
		System.out.println("Map Initialize!! " + getServletContext().getRealPath(""));
		ControllerInitializer ci = new ControllerInitializer();
		ci.init();
		DBConfiguration dbc = new DBConfiguration();
		dbc.init();
//		ControllerConfiguration cc = new ControllerConfiguration();
//		cc.init();
	}
}
