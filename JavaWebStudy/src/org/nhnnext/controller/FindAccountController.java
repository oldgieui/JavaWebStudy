package org.nhnnext.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.dao.UserDAO;
import org.nhnnext.framework.Controller;

public class FindAccountController implements Controller {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("id") == null) {
			findId(req, resp);
		} else
			changePw(req, resp);
	}

	private void findId(HttpServletRequest req, HttpServletResponse resp) {
		UserDAO dao = UserDAO.getInstance();
		String id = dao.findId(req.getParameter("email"));
		System.out.println(id);
		Cookie cookie = new Cookie("foundId", id);
		resp.addCookie(cookie);
	}

	private void changePw(HttpServletRequest req, HttpServletResponse resp) {
		UserDAO dao = UserDAO.getInstance();
		String newPw = dao.changePw(req.getParameter("id"), req.getParameter("email"));
		System.out.println(newPw);
		HttpSession session = req.getSession(false);
		session.setAttribute("newPw", newPw);
		Cookie cookie = new Cookie("newPw", newPw);
		resp.addCookie(cookie);
	}
}
