package org.nhnnext.controller.account;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.dao.UserDAO;
import org.nhnnext.framework.Controller;

public class FindAccount implements Controller {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("id") == null) {
			findId(req, resp);
		} else
			setNewPw(req, resp);
		resp.sendRedirect("./");
	}

	private void findId(HttpServletRequest req, HttpServletResponse resp) {
		UserDAO dao = UserDAO.getInstance();
		String id = dao.findId(req.getParameter("email"));
		System.out.println(id);
		HttpSession session = req.getSession();
		session.setAttribute("foundId", id);
	}

	private void setNewPw(HttpServletRequest req, HttpServletResponse resp) {
		UserDAO dao = UserDAO.getInstance();
		String newPw = dao.setNewPw(req.getParameter("id"), req.getParameter("email"));
		System.out.println(newPw);
		HttpSession session = req.getSession(false);
		session.setAttribute("newPw", newPw);
	}
}
