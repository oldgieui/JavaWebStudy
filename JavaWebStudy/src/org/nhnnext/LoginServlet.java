package org.nhnnext;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		if (checkLogin(id, password)){
			session.setAttribute("ID", id);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		}
		else {
			System.out.println("Login error");
		}
	}

	private void createSession() {
	}
	
	private boolean checkLogin(String id, String password) {
		UserDAO dao = new UserDAO();
		return dao.loginCheck(id, password);
	}
}
