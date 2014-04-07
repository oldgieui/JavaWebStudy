<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/myCSS.css" />
<script type = "text/javascript">
	function checkBbsValue(){
		if(document.bbs.title.value == ""){
			alert("게시글 제목은 공란으로 할 수 없습니다.");
			document.bbs.title.focus();
			return;
		}
		if(document.bbs.content.value == ""){
			alert("게시글 내용은 공란으로 할 수 없습니다.");
			document.bbs.title.focus();
			return;
		}
	}
	function checkLoginValue(){
		if(document.login.id.value == ""){
			alert("아이디를 입력해 주세요.");
			document.login.id.focus();
			return;
		}
		if(document.login.password.value == ""){
			alert("비밀번호를 입력해 주세요.");
			document.login.password.focus();
			return;
		}
		
	}
</script>
<title>Local Webpage</title>
</head>
<body>
	<div class="header">
		<h1>로그인 페이지 테스트</h1>
	</div>
	<div class="container">
		<div class="layout-left">
			
			<div id = "bbs_wrap">
				<form name="bbs" action="/bbs/" method="post">
                    <input type="text" id ="bbstitle" name="title" style="width:100%; margin:3px;"><br>
                    <textarea name="content" style="width:100%; height:100%; margin:3px"></textarea>
                    <input type="submit" value="submit" onclick="checkBbsValue();"><br>
                </form>
			</div>
			

			
<!-- 			<form name="Clock" action="/clock/">
				<center>
					<i><input type="submit" value="What Time Is It Now?"></i>
				</center>
			</form>

			<form name="timetable" action="timetable">
				<a href="TimeTable.html"><input type="button"
					value="show timetable"></a>
			</form> -->

		</div>
		<div class="layout-right">
			<div id="login_wrap">
			<%
				if (session.getAttribute("ID") == null){
			%>
				<form name="login" action="/login/" method="post">
					<div id="login_inputs">
						<input type="text" name="id" value="아이디" autofocus><br>
						<input type="password" name="password" value=""><br>
					</div>
					<div id="login_button">
						<input type="submit" value="Login" onclick = "checkLoginValue();">
					</div>
				</form>
			<%
				} else {
					out.println("Login Completed.<br>");
					out.println(session.getAttribute("ID") + " 접속중<br>");
					StringBuffer buf = new StringBuffer();
					buf.append("<form name = 'logout' action = '/logout/' method ='post'>");
					buf.append("<input type='submit' value='로그아웃'>");
					buf.append("</form><br>");
					out.println(buf);
				}
			%>

			</div>
		</div>
	</div>
	<footer>
	<h2>footer 영역</h2>
	</footer>



</body>
</html>