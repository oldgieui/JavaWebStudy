package org.nhnnext;

import java.util.HashMap;
import java.util.Map;

import org.nhnnext.framework.Controller;

public class ControllerMap {

	// controllerMap에서 매핑처리하는 부분을 XML 파일을 읽어와서 처리하도록 변경하자
	private static Map<String, Controller> controllerMap = new HashMap<String, Controller>();

	// {{
	// put("/login.do", new LoginController());
	// put("/logout.do", new LogoutController());
	// put("/bbs.do", new BbsController());
	// }};

	public static void addController(String name, Controller controller) {
		controllerMap.put(name, controller);
	}

	public static Controller getController(String name) {
		return controllerMap.get(name);
	}

	public static Map<String, Controller> getControllerMap() {
		return controllerMap;
	}

}
