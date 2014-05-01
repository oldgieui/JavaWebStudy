package org.nhnnext.dao;

import org.junit.Test;
import org.nhnnext.dto.Timetable;

public class TimetableDAOTest {

	@Test
	public void addSampleTime() {
		String startTime = "00:00";
		String endTime = "01:00";
		String userID = "sampleID";
		TimetableDAO dao = new TimetableDAO();
		dao.addTime(startTime, endTime, userID);
	}
	
	@Test
	public void showTimetable(){
		TimetableDAO dao = new TimetableDAO();
		for (Timetable tb : dao.getTimetable()) {
			System.out.println(tb.getStartTime());
			System.out.println(tb.getEndTime());
			System.out.println(tb.getUserID());
		}
	}
	
	@Test
	public void deleteTime(){
		
	}
}
