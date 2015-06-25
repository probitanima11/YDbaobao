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
body {
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
	<div id="main-container">
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
							<th>수량</th>
							<th>상품금액</th>
							<th>결재금액</th>
							<th>주문상태</th>
							<th style="width:100px">관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
							<tr class="border_top" data-id="${order.orderId}">
								<td rowspan="${fn:length(order.items)}">${order.orderDate}-${order.orderId}</td>
								<td class="item-image-container">
									<img class="item-image" src="/img/products/${order.items[0].product.productImage}">
								</td>
								<td>
									<span class="item-name"><b>${order.items[0].product.productName}</b></span>
									<br />
									<span class="item-size">사이즈 : ${order.items[0].size}</span>
									<br />
									<span class="item-price">가격 : ${order.items[0].product.productPrice}</span>
								</td>
								<td><span class="item-quantity">${order.items[0].quantity}</span></td>
								<td><span class="order-price">${order.items[0].product.productPrice * order.items[0].quantity}</span></td>
								<td rowspan="${fn:length(order.items)}"><b>${order.realPrice}</b></td>
								<c:choose>
									<c:when test="${order.orderStatus eq 'I'}">
										<td rowspan="${fn:length(order.items)}" colspan="1">주문대기</td>
									</c:when>
									<c:when test="${order.orderStatus eq 'S'}">
										<td rowspan="${fn:length(order.items)}" colspan="1">주문승인</td>
									</c:when>
									<c:when test="${order.orderStatus eq 'R'}">
										<td rowspan="${fn:length(order.items)}" colspan="1">주문반려</td>
									</c:when>
									<c:when test="${order.orderStatus eq 'C'}">
										<td rowspan="${fn:length(order.items)}" colspan="1">주문취소</td>
									</c:when>
									<c:otherwise>
										<td rowspan="${fn:length(order.items)}" colspan="1"></td>
									</c:otherwise>
								</c:choose>
								<td rowspan="${fn:length(order.items)}" colspan="1">
									<c:if test="${order.orderStatus eq 'I'}">
										<button data-id="${order.orderId}" class="order-cancel">취소</button>
									</c:if>
									<button class="" onclick="getReceipt(${order.orderId})">주문서</button>
								</td>
								<c:forEach var="i" begin="1" end="${fn:length(order.items)-1}">
									<tr>
										<td class="item-image-container"><img class="item-image"
											src="/img/products/${order.items[i].product.productImage}">
										</td>
										<td>
											<span class="item-name"><b>${order.items[i].product.productName}</b></span>
											<br />
											<span class="item-size">사이즈 : ${order.items[i].size}</span>
											<br />
											<span class="item-price">가격 : ${order.items[i].product.productPrice}</span>
										</td>
										<td><span class="item-quantity">${order.items[i].quantity}</span></td>
										<td><span class="order-price">${order.items[i].product.productPrice * order.items[i].quantity}</span></td>
									</tr>
								</c:forEach>
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

	<script>
	window.addEventListener('load', function() {
		var readyList = document.querySelectorAll('.order-cancel');
		for(var i=0; i<readyList.length; i++) {
			readyList[i].addEventListener('click', function(e) {
				var orderId = e.target.getAttribute('data-id');
				orderCancel(orderId);
			});
		}
	}, false);
	
	function orderCancel(orderId) {
		if (confirm("정말 주문취소 하시겠습니까??") == false){
		    return;
		}
		ydbaobao.ajax({
			method: "put",
			param: "orderStatus=C",
			url: "/orders/"+orderId,
			success: function(req){
				document.querySelectorAll(".border_top[data-id='" + orderId + "'] > td")[6].innerText = "주문취소";
				document.querySelectorAll(".border_top[data-id='" + orderId + "'] > td")[7].querySelector("button").remove();
			}
		});
	}
	
	function getReceipt(orderId) {
		var url = '/orders/receipt/' + orderId;
		window.open(url, "_blank", "toolbar=yes, scrollbars=yes, resizable=yes, top=100, left=300, width=400, height=400");
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
