package org.nhnnext.dao;

import java.util.ArrayList;

import org.nhnnext.dto.Reservation;

public class ResvDAO extends DAO {

	private ResvDAO() {
	}

	private static ResvDAO dao = null;

	public static ResvDAO getInstance() {
		if (dao == null) {
			dao = new ResvDAO();
		}
		return dao;
	}

	public void addResv(Reservation resv) {
		System.out.println("Add Reservation...");
		executeUpdate(
				"INSERT INTO reservation(USERID, PLACENAME, PURPOSE, DATE, STARTTIME, ENDTIME) VALUES(?, ?, ?, ?, ?, ?)",
				resv.getUserId(), resv.getPlaceName(), resv.getPurpose(),
				resv.getDate(), resv.getStartTime(), resv.getEndTime());
	}

	public void deleteResv(Reservation resv) {
		System.out.println("Delete Reservation...");
		executeUpdate(
				"DELETE FROM reservation WHERE USERID = ? AND PLACENAME = ? AND DATE = ? AND STARTTIME = ? AND ENDTIME = ?",
				resv.getUserId(), resv.getPlaceName(), resv.getDate(),
				resv.getStartTime(), resv.getEndTime());
	}

//	public void updateResv(Reservation oldResv, Reservation newResv) {
//		System.out.println("Update Reservation...");
//		String sql = "UPDATE reservation SET USERID = ?, PLACENAME"
//	}
	
	public ArrayList<Reservation> getResvsByPlace(String placeName) {
		System.out.println("Get Reservation by place name...");
		String sql = "SELECT * FROM reservation WHERE PLACENAME = ?";
		return getRow(sql, placeName);
	}

	public Reservation getResv(String userId, String placeName, String purpose,
			String date, String startTime, String endTime) {
		System.out.println("Get Reservation...");
		String sql = "SELECT * FROM reservation WHERE USERID = ? AND PLACENAME = ? AND PURPOSE = ? AND DATE = ? AND STARTTIME = ? AND ENDTIME = ?";
		return getRow(sql, userId, placeName, purpose, date, startTime, endTime)
				.get(0);
	}


}
