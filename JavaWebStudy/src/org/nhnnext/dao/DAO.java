package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.nhnnext.framework.ConnectionManager;

public abstract class DAO {	
	protected static Connection getConnection() {
		return ConnectionManager.getConnection();
	}
	//connection pool
	//return != close. 자원 활용도에서 큰 차이가 난다.
	protected void releaseResource(ResultSet rs, Statement stmt, PreparedStatement pstmt, Connection conn) {
		try {
			if (rs != null && rs.isClosed() == false) {
				rs.close();
			}
			if (pstmt != null && pstmt.isClosed() == false) {
				pstmt.close();
			}
			if (stmt != null && stmt.isClosed() == false) {
				stmt.close();
			}
			if (conn != null && conn.isClosed() == false) {
				conn.close();
			}
		} catch (SQLException e) {
			System.err.println("releaseResource() Error : " + e.getMessage());
		}
	}
	
	
}
