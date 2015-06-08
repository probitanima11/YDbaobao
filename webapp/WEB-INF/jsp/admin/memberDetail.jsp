<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>관리자페이지::회원관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>회원상세보기</h1>
			<table id="member-table" style="width: 800px;">
				<tr>
					<th>회원아이디</th>
					<td>${customer.customerId}</td>
				</tr>
				<tr>
					<th>회원이름</th>
					<td>${customer.customerName}</td>
				</tr>
				<tr>
					<th>이메일</th>
					<td>${customer.customerEmail}</td>
				</tr>
				<tr>
					<th>등급</th>
					<td>${customer.customerGrade}</td>
				</tr>
				<tr>
					<th>가입일</th>
					<td>${customer.customerCreateDate}</td>
				</tr>
				<tr>
					<th>주소</th>
					<td>${customer.customerAddress}</td>
				</tr>
				<tr>
					<th>핸드폰</th>
					<td>${customer.customerPhone}</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>