package org.nhnnext.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.dao.TimetableDAO;
import org.nhnnext.framework.Controller;

public class TimetableController implements Controller {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession();

		String startTime = req.getParameter("startTime");
		String endTime = req.getParameter("endTime");
		String userID;

		System.out.println(startTime);
		System.out.println(endTime);

		if (session.getAttribute("ID") != null)
			userID = session.getAttribute("ID").toString();
		else
			userID = "sampleID";
		TimetableDAO dao = TimetableDAO.getInstance();
		dao.addTime(startTime, endTime, userID);

		RequestDispatcher rd = req.getRequestDispatcher("./index.jsp");
		rd.forward(req, resp);

	}

}
