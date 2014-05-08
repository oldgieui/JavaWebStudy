package org.nhnnext.dao;

import java.sql.Connection;

import org.nhnnext.framework.ConnectionManager;

public abstract class DAO {	

	protected static Connection getConnection() {
		return ConnectionManager.createConnection();
	}
	
//	protected void closeConnection() throws SQLException{
//		if (resultSet != null && resultSet.isClosed() == false) {
//			resultSet.close();
//		}
//		if (prepStatement != null && prepStatement.isClosed() == false) {
//			prepStatement.close();
//		}
//		if (statement != null && statement.isClosed() == false) {
//			statement.close();
//		}
//		if (connection != null && connection.isClosed() == false) {
//			connection.close();
//		}
//	}
}
