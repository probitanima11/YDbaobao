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
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>상품 관리</h1>
			<div id="confirm">
				<button>저장</button>
				<button>취소</button>
			</div>

			<table  id="categories" style="width: 800px;">
				<tr>
					<th width="50px">상품ID</th>
					<th width="150px">상품이미지</th>
					<th colspan="5">상세내역</th>
					<th width="50px">선택</th>
				</tr>
				<c:forEach var="product" items="${productList}">
				
				<%-- <form:form class="productUpdate" method="post" action="/products/update" modelAttribute="product" enctype="multipart/form-data"> --%>
				<tr>
					<td rowspan="4">${product.productId}</td>
					<td rowspan="4"><img class="productImg" src="/img/products/${product.productImage}" width="150"></td>
					<td>카테고리 : </td>
					<td><select>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.categoryId}" label="${category.categoryName}" />
							</c:forEach>
						</select></td>
					<td>제품명 : </td>
					<td colspan="2"><input type="text" value="" data-id="${product.productName}" ></td>
					<td rowspan="4"><input type="checkbox" class="checkBtn"></td>
				</tr>
				<tr>
					<td>브랜드 : </td>
					<td>${product.productName}</td>
					<td>재고량 : </td>
					<td>사이즈</td>
					<td>수량</td>
				</tr>
				<tr>
					<td>가격 : </td>
					<td><input type="number" value="${product.productPrice}" data-id="${product.productPrice}"></td>
					<td>
					<button id="add-size_quantity-btn" class="${product.productId}">추가</button>
					<button id="delete-size_quantity-btn" class="${product.productId}">삭제</button>
					</td>
					<td class='stock-size'><input type="text" name ='size' id="product-size-input" class="${product.productId}"></td>
					<td class='stock-quantity'><input type="number" name = 'quantity' id="product-quantity-input" class="${product.productId}"></td>
				</tr>
				<tr>
					<td>상품소개 : </td>
					<td colspan="4"><textarea rows="10" cols="75"></textarea></td>
				</tr>
				<%-- </form:form> --%>
				</c:forEach>
			</table>
		</div>
	</div>

	<script>
		document.body.querySelector('#add-size_quantity-btn').addEventListener('click', function(e){
			addSizeAndQuentity();
		});
		
		document.body.querySelector('#delete-size_quantity-btn').addEventListener('click', function(e){
			deleteSizeAndQuentity(e);
		});
		
		function deleteSizeAndQuentity(e) {
			var sizeTable = document.body.querySelectorAll('.stock-size');
			var quantityTable = document.body.querySelectorAll('.stock-quantity');
			debugger;
		}
		
		
		function addSizeAndQuentity(e) {
			var sizeTable = document.body.querySelector('.stock-size');
			var quantityTable = document.body.querySelector('.stock-quantity');
			appendInputBox('product-size-input', sizeTable);
			appendInputBox('product-quantity-input', quantityTable);
		}

		function appendInputBox(idName, targetTable) {
			var el=undefined;
			var br=document.createElement('br')
			el=document.createElement('input');
			el.setAttribute('id', idName);
			targetTable.appendChild(br);
			targetTable.appendChild(el);
		}
		
	</script>

</body>
</html>