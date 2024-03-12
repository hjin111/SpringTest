<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL format library</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body>

	<div class="container">
		<h2>1. 후보자 득표율</h2>
		
		<table class="table text-center">
			<thead>
				<tr>
					<th>기호</th>
					<th>득표 수</th>
					<th>득표 율</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="candidate" items="${candidates}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td><fmt:formatNumber value="${candidate}" /></td>
					<td><fmt:formatNumber value="${candidate/ 1000000}" type="percent"/></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<h2>2. 카드 명세서</h2>
		<table class="table text-center">
			<thead>
				<tr>
					<th>사용자</th>
					<th>가격</th>
					<th>사용날짜</th>
					<th>할부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cardBill" items="${cardBillList}">
				<tr>
					<td>${cardBill.store}</td>
					<td><fmt:formatNumber value="${cardBill.pay}" type="currency"/></td>
					<%-- 2025-09-14 --%>
					<fmt:parseDate value="${cardBill.date}" pattern="yyyy-MM-dd" var="date" />
					<td><fmt:formatDate value="${date}" pattern="yyyy년 M월 d일" /></td>
					<%-- formatDate는 Date 객체를 원하는 형태의 문자열로 바꿔주는 놈이다.
					근데 ${cardBill.date} 얘는 그냥 문자열이다. 문자열로 통해 바로 날짜 정보를 처리할 수는 없다. 
					문자열로 되어 있는 날짜, 시간 정보를 Date 객체로 변환할 수 있다. 그걸 해주는 태그가  formatLibrary의 paseDate라는 태그이다.
					이 태그를 통해서 대상이 되는 값 value 에다가 변환하고자 하는 문자열을 넣어주면 되고 그리고 나서 parseDate는 변환하기 위한게 아니라 
					이 문자열이 가진 형태를 패턴을 잡아 줘야 한다.--%>
					<%-- parseDate는 지금 value에 들어간 이 문자열에 날짜,시간 정보가 어떤 형식인지를 pattern으로 세팅해줘야 한다.--%>
					
					<td>${cardBill.installment}</td>
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