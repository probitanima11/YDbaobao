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

	<div>
		<div>
			<ul id="cart-list">
			</ul>
		</div>
	</div>

	<div id="footer">footer...</div>

	<template id="cartListTemplate">
	<div class="itemBody">
		<div class="productName"></div>
		<span class="productSize"></span> <button>사이즈 수정</button><br /> <span
			class="productQuantity"></span><button>수량 수정</button> <br />
		<span class="productTotalPrice"></span>
		<div class="control">
                <a href="#" class="delete">삭제</a>
            </div>
	</div>
	</template>

	<script>
	window.addEventListener('load', function() {
		var itemList = ${list};
		loadCartList(itemList);
	}, false);

	function loadCartList(itemList) {
		var ul = document.querySelector('#cart-list');
		for (item of itemList) {
			var el = ydbaobao.createElement({
		        name: 'li',
		        attrs: {
		            /* 'class': "brands-item", */
		            'id': 'itemId'+item.itemId
		        },
		    });
			ul.appendChild(el);
			var cartTemplate = document.querySelector("#cartListTemplate").content;
			var elDiv = document.importNode(cartTemplate, true);
		  	elDiv.querySelector('.productName').innerHTML = '상품명 : ' + item.product.productName;
		  	elDiv.querySelector('.productSize').innerHTML = '사이즈 : ' + item.size;
		  	elDiv.querySelector('.productQuantity').innerHTML = '수량 : ' + item.quantity;
		  	elDiv.querySelector('.productTotalPrice').innerHTML = '판매가 : ' + item.product.productPrice*item.quantity;
		  	
		    el.appendChild(elDiv);
		  	el.querySelector('.delete').addEventListener('click', function(e) {
		  		var itemId = e.target.parentElement.parentElement.parentElement.id;
		  		itemId = itemId.substr(6);
				ydbaobao.ajax({
					method:'post',
					url:'/item/delete/'+itemId,
					success: function(req) {
						document.getElementById('itemId'+itemId).remove();
					}
				});
		  		
		  		/* var target = document.querySelector('#cart-list')
	        	var el = e.target.parentElement.parentElement;
	        	var commentText = el.querySelector('.pComment-text').innerHTML;
	        	var pCommnetId = el.id.slice(4);
	            delete(pCommnetId, commentText); */
	        }, false);
			
		}
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>




