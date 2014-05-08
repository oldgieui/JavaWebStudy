<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/src/stylesheets/Style.css">
<title>NEXT CAMPUS MAP</title>
</head>
<body>
	<div id="header">
		<div id="titleBar">
			<img src="src/img/next_bi.png" id="logo">
			<div id="signinButton" class="button">Sign in</div>
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

	<script type="text/javascript">
		var loginWindow = document.createElement("div");
		loginWindow.setAttribute("id", "loginWindow");

		var loginTitle = document.createElement("div");
		loginTitle.setAttribute("id", "loginTitle");
		loginTitle.innerHTML = "Sign in";

		var loginWrap = document.createElement("div");
		loginWrap.setAttribute("id", "loginWrap");

		var loginForm = document.createElement("form");
		loginForm.setAttribute("name", "login");
		loginForm.setAttribute("action", "/login.do");
		loginForm.setAttribute("method", "post");

		var loginInputs = document.createElement("div");
		loginInputs.setAttribute("id", "loginInputs");

		var inputID = document.createElement("input");
		inputID.setAttribute("type", "text");
		inputID.setAttribute("name", "id");

		var inputPW = document.createElement("input");
		inputPW.setAttribute("type", "password");
		inputPW.setAttribute("name", "password");

		var loginButton = document.createElement("div");
		loginButton.setAttribute("id", "loginButton");
		loginButton.setAttribute("class", "button");
		loginButton.innerHTML = "Sign in";

		loginWindow.appendChild(loginTitle);
		loginWindow.appendChild(loginWrap);

		loginWrap.appendChild(loginForm);

		loginInputs.appendChild(document.createTextNode("Username"));
		loginInputs.appendChild(inputID);
		loginInputs.appendChild(document.createTextNode("Password"));
		loginInputs.appendChild(inputPW);

		loginForm.appendChild(loginInputs);
		loginForm.appendChild(loginButton);

		var loginWindowFlag = false;

		document.getElementById("signinButton").addEventListener("click",
				function() {
					showLoginWindow();
				}, false);

		var titleBar = document.getElementById("titleBar");

		function showLoginWindow() {
			if (loginWindowFlag === false) {
				loginWindowFlag = true;
				titleBar.appendChild(loginWindow);
			} else {
				loginWindowFlag = false;
				titleBar.removeChild(loginWindow);
			}
		}

		var map = document.getElementById("campusMap");

		map.addEventListener("click", function() {
			closeLoginWindow();
		}, false);

		function closeLoginWindow() {
			if (loginWindowFlag === true) {
				loginWindowFlag = false;
				titleBar.removeChild(loginWindow);
			}
		}
		loginButton.addEventListener("click", function(e) {
			submitForm("login");
		}, false);

		var resvWindow = document.createElement("div");
		resvWindow.setAttribute("id", "resvWindow");

		var resvHeader = document.createElement("div");
		resvHeader.setAttribute("id", "resvHeader");
		resvHeader.innerHTML = '<div id="resvDate"> :: 오늘 날짜 :: </div><div id="resvInputWrap"><form name="reservation" action="/timetable.do" method="get">시작 : <input name="startTime" type="time">끝 : <input name="endTime" type="time"><input name="submitTime" type="submit" value="예약"></form></div>';
		resvWindow.appendChild(resvHeader);

		var resvContainer = document.createElement("div");
		resvContainer.setAttribute("id", "resvContainer");
		resvWindow.appendChild(resvContainer);

		var resvTimeLine = document.createElement("div");
		resvTimeLine.setAttribute("id", "resvTimeLine");
		resvTimeLine.innerHTML = "<!--            좌측, 시간대별 표시 들어가는 라인-->";

		var resvContentLine = document.createElement("div");
		resvContentLine.setAttribute("id", "resvContentLine");
		resvContentLine.innerHTML = "<!--            우측, 예약 내용 들어가는 라인-->";

		var resvWindowFlag = false;

		function showResvWindow(ele) {
			if (resvWindowFlag === false) {
				resvWindowFlag = true;
//				resvWindow.style.left = getPosition(ele)[0];
//				resvWindow.style.top = getPosition(ele)[1];
								resvWindow.setAttribute("left", getPosition(ele)[0]);
								resvWindow.setAttribute("top", getPosition(ele)[1]);
				map.appendChild(resvWindow);
				for (var i = 0; i < 24; i++) {
					var border = document.createElement("div");
					border.setAttribute("class", "border");

					var hour = document.createElement("div");
					hour.setAttribute("class", "hour");
					if (i < 9)
						hour.innerHTML = "0" + i + "~" + "0" + (i + 1);
					else if (i === 9)
						hour.innerHTML = "0" + i + "~" + (i + 1);
					else
						hour.innerHTML = i + "~" + (i + 1);

					resvTimeLine.appendChild(border);
					resvTimeLine.appendChild(hour);
				}
			} else {
				resvWindowFlag = false;
				map.removeChild(resvWindow);
			}
		}
		var prompts = document.getElementsByClassName("prompt");
		var links = document.getElementsByClassName("link");

		for (var i = 0; i < prompts.length; i++) {
			prompts[i].addEventListener("click", function() {
				showResvWindow(prompts[i]);
			}, false);
		}

		for (var j = 0; j < links.length; j++) {
			links[j].addEventListener("click", function() {
				showResvWindow(links[j]);
			}, false);
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

		for (var k = 0; k < 24; k++) {
			var border = document.createElement("div");
			border.setAttribute("class", "border");

			var hour = document.createElement("div");
			hour.setAttribute("class", "hour");
			if (k < 9)
				hour.innerHTML = "0" + k + "~" + "0" + (k + 1);
			else if (k === 9)
				hour.innerHTML = "0" + k + "~" + (k + 1);
			else
				hour.innerHTML = k + "~" + (k + 1);

			resvTimeLine.appendChild(border);
			resvTimeLine.appendChild(hour);
		}

	</script>
</body>
</html>