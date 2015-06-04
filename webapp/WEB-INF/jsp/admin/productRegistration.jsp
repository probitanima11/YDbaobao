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
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>상품 등록</h1>
		</div>
		<div id="imageFiles">
			<form:form class="imageUploadForm" method="post" action="/products/imageUpload" modelAttribute="product" enctype="multipart/form-data">
				<label class="control-label" >브랜드 선택 :</label>
				<form:select path="brand.brandId">
					<c:forEach items="${brandList}" var="brand">
						<form:option value="${brand.brandId}" label="${brand.brandName}" />
					</c:forEach>
				</form:select>
				<br/>
				<label class="control-label" >이미지 선택 :</label>
				<input class="imageFile" type="file" name="imageFile" accept="image/x-png, image/gif, image/jpeg" multiple />
				<input type="submit" value="저장" />
			</form:form>
			<div class="imageList"></div>
		</div>
	</div>

	<script>
		var imageFile = document.body.querySelector('.imageFile');
		imageFile.addEventListener('change', function(e) {
			appendImageList();
		}, false);

		function appendImageList() {
			refreshImageList();
			var el = undefined;
			var selectedFileList = document.body.querySelector(".imageFile").files;
			for (var i = 0; i < selectedFileList.length; i++) {
				el = document.createElement("li");
				el.setAttribute("class", "file-list");
				el.setAttribute("value", selectedFileList[i].name);
				el.innerHTML = selectedFileList[i].name;
				document.body.querySelector('.imageList').appendChild(el);
			}
		}

		function refreshImageList() {
			document.body.querySelector('.imageList').innerHTML = "";
		}
	</script>
</body>
</html>