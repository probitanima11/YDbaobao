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
		영수증
	</div>
	
	<div>
		고객아이디 : ${order.customer.customerId }
		<br>
		고객이름 : ${order.customer.customerName }
		<br>
		고객등급 : ${order.customer.customerGrade }
		<br>
		주문상태 : ${order.orderStatus }
		<br>
		주문아이디 : ${order.orderId }
		<br>
		주문가격 : ${order.realPrice }
		<br>
		주문자 주소 : ${order.orderAddress }
		<br>
		주문시간 : ${order.orderDate }
		<br>
		<%-- 아이템들 : ${order.items } --%>
		<br>
	</div>
	<script>
	
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
