package org.nhnnext.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionManager {

	private static Stack<Connection> connectionPool = new Stack<Connection>();

	private String driverClassName = "com.mysql.jdbc.Driver";
	private String databaseUrl = "jdbc:mysql://localhost:3306/ResvSystem";
	private String databaseID = "root";
	private String databasePW = "";
	// test case로 작동 확인을 빠르게 하기 위해서 넣어둔 값임. 실제 서버로 작동할 때는 null로 초기화해도 됨.

	public void init(String cls, String db, String id, String pw) {
		driverClassName = cls;
		databaseUrl = db;
		databaseID = id;
		databasePW = pw;
		addConnections();
	}

	private void addConnections() {
		try {
			Class.forName(driverClassName);
			// drivaerClassName에 주어진 값이 없어서 test case에서 문제 발생함
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error : " + e);
		}
		try {
			for (int i = 0; i < 5; i++) {
				connectionPool.push(DriverManager.getConnection(databaseUrl,
						databaseID, databasePW));
			}
			System.out.println("connection Pool Size : " + connectionPool.size());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static Connection getConnection() {		
		if (connectionPool.size() < 2) {
			new ConnectionManager().addConnections();
			System.out.println("added more connections...");
		}

		Connection conn = connectionPool.pop();
		System.out.println("connection popped : " + connectionPool.size() + " remains");
		return conn;
	}

	public static void returnConnection(Connection conn) {
		connectionPool.push(conn);
		System.out.println("connection pushed : " + connectionPool.size() + " remains");
		if (connectionPool.size() > 10) {
			new ConnectionManager().optimizeConnectionPool();
		}
	}
	
	private void optimizeConnectionPool() {
		try {
			int closed = 0;
			for (int i = 0; i < connectionPool.size(); i++) {
				Connection conn = connectionPool.get(i);
				if (conn.isValid(0) == false) {
					conn.close();
					closed++;
				}
			}
			System.out.println(closed + " connections closed");
		} catch (SQLException e) {
			System.err.println("optimizeCP ERROR : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void changeDB(String db) {
		databaseUrl = db;
	}

	public void changeID(String id) {
		databaseID = id;
	}

	public void changePW(String pw) {
		databasePW = pw;
	}

}
