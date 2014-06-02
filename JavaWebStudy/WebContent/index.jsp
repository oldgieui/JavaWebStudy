<%@page import="org.nhnnext.dao.ResvDAO"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally, java.util.ArrayList, org.nhnnext.dto.Reservation"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/src/stylesheets/Style.css">
<title>GetThatROOM :: CAMPUS MAP ::</title>
</head>
<body>
	<div id="filter"></div>
	<div id="main">
		<div id="header">
			<div id="titleBar">
				<img src="src/img/next_bi.png" id="logo">
				<%
					String id = null;
					if (session.getAttribute("ID") != null){
						id = session.getAttribute("ID").toString();
					}
					if (id == null) {
				%>
					<div id="signinButton" class="button">Sign in</div>
				<%
					} else {
				%>
					<div id ="signinButton">
				<% 
					out.print(id + " ");
				%>
					Login completed<br>
					<form name = 'logout' action = '/logout.do' method ='post'>
					<input type='submit' value='로그아웃'>
					</form></div>
				<% 
					}
				%>

				<div id="loginWindow">
					<div id="loginTitle">Sign in</div>
					<div id="loginContainer">
						<div id="loginWrap">
							<form name="login" action="/login.do" method="post">
								<div id="loginInputs">
									ID<br> <input type="text" name="id"><br>
									Password <br> <input type="password" name="password">
								</div>
								<div id="loginButton" class="button">Sign in</div>
							</form>
						</div>
						<div>
							<a href="../src/pages/findAccount.html">(Forgot password/account?)</a><br> 
							<a href="../src/pages/signUp.html">Sign Up?</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="resvWindow">
			<div id="resvHeader">
				<div id="resvTitle"></div>
				<div id="resvInputWrap">
				<% if (id == null){
				%>
					<p>로그인해 주세요</p>
				<%
				} else {
				%>
					<form name="reservation" action="/addReservation.do" method="get">
						<input name="date" type="date"> <input name="startTime"
							type="time"> 부터 <input name="endTime" type="time">
						까지 <input type="submit" value="예약">
					</form>
				<% } %>
				</div>
			</div>
			<div id="resvContainer">
				<%
					ArrayList<Reservation> resvList = ResvDAO.getInstance()
									.getResvList("LINK 2-3");
							for (Reservation resv : resvList) {
								StringBuffer buf = new StringBuffer();
								buf.append(resv.getUserId());
								buf.append(" | ");
								buf.append(resv.getPlaceName());
								buf.append(" | ");
								buf.append(resv.getPurpose());
								buf.append(" | ");
								buf.append(resv.getDate());
								buf.append(" | ");
								buf.append(resv.getStartTime());
								buf.append(" | ");
								buf.append(resv.getEndTime());
				%>
				<div>
					<%
						out.println(buf.toString());
					%>
				</div>
				<%
					}
				%>
			</div>
		</div>
		<div id="campusMap">
			<div id="prompt1-1" class="prompt">
				prompt<br> 1-1
			</div>
			<div id="prompt1-3" class="prompt">
				prompt<br> 1-3
			</div>
			<div id="prompt2-4" class="prompt">
				prompt<br> 2-4
			</div>
			<div id="prompt2-6" class="prompt">
				prompt<br> 2-6
			</div>
			<div id="prompt3-7" class="prompt">
				prompt<br> 3-7
			</div>
			<div id="prompt3-8" class="prompt">
				prompt<br> 3-8
			</div>
			<div id="prompt3-9" class="prompt">
				prompt<br> 3-9
			</div>
			<div id="prompt3-10" class="prompt">
				prompt<br> 3-10
			</div>
			<div id="prompt4-11" class="prompt">
				prompt<br> 4-11
			</div>
			<div id="prompt4-12" class="prompt">
				prompt<br> 4-12
			</div>
			<div id="prompt4-14" class="prompt">
				prompt<br> 4-14
			</div>

			<div id="link1-1" class="link">
				link<br> 1-1
			</div>
			<div id="link2-3" class="link">
				link<br> 2-3
			</div>
			<div id="linkS-5" class="link">
				link<br> S-5
			</div>
			<div id="linkS-6" class="link">
				link<br> S-6
			</div>
			<div id="linkS-7" class="link">
				link<br> S-7
			</div>
			<div id="linkS-8" class="link">
				link<br> S-8
			</div>
			<div id="link4-12" class="link">
				link<br> 4-12
			</div>
			<div id="link4-13" class="link">
				link<br> 4-13
			</div>
			<div id="link4-14" class="link">
				link<br> 4-14
			</div>
		</div>
	</div>


	<script type="text/javascript">
		var filter = document.getElementById("filter");
		var main = document.getElementById("main");
		var map = document.getElementById("campusMap");
		var loginWindow = document.getElementById("loginWindow");
		var resvWindow = document.getElementById("resvWindow");
		var resvTitle = document.getElementById("resvTitle");
		var loginWindowFlag = false;
		var resvWindowFlag = false;

		main.addEventListener("click", function(e) {
			console.log("id : " + e.target.id + ", class : "
					+ e.target.className);
			if (e.target.id === "signinButton") {
				showLoginWindow();
			} else if (e.target.className === "prompt"
					|| e.target.className === "link") {
				resvTitle.innerHTML = e.target.id;
				showResvWindow();
			} else if (e.target.id === "loginButton") {
				submitForm("login");
			}
		}, false);

		filter.addEventListener("click", function(e) {
			closeLoginWindow();
			closeResvWindow();
		}, false);

		function showLoginWindow() {
			if (loginWindowFlag === false) {
				loginWindowFlag = true;
				filter.style.display = "block";
				loginWindow.style.display = "block";
			}
		}

		function closeLoginWindow() {
			if (loginWindowFlag === true) {
				loginWindowFlag = false;
				filter.style.display = "none";
				loginWindow.style.display = "none";
			}
		}

		function showResvWindow() {
			if (resvWindowFlag === false) {
				resvWindowFlag = true;
				resvWindow.style.display = "block";
				filter.style.display = "block";
			}
		}

		function closeResvWindow() {
			if (resvWindowFlag === true) {
				resvWindowFlag = false;
				filter.style.display = "none";
				resvWindow.style.display = "none";
			}
		}

		function submitForm(formName) {
			document.forms[formName].submit();
		}

		function getPosition(ele) {
			var x = 0;
			var y = 0;
			while (ele) {
				x += ele.offsetLeft;
				y += ele.offsetTop;
				ele = ele.offsetParent;
			}
			return [ x, y ];
		}
	</script>
</body>
</html>