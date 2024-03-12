<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	    
	    <section class="my-5">
	    
	         <h2 class="text-center">예약 목록 보기</h2>
	         <table class="table text-center">
	         	<thead>
	         		<tr>
	         			<th>이름</th>
	         			<th>예약날짜</th>
	         			<th>숙박일수</th>
	         			<th>숙박인원</th>
	         			<th>전화번호</th>
	         			<th>예약상태</th>
						<th></th>
	         		</tr>
	         	</thead>
	         	<tbody>
	         		<c:forEach var="booking" items="${bookingList}">
		       		<tr>
		       			<td>${booking.name}</td>
		       			<td><fmt:formatDate value="${booking.date}" pattern="yyyy년 M월 d일" /></td>
		       			<td>${booking.day}</td>
		       			<td>${booking.headcount}</td>
		       			<td>${booking.phoneNumber}</td>
		       			<c:choose>
		       				<c:when test="${booking.state eq '대기중'}">
		       					<td class="text-info">${booking.state}</td>
		       				</c:when>
		       				<c:when test="${booking.state eq '확정'}">
		       					<td class="text-success">${booking.state}</td>
		       				</c:when>
		       				<c:when test="${booking.state eq '취소'}">
		       					<td class="text-danger">${booking.state}</td>
		       				</c:when>
		       				<c:otherwise>
		       					<td>${booking.state}</td>
		       				</c:otherwise>
		       			</c:choose>
		 																			<!-- button 태그의 id 속성 안된다 왜? 버튼 태그는 forEach를 통해서 반복문으로 만들어지고 있다. 정해진 id로 태그를 만들면 같은 id를 가진 태그들이 만들어진다.-->
		 																			<!-- delete btn 이라는 클래스를 버튼 태그에 추가해줌 그러면 같은 이름이 들어간 태그들이 만들어지지만 이거는 클래스 속성에 부합하는 사용법이기 떄문에 이걸 통해서 버튼 태그들을 객체화 할거임-->
		       			<td><button type="button" class="btn btn-danger btn-sm delete-btn" data-booking-id="${booking.id}">삭제</button> </td>
		       																		<!-- 이 버튼에다가 각각 구분할 수 있는 값을 부여를 하는데 이왕 부여하는 김에 쓸모 있는 값, 삭제할 때 필요한 값 머죠?? id 값을 여기다가 부여하기
		       																		     태그들에는 속성이 정해져 있고요 버튼 태그에서 사용할 수 있는 몇몇가지 속성들이 있는데  그 중에서도 태그에 뭔가 데이터를 심어놓고 싶을 때 사용할 수 있는 속성 뭐다?? 데이터 속성이다.
		       																		     데이터 속성은 다른 속성들과 다르게 속성 이름을 직접 부여할 수 있다. 다른 속성들은 정해진 속성 이름을 그대로 활용해야 되지만 데이터 속성은 개발자가 직접 속성 이름을 부여할 수 있다.-->
		       		</tr>
		       		</c:forEach>	
	       		</tbody>
	         </table>
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
			
			// delete-btn 이라는 클래스를 가진 태그들을 모두 객체화 한것임.
			// 태그들을 객체화 했기 떄문에 $(".delete-btn") 이것 자체가 단순히 하나의 버튼 태그가 아니라 버튼 태그들을 묶어 놓은 일종의 배열 형태이다.
			// jquery는 이런 형태의 객체를 다루기 쉽도록 메소드를 구성해 놨음. 그냥 태그 객체들을 묶어놓은 배열에다가 바로 똑같은 메소드를 호출해주면 그 태그들 모두에다가 같은 메소드가 호출이 된다.
			$(".delete-btn").on("click", function(){
				
				// 이벤트가 발생한 버튼의 data-booking-id 값을 얻어 온다.
				// 여러 버튼들을 동시에 처리하는 함수로 구성을 했는데 그 중에서 이벤트가 발생한 그 태그는 this 라는 키워드로 얻어온다.
				// 그 버튼 태그의 data 속성의 값은 data() 라는 함수를 통해 얻어온다.
				// 근데 data 뒤에 따라오는 키워드를 통해 값을 얻어온다. 인자로 넣어준다.
				let id = $(this).data("booking-id");
				
				// ajax 라는 메소드에 요청과 응답에 필요한 여러 정보들을 옵션에 형태로 나열할거임
				// id 하나 전달해서 delete 라는 주소로 요청을 하면 id와 일치하는 행이 삭제되고
				// 성공 : {"result":"success"}
				// 실패 : {"result":"fail"}
				// 형태의 response가 응답으로 전달된다.
				$.ajax({
					type : "get"
					, url:"/ajax/booking/delete"
					, data:{"id":id}
					, success:function(data){
						// 성공 : {"result":"success"}
						// 실패 : {"result":"fail"}
						if(data.result == "success"){
							location.reload(); // list 페이지 새로고침
						}else{
							alert("삭제 실패");
						}
					}
					, error:function(){
						alert("삭제 에러!");
					}
				});
				
			});
		});
	</script>  
	    
	    
</body>
</html>