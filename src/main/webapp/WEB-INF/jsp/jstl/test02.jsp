<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>HOT 5</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

	<div class="container">

		<h2>HOT 5</h2>

		<table class="table text-center">
			<thead>
				<tr>
					<th>순위</th>
					<th>제목</th>
				</tr>
			</thead>
			<tbody>
				<!-- 속성 두 가지가 들어감 향상된 for 문과 매칭 시켜 생각하면 됨
				 향상된 for 문은 list 안에 들어있는 객체 하나를 저장하기 위한 변수를 만들어 주고 
				 그 뒤에 list를 명시해 주면 list 안에 들어 있는 객체 하나를 앞에 있는 변수에 저장 하면서 반복이 된다.
				 그걸 태그 구성으로 만드는거임 var 라는 속성을 통해서 리스트 안에 들어 있는 태그 하나를 저장할 변수를 만드는거임 
				 items 라고 하는 속성에다가 리스트를 지정해주면 됩니다.
				 해당 하는 리스트는 model 안에 들어 있는 값을 꺼내서 쓰는 거니깐 model에 있는 값을 EL 태그를 통해 꺼내 와서 적용시켜줌 
				 -->
				<c:forEach var="music" items="${musicRanking}" varStatus="status">
					<!-- varStatus라는 속성에 변수를 하나 지정해준다. 이 변수 값을 통해 이 반복하는 상태를 확인 할수가 있다. -->
					<tr>
						<td>${status.count}</td>
						<td>${music}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<h2>멤버십</h2>
		<table class="table text-center">
			<thead>
				<tr>
					<th>이름</th>
					<th>전화번호</th>
					<th>등급</th>
					<th>포인트</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberList}">
					<tr>
						<td>${member.name}</td>
						<td>${member.phoneNumber}</td>

						<!-- eq 키워드는 같다 라는 뜻 -->
						<c:choose>
							<c:when test="${member.grade eq 'VIP'}">
								<td class="text-danger">${member.grade}</td>
							</c:when>
							<c:when test="${member.grade eq 'GOLD'}">
								<td class="text-warning">${member.grade}</td>
							</c:when>
							<c:otherwise>
								<td>${member.grade}</td>
							</c:otherwise>
						</c:choose>
						
						<c:choose>
							<c:when test="${member.point >= 5000 }">
								<td class="text-primary">${member.point}</td>
							</c:when>
							<c:otherwise>
								<td>${member.point}</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"
		integrity="sha384-+sLIOodYLS7CIrQpBjl+C7nPvqq+FbNUBDunl/OZv93DB7Ln/533i8e/mZXLi/P+"
		crossorigin="anonymous"></script>
</body>
</html>