<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::상품관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<style>
#product-table td {
	font-size:12px;
	text-align:center;
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
			<h1>상품 관리</h1>
			<ul id="product-menu">
			<li class="select-category">
			<form:form class="category-form" method="GET" action="/admin/products/category" enctype="multipart/form-data">
				<label class="control-label">카테고리 선택 :</label>
				<select class="category-select" name="categoryId">
					<c:forEach var="category" items="${categories}">
					<c:choose>
						<c:when test="${category.categoryId eq selectedCategoryId}">
							<option value="${category.categoryId}" label="${category.categoryName}(${category.categoryCount})" selected="selected" />
						</c:when>
						<c:otherwise>
							<option value="${category.categoryId}" label="${category.categoryName}(${category.categoryCount})" />
						</c:otherwise>
					</c:choose>
					</c:forEach>
				</select>
			</form:form>
			</li>
			<li class="delete-all-product"><button id="all-product-delete-btn">전체상품 삭제</button><br/><br/></li>
			</ul>
			<table id="product-table" style="width: 800px;">
				<tr>
					<th width="50px">ID</th>
					<th width="100px">상품이미지</th>
					<th colspan="4">상세내역</th>
					<th width="50px">선택</th>
				</tr>
				<c:forEach var="product" varStatus="status" items="${products}">
				<form:form class="product-update-form" action="" modelAttribute="product">
				<tr>
					<td rowspan="4">${product.productId}
					<form:input path="productId" type="hidden" value="${product.productId}"/></td>
					<td rowspan="4"><img class="productImg" src="/img/products/${product.productImage}" width="150">
					<form:input path="productImage" type="hidden" value="${product.productImage}"/></td>
					<td>카테고리 :</td>
					<td>
					<input id="categoryId" name="categoryId" type="hidden" value="${product.category.categoryId}"/>
					<select class="${status.index}" onchange="setCategoryId(this)">
							<c:forEach var="category" items="${categories}">
										<c:choose>
											<c:when test="${category.categoryId eq product.category.categoryId}">
												<option value="${category.categoryId}" label="${category.categoryName}" selected="selected"/>
											</c:when>
											<c:otherwise>
												<option value="${category.categoryId}" label="${category.categoryName}" />
											</c:otherwise>
										</c:choose>
										
							</c:forEach>
						</select>
					</td>
					<td>브랜드 :</td>
					<td>
					<input id="brandId" name="brandId" type="hidden" value="${product.brand.brandId}"/>
					<select class="${status.index}" onchange="setBrandId(this)">
							<c:forEach var="brand" items="${brands}">
										<c:choose>
											<c:when test="${brand.brandId eq product.brand.brandId}">
												<option value="${brand.brandId}" label="${brand.brandName}" selected="selected"/>
											</c:when>
											<c:otherwise>
												<option value="${brand.brandId}" label="${brand.brandName}" />
											</c:otherwise>
										</c:choose>
							</c:forEach>
						</select>
					</td>
					<td rowspan="4"><button type="button" id="update-product-btn" class="${status.index}" value="${product.productId}">수정</button><br/>
					<button type="button" id="delete-product-btn" class="${status.index}" value="${product.productId}">삭제</button> 
					</td>
				</tr>
				<tr>
					<td>제품명 :</td>
					<td><form:input path="productName" class="productName" value="${product.productName}"/></td>
					<td>사이즈 :</td>
					<td><form:input path="productSize" type="text" id="product-size-input" value="${product.productSize}"/></td>
				</tr>
				<tr>
					<td>가 격 :</td>
					<td><form:input type="number" path="productPrice" value="${product.productPrice}" min="0" /></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>상품소개 :</td>
					<td colspan="3"><textarea name="productDescription" rows="4" cols="70">${product.productDescription}</textarea></td>
				</tr>
				</form:form>
				</c:forEach>
			</table>
		</div>
	</div>

	<script>
		window.addEventListener('load', function(e) {
			document.querySelector('#all-product-delete-btn').addEventListener('click', deleteAllProducts, false);
			setUpdateProductBtn();
			setDeleteProductBtn();
		}, false);

		function deleteAllProducts() {
			if(confirm('전체 상품을 삭제하시겠습니까?') === true) {
				ydbaobao.ajax({
					method : 'delete',
					url : '/admin/products',
					success : function(req) {
						if(req.responseText === 'success') {
							alert('전체 상품이 삭제되었습니다');
							location.reload();
						}
					}
				});
			}
		}
		
		function setDeleteProductBtn() {
			var deleteBtn = document.querySelectorAll('#delete-product-btn');
			for (var i = 0; i < deleteBtn.length; i++) {
				deleteBtn[i].addEventListener('click', function(e) {
					debugger;
					var form = document.querySelectorAll('.product-update-form')[e.target.className];
					deleteProduct(form, e.target.value);
				}, false);
			}
		}
		
		function deleteProduct(form, productId) {
			if(confirm('상품을 삭제하시겠습니까?') === true) {
				ydbaobao.ajax({
					method : 'delete',
					url : '/admin/products/'+productId,
					success : function(req) {
						if(req.responseText === 'success') {
							alert('상품이 삭제되었습니다');
							location.reload();
						}
					}
				});
			}
		}
		
		function setUpdateProductBtn() {
			var updateBtn = document.querySelectorAll('#update-product-btn');
			for (var i = 0; i < updateBtn.length; i++) {
				updateBtn[i].addEventListener('click', function(e) {
					var form = document.querySelectorAll('.product-update-form')[e.target.className];
					updateProduct(form, e.target.value);
				}, false);
			}
		}
		
		function updateProduct(form, productId) {
		var params = "productName="+ form.productName.value + "&categoryId=" + form.categoryId.value +
					"&brandId=" + form.brandId.value + "&productPrice=" + form.productPrice.value + 
					"&productDescription=" + form.productDescription.value + 
					"&productSize=" + form.productSize.value;
		ydbaobao.ajax({
				method : 'put',
				url : '/admin/products/'+productId,
				param : params,
				success : function(req) {
					if (req.responseText === 'success') {
						alert('상품정보가 수정되었습니다.');
					}
				}
			});
		}

		function setBrandId(e) {
			var form = document.querySelectorAll('.product-update-form')[e.className];
			form.brandId.value = e.value;
		}

		function setCategoryId(e) {
			var form = document.querySelectorAll('.product-update-form')[e.className];
			form.categoryId.value = e.value;
		}
		
		
		document.body.querySelector('.category-select').addEventListener('change', function(e) {
			e.target.parentElement.submit();
		}, false);
		
		
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
