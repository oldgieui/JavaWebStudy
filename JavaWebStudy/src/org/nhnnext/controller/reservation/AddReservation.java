package org.nhnnext.controller.reservation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.dao.ResvDAO;
import org.nhnnext.dto.Reservation;
import org.nhnnext.framework.Controller;

public class AddReservation implements Controller{

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = session.getAttribute("ID").toString();
		String placeName = "LINK 1-1";
		String purpose = "test";
		String date = req.getParameter("date").toString();
		String startTime = req.getParameter("startTime").toString();
		String endTime = req.getParameter("endTime").toString();
		
		Reservation resv = new Reservation(userId, placeName, purpose, date, startTime, endTime);
		ResvDAO.getInstance().addResv(resv);
		
		resp.sendRedirect("/");
	}

}
