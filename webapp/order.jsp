<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
<style>
html,body {
	background-color: #fff;
}

#order-section {
	margin:25px 0px;
}

.order-head {
	background-color: lightgray;
}

table#order-list {
	width: 100%;
	font-size: 12px;
	border: 1px solid #ccc;
	border-spacing: 0;
}

table#order-list th {
	padding: 5px;
	background-color: #f8f8f8;
}

tbody td {
	padding: 10px 0;
}

.item-name-container {
	text-align: left;
}

.item-name-container a {
	text-decoration: underline;
}

.item-image {
	width: 50px;
	height: 50px;
}

tfoot {
	background-color: #f8f8f8;
}

tfoot tr {
	padding: 10px;
}

tr.border_top td {
  border-top:1pt solid #ccc;
}

</style>
<title>YDbaobao:: 주문목록</title>
</head>
<body>
	<div id="main-container">
		<div id="header">
			<!-- 상단 navigator -->
			<%@ include file="./commons/_topNav.jsp"%>
			<!-- 브랜드/제품 검색바 -->
			<%@ include file="./commons/_search.jsp"%>
		</div>
		<div>
			<!-- 수평 카테고리 메뉴 -->
			<%@ include file="./commons/_horizontalCategory.jsp"%>
		</div>
		<div id="first-section" class="wrap content">
			<div id="progress-info">
				<div class="on"><i class='fa fa-archive'></i>  주문내역</div>
			</div>
			<div id="order-section">
				<table id="order-list">
					<thead>
						<tr>
							<th>주문번호</th>
							<th></th>
							<th>상품설명</th>
							<th>받아야 할 수량</th>
							<th>상품금액</th>
							<th style="width:100px">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${items}">
							<tr class="border_top" data-id="${item.itemId}">
								<td rowspan="">${item.itemId}</td>
								<td class="item-image-container">
									<img class="item-image" src="/image/products/${item.product.productImage}">
								</td>
								<td>
									<span class="item-name"><b>${item.product.productName}</b></span>
									<br />
									<span class="item-size">사이즈 : ${item.size}</span>
									<br />
									<span class="item-price">가격 : ${item.product.productPrice}</span>
								</td>
								<td><span class="item-quantity">${item.quantity}</span></td>
								<td><span class="order-price">${item.product.productPrice * item.quantity}</span></td>
								<td><button>주문취소</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div id="footer">
		<%@ include file="./commons/_footer.jsp"%>
	</div>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
