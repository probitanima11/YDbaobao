<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/main.css" />
<title>카테고리 별 상품 보기</title>
</head>
<body>
	<div id='header' style='width: 100%;'>
		<!-- 상단 navigator -->
		<%@ include file="./commons/_topNav.jsp"%>
		<!-- 브랜드/제품 검색바 -->
		<%@ include file="./commons/_search.jsp"%>
	</div>
	<div style="outline: 1px solid red; width: 100%">
		<div id="categoryBar" class="wrap content" style="height: 40px;">카테고리
			메뉴 바</div>
	</div>
	<div id='main-container' class="wrap content">
		<div style="font-size: 50px;">${category.categoryName}</div>
		<%@ include file="./commons/_brand.jsp"%>
	</div>
	<div id='item-container' class='wrap content'>
		<%@ include file="./commons/_productsBox.jsp" %>
		<%@ include file="./commons/_productsListBar.jsp" %>
	</div>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>