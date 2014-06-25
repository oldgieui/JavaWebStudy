package org.nhnnext.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nhnnext.dao.HistoryDAO;
import org.nhnnext.framework.Controller;

public class HistoryController implements Controller {

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HistoryDAO.getInstance().recordHistory(getCurDate());
	}

	private String getCurDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(System.currentTimeMillis()));
		return new SimpleDateFormat("yyyyMMdd").format(cal.getTime());
	}

}
