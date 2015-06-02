<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>YDbaobao</title>
</head>
<body>
	<h1>로그인</h1>
	<form method="post" action="/customer/login">
		<label for="customerId">아이디</label>
		<input type="text" name="customerId" />
		<br />
		<label for="customerPassword">비밀번호</label>
		<input type="password" name="customerPassword" />
		<br />
		<button type="submit">로그인</button>
	</form>
</body>
</html>