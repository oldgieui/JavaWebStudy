<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>welcome to world</title>
<link rel="stylesheet" type="text/css" href="newCSS.css" />
</head>
<body>
	<div class="header">
		<a href="http://nhnnext.org"><img src="http://nhnnext.org/img/bi/next_bi.png"></a><br>
		<br> <b>추가 기능을 사용하려면 로그인하세요.</b><br>
	</div>
	<div class="container">
		<div id="main_ui">
			<div id="login_wrap">
				<form name="login" action="/Login.jsp" method="post">
					<div id="login_inputs">
						<input id="input_box" type="text" name="id" value="아이디" autofocus><br>
						<input id="input_box" type="password" name="password"
							value="?????????"><br>
					</div>
					<div id="login_image_button">
						<input id="login_button" type="submit" value="Login">
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>