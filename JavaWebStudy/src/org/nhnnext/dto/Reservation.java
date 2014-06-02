package org.nhnnext.dto;

public class Reservation {
	private String userId;
	private String placeName;
	private String purpose;
	private String date;
	private String startTime;
	private String endTime;

	public Reservation() {
	}

	public Reservation(String userId, String placeName, String purpose,
			String date, String startTime, String endTime) {
		this.userId = userId;
		this.placeName = placeName;
		this.purpose = purpose;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
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
	
/**
	+------------+--------------+------+-----+-------------------+-----------------------------+
	| Field      | Type         | Null | Key | Default           | Extra                       |
	+------------+--------------+------+-----+-------------------+-----------------------------+
	| USERID     | varchar(12)  | NO   | PRI | NULL              |                             |
	| PLACENAME  | varchar(12)  | NO   | PRI | NULL              |                             |
	| PURPOSE    | varchar(100) | NO   |     | NULL              |                             |
	| DATE       | date         | NO   |     | NULL              |                             |
	| STARTTIME  | time         | NO   |     | NULL              |                             |
	| ENDTIME    | time         | NO   |     | NULL              |                             |
	| SUBMITTIME | timestamp    | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
	+------------+--------------+------+-----+-------------------+-----------------------------+
*/
}
