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
	#brands input[type='text'], input[type='text'] {
		width:300px;
		font-size:15px;
	}
</style>
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>브랜드 관리</h1>
			<input id="search-brand" type="text">
			<button class="search-brand-btn">검색</button>
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
			setBrandEvent();
		}, false);

		function setBrandEvent() {
			var updateBtn = document.querySelectorAll('.update-brand-btn');
			var deleteBtn = document.querySelectorAll('.delete-brand-btn');
			var createBtn = document.querySelector('.create-brand-btn');
			var searchBtn = document.querySelector('.search-brand-btn');
	
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
			searchBtn.addEventListener('click', searchBrand, false);
			
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
					alert('브랜드가 추가되었습니다');
					location.reload();
				}
			});
		}
		
		function searchBrand() {
			var searchValue = document.querySelector('#search-brand').value;
			ydbaobao.ajax({
				method:'get',
				url:'/api/brands/find?searchValue=' + searchValue,
				success: function(req) {
					appendBrandList(JSON.parse(req.responseText));
				}
			});
		}
		
		function appendBrandList(json) {
			var table = document.querySelector('#brands');
			while(table.rows.length > 2)
				table.deleteRow(1);
			var brandsLength = json.length;
			for(var i=0; i<brandsLength; i++) {
				row = table.insertRow(1);
				row.insertCell(0).innerHTML = "<td><input type='text' value='"+json[i].brandName+"' data-id='"+json[i].brandId+"'></td>";
				row.insertCell(1).innerHTML = "<td><button class='update-brand-btn'>수정</button><button class='delete-brand-btn'>삭제</button></td>";
			}
		}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>