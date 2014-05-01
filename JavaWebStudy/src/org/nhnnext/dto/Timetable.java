package org.nhnnext.dto;

public class Timetable {
	String startTime;
	String endTime;
	String userID;
	
	public Timetable(String startTime, String endTime, String userID){
		this.startTime = startTime;
		this.endTime = endTime;
		this.userID = userID;
	}
	
	public Timetable() {
	}
	
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	
}
