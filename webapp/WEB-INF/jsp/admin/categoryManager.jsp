<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::카테고리관리</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>카테고리 관리</h1>
			<table id="categories" style="width: 800px;">
				<tr>
					<th>카테고리명</th>
					<th></th>
				</tr>
				<c:forEach var="category" items="${categories}">
					<tr id="${category.categoryId}">
						<td><input type="text" value="${category.categoryName}" data-id="${category.categoryId}" /></td>
						<td>
							<button class="update-category-btn">수정</button>
							<button class="delete-category-btn">삭제</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td><input type="text"></td>	
					<td>
						<button class="create-category-btn">추가</button>
					</td>
				</tr>
			</table>
		</div>
		<div>
			<button>취소</button>
		</div>
	</div>
	<script>
		window.addEventListener('load', function() {
			setCategoryEvent();
		}, false);

		function setCategoryEvent() {
			var updateBtn = document.querySelectorAll('.update-category-btn');
			var deleteBtn = document.querySelectorAll('.delete-category-btn');
			var createBtn = document.querySelector('.create-category-btn');
	
			// for문 조건문용 변수
			var length;

			for(var i = 0, length = updateBtn.length; i < length; i++) {
				updateBtn[i].addEventListener('click', function(e) {
					updateCategory(e);
				}, false);
			}

			for(var j = 0, length = deleteBtn.length; j < length; j++) {
				deleteBtn[j].addEventListener('click', function(e) {
					deleteCategory(e);
				}), false;
			}

			createBtn.addEventListener('click', function(e) {
				createCategory(e);
			}, false);
		}

		function updateCategory(e) {
			var categoryId = e.target.parentElement.parentElement.id;
			var categoryName = document.querySelector('input[data-id="' + categoryId + '"]').value;
			console.log(categoryName);
			ydbaobao.ajax({
				method:"put",
				url:"/admin/manage/category/" + categoryId + "/" + categoryName,
				success: function(req) {
					console.log(req.responseText);
				}
			});
		}

		function deleteCategory(e) {
			console.log(e.target.parentElement.parentElement.id);
		}

		function createCategory(e) {
			console.log(e.target.parentElement.parentElement.id);
		}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>