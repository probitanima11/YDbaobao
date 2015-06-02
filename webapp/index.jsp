<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome YDbaobao!</title>
<meta charset="utf-8">
<style>
html,body {
	margin:0;
	padding:0
}

.wrap {
	margin:0 auto;
}

.content {
	width:980px;
}
</style>
</head>
<body>
<div id='header' style='width:100%;'>
	<div id='util-container' style='width:100%; border-bottom:1px solid #ccc;'>
		<div id='util-bar' class='wrap content' style='height:50px;'>
			로그인/회원가입/장바구니/...
		</div>
	</div>
	<div id='brand-search-container' style='border-bottom:2px solid #EA6576; height:95px;'>
		<div id='brand-search-bar' class='wrap content'>
		브랜드로고 / 검색창
		</div>
	</div>
	<div id='vertical-menu-bar' style='background-color:#EA6576;'>
		<div id='' class='wrap content' style='height:55px; color:#fff'>
			가로메뉴 또는 카테고리
		</div>
	</div>
	<div id='main-container'>
		<div id='first-section' class='wrap content' style='height:500px;'>
			<div id='' style='width:20%; height:100%; outline:1px solid #ccc; display:table; float:left;'>
				세로메뉴 또는 카테고리
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