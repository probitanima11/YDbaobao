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
	#brands input[type='text'] {
		width:300px;
		font-size:20px;
	}
</style>
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>브랜드 관리</h1>
			<table id="brands" style="width: 500px;">
				<tr>
					<th style="width:300px;">브랜드명</th>
					<th style="width:200px;"></th>
				</tr>
				<c:forEach var="brand" items="${brands}">
					<tr id="${brand.brandId}">
						<td><input type="text" value="${brand.brandName}" data-id="${brand.brandId}"></td>
						<td>
							<button class="update-brand-btn">수정</button>
							<button class="delete-brand-btn">삭제</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td><input id="new-brand" type="text"></td>	
					<td>
						<button class="create-brand-btn">추가</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		window.addEventListener('load', function() {
			setCategoryEvent();
		}, false);

		function setCategoryEvent() {
			var updateBtn = document.querySelectorAll('.update-brand-btn');
			var deleteBtn = document.querySelectorAll('.delete-brand-btn');
			var createBtn = document.querySelector('.create-brand-btn');
	
			// for문 조건문용 변수
			var length;

			for(var i = 0, length = updateBtn.length; i < length; i++) {
				updateBtn[i].addEventListener('click', function(e) {
					updateBrand(e);
				}, false);
			}

			for(var j = 0, length = deleteBtn.length; j < length; j++) {
				deleteBtn[j].addEventListener('click', function(e) {
					deleteBrand(e);
				}), false;
			}

			createBtn.addEventListener('click', createBrand, false);
		}

		function updateBrand(e) {
			var brandId = e.target.parentElement.parentElement.id;
			var brandName = document.querySelector('input[data-id="' + brandId + '"]').value;
			ydbaobao.ajax({
				method:'put',
				url:'/api/brands/' + brandId + '/' + brandName,
				success: function(req) {
					alert('브랜드명이 수정되었습니다')
				}
			});
		}

		function deleteBrand(e) {
			var brandId = e.target.parentElement.parentElement.id;
			if(confirm('정말 브랜드를 삭제하시겠습니까?') === true) {
				ydbaobao.ajax({
					method:'delete',
					url:'/api/brands/' + brandId,
					success: function(req) {
						alert('브랜드가 삭제되었습니다');
						document.getElementById(brandId).remove();
					}
				});
				e.target.removeEventListener('click', deleteBrand, false);
			}
		}

		function createBrand() {
			var brandName = document.querySelector('#new-brand').value;
			ydbaobao.ajax({
				method:'post',
				url:'/api/brands/',
				param: 'brandName=' + brandName,
				success: function(req) {
					alert('카테고리가 추가되었습니다');
					location.reload();
				}
			});
		}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>