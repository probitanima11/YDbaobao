<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::주문관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<style>
td {
	width:150px;
}
img.hover{
	display:block;
	float:left;
	max-width:150px;
	display:none;
	position: absolute;
}

img.snap{
	display:block;
	float:left;
	max-width:150px;
}
</style>
</head>
<body>
	<div id="header" style="width: 100%;">
		<%@ include file="./_adminTopNav.jsp"%>
	</div>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>첫화면 관리</h1>

 			<form id="imgForm" enctype="multipart/form-data" action="/admin/indexImages/" method="post">
				<!-- <input type="hidden" name="_method" value="delete" /> -->
				<input type="file" id="imageFile"  name="profileImage" accept="image/x-png, image/gif, image/jpeg" style="display:none;" onchange="changeImage(this)"/>
				<input type="hidden" id="imgIndex" name="imgIndex" value="${imgIndex}"/>
			</form>
			<table>
				<tr>
					<c:forEach  var="path" items="${imgPath}" varStatus="status" begin="0" end="3" step="1" >
						<td>
						<a href="#">
							<c:if test="${path == ''}">
								<i onclick="addClick(${status.index})" style="color: rgb(234, 101, 118); font-size: 100px; width: 100%; text-align: center;" class="fa fa-plus-circle"></i>
							</c:if>
							<c:if test="${path != ''}">
								<img id="hoverImage${status.index}" class="hover" onclick="deleteClick(${status.index})" onmouseout="mouseOut(${status.index})" src="/image/delete_hover.png">
								<img class="snap" src="${path}" onmouseover="mouseOver(${status.index})">
							</c:if>
						</a>
						</td>
					</c:forEach>
				</tr>
				<tr>
					<c:forEach  var="path" items="${imgPath}" varStatus="status" begin="4" end="7" step="1" >
						<td>
						<a href="#">
							<c:if test="${path == ''}">
								<i onclick="addClick(${status.index})" style="color: rgb(234, 101, 118); font-size: 100px; width: 100%; text-align: center;" class="fa fa-plus-circle"></i>
							</c:if>
							<c:if test="${path != ''}">
								<img id="hoverImage${status.index}" class="hover" onclick="deleteClick(${status.index})" onmouseout="mouseOut(${status.index})" src="/image/delete_hover.png">
								<img class="snap" src="${path}" onmouseover="mouseOver(${status.index})">
							</c:if>
						</a>
						</td>
					</c:forEach>
				</tr>
			</table>
		</div>
	</div>
	<script>
	function addClick(imgIndex) {
		document.querySelector("#imgIndex").value = imgIndex;
		document.querySelector('#imageFile').click();
	}
	
	function changeImage(input) {
		var form = document.querySelector("#imgForm");
		form.action = action="/admin/indexImages/";
		form.submit();
	}
	
	function mouseOver(imgIndex) {
		document.querySelector("#hoverImage"+imgIndex).style.display = "block";
	}
	
	function mouseOut(imgIndex) {
		document.querySelector("#hoverImage"+imgIndex).style.display = "none";
	}
	
	function deleteClick(imgIndex) {
		var form = document.querySelector("#imgForm");
		form.action = action="/admin/indexImages/"+imgIndex;
		form.submit();
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
