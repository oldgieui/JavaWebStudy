package org.nhnnext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;



public abstract class DAO {
	Connection connection;
	Statement statement;
	PreparedStatement prepStatement;
	ResultSet resultset;
	String sql;
	
	String dbUrl = "jdbc:mysql://localhost:3306/webDB";
	String dbId = "root";
	String dbPw = "";
	
	public DAO() {
		ConnectionManager.initDB(dbUrl, dbId, dbPw);
	}
	
	public Connection getConnection(){
		return ConnectionManager.createConnection();
	}
	
}
