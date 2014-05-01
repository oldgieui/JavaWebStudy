package org.nhnnext.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import org.nhnnext.dto.Timetable;

public class TimetableDAO extends DAO {

	public void addTime(String startTime, String endTime, String userID) {
		try {
			connection = getConnection();
			sql = "INSERT INTO TIMETABLE(STARTTIME, ENDTIME, USERID) VALUES(?, ?, ?)";
			prepStatement = connection.prepareStatement(sql);
			prepStatement.setString(1, startTime);
			prepStatement.setString(2, endTime);
			prepStatement.setString(3, userID);
			prepStatement.executeUpdate();
			closeConnection();
		} catch (Exception e) {
			System.err.println("addTime error : " + e);
		}		
	}

	public ArrayList<Timetable> getTimetable() {
		try {
			connection = getConnection();
			sql = "SELECT * FROM TIMETABLE";
			statement = connection.createStatement();
			resultset = statement.executeQuery(sql);
			ArrayList<Timetable> timeTableSet = new ArrayList<Timetable>();
			while (resultset.next()) {
				Timetable tb = new Timetable();
				tb.setStartTime(resultset.getString("STARTTIME"));
				tb.setEndTime(resultset.getString("ENDTIME"));
				tb.setUserID(resultset.getString("USERID"));
				timeTableSet.add(tb);
			}
			return timeTableSet;
		} catch (Exception e) {
			System.err.println("getTimeTable error : " + e);
		}
		finally{
			try {
				closeConnection();
			} catch (SQLException e) {
				System.err.println("getTimeTable error : " + e);
			}
		}
		return new ArrayList<Timetable>();
		
	}
	
	
}
