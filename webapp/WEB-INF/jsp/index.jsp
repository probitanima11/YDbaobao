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
				<img id="indexImage">
			</div>
		</div>
		<div id="second-section" class="wrap content">
			<!-- 브랜드 탭(A~Z) 메뉴 -->
			<%@ include file="./commons/_brand.jsp" %>

			<div id="item-container" class="wrap content">
				<%@ include file="./commons/_productsBox.jsp" %>

				<%@ include file="./commons/_paging.jsp" %>
			</div>
		</div>
	</div>
	<div id="footer">
		<div class="content wrap">Footer...</div>
	</div>
	<script>
		var imgPaths = "${imgPath}".substring(1).split(",");
		var imgPathsLength = imgPaths.length;
		var imgIndex = 0;
 		window.addEventListener("load", function() {
 			// paging();
			var imgEl = document.querySelector("#indexImage");
 			if(imgPaths[0].length === 0) {
 				imgEl.remove();
 				return;
 			}
 			imgEl.src = imgPaths[imgIndex];
 			
			setInterval(function(){
				addIndex();
				fadeOut(imgEl);
			}, 5000);
		}, false);
 		
 		function addIndex() {
 			if(imgIndex >= imgPathsLength-1)
 				imgIndex=0;
 			else
 				imgIndex++;
 		}

		function fadeIn(id) {
			var level = 0;
			var inTimer = null;
			inTimer = setInterval( function(){ level = fadeInAction(id, level, inTimer); }, 50 );
		}

		function fadeInAction(id, level, inTimer) {
			level = level + 0.1;
			changeOpacity(id, level);
			if(level > 1) 
			{
				clearInterval(inTimer);
			}
			return level;
		}

		function fadeOut(id) {
			var level = 1;
			var outTimer = null;
			outTimer = setInterval( function(){ level = fadeOutAction(id, level, outTimer); }, 50 );
		}

		function fadeOutAction(id, level, outTimer) {
			level = level - 0.1;
			changeOpacity(id, level);
			if(level < 0) 
			{
				clearInterval(outTimer);
				var imgEl = document.querySelector("#indexImage");
				imgEl.src = imgPaths[imgIndex];
				fadeIn(imgEl);
			}
			return level;
		}

		function changeOpacity(obj,level) {
			obj.style.opacity = level; 
			obj.style.MozOpacity = level; 
			obj.style.KhtmlOpacity = level;
			obj.style.MsFilter = "'progid:DXImageTransform.Microsoft.Alpha(Opacity=" + (level * 100) + ")'";
			obj.style.filter = "alpha(opacity=" + (level * 100) + ");"; 
		}

		function paging() {
			for(var i = 1; i <= ${totalPage}; i++) {
				var li = document.createElement('li');
				li.innerHTML = '<a href="/index?page=' + i + '">' + i + '</a>';
				document.querySelector('.contents-nav ul').appendChild(li);
			}
		}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
