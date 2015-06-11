<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<title>YDbaobao:: 주문목록</title>
</head>
<body>
	<div id="header" style="width: 100%;">
		<!-- 상단 navigator -->
		<%@ include file="./commons/_topNav.jsp"%>
		<!-- 브랜드/제품 검색바 -->
		<%@ include file="./commons/_search.jsp"%>
	</div>

	<div id="main-container">
		<div id="first-section" class="wrap content" style="height: 500px;">
			<!-- 카테고리 메뉴 -->
			<%@ include file="./commons/_category.jsp"%>
			<div id="order-section">
				<table id="order-list">
					<thead>
						<tr>
							<th>구매일자</th>
							<th>구매상태</th>
							<th>구매가격</th>
							<th>구매내역</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</div>
	</div>

	<div id="footer">footer...</div>

	<template id="order-item-template">
		<td><span class="order-date"></span></td>
		<td><span class="order-status"></span></td>
		<td><span class="order-price"></span></td>
		<td><span class="order-item"></span></td>
	</template>

	<script>
	window.addEventListener('load', function() {
		var orderList = ${order_list};
		loadOrderList(orderList);
	}, false);

	function loadOrderList(orderList) {
		var orderEl = document.querySelector('#order-list');
		var length = orderList.length;
		var topOrder = undefined;
		for (var i = 0; i < length; i++) {
			var tr = ydbaobao.createElement({
		        name: 'tr',
		        attrs: {
		            'data-id': orderList[i].itemId
		        },
		    });
			orderEl.tBodies[0].appendChild(tr);
			var orderTemplate = document.querySelector("#order-item-template").content;
			var order = document.importNode(orderTemplate, true);
			if(topOrder === undefined) {
				topOrder = orderList[i].orderId;
				order.querySelector('.order-date').textContent = orderList[i].orderUpdateDate;
				order.querySelector('.order-status').textContent = orderList[i].orderStatus;
				order.querySelector('.order-price').textContent = orderList[i].realPrice;
			}
			else {
				if(topOrder !== orderList[i].orderId) {
					topOrder = orderList[i].orderId;
					order.querySelector('.order-date').textContent = orderList[i].orderUpdateDate;
					order.querySelector('.order-status').textContent = orderList[i].orderStatus;
					order.querySelector('.order-price').textContent = orderList[i].realPrice;
				}
			}
			order.querySelector('.order-item').textContent = orderList[i].productName;
			tr.appendChild(order);	
		}
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>




