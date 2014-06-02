package org.nhnnext.dao;

import java.util.ArrayList;

import org.junit.Test;
import org.nhnnext.dto.Schedule;

public class ScheduleDAOTest {

	private void printSchedule(Schedule schedule) {
		StringBuffer sb = new StringBuffer();
		sb.append(schedule.getUserId());
		sb.append(" | ");
		sb.append(schedule.getPlaceName());
		sb.append(" | ");
		sb.append(schedule.getPurpose());
		System.out.println(sb.toString());
	}

	@Test
	public void add() {
		Schedule schedule = new Schedule("oldgieui", "LINK 1-1", "정기 대실망쑈",
				"MON", "20140302", "20140503", "130101", "150101");
		ScheduleDAO.getInstance().addSchedule(schedule);
	}

	@Test
	public void get() {
		Schedule schedule = ScheduleDAO.getInstance().getSchedule(2);
		printSchedule(schedule);
	}
	
	@Test
	public void getAll(){
		ArrayList<Schedule> schList = ScheduleDAO.getInstance().getScheduleList();
		for (Schedule schedule : schList) {
			printSchedule(schedule);
		}
	}
	
	@Test
	public void delete(){
		ScheduleDAO.getInstance().deleteSchedule(1);
		getAll();
	}

}
