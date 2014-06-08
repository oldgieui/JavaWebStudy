	main.addEventListener("click", function(e) {
			console.log("id : " + e.target.id + ", class : "
					+ e.target.className);
			if (e.target.id === "signinButton") {
				showLoginWindow();
			} else if (e.target.className === "prompt"
					|| e.target.className === "link") {
				resvTitle.innerHTML = e.target.id;
				showResvWindow();
				showResvList(resvTitle.innerHTML);
			} else if (e.target.id === "loginButton") {
				submitForm("login");
			} else if (e.target.id === "sendReservation") {
				sendReservation(resvTitle.innerHTML);
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

		function showResvList(title) {
			resvContents.innerHTML = '';
			var request = new XMLHttpRequest();
			request.open("GET", "/getReservation.do?placeName=" + title, true);
			request.send(null);
			request.onreadystatechange = function() {
				//한번 받아왔던 내용과 같으면 다시 로드하지 않게 하고 싶은데... 작동할 때마다 다 200으로 뜨네 
				if (request.readyState == 4
						&& (request.status == 200 || reqest.status == 304)) {
					var result = JSON.parse(request.responseText);
					console.log(result);
					//일일이 생성하지 않고 content 클래스를 가진 엘리먼트 밑에 다 붙어있어서(구조체마냥) 상위 엘리먼트를 생성하면 밑에 다 붙어 나오게 할 수는 없을까
					for (var i = 0; i < result.length; i++) {
						var row = document.createElement("tr");
						var rowHTML = "<td>" + result[i].RID + "</td>";
						rowHTML = rowHTML + "<td>" + result[i].USERID + "</td>";
						rowHTML = rowHTML + "<td>" + result[i].PLACENAME + "</td>";
						rowHTML = rowHTML + "<td>" + result[i].PURPOSE + "</td>";
						rowHTML = rowHTML + "<td>" + result[i].DATE + "</td>";
						rowHTML = rowHTML + "<td>" + result[i].STARTTIME + "</td>";
						rowHTML = rowHTML + "<td>" + result[i].ENDTIME + "</td>";
						rowHTML = rowHTML + "<td>" + result[i].SUBMITTIME + "</td>";
						
						row.innerHTML = rowHTML;
						resvContents.appendChild(row);
					}
				}
			};
		}


		/**	
		 * 현재 예약정보를 일단 송신한 뒤 전체 목록을 refresh하고 있는데, 이미 있는 목록은 내버려두고 addReservation을 실행한 뒤 
		 * 추가된 것만 확인하고 신규 내역을 직접 엘리먼트로 작성해 추가하도록 하면 서버에 요청을 두 번씩 보낼 필요가 없어짐. 
		 * 세션 정보는 서버에 저장되어 있으므로 ajax로 사용자 id를 받아올 수 있게 하는 기능을 추가해야 한다.
		 */
		function sendReservation(placeName) {
			var request = new XMLHttpRequest();
			var resvForm = document.getElementById("resvForm");
			var url = "/addReservation.do?placeName=" + placeName + "&date="
					+ resvForm.date.value + "&startTime="
					+ resvForm.startTime.value + "&endTime="
					+ resvForm.endTime.value + "&purpose="
					+ resvForm.purpose.value;
			console.log(resvForm.purpose.value);
			console.log(url);
			request.open("GET", url, true);
			request.send(null);
			request.onreadystatechange = function() {
				if (request.readyState == 4
						&& (request.status == 200 || reqest.status == 304)) {
					showResvList(placeName);
				}
			};
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