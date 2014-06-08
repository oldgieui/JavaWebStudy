package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.nhnnext.dto.Timetable;

public class TimetableDAO extends DAO<Timetable> {

	private TimetableDAO() {
	}

	private static TimetableDAO dao = null;

	public static TimetableDAO getInstance() {
		if (dao == null) {
			dao = new TimetableDAO();
		}
		return dao;
	}

	public void addTime(String startTime, String endTime, String userID) {
		Connection connection = null;
		PreparedStatement prepStatement = null;
		try {
			connection = getConnection();
			// connection.setAutoCommit(false);
			String sql = "INSERT INTO TIMETABLE(STARTTIME, ENDTIME, USERID) VALUES(?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, startTime);
			prepStatement.setString(2, endTime);
			prepStatement.setString(3, userID);
			prepStatement.executeUpdate();
			// connection.commit();
			// connection.rollback();
			prepStatement.close();
			connection.close();
		} catch (Exception e) {
			System.err.println("addTime error : " + e);
		}
	}

	public ArrayList<Timetable> getTimetable() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			String sql = "SELECT * FROM TIMETABLE";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			ArrayList<Timetable> timeTableSet = new ArrayList<Timetable>();
			while (resultSet.next()) {
				Timetable tb = new Timetable();
				tb.setStartTime(resultSet.getString("STARTTIME"));
				tb.setEndTime(resultSet.getString("ENDTIME"));
				tb.setUserID(resultSet.getString("USERID"));
				timeTableSet.add(tb);
			}
			return timeTableSet;
		} catch (Exception e) {
			System.err.println("getTimeTable error : " + e);
		} finally {
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				System.err.println("getTimeTable error : " + e);
			}
		}
		return new ArrayList<Timetable>();

	}

}
