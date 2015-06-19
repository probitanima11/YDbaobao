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

h1 {
	margin: 0;
	padding: 0;
	font-size: 35px;
}

#product-buy-container {
	position: absolute; 
	top: 0; 
	left: 100%; 
	margin-left: -350px; 
	width: 350px;
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
		<div id="product-buy-container">
			<h1 class="product-name" style="margin-top: 25px; margin-left: 15px;">${product.productName}</h1>
			<div class="product-price">${product.productPrice}</div>
			<div style="margin-top: 25px; margin-left: 15px;">
				<h3>구매수량</h3>
				<button onclick="qtyControl('minus')"><i class='fa fa-minus'></i></button>
					<ul id="quantity" style="display: inline-block; padding: 0px">
					</ul>
				<!-- <input class="qty-selector" value="1" style="width: 32px;" onkeypress="return isNumberKey(event);"> -->
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
		var sizeArray = "${product.productSize}".split('|');
		var productBuyContainer;
		window.addEventListener('load', function() {
			
			for (var i = 0; i < sizeArray.length; i++) {
				var li = ydbaobao.createElement({
			        name: 'li',
			        attrs: {
			            'class': 'qty',
			            'style': 'display: block',
			            'value': sizeArray[i]
			        },
			        content: sizeArray[i]
			    });
				
				var el = ydbaobao.createElement({
			        name: 'input',
			        attrs: {
			            'class': 'qty-selector',
			            'type' : 'number',
			            'min'  : '1',
			            'name' : sizeArray[i],
			            'value': '1',
			            'style': 'width: 32px; margin-left: 5px',
			            'onkeypress': 'return isNumberKey(event);'
			        },
			        //content: sizeArray[i]
			    });
				li.appendChild(el);
				
				document.body.querySelector('#quantity').appendChild(li);
			}
			
			document.querySelector('.addtocart').addEventListener('click', function(e) {
				addToCart(e);
			}, false);

			// 주석처리 한사람 : jyb
			// 이유 : 현재 바로 주문하기로 안하고 장바구니에 담긴 다음, 그대로 주문으로 넘어감.
			document.querySelector('.buyitnow').addEventListener('click', function(e) {
			buyitnow(e);
			}, false);

			productBuyContainer = document.querySelector("#product-buy-container");
			document.addEventListener('scroll', function(e){
				if (window.scrollY > 150) {
					productBuyContainer.style.top = (window.scrollY - 150)+"px";
				} else {
					productBuyContainer.style.top = "0px";
				}
			},false);

			 var image = document.querySelector("#product-photo img");
			 if (image.naturalWidth > image.naturalHeight) {
				 document.querySelector("#product-photo img").style.width = "100%";
			 } else {
				 document.querySelector("#product-photo img").style.height = "100%";
			 }

			 // 가격에 comma 추가(5000 -> 5,000)
			 priceWithComma();
		}, false);

		function addToCart(e) {
			var el = document.querySelectorAll(".qty-selector");
			var size = "";
			var quantity = "";
			for(var i=0;i<el.length;i++){
				size += el[i].name + "-";
				quantity += el[i].value + "-";
			}
			var param = 'productId=' + productId + '&size=' + size + '&quantity=' + quantity;
			ydbaobao.ajax({
				/* TODO productId, size, quantity 전달. */
				method : 'post',
				url : '/carts/${customer.sessionId}',
				param : param,
				success : function(req) {
					if(req.responseText === "fail")
						alert('로그인이 필요합니다.');
					else
						alert('장바구니에 담겼습니다.');
						
				}
			});
		}

		function buyitnow(e) {
			var el = document.querySelectorAll(".qty-selector");
			var size = "";
			var quantity = "";
			for(var i=0;i<el.length;i++){
				size += el[i].name + "-";
				quantity += el[i].value + "-";
			}
			
			var param = 'productId=' + productId + '&size=' + size + '&quantity=' + quantity;
			ydbaobao.ajax({
				/* TODO productId, size, quantity 전달. */
				method : 'post',
				url : '/carts/${customer.sessionId}',
				param : param,
				success : function(req) {
					if(req.responseText === "fail")
						alert('로그인이 필요합니다.');
					else {
						alert('주문요청이 완료되었습니다.');
						window.location.href = "/orders";
					}
				}
			});
		}
		
		 function qtyControl(type) {
			 var qtyArray = document.querySelectorAll(".qty-selector");
			 switch (type) {
			 	case "plus":
			 		for(var i=0;i<qtyArray.length;i++){
			 			qtyArray[i].value = qtyArray[i].value*1+1;
			 		}
			 		break;
			 	case "minus": 
			 		for(var i=0;i<qtyArray.length;i++){
			 			qtyArray[i].value = qtyArray[i].value*1-1;
				 		if (qtyArray[i].value*1 <= 0) qtyArray[i].value = 1;
			 		}
			 		break;
			 }
		 }

		 function priceWithComma() {
		 	var el = document.querySelector('.product-price');
		 	el.textContent = parseInt(el.textContent).toLocaleString();
		 }
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
