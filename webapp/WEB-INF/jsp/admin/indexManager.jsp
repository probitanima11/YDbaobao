<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::주문관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>첫화면 관리</h1>
			<form id="imgForm" enctype="multipart/form-data" action="/admin/indexImages" method="POST">
				<input id="imageFile" type="file" name="profileImage" accept="image/x-png, image/gif, image/jpeg" style="display:none;" onchange="changeImage(this)"/>
				<input type="hidden" name="imgCount" value="${imgCount}"/>
			</form>
			<table>
				<tr>
					<c:forEach  var="path" items="${imgPath}" varStatus="status" begin="0" end="3" step="1" >
						<td>
							<c:if test="${imgCount == status.index}"><a href="#"><i onclick="mouseClick()" style="color: rgb(234, 101, 118); font-size: 100px; width: 100%; text-align: center;" class="fa fa-plus-circle"></i></a></c:if>
							<c:if test="${imgCount != status.index}"><img style="max-width:150px;"src="${path}"></c:if>
						</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach  var="path" items="${imgPath}" varStatus="status" begin="4" end="7" step="1" >
						<td>
							<c:if test="${imgCount == status.index}"><a href="#"><i onclick="mouseClick()" style="color: rgb(234, 101, 118); font-size: 100px; width: 100%; text-align: center;" class="fa fa-plus-circle"></i></a></c:if>
							<c:if test="${imgCount != status.index}"><img style="max-width:150px;"src="${path}"></c:if>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
	<script>
	function mouseClick() {
		document.getElementById('imageFile').click();
	}
	
	function changeImage(input) {
		document.querySelector("#imgForm").submit();
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
