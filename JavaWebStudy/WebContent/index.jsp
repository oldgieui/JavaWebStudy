<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/src/stylesheets/Style.css">
<title>NEXT CAMPUS MAP</title>
</head>
<body>
	<div id="filter"></div>
	<div id="main">
		<div id="header">
			<div id="titleBar">
				<img src="src/img/next_bi.png" id="logo">
				<div id="signinButton" class="button">Sign in</div>
				<div id="loginWindow">
					<div id="loginTitle">Sign in</div>
					<div id="loginWrap">
						<form name="login" action="/login.do" method="post">
							<div id="loginInputs">
								Username<input type="text" name="id"> Password<input
									type="password" name="password">
							</div>
							<div id="loginButton" class="button">Sign in</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<div id="campusMap">
			<div id="resvWindow">
				<div id="resvHeader">
					<div id="resvDate">:: 오늘 날짜 ::</div>
					<div id="resvInputWrap">
						<form name="reservation" action="/timetable.do" method="get">
							시작 : <input name="startTime" type="time"> 끝 : <input
								name="endTime" type="time"> <input name="submitTime"
								type="submit" value="예약">
						</form>
					</div>
				</div>
				<div id="resvContainer">
					<div id="resvTimeLine">
						<!--            좌측, 시간대별 표시 들어가는 라인-->
						<div class="border"></div>
						<div class="hour">00~01</div>
						<div class="border"></div>
						<div class="hour">01~02</div>
						<div class="border"></div>
						<div class="hour">02~03</div>
						<div class="border"></div>
						<div class="hour">03~04</div>
						<div class="border"></div>
						<div class="hour">04~05</div>
						<div class="border"></div>
						<div class="hour">05~06</div>
						<div class="border"></div>
						<div class="hour">06~07</div>
						<div class="border"></div>
						<div class="hour">07~08</div>
						<div class="border"></div>
						<div class="hour">08~09</div>
						<div class="border"></div>
						<div class="hour">09~10</div>
						<div class="border"></div>
						<div class="hour">10~11</div>
						<div class="border"></div>
						<div class="hour">11~12</div>
						<div class="border"></div>
						<div class="hour">12~13</div>
						<div class="border"></div>
						<div class="hour">13~14</div>
						<div class="border"></div>
						<div class="hour">14~15</div>
						<div class="border"></div>
						<div class="hour">15~16</div>
						<div class="border"></div>
						<div class="hour">16~17</div>
						<div class="border"></div>
						<div class="hour">17~18</div>
						<div class="border"></div>
						<div class="hour">18~19</div>
						<div class="border"></div>
						<div class="hour">19~20</div>
						<div class="border"></div>
						<div class="hour">20~21</div>
						<div class="border"></div>
						<div class="hour">21~22</div>
						<div class="border"></div>
						<div class="hour">22~23</div>
						<div class="border"></div>
						<div class="hour">23~24</div>
					</div>
					<div id="resvContentLine">
						<!--            우측, 예약 내용 들어가는 라인-->
					</div>
				</div>
			</div>

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
		var resvTimeLine = document.getElementById("resvTimeLine");
		var loginWindowFlag = false;
		var resvWindowFlag = false;
        
        main.addEventListener("click", function(e) {
			console.log("id : " + e.target.id + ", class : " + e.target.className);
			if (e.target.id === "signinButton") {
				showLoginWindow(); 
			} else if (e.target.className === "prompt" || e.target.className === "link") {
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
            if(loginWindowFlag === false){
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