package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.nhnnext.dto.Reservation;
import org.nhnnext.framework.ConnectionManager;

public abstract class DAO {
	protected Connection getConnection() {
		return ConnectionManager.getConnection();
	}

	protected void executeUpdate(String sql, Object... args) {
		Connection conn= null; 
		PreparedStatement pstmt = null; 
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("executeUpdate error : " + e.getMessage());
			e.printStackTrace();
		} finally {
			releaseResource(pstmt, conn);
		}
	}
	
//	protected ResultSet executeQuery(PreparedStatement pstmt, String... args) {
//		ResultSet rs = null;
//		try {
//			for (int i = 0; i < args.length; i++) {
//				pstmt.setString(i + 1, args[i]);
//			}
//			rs = pstmt.executeQuery();
//			return rs;
//		} catch (SQLException e) {
//			System.out.println("executeQuery error : " + e.getMessage());
//			e.printStackTrace();
//		}
//		return rs;
//	}
	
	protected String getString(String sql, Object...args) {
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rs = null;
		String str = "Not in DB";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				str = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("getString error : " + e.getMessage());
			e.printStackTrace();
		} finally {
			releaseResource(rs, pstmt, conn);
		}
		return str;
	}
	
	protected ArrayList<Reservation> getRow(String sql, Object... args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Reservation> resvList = new ArrayList<Reservation>();
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				pstmt.setObject(i + 1, args[i]);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resvList.add(new Reservation(rs.getString("USERID"), rs
						.getString("PLACENAME"), rs.getString("PURPOSE"), rs
						.getString("DATE"), rs.getString("STARTTIME"), rs
						.getString("ENDTIME")));
			}
		} catch (SQLException e) {
			System.out.println("getString error : " + e.getMessage());
			e.printStackTrace();
		} finally {
			releaseResource(rs, pstmt, conn);
		}
		return resvList;
	}
	
	// connection pool
	// return != close. 자원 활용도에서 큰 차이가 난다.
	protected void releaseResource(ResultSet rs, PreparedStatement pstmt,
			Connection conn) {
		try {
			if (rs != null && rs.isClosed() == false) {
				rs.close();
			}
			if (pstmt != null && pstmt.isClosed() == false) {
				pstmt.close();
			}
			if (conn != null) {
				ConnectionManager.returnConnection(conn);
			}
		} catch (SQLException e) {
			System.err.println("releaseResource() Error : " + e.getMessage());
		}
	}

	protected void releaseResource(PreparedStatement pstmt, Connection conn) {
		releaseResource(null, pstmt, conn);
	}

}
