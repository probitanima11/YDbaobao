<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>관리자페이지::브랜드관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<style>
	#grades input[type='text'], input[type='text'] {
		width:200px;
		font-size:15px;
	}
</style>
</head>
<body>
	<div id="header" style="width: 100%;">
		<%@ include file="./_adminTopNav.jsp"%>
	</div>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>등급 관리</h1>
			<table id="grades" style="width: 500px;">
				<tr>
					<th style="width:100px;">등급</th>
					<th style="width:200px;">할인율(%)</th>
					<th style="width:200px;"></th>
				</tr>
				<c:forEach var="grade" items="${grades}">
					<tr id="${grade.gradeId}">
						<td><input type="text" value="${grade.gradeId}" readonly></td>
						<td style="display: inline-flex;">
							<input type="text" value="${grade.discount}" data-id="${grade.gradeId}" onkeypress="return isNumberKey(event);">
							<span>(%)</span>
						</td>
						<td>
							<button class="update-grade-btn">수정</button>
						</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script>
		window.addEventListener('load', function() {
			setBrandEvent();
		}, false);

		function setBrandEvent() {
			var updateBtn = document.querySelectorAll('.update-grade-btn');
			// for문 조건문용 변수
			var length;

			for(var i = 0, length = updateBtn.length; i < length; i++) {
				updateBtn[i].addEventListener('click', function(e) {
					updateGrade(e);
				}, false);
			}

		}

		function updateGrade(e) {
			var gradeId = e.target.parentElement.parentElement.id;
			var discount = document.querySelector('input[data-id="' + gradeId + '"]').value;
			ydbaobao.ajax({
				method:'put',
				url:'/api/grades/' + gradeId + '/' + discount,
				success: function(req) {
					if(req.responseText === "fail")
						alert('숫자만 입력 가능합니다.');
					else
						alert(gradeId + '등급의 할인율이 조정되었습니다');
				}
			});
		}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>