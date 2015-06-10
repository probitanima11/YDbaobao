<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="http://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/main.css" />
<title>카테고리 별 상품 보기</title>
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
	<div id="main-container" class="wrap content">
		<c:choose>
			<c:when test="${not empty brand}">
				<div style="font-size: 40px; padding:25px 0 15px 0;">${brand.brandName}</div>
			</c:when>

			<c:otherwise>
				<div style="font-size: 40px; padding:25px 0 15px 0;">${category.categoryName}</div>
			</c:otherwise>
		</c:choose>
		<%@ include file="./commons/_brand.jsp"%>
	</div>
	<div id="item-container" class="wrap content">
		<%@ include file="./commons/_productsBox.jsp" %>
		<%@ include file="./commons/_productsListBar.jsp" %>
	</div>
		<script>
		window.addEventListener('load', function() {
			setBrandSearchEvent();
		}, false)

		function setBrandSearchEvent() {
			var firstLetterList = document.querySelectorAll('.first-letter');
			
			for(var i = 0, length = firstLetterList.length; i < length; i++) {
				firstLetterList[i].addEventListener('click', function(e) {
					searchBrand(e.target);
				}, false);
			}
		}

		function searchBrand(target) {
			ydbaobao.ajax({
				method:'get',
				url:'/brand/search?firstLetter=' + target.textContent,
				success: function(req) {
					changeBrandList(JSON.parse(req.responseText));
				}
			});
		}

		function changeBrandList(brands) {
			// 기존 brand list 삭제
			var ul = document.querySelector('#brand-list > ul');
			while(ul.firstChild) {
				ul.removeChild(ul.firstChild);
			}

			// 검색된 브랜드 리스트 출력
			for(var i = 0, length = brands.length; i < length; i++) {
				var li = document.createElement('li');
				li.innerHTML += '<a href="/brand/products/' + brands[i].brandId + '"><span>' + brands[i].brandName + '</span></a>';
				ul.appendChild(li);
			}
		}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
