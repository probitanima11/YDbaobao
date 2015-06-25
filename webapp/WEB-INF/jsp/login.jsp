<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="/css/main.css" />
<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css" />
<title>YDbaobao</title>
</head>
<body>
	<div id="header">
		<!-- 상단 navigator -->
		<%@ include file="./commons/_topNav.jsp"%>
	</div>
	<div id="main-container">
		<div id="first-section" class="wrap" style="height: 635px;">
			<div class="loginBox">
				<form:form modelAttribute="customer" method="post" action="/login">
					<h1>로그인</h1>
					<label for="customerId">아이디</label>
					<form:input path="customerId" />
					<br/>
					<label for="customerPassword">비밀번호</label>
					<form:password path="customerPassword" />
					<br/>
					<c:if test="${not empty message}">
						<label class="error">${message}</label><br />
					</c:if>
					<button type="submit">로그인</button>
				</form:form>
			</div>
		</div>
	</div>
	<div id="footer">
		<%@ include file="./commons/_footer.jsp"%>
	</div>
</body>
</html>