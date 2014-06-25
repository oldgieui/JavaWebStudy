package org.nhnnext.dao;

import org.nhnnext.dto.History;

public class HistoryDAO extends DAO<History> {
	private HistoryDAO() {
	}

	private static HistoryDAO dao = null;

	public static HistoryDAO getInstance() {
		if (dao == null) {
			dao = new HistoryDAO();
		}
		return dao;
	}
	
	public void recordHistory(String curDate){
		if (curDate.length() != 8) {
			System.err.println("curdate is not acceptable.");
			return;
		}
		executeUpdate("CALL RECORD_HISTORY(?)", curDate);
	}
}
