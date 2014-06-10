package org.nhnnext.controller.reservation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
		String inCharSetName = "8859_1";
		String outCharSetName = "utf-8";
		String userId = 	session.getAttribute("ID").toString();
		String placeName = 	encodeString(req.getParameter("placeName"), inCharSetName, outCharSetName); 
		String purpose = 	encodeString(req.getParameter("purpose"), inCharSetName, outCharSetName); 
		String date = 		encodeString(req.getParameter("date"), inCharSetName, outCharSetName);
		String startTime = 	encodeString(req.getParameter("startTime"), inCharSetName, outCharSetName);
		String endTime = 	encodeString(req.getParameter("endTime"), inCharSetName, outCharSetName);
		
		Reservation resv = new Reservation(userId, placeName, purpose, date, startTime, endTime);
		ResvDAO.getInstance().addResv(resv);
	}
	
	private String encodeString(String arg, String inCharSetName, String outCharSetName){
		String str = null;
		try {
			str = new String(arg.getBytes(inCharSetName), outCharSetName);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
}
