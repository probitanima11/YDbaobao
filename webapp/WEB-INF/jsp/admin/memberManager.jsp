<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>관리자페이지::회원관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<script src="/js/ydbaobao.js"></script>
<style>
 #member-table .btn {
 	border:0;
 	border-bottom:2px solid black;
 	margin:0 1px;
 	padding:5px;
 	color:#fff;
 }
 
 #member-table .btn.btn-warn {
 	background-color:#EDA900;
 	border-bottom:2px solid #813D00;
 }
 
 #member-table .btn.btn-err {
 	background-color:#F15F5F;
 	border-bottom:2px solid #850000;
 }
</style>
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>회원관리</h1>
			<table id="member-table" style="width: 800px;">
				<thead>
					<tr>
						<th>회원아이디</th>
						<th>회원이름</th>
						<th>이메일</th>
						<th>등급</th>
						<th>가입일</th>
						<th>  </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="member" items="${members}">
						<tr id="member-${member.customerId}">
							<td>${member.customerId}</td>
							<td>${member.customerName}</td>
							<td>${member.customerEmail}</td>
							<td>${member.customerGrade}</td>
							<td>${member.customerCreateDate}</td>
							<td>
								<a href="/admin/customers/${member.customerId}"><button class="btn btn-warn"><i class="fa fa-info-circle"></i>  상세정보</button></a>
								<a href="#"><button class="btn btn-err" onclick="deleteCustomer('${member.customerId}')"><i class="fa fa-remove"></i>  삭제</button></a>
							</td>	
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		function deleteCustomer(customerId) {
			var isDelete = confirm("다음의 고객 데이터가 삭제됩니다:"+customerId);
			if (isDelete) {
				window.location.href="/customer/delete/"+customerId;
			}
		}
	</script>
</body>
</html>