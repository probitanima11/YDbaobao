<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<style>
	.order-item-td {
		border-bottom: 1px gray solid;
		width: 200px;
	}
	#order-list {
		border: 1px gray solid;
		margin: 0 auto;
	}
	#order-section {
		overflow:scroll;
		height: 450px;
	}
	.order-date-td, .order-status-td, .order-price-td {
		border-right: 1px lightgray solid;
		padding: 5px;
		width: 100px;
	}
	.order-name {
		display: block;
		text-align: center;
		border-bottom: 1px lightgray solid;
	}
	.order-detail {
		display: block;
		text-align: center;
	}
	.top-order-date-td, .top-order-status-td, .top-order-price-td {
		border-top: 1px gray solid;
		border-right: 1px lightgray solid;
		padding: 5px;
		width: 100px;
	}
	.top-order-item-td{
		border-top: 1px gray solid;
		border-bottom: 1px gray solid;
		padding: 5px;
		width: 100px;
	}
</style>
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
			<div id="order-section">
				<table id="order-list" cellspacing="0" >
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
		<td class="order-date-td"><span class="order-date"></span></td>
		<td class="order-status-td"><span class="order-status"></span></td>
		<td class="order-price-td"><span class="order-price"></span></td>
		<td class="order-item-td">
			<span class="order-name"></span>
			<span class="order-detail"></span>
		</td>
	</template>

	<script>
	window.addEventListener('load', function() {
		var orderList = ${orders};
		loadOrderList(orderList);
	}, false);

	function loadOrderList(orderList) {
		var orderEl = document.querySelector('#order-list');
		var length = orderList.length;
		var topOrder = undefined;
		var count = 0;
		for (var i = 0; i < length; i++) {
			var tr = ydbaobao.createElement({
		        name: 'tr',
		        attrs: {
		            'data-id': orderList[i].itemId
		        },
		    });
			var orderTemplate = document.querySelector("#order-item-template").content;
			var order = document.importNode(orderTemplate, true);
			if(topOrder === undefined || topOrder !== orderList[i].orderId) {
				topOrder = orderList[i].orderId;
				order.querySelector('.order-date').textContent = orderList[i].orderUpdateDate;
				order.querySelector('.order-date').parentNode.setAttribute("class", "top-order-date-td");
				order.querySelector('.order-status').textContent = orderList[i].orderStatus;
				order.querySelector('.order-status').parentNode.setAttribute("class", "top-order-status-td");
				order.querySelector('.order-price').textContent = orderList[i].realPrice;
				order.querySelector('.order-price').parentNode.setAttribute("class", "top-order-price-td");
				order.querySelector('.order-name').parentNode.setAttribute("class", "top-order-item-td");
			}
			order.querySelector('.order-name').textContent = orderList[i].productName;
			order.querySelector('.order-detail').textContent = "수량: " + orderList[i].quantity + "/ 사이즈: "+ orderList[i].size;
			orderEl.tBodies[0].appendChild(tr);
			tr.appendChild(order);	
		}
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>