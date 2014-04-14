<%@page import="org.nhnnext.*"%>
<%@ page import="java.io.IOException, 
				javax.servlet.ServletException, 
				javax.servlet.http.HttpServlet, 
				javax.servlet.http.HttpServletRequest, 
				javax.servlet.http.HttpServletResponse"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/* session.setAttribute("id", request.getParameter("id"));
	session.setAttribute("password", request.getParameter("password")); */
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 검증 페이지</title>
<link rel="stylesheet" type="text/css" href="newCSS.css" />
</head>
<body>
	<div class="header">
		<a href="http://nhnnext.org"><img
			src="http://nhnnext.org/img/bi/next_bi.png"></a><br> <br>
		<b>추가 기능을 사용하려면 로그인하세요.</b><br>
	</div>
	<div class="container">
		<%
			String id = (String) session.getAttribute("id");
			String password = (String) session.getAttribute("password");
			UserDAO dao = new UserDAO();
			out.println(dao.loginCheck(id, password));
		%>
	</div>
</body>
</html>