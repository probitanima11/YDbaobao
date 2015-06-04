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
					<th width="50">상품ID</th>
					<th width="150">상품이미지</th>
					<th>상세내역</th>
					<th width="50">선택</th>
				</tr>
				<c:forEach var="product" items="${productList}">
					<tr id="${product.productId}">
						<td>${product.productId}</td>
						<td><img class="productImg" src="/img/products/${product.productImage}" width="150"></td>
						<td>
						<ul>
							<li><span>브랜드명 : </span>${product.productName}</li>
							<li><span>카테고리 : </span> 
								<select>
									<c:forEach var="category" items="${categoryList}">
										<option value="${category.categoryId}" label="${category.categoryName}" />
									</c:forEach>
								</select>
							</li>
							<li><span>상품명 : </span><input type="text" value="" data-id="${product.productName}"></li>
							<li><span>상품가격 : </span><input type="number" value="${product.productPrice}" data-id="${product.productPrice}"></li>
							<li><span>상품소개 : </span><textarea rows="5" cols="60"></textarea></li>
							<li><span>사이즈 : </span><input type="text" class="product-size-input"><span> 수량: </span><input type="number" class="product-quantity-input"><button class="add-size_quantity-btn">추가</button></li>
						</ul>
						</td>
						<td><input type="checkbox" class="checkBtn"></td>
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

		
		document.querySelector('.add-size_quantity-btn').addEventListener('click', function(e){addSizeQuantity(e);}, false);
		function addSizeQuantity(e) {
			
		}
	</script>
</body>
</html>
