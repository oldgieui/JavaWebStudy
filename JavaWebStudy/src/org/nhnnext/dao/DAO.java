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
	
	public Connection getConnection(){
		return ConnectionManager.createConnection();
	}
	
}
