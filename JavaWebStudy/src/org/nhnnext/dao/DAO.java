package org.nhnnext.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	
	protected void closeConnection() throws SQLException{
		if (resultset != null && resultset.isClosed() == false) {
			resultset.close();
		}
		if (prepStatement != null && prepStatement.isClosed() == false) {
			prepStatement.close();
		}
		if (statement != null && statement.isClosed() == false) {
			statement.close();
		}
		if (connection != null && connection.isClosed() == false) {
			connection.close();
		}
	}
}
