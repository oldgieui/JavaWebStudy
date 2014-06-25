package org.nhnnext.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.nhnnext.dto.Reservation;

public class ResvDAO extends DAO<Reservation> {

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

	public void deleteResv(int rid) {
		System.out.println("Delete Reservation...");
		executeUpdate("DELETE FROM reservation WHERE RID = ?", rid);
	}

	public ArrayList<Reservation> getResvList(String placeName) {
		System.out.println("Get Reservation by place name...");
		String sql = "SELECT RESV.*, U.USERNAME FROM RESERVATION RESV INNER JOIN USER U WHERE PLACENAME = ? AND U.USERID = RESV.USERID;";
		RowMapper<Reservation> rm = new RowMapper<Reservation>() {
			@Override
			public Reservation mapRow(ResultSet rs) throws SQLException {
				return new Reservation(rs.getInt("RID"),
						rs.getString("USERID"), rs.getString("USERNAME"),
						rs.getString("PLACENAME"), rs.getString("PURPOSE"),
						rs.getString("DATE"), rs.getString("STARTTIME"),
						rs.getString("ENDTIME"), rs.getString("SUBMITTIME"));
			}
		};
		return getDTORow(sql, rm, placeName);
	}

	public Reservation getResv(String userId, String placeName, String purpose,
			String date, String startTime, String endTime) {
		System.out.println("Get Reservation...");
		String sql = "SELECT RESV.*, U.USERNAME FROM RESERVATION RESV INNER JOIN USER U WHERE USERID = ? AND PLACENAME = ? AND PURPOSE = ? AND DATE = ? AND STARTTIME = ? AND ENDTIME = ? AND U.USERID = RESV.USERID;";
		RowMapper<Reservation> rm = new RowMapper<Reservation>() {
			@Override
			public Reservation mapRow(ResultSet rs) throws SQLException {
				return new Reservation(rs.getInt("RID"),
						rs.getString("USERID"), rs.getString("USERNAME"),
						rs.getString("PLACENAME"), rs.getString("PURPOSE"),
						rs.getString("DATE"), rs.getString("STARTTIME"),
						rs.getString("ENDTIME"), rs.getString("SUBMITTIME"));
			}
		};

		return getDTO(sql, rm, userId, placeName, purpose, date, startTime,
				endTime);
	}

}
