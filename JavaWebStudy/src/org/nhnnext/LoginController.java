package org.nhnnext;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.framework.Controller;

public class LoginController implements Controller{
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		resp.setContentType("text/html");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		if (checkLogin(id, password)){
			session.setAttribute("ID", id);
			RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}
		else {
			System.err.println("Login error");
		}
	}

	private boolean checkLogin(String id, String password) {
		UserDAO dao = new UserDAO();
		return dao.loginCheck(id, password);
	}
}
