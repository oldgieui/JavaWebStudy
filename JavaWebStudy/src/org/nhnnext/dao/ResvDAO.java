package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
		System.out.println("Add Reservation With Transaction...");
		String timeCheckSql = "select * from reservation where date =? and placename = ? and starttime <= ? and endtime >= ?";
		String addSql = "INSERT INTO reservation(USERID, PLACENAME, PURPOSE, DATE, STARTTIME, ENDTIME) VALUES(?, ?, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(timeCheckSql);
			pstmt.setObject(1, resv.getDate());
			pstmt.setObject(2, resv.getPlaceName());
			pstmt.setObject(3, resv.getEndTime());
			pstmt.setObject(4, resv.getStartTime());
			// EndTime >= StartTime 이고 StartTime <= EndTime일 때 충돌. (1차원 충돌체크)
			System.out.println(pstmt.toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				conn.rollback();
				return;
			}

			pstmt.close();

			pstmt = conn.prepareStatement(addSql);
			pstmt.setObject(1, resv.getUserId());
			pstmt.setObject(2, resv.getPlaceName());
			pstmt.setObject(3, resv.getPurpose());
			pstmt.setObject(4, resv.getDate());
			pstmt.setObject(5, resv.getStartTime());
			pstmt.setObject(6, resv.getEndTime());
			System.out.println(pstmt.toString());
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			releaseResource(rs, pstmt, conn);
		}
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
