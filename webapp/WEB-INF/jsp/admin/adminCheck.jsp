<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>YDbaobao:: 관리자페이지</title>
</head>
<body>
<h1>관리자페이지 접근</h1>
<form method="post" action="/admin/check">
	<label for="adminPassword">관리자 비밀번호 입력: 1111</label>
	<input type="password" name="adminPassword" value="1111"/><br/>
	<button type="submit">확인</button>
</form>
</body>
</html>