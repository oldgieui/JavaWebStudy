package org.nhnnext.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nhnnext.dao.ResvDAO;
import org.nhnnext.dao.UserDAO;
import org.nhnnext.dto.Reservation;
import org.nhnnext.dto.User;
import org.nhnnext.framework.Controller;

public class BulkInsert implements Controller {

	private char chars[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
			'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

	private int nums[] = { 0, 1, 2, 3 };

	@Override
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String db = req.getParameter("db").toString();
		System.out.println(db);
		if (db.equals("account")) {
			System.out.println("account");
			bulkAccount();
		} else if (db.equals("reservation")) {
			System.out.println("resv");
			bulkResv();
		} else if (db.equals("schedule")) {
			bulkSchedule();
		}
		resp.sendRedirect("/");
	}

	private void bulkAccount() {
		String userType = null;
		for (int i = 0; i < 1000; i++) {
			Random rand = new Random();
			switch (rand.nextInt(3)) {
			case 0:
				userType = "student";
				break;
			case 1:
				userType = "professor";
				break;
			case 2:
				userType = "teacher";
				break;
			}
			UserDAO.getInstance().addUser(randString(12), randString(12),
					randString(10),
					randString(8) + "@" + randString(8) + ".com",
					randString(10), userType);
			System.out.println("user added");
		}
	}

	private String randString(int num) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			sb.append(chars[random.nextInt(chars.length)]);
		}
		return sb.toString();
	}

	private String randInt(int num) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			sb.append(nums[random.nextInt(nums.length)]);
		}
		return sb.toString();
	}

	@SuppressWarnings("static-access")
	private String randDate() {
		GregorianCalendar gc = new GregorianCalendar();

		int year = randBetween(1900, 2010);

		gc.set(gc.YEAR, year);

		int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));

		gc.set(Calendar.DAY_OF_YEAR, dayOfYear);

		return (gc.get(gc.YEAR) + "-" + gc.get(gc.MONTH) + "-"
				+ gc.get(gc.DAY_OF_MONTH));
	}

	private int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	private void bulkResv() {
		ArrayList<User> allUsers = UserDAO.getInstance().getAllUser();
		ArrayList<String> places = new ArrayList<String>();
		places.add("prompt1-1");
		places.add("prompt1-3");
		places.add("prompt2-4");
		places.add("prompt2-6");
		places.add("prompt3-7");
		places.add("prompt3-8");
		places.add("prompt3-9");
		places.add("prompt3-10");
		places.add("prompt4-11");
		places.add("prompt4-12");
		places.add("prompt4-14");
		Random rand = new Random();
		for (int i = 0; i < 10000; i++) {
			Reservation resv = new Reservation(allUsers.get(
					rand.nextInt(allUsers.size())).getUserID(), places.get(rand
					.nextInt(places.size())), randString(25), randDate(),
					randInt(6), randInt(6));
			ResvDAO.getInstance().addResv(resv);
			System.out.println("resv added");
		}
	}

	private void bulkSchedule() {

	}
}
