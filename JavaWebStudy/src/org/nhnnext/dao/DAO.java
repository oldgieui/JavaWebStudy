package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.nhnnext.framework.ConnectionManager;

public abstract class DAO {
	protected Connection connection;
	protected Statement statement;
	protected PreparedStatement prepStatement;
	protected ResultSet resultset;
	protected String sql;

	String dbUrl = "jdbc:mysql://localhost:3306/webDB";
	String dbId = "root";
	String dbPw = "";

	public DAO() {
		ConnectionManager.initDB(dbUrl, dbId, dbPw);
	}

	protected Connection getConnection() {
		return ConnectionManager.createConnection();
	}

	// protected String querySelect(String finder, String table, String
	// condition) {
	// StringBuffer query = new StringBuffer();
	// query.append("SELECT ");
	// query.append(finder);
	// query.append(" FROM ");
	// query.append(table);
	// query.append(" WHERE ");
	// query.append(condition);
	// return query.toString();
	// }
	//
	// protected String queryInsert() {
	//
	// return null;
	// }
	//
	// protected String queryDelete() {
	//
	// return null;
	// }
	//
	// 패러미터를 받아서 sql 쿼리문을 생성해 주는 메소드들을 만드려고 했는데 배보다 배꼽이 커지고 있음
	//
}
