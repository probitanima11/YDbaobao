<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome YDbaobao!</title>
<meta charset="utf-8">
<link href="http://fonts.googleapis.com/css?family=Lobster" rel="stylesheet" type="text/css">
<style>
html,body {
	margin:0;
	padding:0;
	color:#454545;
}

.wrap {
	margin:0 auto;
}

.content {
	width:980px;
}

ul#util {
	padding:0;
	margin:0;
	list-style:none;
	float:right;
	font-size:12px;
	display:table;
}

ul#util li{
	display:inline-block;
}

ul#util li a {
	text-decoration:none;
	color:#454545;
}

ul#util li a span{
	display:block;
	padding:10px 5px;
}

#search-bar {
	width:410px;
	height:35px;
	border:5px solid #EA6576;
	font-size:20px;
}
</style>
</head>
<body>
<div id='header' style='width:100%;'>
	<div id='util-container' style='width:100%; border-bottom:1px solid #ccc;'>
		<div id='util-bar' class='wrap content' style='height:35px;'>
			<ul id='util'>
				<li><a href='#'><span>로그인</span></a></li>
				<li><a href='#'><span>회원가입</span></a></li><!-- 
				<li><a href='#'><span>장바구니</span></a></li>
				<li><a href='#'><span>주문내역</span></a></li>
				<li><a href='#'><span>문의</span></a></li>
				<li><a href='#'><span>관리자</span></a></li> -->
			</ul>
		</div>
	</div>
	<div id='brand-search-container' style='border-bottom:2px solid #EA6576;'>
		<div id='brand-search-bar' class='wrap content'>
			<div id='search-bar-container' style='padding:25px;'>
				<input id='search-bar' type='text' style='' >
				<button style=' background-color:#EA6576; border-radius:2px; border:0; font-size:20px; color:white; padding:11px 15px; margin:0'>
					검색
				</button>
			</div>			
		</div>
	</div>
	<div id='main-container'>
		<div id='first-section' class='wrap content' style='height:500px;'>
			<div id='' style='width:20%; height:100%; outline:1px solid #ccc; display:table; float:left;'>
				<div>카테고리</div>
				<ul>
					<li><a href='#'><span>상의</span></a></li>
					<li><a href='#'><span>하의</span></a></li>
					<li><a href='#'><span>티셔츠</span></a></li>
					<li><a href='#'><span>아우터</span></a></li>
					<li><a href='#'><span>반바지</span></a></li>
					<li><a href='#'><span>드레스</span></a></li>
					<li><a href='#'><span>스커트</span></a></li>
					<li><a href='#'><span>레깅스/양말</span></a></li>
				</ul>
			</div>
			<div id='' style='width:80%; height:100%; outline:1px solid #ccc; display:table; float:left;'>
				아마도 신상품 페이지
			</div>
		</div>
		<div id='second-section'>
			<div id='brand-selector' class='wrap content' style='height:200px; outline:1px solid #ccc;'>
				브랜드 선택메뉴
			</div>
			<div id='' class='wrap content' style='min-height:600px; border:1px solid #ccc;'>
				상품보여주는 곳
			</div>
		</div>
	</div>
</div>
</body>
</html>