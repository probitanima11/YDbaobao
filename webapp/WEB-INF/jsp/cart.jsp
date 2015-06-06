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
			<!-- 카테고리 메뉴 -->
			<%@ include file="./commons/_category.jsp"%>

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
									<button>선택삭제</button>
									<button>전체선택</button>
								</div>
								<div style="float:right">
									<span>상품합계금액:</span>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</div>

	<div id="footer">footer...</div>

	<template id="cart-item-template">
		<td><input type="checkbox"></td>
		<td><span class="item-name"></span></td>
		<td><span class="item-size"></span></td>
		<td><span class="item-quantity"></span></td>
		<td><span class="item-price"></span></td>
	</template>

	<script>
	window.addEventListener('load', function() {
		var itemList = ${list};
		loadCartList(itemList);
	}, false);

	function loadCartList(itemList) {
		var cartList = document.querySelector('#cart-list');

		var length = itemList.length;
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
			item.querySelector('.item-name').innerText = itemList[i].product.productName;
			item.querySelector('.item-size').innerText = itemList[i].size;
			item.querySelector('.item-quantity').innerText = itemList[i].quantity;
			item.querySelector('.item-price').innerText = itemList[i].product.productPrice * itemList[i].quantity;
			tr.appendChild(item);	
		}
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>




