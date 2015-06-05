<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::상품등록</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>상품등록</h1>
			<div id="imageFiles">
				<form:form class="imageUploadForm" method="post" action="/products/imageUpload" modelAttribute="product" enctype="multipart/form-data">
					<label class="control-label">브랜드 선택 :</label>
					<form:select path="brand.brandId">
						<c:forEach var="brand" items="${brandList}">
							<form:option value="${brand.brandId}" label="${brand.brandName}" />
						</c:forEach>
					</form:select>
					<br />
					<label class="control-label">이미지 선택 :</label>
					<input class="imageFile" type="file" name="imageFile" accept="image/x-png, image/gif, image/jpeg" multiple />
					<input type="submit" class='submitBtn' value="저장" disabled='true' />
				</form:form>
				<div class="imageList"></div>
			</div>
			
			<table id="categories" style="width: 800px;">
				<tr>
					<th>브랜드</th>
					<th>미등록 상품 갯수</th>
				</tr>
				<c:forEach var="product" items="${unregisteredProductsCountByBrand}">
					<tr>
						<td>${product.key}</td>
						<td>${product.value}</td>
					</tr>
				</c:forEach>
			</table>
			
			
		</div>
	</div>

	<script>

		var imageFile = document.body.querySelector('.imageFile');
		imageFile.addEventListener('change', function(e) {
			appendImageList();
			disableSubmitBtn();
		}, false);

		function appendImageList() {
			refreshImageList();
			var el = undefined;
			var selectedFileList = document.body.querySelector('.imageFile').files;
			for (var i = 0; i < selectedFileList.length; i++) {
				el = document.createElement('li');
				el.setAttribute('class', 'file-list');
				el.setAttribute('value', selectedFileList[i].name);
				el.innerHTML = selectedFileList[i].name;
				document.body.querySelector('.imageList').appendChild(el);
			}
		}

		function refreshImageList() {
			document.body.querySelector('.imageList').innerHTML = '';
		}

		function disableSubmitBtn() {
			if (document.body.querySelector('.imageFile').files.length > 0) {
				document.body.querySelector('.submitBtn').disabled = false;
			}
			if (document.body.querySelector('.imageFile').files.length === 0) {
				document.body.querySelector('.submitBtn').disabled = true;
			}
		}
	</script>
</body>
</html>
