package org.nhnnext.dto;

public class Schedule {
	private int sid;

	private String userId;
	private String placeName;
	private String purpose;
	private String day;
	private String startDate;
	private String endDate;
	private String startTime;
	private String endTime;

	public Schedule(int sid, String userId, String placeName, String purpose,
			String day, String startDate, String endDate, String startTime,
			String endTime) {
		this.sid = sid;
		this.userId = userId;
		this.placeName = placeName;
		this.purpose = purpose;
		this.day = day;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	public Schedule(String userId, String placeName, String purpose,
			String day, String startDate, String endDate, String startTime,
			String endTime) {
		super();
		this.userId = userId;
		this.placeName = placeName;
		this.purpose = purpose;
		this.day = day;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public Schedule() {
	}

	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

}
