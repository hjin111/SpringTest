<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
		<h2>회원 정보 리스트</h2>
		<table class="table text-center">
			<thead>
				<tr>
					<th>No</th>
					<th>이름</th>
					<th>전화번호</th>
					<th>국적</th>
					<th>이메일</th>
					<th>자기소개</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="member" items="${memberList}" varStatus="status">
				<tr>
					<td>${status.count}</td>
					<td>${member.name}</td>
					<c:choose>
						<c:when test="${fn:startsWith( member.phoneNumber, 010)}">
						</c:when>
					</c:choose>
					<td>${member.phoneNumber}</td>
					<td>${fn:replace(member.nationality,"삼국시대", "삼국 - ")}</td><%-- member.nationality 여기에 있는 문자 중에 "삼국시대"라고 하는 문자열을 "삼국 - "로 바꾼다.  --%>
					<td><b>${fn:split(member.email, "@")[0]}</b>@${fn:split(member.email,"@")[1]}</td>
					<%--@라는 기호를 통해서 앞 뒤로 구분이 된다. @기준으로 쪼개서 앞부분만 b 태그로 감싸기
						split에 첫번째 인자로는 대상 문자열 두번째 인자로는 쪼갤 기준인 @를 넣으면 얘는 머다?? 배열 그 자체임
						@이로 쪼개면 @기준으로 앞부분이 0번 인덱스 @기준으로 뒷부분이 1번 인덱스니깐 0번 인덱스 값만 끄집어 내서 b 태그로 감싼다.
						@로 쪼갠 배열에서 0번 인덱스에 들어 있는 값만 결국 @ 앞 부분, id 부분만 얻어 오게 됨 이걸 b 태그로 감쌈--%>
					<td>${member.introduce}</td>
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