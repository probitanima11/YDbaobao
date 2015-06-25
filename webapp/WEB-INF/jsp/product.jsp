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
	height: 300px;
	padding: 25px 0;
	margin-bottom: 100px;
	/* outline: 1px solid #EA6576; */
}

#product-photo {
	position: relative;
	float : left;
	width: 500px;
	height: 320px;
	margin: 0 auto;
	overflow: hidden;
}

#product-display {
	position: relative;
	width: 850px;
	height: 300px;
	padding : 20px;
	margin-top: -50px;
	margin-left: 40px;
	margin-bottom: 50px;
	outline: 1px solid #EA6576;
}

h1 {
	margin: 0;
	padding: 0;
	font-size: 35px;
}

#product-buy-container {
	float:right;
	margin-top: -25px;
	margin-right: 100px;
	width: 356px;
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

.btn.disabled {
	background-color: #c8c8c8;
}

#product-display img {
	width: 100%;
}

.isSoldout {
	color: #EA6576;
	font-weight:800;
	font-size: 40px;
	padding:10px;
}

i {
	cursor: pointer;
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
	<div id="product-container" class="content wrap" style="position: relative;">
		<div id="product-info-container">
			<div align = center id="product-photo">
				<img src="/img/products/${product.productImage}">
			</div>
			<div id="product-buy-container">
				<h1 class="product-name"
					style="margin-top: 25px; margin-left: 15px;">${product.productName}</h1>
				<div class="product-price">${product.productPrice}</div>
				<div id="sizeBox" style="margin-top: 25px; margin-left: 15px;">
					<h3>구매수량</h3>
					<table>
						<td>
							<ul id="quantity" style="display: inline-block; padding: 0px">
							</ul>
						</td>
							<!-- <input class="qty-selector" value="1" style="width: 32px;" onkeypress="return isNumberKey(event);"> -->
						<td>
							<div id="btnBox">
								<button id="plusBtn" class="plusMinus-btn" onclick="qtyControl('plus')">
									<i class='fa fa-plus'></i>
								</button>
								<button class="plusMinus-btn" onclick="qtyControl('minus')">
									<i class='fa fa-minus'></i>
								</button>
							</div>
						</td>
					</table>
				</div>
				<c:choose>
					<c:when test="${product.isSoldout == 1}">
						<div class="isSoldout">품 절</div>
						<button class="btn addtocart disabled" disabled>장바구니</button>
					</c:when>
					<c:otherwise>
						<div class="button-group">
							<button class="btn addtocart">장바구니</button>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div id="product-display">${product.productDescription}</div>
	</div>
	<div id="footer">
		<%@ include file="./commons/_footer.jsp"%>
	</div>
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
			            'style': 'margin-left: 5px',
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
