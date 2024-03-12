<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통나무 펜션</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/ajax/css/style.css" type="text/css">
</head>
<body>
	<div id="wrap">
		<header class="mt-4">
			<div class="text-center display-4">통나무 팬션</div>
			<nav class="mt-4">
				<ul class="nav nav-fill">
					<li class="nav-item"><a class="nav-link" href="/ajax/booking/main">팬션소개</a></li>
					<li class="nav-item"><a class="nav-link" href="#">객실보기</a></li>
					<li class="nav-item"><a class="nav-link" href="/ajax/booking/input">예약하기</a></li>
					<li class="nav-item"><a class="nav-link" href="/ajax/booking/list">예약목록</a></li>
				</ul>
			</nav>
		</header>
		
		<section>
			<h2 class="my-4 text-center">예약 하기</h2>
			
			<div class="d-flex justify-content-center">
				<div class="w-50">
					<label>이름</label>
					<input type="text" class="form-control" id="nameInput"> 
					<!-- input 태그에서 입력된 값을 가져오려면 해당하는 input 태그를 객체화 하고 거기서 value 속성의 값을 가져오면 된다 
						 객체화 하기 위해서는 id를 통해 태그를 지칭 하고 그걸로 객체화 시키겠다. -->
	                <label class="mt-3">예약날짜</label>
	                <input type="text" class="form-control" id="dateInput">
	
	                <label class="mt-3">숙박일수</label>
	                <input type="text" class="form-control" id="dayInput">
	
	                <label class="mt-3">숙박인원</label>
	                <input type="text" class="form-control" id="headcountInput">
	
	                <label class="mt-3">전화번호</label>
	                <input type="text" class="form-control" id="phoneNumberInput">
					<!-- 버튼 클릭 이벤트는 해당하는 버튼을 객체화 해서 이벤트를 등록하는 과정으로 진행 된다. 그러면 해당 하는 태그가 객체화 될 수 있도록 지칭 할 수 있는 값 id 속성 부여 하기-->
	                <button  type="button" id="bookingBtn" class="btn btn-warning btn-block mt-3">예약하기</button>
				</div>
			</div>
		</section>
		
		<footer class="mt-3 ml-4">
			 <address>
	            제주특별자치도 제주시 애월읍  <br>
	            사업자등록번호: 111-22-255222 / 농어촌민박사업자지정 / 대표:김통목 <br>
	            Copyright 2025 tongnamu All right reserved
	        </address>
		</footer>
		
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.7.0.min.js" integrity="sha256-2Pmvv0kuTBOenSvLm6bvfBSSHrUJ+3A7x6P5Ebd07/g=" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.min.js" integrity="sha384-+YQ4JLhjyBLPDQt//I+STsc9iw4uQqACwlvpslubQzn4u2UU2UFM80nGisd026JF" crossorigin="anonymous"></script>


	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script>
		$(document).ready(function(){
			
			// on 메소드를 통해 이벤트 등록하기 / 이벤트 이름은 click 이벤트이구 해당 하는 이벤트가 발생했을 떄 수행할 코드 function 익명 함수를 통해 등록해주는 과정 
			$("#bookingBtn").on("click", function(){
				
				let name = $("#nameInput").val();
				let date = $("#dateInput").val();
				let day = $("#dayInput").val();
				let headcount = $("#headcountInput").val();
				let phoneNumber = $("#phoneNumberInput").val();
				
				// validation 유효성 검사
				// 항상 사용자가 입력받은 값을 가져와서 처리할 때는 벨리데이션 무조건 따라온다.
				// 사용자가 입력한 값이 제대로 안된 상태에서 서버로 전달이 되면 서버에서 잘못 처리가 되서 문제를 발생 시킬 수 있음
				// 사용자는 절대 우리가 의도한 대로 꼭 정확히 입력할거란 보장이 없다.
				// 그래서 항상 사용자가 입력한 값을 다룰 때는 벨리데이션이 필수로 들어간다.
				
				// 가장 기본이 되는 validation은 비어 있는지 확인 하는 것
				if( name == ""){
					alert("이름을 입력하세요");
					return ;
				}
				
				if(date == ""){
					alert("날짜를 선택하세요");
					return ;
				}
				
				if(day == ""){
					alert("숙박일수를 입력하세요");
					return ;
				}
				
				// day에 들어가 있는 값이 숫자인지 아닌지를 판단해서 
				// day 가 숫자가 아닌 경우 - 경고창 띄우가 기능 수행 안되도록 만들기
				// NaN - Not a Number , true로 리턴하면 숫자가 아닌거임
				if(isNaN(day)){ // isNaN 함수에 인자로 확인하고 싶은 값을 넣어주면 해당하는 문자열이 숫자로 만들어진건지 아닌건지를 판단해줌
					//이렇게 표현하면 숫자가 아닌거임
					alert("숙박일수는 숫자만 입력 가능합니다")
					return ;
				}
				
				if(headcount == ""){
					alert("숙박인원을 입력하세요");
					return ;
				}
				
				if(isNaN(headcount)){
					alert("숙박인원은 숫자만 입력 가능합니다");	
					return ;
				}
				
				if(phoneNumber == ""){
					alert("전화번호를 입력하세요");
					return ;
				}
				
				// if문 들을 잘 타고 내려와서 하나도 안 걸리고 내려왔을 떄만 수행될 코드가 여기 들어가면 됨
				// 입력된 값을 기반으로 API로 진행 되면 됨
				$.ajax({
					// request 요청에 필요한 데이터 정리
					type:"get"
					, url:"/ajax/booking/create"
					, data:{"name": name, "date":date, "day":day, "headcount":headcount, "phoneNumber":phoneNumber} // 파라미터 이름을 key로 그에 대응 되서 전달될 값을 value(input에 입력된 값)로 정하기
					// 요청에 의한 응답이 돌아왔을 때 그 응답에 대한 처리를 어떻게 할건지를 함수로 등록해서 진행
					// 응답에 대한 데이터, response body에 대한 데이터를 data라는 파라미터로 전달 받을 수 있음
					, success:function(data){ 
						// 성공 : {"result":"success"} -> 이 response 내용은 data라는 변수에 저장이 되어서 전달이 된다. 근데 json 문자열인 경우는 ajax가 판단을 해서 해당하는 json 문자열을 기반으로 자바스크립트 객체로 변환을 해서 data라는 변수에 저장을 시켜준다. 그래서 문자열이 아니라 변형된 자바스크립트 객체이다.
						// 실패 : {"result":"fail"}
						if(data.result == "success"){
							// 입력 후 예약이 성공하면 예약 목록 페이지로 이동
							location.href = "/ajax/booking/list";
						} else {
							alert("예약 실패");
						}
					}
					,error:function(){
						alert("예약 에러");
					}
				});
				
			});
				
			$("#dateInput").datepicker({
				minDate:0
				, dateFormat:"yy년 m월 d일"
			});
		})
	</script>
	
	
</body>
</html>