<%@page import="org.nhnnext.dto.BbsArticle"%>
<%@page import="org.nhnnext.dao.BbsDAO"%>
<%@page
	import="javax.servlet.jsp.tagext.TryCatchFinally , java.util.ArrayList, org.nhnnext.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="./src/stylesheets/myCSS.css" />

<title>Local Webpage</title>
</head>
<body>
	<div class="header">
		<h1>
			<a href="/index.jsp">로그인 페이지 테스트</a>
		</h1>
	</div>
	<div class="container">
		<div class="layout-left">
			<%
				if (session.getAttribute("ID") != null) {
			%>
			<div id="bbs_wrap">
				<form name="bbs" action="/bbs.do" method="post">
					<input type="text" id="bbstitle" name="title"
						style="width: 100%; margin: 3px;"><br>
					<textarea name="content"
						style="width: 100%; height: 100%; margin: 3px"></textarea>
					<input type="submit" value="submit" onclick="checkBbsValue();"><br>
				</form>
			</div>
			<%
				} 
			%>
			<div>
				<%	
					ArrayList<BbsArticle> bbsList = BbsDAO.getInstance().showBoard();
					for (BbsArticle bbsArticle : bbsList) {
						StringBuffer buf = new StringBuffer();
						buf.append(bbsArticle.getName());
						buf.append(" | ");
						buf.append(bbsArticle.getTitle());
						buf.append(" | ");
						buf.append(bbsArticle.getContents());
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
		<div class="layout-right">
			<div id="login_wrap">
				<%
					if (session.getAttribute("ID") == null) {
				%>
				<form name="login" action="/login.do" method="post">
					<div id="login_inputs">
						<input type="text" name="id" value="아이디" autofocus><br>
						<input type="password" name="password" value=""><br>
					</div>
					<div id="login_button">
						<input type="submit" value="Login" onclick="checkLoginValue();"><br>
						Click here<br> to login.
					</div>
				</form>
				<%
				} else { 
					out.println("Login Completed.<br>");
					out.println(session.getAttribute("ID") + " 접속중<br>");
					StringBuffer buf = new StringBuffer();
					buf.append("<form name = 'logout' action = '/logout.do' method ='post'>");
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

	<script type="text/javascript">
        
 		window.addEventListener("load", function(){
 		//아래 함수는 다른 콜백함수 안에 있어서 외부에서 접근이 안되. 재사용 관점으로 사용하려면 밖으로 빼.
 		//그렇지 않고 이 콜백함수의 전용이라면 보통 _(언더스코어)로 private한 의미를 주는게 일반적임.
			function randomColor(){
				var colorValue = Math.floor((Math.random() * 1000) % 256);
				console.log(colorValue);
				return colorValue;
			} 
 		    //디버깅은 변수의 값을 확인함으로써 데이터의 전달이 잘 이뤄지는 본다.
 		    //var elBody = document.querySelector('body');
 		    //console.log(elBody);
 		    //elBody.style.backgroudColor = ....
 		    function _makeRandomColor () {
 		    	"rgb("+randomColor() + "," +randomColor() + ","+ randomColor()+")"
 		    }
 		    
 		    var _sRandomData = _makeRandomColor(); 
 		    
 		    elBody.style.backgroundColor = _sRandomData;
 		    
			//document.querySelector('body').style.backgroundColor= "rgb("+randomColor() + "," +randomColor() + ","+ randomColor()+")";
			},
	   false); 
 		
 		document.getElementById("login_button").addEventListener("click", function(e){submitter("login");} , false); 
 		//document.getElementById("login_button").addEventListener("click", function(e){submitter("login");} , false); 
 		
 		//submitForm(동사+명사 )
		function submitter(formName) {
			document.forms[formName].submit();
		}

		function checkBbsValue() {
			// == 와 ===는 다르다.
			if (document.bbs.title.value == "") {
				alert("게시글 제목은 공란으로 할 수 없습니다.");
				document.bbs.title.focus();
				return;
			}
			if (document.bbs.content.value == "") {
				alert("게시글 내용은 공란으로 할 수 없습니다.");
				document.bbs.title.focus();
				return;
			}
		}
		function checkLoginValue() {
			if (document.login.id.value == "") {
				alert("아이디를 입력해 주세요.");
				document.login.id.focus();
				return false;
			}
			if (document.login.password.value == "") {
				alert("비밀번호를 입력해 주세요.");
				document.login.password.focus();
				return false;
			}

		}
	</script>

</body>
</html>