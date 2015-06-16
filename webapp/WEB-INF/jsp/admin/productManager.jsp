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
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>		
		<div id="content">
			<h1>상품 관리</h1>
			<button id="all-product-delete-btn">상품 삭제</button>
			<table id="product-table" style="width: 800px;">
				<tr>
					<th width="50px">상품ID</th>
					<th width="100px">상품이미지</th>
					<th colspan="4">상세내역</th>
					<th width="50px">선택</th>
				</tr>
				<c:forEach var="product" varStatus="status" items="${productList}">
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
							<c:forEach var="category" items="${categoryList}">
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
							<c:forEach var="brand" items="${brandList}">
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
		}, false);

		function deleteAllProducts() {
			if(confirm('전체 상품을 삭제하시겠습니까?') === true) {
				ydbaobao.ajax({
					method : 'delete',
					url : '/product',
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
					debugger;
					var form = document.querySelectorAll('.product-update-form')[e.target.className];
					updateProduct(form, e.target.value);
				}, false);
			}
		}
		
		function updateProduct(form, productId) {
		var param = "/" + form.productId.value + "/" + form.productName.value + "/" + 
					form.categoryId.value + "/" + form.brandId.value + "/" + form.productPrice.value + "/" + 
					form.productSize.value + "/" + form.productDescription.value; 
		debugger;
		ydbaobao.ajax({
				method : 'put',
				url : '/product/'+param,
				success : function(req) {
					if (req.responseText === 'success') {
						alert('상품정보가 수정되었습니다.');
						location.reload();
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
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
