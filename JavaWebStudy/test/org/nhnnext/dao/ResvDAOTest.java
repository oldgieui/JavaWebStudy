package org.nhnnext.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.nhnnext.dto.Reservation;

public class ResvDAOTest {
	public ResvDAOTest() {
	}
	
	private void printResv(Reservation resv){
		System.out.println("UserID : " + resv.getUserId());
		System.out.println("Place : " + resv.getPlaceName());
		System.out.println("Purpose : " + resv.getPurpose());
		System.out.println("Date : " + resv.getDate());
		System.out.println("StartTime : " + resv.getStartTime());
		System.out.println("EndTime : " + resv.getEndTime());
	}
	
	@Test
	public void addReservation() {
		Reservation resv = new Reservation("oldgieui", "LINK 2-3", "수업", "20140531", "083212", "120212");
		ResvDAO.getInstance().addResv(resv);
	}

	@Test
	public void deleteReservation() {
		Reservation resv = new Reservation("oldgieui", "LINK 2-3", "수업", "20140531", "083212", "120212");
		ResvDAO.getInstance().deleteResv(resv);
	}
	
	@Test
	public void getResvByPlace(){
		ArrayList<Reservation> resvList = ResvDAO.getInstance().getResvsByPlace("LINK 2-3");
		for (int i = 0; i < resvList.size(); i++) {
			assertEquals(resvList.get(i).getPlaceName(), "LINK 2-3");
			printResv(resvList.get(i));
		}
	}
	
	@Test
	public void getResv(){
		Reservation resv = ResvDAO.getInstance().getResv("oldgieui", "LINK 2-3", "수업", "20140531", "083212", "120212");
		printResv(resv); 
	}
//	
//	@Test
//	public void updateReservation(){
//		Reservation oldResv = new Reservation("oldgieui", "LINK 2-3", "수업", "20140531", "083212", "120212");
//		Reservation newResv = new Reservation("oldgieui", "LINK 2-3", "식사", "20140621", "093311", "150232");
//		ResvDAO.getInstance().updateResv(oldResv, newResv);
//	}
}
