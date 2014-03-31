package org.nhnnext;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	// @Override
	// protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	// throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// super.doGet(req, resp);
	// }

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		String id = request.getParameter("id");
		String password = request.getParameter("password");
//		checkLogin(id, password, writer);
		if (checkLogin(id, password, writer)) {
			response.addHeader("location", "/TimeTable.html");
		}
		writer.close();
		// super.doPost(request, response);
	}

	private boolean checkLogin(String id, String password, PrintWriter writer) {
		Connection connection;
		Statement statement;
		ResultSet resultset;
		String sql;
		String jdbcUrl = "jdbc:mysql://localhost:3306/webDB";
		String databaseID = "root";
		String databasePW = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver Error : " + e );
			return false;
		}
		System.out.println("JDBC Driver is found. OK.");

		try {
			connection = DriverManager.getConnection(jdbcUrl, databaseID,
					databasePW);
			System.out.println("Connection Success");
			statement = connection.createStatement();
			sql = "SELECT rawPW FROM USERDATA WHERE ID ='" + id + "'";
			resultset = statement.executeQuery(sql);
			System.out.println(resultset);
			if (resultset.next()) {
				if (password.equals(resultset.getString("rawPW"))) {
					writer.println("Login Success");
					return true;
				}
			}else {
				writer.println("Failed to Log-in.");
				writer.println("<br><a href = '/index.jsp'>" + "Back to login page");
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.err.println("DB Error : " + e.getMessage());
		} catch (Exception e) {
			System.err.println("Error : " + e.getMessage());
		}
		return false;
	}

	// public void service(ServletRequest request, ServletResponse response)
	// throws ServletException, IOException {
	// response.setContentType("text/html");
	// PrintWriter writer = response.getWriter();
	// String id = request.getParameter("id");
	// String password = request.getParameter("password");
	// checkLogin(id, password, writer);
	// writer.close();
	// }

	// private void checkLogin(String id, String password, PrintWriter writer) {
	// if (id != null && password != null) {
	// if (id.equals("oldgieui") && password.equals("rmffkdlej")) {
	// writer.println("Log-in success.");
	// } else {
	// loginFail(writer);
	// }
	// } else {
	// loginFail(writer);
	// }
	// }
	//
	// private void loginFail(PrintWriter writer) {
	// writer.println("Failed to Log-in.");
	// writer.println("<br><a href = '/index.jsp'>" + "Back to login page");
	// }
}
