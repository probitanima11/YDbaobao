<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>YDbaobao</title>
</head>
<body>
	<h1>회원가입</h1>
	<%-- <form method="post" action="create">
		<label for="userId">아이디(이메일)</label> <input type="text" name="userId" />
		<br /> <label for="userPassword">비밀번호</label> <input type="password"
			name="userPassword" /> <br /> <label for="userPasswordAgain">비밀번호확인</label>
		<input type="userPasswordAgain" name="userPasswordAgain" /> <br /> <label
			for="userName">이름</label> <input type="text" name="userName" /> <br />
		<button type="submit">가입</button>
	</form> --%>
	<form:form modelAttribute="customer" id="signup-form" method="post"
		action="/customer/create">
		<form:input cssClass="text" path="customerId" placeholder="아이디(이메일)"
			autocomplete="off" value="" required="required" />
		<form:input cssClass="text" path="customerName" placeholder="이름"
			autocomplete="off" value="" required="required" />
		<form:password cssClass="text" path="customerPassword"
			placeholder="비밀번호" required="required" />
		<label for="customerPasswordAgain">비밀번호확인</label>
		<input type="password" name="customerPasswordAgain" />
		<button type="submit">회원가입</button>
	</form:form>
</body>
</html>