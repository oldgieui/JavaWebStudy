package org.nhnnext.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.nhnnext.dto.Schedule;

public class ScheduleDAO extends DAO<Schedule> {
	private ScheduleDAO() {
	}

	private static ScheduleDAO dao = null;

	public static ScheduleDAO getInstance() {
		if (dao == null) {
			dao = new ScheduleDAO();
		}
		return dao;
	}

	public void addSchedule(Schedule sch) {
		String sql = "INSERT INTO schedule(USERID, PLACENAME, PURPOSE, DAY, STARTDATE, ENDDATE, STARTTIME, ENDTIME) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		executeUpdate(sql, sch.getUserId(), sch.getPlaceName(),
				sch.getPurpose(), sch.getDay(), sch.getStartDate(),
				sch.getEndDate(), sch.getStartTime(), sch.getEndTime());
	}

	public void deleteSchedule(int sid) {
		String sql = "DELETE FROM schedule WHERE SID = ?";
		executeUpdate(sql, sid);
	}

	public Schedule getSchedule(int sid) {
		String sql = "SELECT * FROM schedule WHERE SID = ?";
		RowMapper<Schedule> rm = new RowMapper<Schedule>() {
			@Override
			public Schedule mapRow(ResultSet rs) throws SQLException {
				return new Schedule(rs.getInt("SID"), rs.getString("USERID"),
						rs.getString("PLACENAME"), rs.getString("PURPOSE"),
						rs.getString("DAY"), rs.getString("STARTDATE"),
						rs.getString("ENDDATE"), rs.getString("STARTTIME"),
						rs.getString("ENDTIME"));
			}
		};
		Schedule sch = getDTO(sql, rm, sid);
		return sch;
	}

	public ArrayList<Schedule> getScheduleList() {
		String sql = "SELECT * FROM schedule";
		RowMapper<Schedule> rm = new RowMapper<Schedule>() {
			@Override
			public Schedule mapRow(ResultSet rs) throws SQLException {
				return new Schedule(rs.getInt("SID"), rs.getString("USERID"),
						rs.getString("PLACENAME"), rs.getString("PURPOSE"),
						rs.getString("DAY"), rs.getString("STARTDATE"),
						rs.getString("ENDDATE"), rs.getString("STARTTIME"),
						rs.getString("ENDTIME"));
			}
		};
		ArrayList<Schedule> schList = getDTORow(sql, rm);
		return schList;
	}

}
