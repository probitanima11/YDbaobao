<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" type="text/css" href="/css/font-awesome.min.css">
<title>YDbaobao:: 장바구니</title>
<style>
	table#cart-list {
		width:100%;
		font-size:12px;
		border:1px solid #ccc;
		border-spacing:0;
	}
	table#cart-list th{
		padding:5px;
		background-color:#f8f8f8;
	}
	tbody td{
		padding:10px 0;
	}
	.item-name-container {
		text-align:left;
	}
	.item-image {
		width:50px;
		height:50px;
	}
	.item-price {
		font-weight:800;
	}
	tfoot {
		background-color:#f8f8f8;
	}
	tfoot tr{
		padding:10px;
	}

	.sold-out {
		color: red;
		font-weight: bold;
	}
</style>
</head>
<body>
	<div id="header" style="width: 100%;">
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
		<div id="first-section" class="wrap content" style="padding:25px 0;">
			<div id="progress-info">
				<div class="on"><i class='fa fa-shopping-cart'></i>  장바구니</div>
			</div>
			<div id="cart-section">
				<table id="cart-list">
					<thead>
						<tr>
							<th style="width:60px;"><button id="select-all-btn">전체선택</button></th>
							<th colspan="2">상품설명</th>
							<th>사이즈</th>
							<th>수량</th>
							<th>판매가</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="item" items="${items}">
						<c:choose>
							<c:when test="${item.product.isSoldout eq 1}">
								<tr data-id="${item.itemId}">
									<td><input type="checkbox" onclick="" disabled></td>
									<td class="item-image-container"><img class="item-image" src="/img/products/${item.product.productImage}"></td>
									<td class="item-name-container"><span class="item-name">${item.product.productName}</span><span class="sold-out"> [품절]</span></td>
									<td><span class="item-size">${item.size}</span></td>
									<td><span class="item-quantity">${item.quantity}</span></td>
									<td><span>${item.product.productPrice * item.quantity}</span></td>
								</tr>
							</c:when>
							<c:otherwise>
								<tr data-id="${item.itemId}">
									<td><input class="item-check" type="checkbox" onclick="calcSelectedPrice()"></td>
									<td class="item-image-container"><img class="item-image" src="/img/products/${item.product.productImage}"></td>
									<td class="item-name-container"><span class="item-name">${item.product.productName}</span></td>
									<td><span class="item-size">${item.size}</span></td>
									<td><span class="item-quantity">${item.quantity}</span></td>
									<td><span class="item-price">${item.product.productPrice * item.quantity}</span></td>
								</tr>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div style="float:left">
								</div>
								<div id="total-price" style="float:right; padding:15px; font-size:15px;">전체상품금액 :
									<span style="font-weight:800;"></span>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="6">
								<div style="float:left">
								</div>
								<div id="selected-price" style="float:right; padding:15px; font-size:15px;">선택상품금액 :
									<span style="font-weight:800;">0</span>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
				<div id="order-section">
					<button id="selection-delete-btn" class="btn" style="float:left; background-color:#ccc">선택상품삭제</button>
					<button id="select-order-btn" class="btn">선택주문하기</button>
					<button id="order-btn" class="btn">전체주문하기</button>
				</div>
			</div>
		</div>
	</div>

	<div id="footer">footer...</div>

	<script>
	window.addEventListener('load', function() {
		document.querySelector('#selection-delete-btn').addEventListener('click', function() {
			var checkedItems = document.querySelectorAll('.item-check');
			var length = checkedItems.length;
			for(var i = 0; i < length; i++) {
				if(checkedItems[i].checked) {
					var tr = checkedItems[i].parentElement.parentElement;
					ydbaobao.ajax({
						method : 'delete',
						url : '/carts/${sessionCustomer.sessionId}/items/' + tr.dataset.id,
						success : function(req) {
							document.querySelector('#total-price span').textContent -= document.querySelector('tr[data-id="'+ req.responseText + '"] .item-price').innerText;
							document.querySelector('tr[data-id="'+ req.responseText + '"]').remove();
							addItemsPrice();
							calcSelectedPrice();
						}
					});
				}
			}
		});

		document.querySelector('#select-all-btn').addEventListener('click', function(e) {
			var checkedItems = document.querySelectorAll('.item-check');
			var length = checkedItems.length;

			//전체선택 해제
			if(e.target.classList.contains('checked')) {
				e.target.classList.remove('checked');
				for(var i = 0; i < length; i++) {
					checkedItems[i].checked = false;
				}
				calcSelectedPrice();
				return;
			}

			e.target.classList.add('checked');
			for(var j = 0; j < length; j++) {
				checkedItems[j].checked = true;
			}
			calcSelectedPrice();
		});

		document.querySelector('#select-order-btn').addEventListener('click', function() {
			var checkList = document.querySelectorAll('.item-check');
			var checkLength = checkList.length;
			var paramList = [];
			var param = 'itemList=';
			for(var i = 0; i < checkLength; i++) {
				if(checkList[i].checked) {
					paramList.push(checkList[i].parentNode.parentNode.getAttribute('data-id'));
				}
			}
			if(paramList.length === 0) {
				alert('상품을 선택해주세요');
				return;
			}
			param += paramList;
			order(param);
		}, false);

		document.querySelector('#order-btn').addEventListener('click', function() {
			var checkList = document.querySelectorAll('.item-check');
			var checkLength = checkList.length;
			var paramList = new Array();
			var param = 'itemList=';
			for(var i = 0; i < checkLength; i++) {
				paramList.push(checkList[i].parentNode.parentNode.getAttribute('data-id'));
			}
			param += paramList;
			order(param);
		}, false);

		addItemsPrice();

		priceWithComma();

		totalPriceWithComma();

	}, false);

	function order(param) {
		ydbaobao.ajax({
			method : 'post',
			url : '/orders',
			param : param,
			success : function(req) {
				alert('주문요청이 완료되었습니다.');
				window.location.href = '/orders';
			}
		});
	}

	function addItemsPrice() {
		var el = document.querySelectorAll('.item-price');
		var length = el.length;
		var totalPrice = 0;
		for(var i = 0; i < length; i++) {
			totalPrice += parseInt(el[i].textContent.replace(",", ""));
		}

		document.querySelector('#total-price span').textContent = totalPrice.toLocaleString();
	}

	function priceWithComma() {
		var el = document.querySelectorAll('.item-price');
		var length = el.length;

		for(var i = 0; i < length; i++) {
			el[i].textContent = parseInt(el[i].textContent).toLocaleString();
		}
	}

	function totalPriceWithComma() {
		 	var el = document.querySelector('#total-price span');
		 	el.textContent = parseInt(el.textContent.replace(",", "")).toLocaleString();
	}

	function calcSelectedPrice() {
		var checkList = document.querySelectorAll('.item-check');
		var checkLength = checkList.length;
		var totalPrice = 0;
		var paramList = [];
		for(var i = 0; i < checkLength; i++) {
			if(checkList[i].checked) {
				totalPrice += checkList[i].parentNode.parentNode.querySelector('.item-price').innerText.replace(/,/g,"")*1;
			}
		}
		document.querySelector('#selected-price span').textContent = parseInt(totalPrice).toLocaleString();
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
