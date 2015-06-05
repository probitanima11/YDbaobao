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
			<table id="categories" style="width: 800px;">
				<tr>
					<th width="50px">상품ID</th>
					<th width="150px">상품이미지</th>
					<th colspan="6">상세내역</th>
					<th width="50px">선택</th>
				</tr>
				<c:forEach var="product" items="${productList}">
				<tr>
					<td rowspan="3">${product.productId}</td>
					<td rowspan="3"><img class="productImg" src="/img/${product.productImage}" width="150"></td>
					<td width="60px">카테고리 : </td>
					<td>
						<select>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.categoryId}" label="${category.categoryName}" />
							</c:forEach>
						</select>
								</td>
					<td width="80px">브랜드 : </td>
					<td>${product.productName}</td>
					<td>상품명 : </td>
					<td><input type="text" value="" data-id="${product.productName}"></td>
					<td rowspan="3"><input type="checkbox" class="checkBtn"></td>
				</tr>
				<tr>
					<td>가격 : </td>
					<td><input type="number" value="${product.productPrice}" data-id="${product.productPrice}"  style="width: 75px"></td>
					<td>사이즈/수량<br><button class="add-size_quantity-btn" >추가</button>
					</td>
					<td colspan="3"><span>사이즈 : </span><input type="text" class="product-size-input" style="width: 60px"><span> /  수량: </span><input type="number" class="product-quantity-input" style="width: 60px"></td>
				</tr>
				<tr>
					<td>상품소개</td>
					<td colspan="5"><textarea rows="10" cols="75"></textarea></td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>