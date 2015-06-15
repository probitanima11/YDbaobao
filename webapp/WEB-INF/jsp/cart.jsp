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
				<div class="on"><i class='fa fa-shopping-cart'></i>  장바구니  <i class='fa fa-chevron-right'></i></div>
				<div><i class="fa fa-barcode"></i>  주문하기  <i class='fa fa-chevron-right'></i></div>
				<div><i class="fa fa-archive"></i>  주문완료</div>
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
					<tbody></tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div style="float:left">
								</div>
								<div id="total-price" style="float:right; padding:15px; font-size:15px;">상품합계금액 : 
									<span style="font-weight:800;"></span>
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

	<template id="cart-item-template">
		<td><input class="item-check" type="checkbox"></td>
		<td class="item-image-container"><img class="item-image" src=""></td>
		<td class="item-name-container"><span class="item-name"></span></td>
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
			item.querySelector('img').src = "/img/products/"+itemList[i].product.productImage;
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




