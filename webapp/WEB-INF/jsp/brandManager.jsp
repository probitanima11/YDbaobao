<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::브랜드관리</title>
<style>
html, body {
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	background-color: #f8f8f8;
}
#container {
	width: 980px;
	height: 100%;
	background-color: #fff;
	border: 1px solid #ccc;
	margin: 0 auto;
}
#menu {
	position: relative;
	float: left;
	width: 150px;
	height: 100%;
	border-right: 1px solid #ccc;
	z-index: 9;
}
#menu ul {
	list-style: none;
	padding: 0;
	margin: 0;
}
#menu ul li a {
	text-decoration: none;
}
#menu ul li a span:hover {
	position: relative;
	left: 15px;
}
#menu ul li a span {
	position: relative;
	display: block;
	padding: 10px 15px;
	left: 0;
	color: #454545;
	transition-property: left;
	transition-duration: .3s;
}
#content {
	position: relative;
	padding: 20px;
	padding-left: 170px;
	min-height: 200px;
}
h1 {
	margin: 0;
}
table {
	padding-top: 25px;
}
table th {
	background-color: #EA6576;
	color: #fff;
	font-weight: 200;
	padding: 5px 0;
}
table td {
	text-align: center;
	font-size: 13px;
}
#confirm {
	float: right;
}
</style>
</head>
<body>
	<div id="container">
		<div id="menu">
			<a href="/admin" style="text-decoration: none;"> <span
				style="display: block; padding: 15px 15px; color: #fff; background-color: #EA6576">관리자메뉴</span>
			</a>
			<ul>
				<li><a href="/admin/manage/member"><span>회원관리</span></a></li>
				<li><a href="/admin/add/product"><span>상품등록</span></a></li>
				<li><a href="/admin/manage/product"><span>상품관리</span></a></li>
				<li><a href="/admin/manage/brand"><span>브랜드관리</span></a></li>
				<li><a href="/admin/manage/category"><span>카테고리관리</span></a></li>
				<li><a href="/admin/config"><span>관리자설정</span></a></li>
			</ul>
		</div>
		<div id="content">
			<h1>브랜드 관리</h1>
		</div>
		<div id="confirm">
			<button>저장</button>
			<button>취소</button>
		</div>
	</div>
</body>
</html>