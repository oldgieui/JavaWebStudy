package org.nhnnext.controller.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.framework.Controller;

public class Logout implements Controller {
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session == null) {
			System.out.println("No session");
		}
		session.invalidate();
		resp.sendRedirect("/");
	}

}
