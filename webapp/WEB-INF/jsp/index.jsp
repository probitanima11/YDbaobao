<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Welcome YDbaobao!</title>
<meta charset="utf-8" />
<link rel="stylesheet" type="text/css" href="/css/main.css" />
<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css" />
</head>
<body>
	<div id="header" style="width: 100%;">
		<!-- 상단 navigator -->
		<%@ include file="./commons/_topNav.jsp"%>
		<!-- 브랜드/제품 검색바 -->
		<%@ include file="./commons/_search.jsp"%>
	</div>
	<div>
		<!-- 수평 카테고리 메뉴 -->
		<%@ include file="./commons/_horizontalCategory.jsp"%>
	</div>
	<div id="main-container">
		<div id="first-section" class="wrap content" style="position:relative; padding:25px 0;">
			<div id="showcase">
				<img src="http://www.stylenoriter.co.kr/images/02.jpg">
			</div>
		</div>
		<div id="second-section" class="wrap content">
			<!-- 브랜드 탭(A~Z) 메뉴 -->
			<%@ include file="./commons/_brand.jsp" %>

			<div id="item-container" class="wrap content">
				<%@ include file="./commons/_productsBox.jsp" %>
				<%@ include file="./commons/_productsListBar.jsp" %>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="content wrap">Footer...</div>
	</div>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
