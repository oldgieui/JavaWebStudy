package org.nhnnext.dto;

public class Reservation {
	private int rid;
	private String userId;
	private String placeName;
	private String purpose;
	private String date;
	private String startTime;
	private String endTime;
	private String submitTime;

	public Reservation() {
	}

	//DB로부터 받을 때 씀 
	public Reservation(int rid, String userId, String placeName,
			String purpose, String date, String startTime, String endTime, String submitTime) {
		this.rid = rid;
		this.userId = userId;
		this.placeName = placeName;
		this.purpose = purpose;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.submitTime = submitTime;
	}

	//보낼 때 씀 (rid와 submitTime은 DB에서 자동 생성되는 값이므로 넣을 필요 없음)
	public Reservation(String userId, String placeName, String purpose,
			String date, String startTime, String endTime) {
		this.userId = userId;
		this.placeName = placeName;
		this.purpose = purpose;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getRid() {
		return rid;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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

	public String getSubmitTime() {
		return submitTime;
	}
	
	

	/**
	 * +------------+--------------+------+-----+-------------------+----------
	 * -------------------+ | Field | Type | Null | Key | Default | Extra |
	 * +----
	 * --------+--------------+------+-----+-------------------+------------
	 * -----------------+ | USERID | varchar(12) | NO | PRI | NULL | | |
	 * PLACENAME | varchar(12) | NO | PRI | NULL | | | PURPOSE | varchar(100) |
	 * NO | | NULL | | | DATE | date | NO | | NULL | | | STARTTIME | time | NO |
	 * | NULL | | | ENDTIME | time | NO | | NULL | | | SUBMITTIME | timestamp |
	 * NO | | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
	 * +------------+----
	 * ----------+------+-----+-------------------+------------
	 * -----------------+
	 */
}
