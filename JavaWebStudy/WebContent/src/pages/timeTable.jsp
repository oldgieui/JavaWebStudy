<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>TimeTable</title>
<link rel="stylesheet" type="text/css" href="../stylesheets/TimeTable.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<div id = "header">
        <div id="todayDate">
            :: 오늘 날짜 ::
        </div>
        <div id="inputWrap">
        	<form name="reservation" action="/timetable.do" method="get">
                시작 : <input name="startTime" type="time">
                끝 : <input name="endTime" type="time">
                <input name="submitTime" type="submit" value="예약">
        	</form>
        </div>
    </div>
    <div id="container">
        <div id="timeLine">
<!--            좌측, 시간대별 표시 들어가는 라인-->
        </div>
        <div id="contentLine">
<!--            우측, 예약 내용 들어가는 라인-->
        </div>
    </div>
    
    
    <script type="text/javascript">
        var timeLine = document.getElementById("timeLine");
        for (var i = 0; i < 24; i++) {
            var border = document.createElement("div");
            border.setAttribute("class", "border");
            
            var hSquare = document.createElement("div");
            hSquare.setAttribute("class", "hour");
            if (i<9) 
                hSquare.innerHTML = "0"+ i + "~" +"0"+ (i+1);
            else if (i === 9)
                hSquare.innerHTML = "0"+ i + "~" + (i+1);
            else
                hSquare.innerHTML = i + "~" +(i+1);
            
            timeLine.appendChild(border);
            timeLine.appendChild(hSquare);
		}
        
        function setTime(){
        	
        }
    </script>
</body>
</html>