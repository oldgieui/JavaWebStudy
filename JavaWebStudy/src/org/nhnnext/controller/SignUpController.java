package org.nhnnext.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nhnnext.dao.UserDAO;
import org.nhnnext.framework.Controller;

public class SignUpController implements Controller {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserDAO dao = UserDAO.getInstance();
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String type = req.getParameter("type");
		System.out.println(id + ", " + password + ", " + name + ", " + phone + ", " + type);
		dao.addUser(id, password, name, email, phone, type);
	}

}
