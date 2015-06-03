<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::브랜드관리</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>브랜드 관리</h1>
		</div>
		
		<div style=" margin: 0 auto; width: 500px;">
			<div>
				<input type="text"/>
				<input type="button" id="brand-add" value="추가"/>
			</div>
			<div style="margin-left: 135px;">
				<input type="button" id="brand-delete" value="삭제" />
			</div>
			<div>
				<select id="brands-list" name="brands" size=10 style=" width: 132px;">
					<c:forEach items="${brands}" var="brand">
						<option class="brands-item" value="${brand.brandId}">${brand.brandName}</option>
					</c:forEach>
				</select>
				
			</div>
		</div>
		<div id="confirm">
			<button>저장</button>
			<button>취소</button>
		</div>
	</div>
	<script>
		// 브랜드 리스트에서 브랜드 선택 시 해당 정보 띄우기 위한 이벤트
		document.querySelector("#brands-list").addEventListener("click", function(e) {
			if(e.target.className === "brands-item")
				console.log(e.target.value);
		}, false);
		document.querySelector("#brand-add").addEventListener("click", function(e) {
			if(e.target.className === "brands-item")
				console.log(e.target.value);
		}, false);
		document.querySelector("#brand-delete").addEventListener("click", function(e) {
			if(e.target.className === "brands-item")
				console.log(e.target.value);
		}, false);
		
			
	</script>
</body>
</html>