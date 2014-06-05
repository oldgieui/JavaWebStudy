package org.nhnnext.controller.schedule;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhnnext.dao.ScheduleDAO;
import org.nhnnext.dto.Schedule;
import org.nhnnext.framework.Controller;

public class AddSchedule implements Controller {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String id = session.getAttribute("ID").toString();
		Schedule sch = new Schedule(id, req.getParameter("placeName"),
				req.getParameter("purpose"), req.getParameter("day"),
				req.getParameter("startDate"), req.getParameter("endDate"),
				req.getParameter("startTime"), req.getParameter("endTime"));
		
		ScheduleDAO.getInstance().addSchedule(sch);
	}

}
