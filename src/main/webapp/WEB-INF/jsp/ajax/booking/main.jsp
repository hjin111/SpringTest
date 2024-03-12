<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>통나무 펜션</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="/ajax/css/style.css" type="text/css">
</head>
<body>

	<div id="wrap" >
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
	
	    <section class="banner">
	        <img src="http://marondal.com/material/images/dulumary/web/front/jquery/test06_banner1.jpg" id="bannerImage">
	    </section>
	    <section class="d-flex">
	        <article class="reservation d-flex justify-content-center align-items-center">
	            <div class="display-4">
	                실시간 <br>
	                예약 하기 
	            </div>
	        </article>
	        <article class="reservation-confirm">
	            <div class="m-4">
	                <div class="d-flex align-items-end">
	                    <h3 class="mr-4">예약 확인</h3>
	                
	                </div>
	            
	
	                <div class="no-member-input mt-3y" id="nonMember">
	                    <div class="input-gorup form-inline">
	                        <label class="input-label">이름 :</label>
	                        <input type="text" id="nameInput" class="form-control text-input" id="nameInput">
	                    </div>
	                    <div class="input-gorup form-inline mt-3">
	                        <label class="input-label">전화번호 :</label>
	                        <input type="text" id="phoneNumberInput" class="form-control text-input" id="phoneNumberInput">
	                    </div>
	               
	                </div>
	                <div class="d-flex justify-content-end">
	                    <button class="btn btn-success mt-3 mr-5" id="lookupBtn">조회 하기</button>
	                </div>
	            </div>
	        </article>
	        <article class="reservation-call d-flex justify-content-center align-items-center">
	            <div>
	                <h3>예약문의 : </h3>
	                <h1>010-</h1>
	                <h1>000-1111</h1>
	            </div>
	        </article>
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
   
	<script>
   		$(document).ready(function() {
   			$("#lookupBtn").on("click", function(){
   				
   				// 입력한 값 가져오기
   				let name = $("#nameInput").val();
   				let phoneNumber = $("#phoneNumberInput").val();
   				
   				// validation 유효성 검사
   				if(name == ""){
   					alert("이름을 입력하세요");
   					return ;
   				}
   				
   				if(phoneNumber == ""){
   					alert("전화번호를 입력하세요");
   					return ;
   				}
   				
   				// validation 통과 되면 API 호출하기
   				// API는 jquery의 ajax 함수를 활용해서 수행
   				$.ajax({
   					// 요청에 대한 처리
   					type:"get"
   					, url:"/ajax/booking/search"
   					, data:{"name":name, "phoneNumber":phoneNumber}
   					// response 을 어떻게 처리 할지 익명함수를 통해 등록
   					// 성공했을 때는 response body 데이터를 활용해야 하니깐 그 데이터를 저장할 변수를 파라미터로 추가해준다.
   					, success:function(data){
   						
   						if(data.result == "fail"){ // 비워있는 상태 
   							alert("조회결과가 없습니다.");
   						}else{
	   						
	   						// 조회된 데이터를 가지고 alert 띄우기
	   						alert("이름 : " + data.booking.name 
	   								+ "\n날짜 : " + data.booking.date 
	   								+ "\n일수 : " + data.booking.day
	   								+ "\n인원 : " + data.booking.headcount
	   								+ "\n상태 : " + data.booking.state);
   							
   						}
   						
   					}
   					, error:function(){
   						alert("조회 에러!");
   					}
   				});
   			})
   		});
   
   	</script>
   
</body>
</html>