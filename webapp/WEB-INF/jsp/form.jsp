<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>YDbaobao</title>
</head>
<body>
	<h1>회원가입</h1>
	<form method="post" action="/customer/create">
		<label for="customerId">아이디</label>
		<input type="text" name="customerId" />
		<br />
		<label for="customerName">이름</label>
		<input type="text" name="customerName" /> <br />
		<label for="customerPassword">비밀번호</label>
		<input type="password" name="customerPassword" />
		<br />
		<label for="customerAgainPassword">비밀번호확인</label>
		<input type="password" name="customerAgainPassword" />
		<br />
		<label for="customerPhone">전화번호</label>
		<input type="text" name="customerPhone" /> <br />
		<label for="customerEmail">이메일</label>
		<input type="text" name="customerEmail" /> <br />
		<label for="customerAddress">주소</label>
		<input type="text" name="customerAddress" /> <br />
		<button type="submit">가입</button>
	</form>
</body>
</html>