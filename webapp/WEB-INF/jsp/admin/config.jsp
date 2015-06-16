<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::설정</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>관리자 설정</h1>
		</div>
		<form:form id="confirm" modelAttribute="adminConfig" method="POST" action="/admin/config/update">
			<form:input type="hidden" path="adminConfigId" value="${adminConfig.adminConfigId}" />
			<form:input type="number" path="adminDisplayProducts" value="${adminConfig.adminDisplayProducts}"/>
			<button type="submit">저장</button>
			<button>취소</button>
		</form:form>
	</div>
</body>
</html>