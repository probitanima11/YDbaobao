<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::주문관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<style>
	table {
		text-align:center;
		font-size:12px;
	}
	
	th {
		min-width:80px;
	}

	.waiting {
		color: #DBC000;
	}
	
	.success {
		color: #62C15B;
	}
	
	.claim, .cancel {
		color: #F15F5F;
	}
	
	button.info, button.success, button.claim {
		border: 0;
		padding: 5px;
		color: #fff;
	}
	
	button.info {
		background-color: #4374D9;
		border-bottom:2px solid #002C91;
	}
	
	button.success {
		background-color: #62C15B;
		border-bottom:2px solid #086701;
	}
	
	button.claim {
		background-color: #F15F5F;
		border-bottom:2px solid #840000;
	}
</style>
</head>
<body>
	<div id="header" style="width: 100%;">
		<%@ include file="./_adminTopNav.jsp"%>
	</div>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>주문 관리</h1>
			<table>
				<tr>
					<th>상품번호</th>
					<th>상품명</th>
					<th>주문자</th>
					<th>주문수량</th>
					<th>보낼수량</th>
					<th>상태</th>
				</tr>
				<c:forEach var="order" items="${orders}">
					<tr class="order-head" data-id="${order.itemId}">
						<td>${order.product.productId}</td>
						<td>${order.product.productName}</td>
						<td>${order.customer.customerId}</td>
						<td>${order.quantity}</td>
						<td><input type="text" /></td>
						<td><input type="button" value="보내기"></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script>
		window.addEventListener('load', function(){
			var i;
			var infoBtns = document.querySelectorAll('button.info');
			for (i = 0; i < infoBtns.length; i++) {
				infoBtns[i].addEventListener('click', showOrder, false);
			}
			var checkBtns = document.querySelectorAll('button.success');
			for (i = 0; i < checkBtns.length; i++) {
				checkBtns[i].addEventListener('click', checkOrder, false);
			}
			var claimBtns = document.querySelectorAll('button.claim');
			for (i = 0; i < claimBtns.length; i++) {
				claimBtns[i].addEventListener('click', claimOrder, false);
			}
		}, false);
		
		function showOrder(e) {
			var orderId = e.target.parentNode.parentNode.getAttribute('data-id');
			ydbaobao.ajax({
				method: "get",
				url: "/admin/orders/"+orderId,
				success: function(req){
					console.log(req.responseText);
				}
			});
		}
		
		function checkOrder(e) {
			var orderId = e.target.parentNode.parentNode.getAttribute('data-id');
			ydbaobao.ajax({
				method: "put",
				param: "orderStatus=S",
				url: "/admin/orders/"+orderId,
				success: function(req){
					alert(req.responseText);
					window.location.href="/admin/orders";
				}
			});
		}
		
		function claimOrder(e) {
			var orderId = e.target.parentNode.parentNode.getAttribute('data-id');
			ydbaobao.ajax({
				method: "put",
				param: "orderStatus=R",
				url: "/admin/orders/"+orderId,
				success: function(req){
					alert(req.responseText);
					window.location.href="/admin/orders";
				}
			});
		}
		
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
