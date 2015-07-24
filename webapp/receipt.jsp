<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
<title>YDbaobao:: 영수증</title>
<style>
</style>
</head>
<body>
	<div id="main-container">
		<span>영수증</span>
	</div>
	<div style="margin-bottom: 10px; text-align: center;">(고객용)</div>

	<div>
		<table id="receiptInfo">
			<tr>
				<td>상호</td>
				<td>yuandamaoyi</td>
			</tr>
			<tr>
				<td>PHONE</td><td>中国 18601232408<br/>韩国 010-7651-7888</td>
			</tr>
		</table>

		<div class="line"></div>

		<table id=receipt>
			<tr>
				<td>주문시간</td>
				<td>${order.orderDate }</td>
			</tr>
			<tr>
				<td>주문 번호</td>
				<td>${order.orderId }</td>
			</tr>
			<tr>
				<td>고객아이디</td>
				<td>${order.customer.customerId }</td>
			</tr>
			<tr>
				<td>고객이름</td>
				<td>${order.customer.customerName }</td>
			</tr>
			<tr>
				<td>주문상태</td>
				<td id="orderStatus">${order.orderStatus }</td>
			</tr>
			<tr>
				<td>주문 가격</td>
				<td>${order.realPrice }</td>
			</tr>
			<tr>
				<td>배송지</td>
				<td>${order.orderAddress }</td>
			</tr>
			<%-- 아이템들 : ${order.items } --%>
		</table>
	</div>
	<script>
		window.addEventListener('load', function() {
			var orderStatus = document.body.querySelector("#orderStatus");
			var status = orderStatus.textContent;
			if (status == 'I') {
				orderStatus.textContent = "주문 대기";
			}
			if (status == 'S') {
				orderStatus.textContent = "주문 승인";
			}
			if (status == 'R') {
				orderStatus.textContent = "주문 반려";
			}
			if (status == 'C') {
				orderStatus.textContent = "주문 취소";
			}
		}, false);
	</script>
	<script src="/js/ydbaobao.js"></script>
	<style>
* {
	background-color: white !important;
}

#main-container {
	width: 100%;
	text-align: center;
	font-size: 30px;
	font-weight: 500;
	margin-bottom: 10px;
	margin-top: 10px;
}

#main-container>span {
	margin: 0 auto;
	border-bottom: double black;
	width: 140px;
	display: block;
}

td {
	border: 1px solid gray;
	text-align: center;
	margin: 0px;
}

#receipt {
	margin: 0 auto;
	width: 240px;
	border-collapse: collapse;
}

#receiptInfo {
	margin: 0 auto;
	width: 240px;
	border-collapse: collapse;
}

.line {
	border-bottom: 1px solid gray;
	width: 237px;
	margin: 0 auto;
	margin-bottom: 10px;
	margin-top: 10px;
}
</style>
</body>
</html>
