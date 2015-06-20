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
		width: 75px;
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
					<th>주문번호</th>
					<th>주문일자</th>
					<th>주문자</th>
					<th>금액</th>
					<th>상태</th>
					<th></th>
				</tr>
				<c:forEach var="order" items="${orders}">
					<tr class="order-head" data-id="${order.orderId}">
						<td>${order.orderId}</td>
						<td>${order.orderDate}</td>
						<td>${order.customer.customerId}</td>
						<td>${order.realPrice}</td>
						<c:choose>
							<c:when test="${order.orderStatus eq 'I'}">
								<td colspan="1" class="waiting">주문대기</td>
								<td>
									<button class='info' ><i class='fa fa-navicon'></i>  주문서보기</button>
									<button class='success' ><i class='fa fa-check'></i>  주문승인</button>
									<button class='claim' ><i class='fa fa-close'></i>  주문반려</button>
								</td>
							</c:when>
							<c:when test="${order.orderStatus eq 'S'}">
								<td colspan="1" class="success">주문승인</td>
								<td>
									<button class='info'><i class='fa fa-navicon'></i>  주문서보기</button>
								</td>
							</c:when>
							<c:when test="${order.orderStatus eq 'R'}">
								<td colspan="1" class='claim'>주문반려</td>
								<td>
									<button class='info'><i class='fa fa-navicon'></i>  주문서보기</button>
								</td>
							</c:when>
							<c:when test="${order.orderStatus eq 'C'}">
								<td colspan="1" class='cancel'>주문취소</td>
								<td>
									<button class='info'><i class='fa fa-navicon'></i>  주문서보기</button>
								</td>
							</c:when>
							<c:otherwise>
								<td colspan="1"></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	<script>
		window.addEventListener('load', function(){
			var i;
			var infoBtns = document.querySelectorAll('.info');
			for (i = 0; i < infoBtns.length; i++) {
				infoBtns[i].addEventListener('click', showOrder, false);
			}
			var checkBtns = document.querySelectorAll('.success');
			for (i = 0; i < checkBtns.length; i++) {
				checkBtns[i].addEventListener('click', checkOrder, false);
			}
			var claimBtns = document.querySelectorAll('.claim');
			for (i = 0; i < claimBtns.length; i++) {
				claimBtns[i].addEventListener('click', claimOrder, false);
			}
		}, false);
		
		function showOrder(e) {
			var orderId = e.target.parentNode.parentNode.getAttribute('data-id');
			ydbaobao.ajax({
				method: "get",
				url: "/admin/orders/read/"+orderId,
				success: function(req){
					console.log(req.responseText);
				}
			});
		}
		
		function checkOrder(e) {
			var orderId = e.target.parentNode.parentNode.getAttribute('data-id');
			ydbaobao.ajax({
				method: "post",
				url: "/admin/orders/check/"+orderId,
				success: function(req){
					alert(req.responseText);
				}
			});
		}
		
		function claimOrder(e) {
			var orderId = e.target.parentNode.parentNode.getAttribute('data-id');
			ydbaobao.ajax({
				method: "post",
				url: "/admin/orders/claim/"+orderId,
				success: function(req){
					alert(req.responseText);
				}
			});
		}
		
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
