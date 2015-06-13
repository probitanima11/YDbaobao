<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<title>YDbaobao:: 장바구니</title>
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
			<div id="cart-section">
				<table id="cart-list">
					<thead>
						<tr>
							<th>선택</th>
							<th>상품명</th>
							<th>사이즈</th>
							<th>수량</th>
							<th>판매가</th>
						</tr>
					</thead>
					<tbody></tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<div style="float:left">
									<button id="selection-delete-btn">선택삭제</button>
									<button id="select-all-btn">전체선택</button>
								</div>
								<div id="total-price" style="float:right">상품합계금액 : 
									<span></span>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<div id="order-section">
				<button id="select-order-btn" class="btn">선택주문하기</button>
				<button id="order-btn" class="btn">전체주문하기</button>
			</div>
		</div>
	</div>

	<div id="footer">footer...</div>

	<template id="cart-item-template">
		<td><input class="item-check" type="checkbox"></td>
		<td><span class="item-name"></span></td>
		<td><span class="item-size"></span></td>
		<td><span class="item-quantity"></span></td>
		<td><span class="item-price"></span></td>
	</template>

	<script>
	window.addEventListener('load', function() {
		var itemList = ${list};
		loadCartList(itemList);
		document.querySelector('#selection-delete-btn').addEventListener('click', function() {
			var checkedItems = document.querySelectorAll('.item-check');
			var length = checkedItems.length;
			for(var i = 0; i < length; i++) {
				if(checkedItems[i].checked) {
					var tr = checkedItems[i].parentElement.parentElement;
					ydbaobao.ajax({
						method : 'delete',
						url : '/item/delete/' + tr.dataset.id,
						success : function(req) {
							document.querySelector("#total-price span").textContent -= document.querySelector('tr[data-id="'+ req.responseText + '"] .item-price').innerText;
							document.querySelector('tr[data-id="'+ req.responseText + '"]').remove();
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
				return;
			}

			e.target.classList.add('checked');
			for(var j = 0; j < length; j++) {
				checkedItems[j].checked = true;
			}
		});
		
		document.querySelector('#select-order-btn').addEventListener('click', function() {
			var checkList = document.querySelectorAll(".item-check");
			var checkLength = checkList.length;
			var paramList = new Array();
			var param = 'itemList=';
			for(var i = 0; i < checkLength; i++) {
				if(checkList[i].checked) {
					paramList.push(checkList[i].parentNode.parentNode.getAttribute("data-id"));
				}
			}
			param += paramList;
			order(param);
		}, false); 
		
		document.querySelector('#order-btn').addEventListener('click', function() {
			var checkList = document.querySelectorAll(".item-check");
			var checkLength = checkList.length;
			var paramList = new Array();
			var param = 'itemList=';
			for(var i = 0; i < checkLength; i++) {
				paramList.push(checkList[i].parentNode.parentNode.getAttribute("data-id"));
			}
			param += paramList;
			order(param);
		}, false); 

	}, false);
	
	function order(param) {
		ydbaobao.ajax({
			method : 'post',
			url : '/orders',
			param : param,
			success : function(req) {
				alert('주문요청이 완료되었습니다.');
				window.location.href = "/orders";
			}
		});
	}

	function loadCartList(itemList) {
		var cartList = document.querySelector('#cart-list');

		var length = itemList.length;
		var totalPrice = 0;
		for (var i = 0; i < length; i++) {
			var tr = ydbaobao.createElement({
		        name: 'tr',
		        attrs: {
		            'data-id': itemList[i].itemId
		        },
		    });

			cartList.tBodies[0].appendChild(tr);

			var itemTemplate = document.querySelector("#cart-item-template").content;
			var item = document.importNode(itemTemplate, true);
			item.querySelector('.item-name').textContent = itemList[i].product.productName;
			item.querySelector('.item-size').textContent = itemList[i].size;
			item.querySelector('.item-quantity').textContent = itemList[i].quantity;
			var price = itemList[i].product.productPrice * itemList[i].quantity;
			item.querySelector('.item-price').textContent = price;
			tr.appendChild(item);	
			totalPrice += price;
		}
		document.querySelector("#total-price span").textContent = totalPrice;
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>




