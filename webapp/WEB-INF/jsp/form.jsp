<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>YDbaobao</title>
</head>
<body>
	<h1>회원가입</h1>
	<form:form modelAttribute="customer" method="post"
		action="/customer/create">
		<label for="customerId">아이디</label>
		<form:input path="customerId" />
		<c:if test="${not empty message}">
			<label class="error">${message}</label>
		</c:if>
		<br />
		<label for="customerName">이름</label>
		<form:input path="customerName" />
		<br />
		<label for="customerPassword">비밀번호</label>
		<form:password path="customerPassword" />
		<br />
		<label for="customerAgainPassword">비밀번호확인</label>
		<input type="password" name="customerAgainPassword" />
		<br />
		<label for="customerPhone">전화번호</label>
		<form:input path="customerPhone" />
		<br />
		<label for="customerEmail">이메일</label>
		<form:input path="customerEmail" />
		<br />
		<label for="customerAddress">주소</label>
		<form:input path="customerAddress" />
		<br />
		<button type="submit">가입</button>
	</form:form>
</body>
</html>