package org.nhnnext;

import java.util.HashMap;
import java.util.Map;

import org.nhnnext.controller.BbsController;
import org.nhnnext.controller.LoginController;
import org.nhnnext.controller.LogoutController;
import org.nhnnext.framework.Controller;

public class ControllerMap {
	private static Map<String, Controller> controllerMap = new HashMap<String, Controller>() {{
		put("/login.do", new LoginController());
		put("/logout.do", new LogoutController());
		put("/bbs.do", new BbsController());
	}};

	public static void addController(String name, Controller controller){
		controllerMap.put(name, controller);
	}
	
	public static Controller getController(String name){
		return controllerMap.get(name);
	}
	
	
	
	
	
	
}
