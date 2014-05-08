package org.nhnnext.dao;

import org.junit.Test;
import org.nhnnext.dto.Timetable;

public class TimetableDAOTest {

	@Test
	public void addSampleTime() {
		String startTime = "00:00";
		String endTime = "01:00";
		String userID = "sampleID";
		TimetableDAO.addTime(startTime, endTime, userID);
	}
	
	@Test
	public void showTimetable(){
		for (Timetable tb : TimetableDAO.getTimetable()) {
			System.out.println(tb.getStartTime() + " : " + tb.getEndTime() + " : " + tb.getUserID());
		}
	}
	
	@Test
	public void deleteTime(){
		
	}
}
