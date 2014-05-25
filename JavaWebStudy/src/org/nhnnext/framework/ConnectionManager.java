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
		createConnectionPool();
	}

	private void createConnectionPool() {
		try {
			Class.forName(driverClassName);
			// drivaerClassName에 주어진 값이 없어서 test case에서 문제 발생함
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error : " + e);
		}
		try {
			for (int i = 0; i < 10; i++) {
				connectionPool.push(DriverManager.getConnection(databaseUrl,
						databaseID, databasePW));
				System.out.println("conn + 1");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static Connection getConnection() {
		boolean isPoolEmpty = connectionPool.empty();
		while (isPoolEmpty) {
			isPoolEmpty = connectionPool.empty();
			System.out.println("connectionPool is empty.... waiting...");
		}
		System.out.println("conn size :" + connectionPool.size());
		
		return connectionPool.pop();
	}
	
	public static void returnConnection(Connection conn){
		System.out.println("conn size :" + connectionPool.size());
		connectionPool.push(conn);
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
