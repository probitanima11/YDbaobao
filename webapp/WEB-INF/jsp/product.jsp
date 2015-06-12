<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/css/main.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<title>YDbaobao:: 상품페이지</title>
<style>
#product-container {
	
}

#product-info-container {
	position: relative;
	width: 620px;
	padding: 25px 0;
	margin-bottom: 100px;
	outline: 1px solid #EA6576;
}

#product-photo {
	position: relative;
	width: 500px;
	height: 500px;
	margin: 0 auto;
	overflow: hidden;
	outline: 1px solid #ccc;
}

#product-photo img {
	max-height: 100%;
	max-width: 100%;
}

h1 {
	margin: 0;
	padding: 0;
	font-size: 35px;
}

#product-buy-container .product-price {
	font-size: 30px;
	margin-top: 10px;
	margin-left: 15px;
	font-weight: 800;
	color: #EA7565;
}

.button-group {
	padding: 15px;
}

.btn {
	padding: 15px;
	border: 0;
	font-size: 20px;
	color: white;
	border-radius: 2px;
	margin-right: 10px;
}

.btn.buyitnow {
	background-color: #EA6576;
}

#product-display {
	
}

#product-display img {
	width: 100%;
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
	<div id="product-container" class="content wrap"
		style="position: relative;">
		<div id="product-info-container">
			<div align=center id="product-photo">
				<img
					src="/img/products/${product.productImage}">
			</div>

			<div id="product-display">
				${product.productDescription}
			</div>
		</div>
		<div id="product-buy-container"
			style="position: absolute; top: 0; left: 100%; margin-left: -350px; width: 350px; outline: 1px solid green;">
			<h1 class="product-name" style="margin-top: 25px; margin-left: 15px;">${product.productName}</h1>
			<div class="product-price">${product.productPrice}원</div>
			<div style="margin-top: 25px; margin-left: 15px;">
				<h3>사이즈</h3>
				<select id="size-selector">
                   <c:forEach items="${product.stockList}" var="stock">
                         <option value="${stock.size}" label="${stock.size} ( ${stock.quantity}개 남음 )"></option>
                   </c:forEach>
				</select>
			</div>
			<div style="margin-top: 25px; margin-left: 15px;">
				<h3>구매수량</h3>
				<button onclick="qtyControl('minus')"><i class='fa fa-minus'></i></button>
				<input id="qty-selector" type="text" value="1" style="width: 32px;" onkeypress="return isNumberKey(event);">
				<button onclick="qtyControl('plus')"><i class='fa fa-plus'></i></button>
			</div>
			<div class="button-group">
				<button class="btn addtocart">장바구니</button>
				<button class="btn buyitnow">바로주문</button>
			</div>
		</div>
	</div>
	<div id="footer">footer...</div>

	<script>
		var productId = ${product.productId};
		var productBuyContainer;
		window.addEventListener('load', function() {
			document.querySelector('.addtocart').addEventListener('click', function(e) {
				addToCart(e);
			}, false);
			document.querySelector('.buyitnow').addEventListener('click', function(e) {
				buyitnow(e);
			}, false);
			productBuyContainer = document.querySelector("#product-buy-container");
			document.addEventListener('scroll', function(e){
				console.log(window.scrollY);
				if (window.scrollY > 150) {
					productBuyContainer.style.top = (window.scrollY - 150)+"px";
				} else {
					productBuyContainer.style.top = "0px";
				}
			},false);
		}, false);

		function addToCart(e) {
			var size = document.getElementById("size-selector").value;
			var quantity = document.getElementById("qty-selector").value;
			var param = 'productId=' + productId + '&size=' + size + '&quantity=' + quantity;
			ydbaobao.ajax({
				/* TODO productId, size, quantity 전달. */
				method : 'post',
				url : '/item/add/',
				param : param,
				success : function(req) {
					alert('장바구니에 담겼습니다.');
				}
			});
		}

		function buyitnow(e) {
			var size = document.getElementById("size-selector").value;
			var quantity = document.getElementById("qty-selector").value;
			var param = 'productId=' + productId + '&size=' + size + '&quantity=' + quantity;
			ydbaobao.ajax({
				/* TODO productId, size, quantity 전달. */
				method : 'post',
				url : '/item/order/',
				param : param,
				success : function(req) {
					alert('주문요청이 완료되었습니다.');
					window.location.href = "/orders";
				}
			});
		}
		
		 function isNumberKey(evt) {
	            var charCode = (evt.which) ? evt.which : event.keyCode
	            return !(charCode > 31 && (charCode < 48 || charCode > 57));
	     }
		 
		 function qtyControl(type) {
			 var qty = document.querySelector("#qty-selector");
			 switch (type) {
			 	case "plus": qty.value = qty.value*1+1; break;
			 	case "minus": 
			 		qty.value = qty.value*1-1;
			 		if (qty.value*1 <= 0) qty.value = 1;
			 		break;
			 }
		 }
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>