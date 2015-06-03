<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>YDbaobao</title>
</head>
<body>
	<h1>로그인</h1>
	<form:form modelAttribute="customer" method="post" action="/customer/login">
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
</body>
</html>