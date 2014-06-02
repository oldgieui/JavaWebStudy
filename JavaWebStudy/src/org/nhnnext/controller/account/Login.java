package org.nhnnext.controller.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.dao.UserDAO;
import org.nhnnext.framework.Controller;

public class Login implements Controller{
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		if (checkLogin(id, password)){
			session.setAttribute("ID", id);
			resp.sendRedirect("/");
		}
		else {
			System.err.println("Login error");
		}
	}

	private boolean checkLogin(String id, String password) {
		return UserDAO.getInstance().checkLogin(id, password);
	}
}
