<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::상품관리</title>
<link rel="stylesheet" href="/css/admin.css">
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
			<table id="categories" style="width: 800px;">
				<tr>
					<th width="50">상품ID</th>
					<th width="150">상품이미지</th>
					<th colspan=2>상세내역</th>
					<th width="50">선택</th>
				</tr>
				<c:forEach var="product" items="${productList}">
					<tr id="${product.productId}">
						<td>${product.productId}</td>
						<td><img class="productImg" src="/img/products/${product.productImage}" width="150"></td>
						<td>
							<span>브랜드명 : </span>${product.productName}<br/>
							<span>카테고리 : </span> 
								<select>
									<c:forEach var="category" items="${categoryList}">
										<option value="${category.categoryId}" label="${category.categoryName}" />
									</c:forEach>
								</select>
							<br/>
							<span>상품명 : </span><input type="text" value="" data-id="${product.productName}"><br/>
							<span>상품가격 : </span><input type="number" value="${product.productPrice}" data-id="${product.productPrice}"><br/>
							<span>사이즈 : </span><input type="text" class="product-size-input"><span> 수량: </span><input type="number" class="product-quantity-input"><button class="add-size_quantity-btn">추가</button><br/>
							<span>상품소개 : </span><textarea rows="10" cols="60"></textarea>

						</td>
						<td><input type="checkbox" class="checkBtn"></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>