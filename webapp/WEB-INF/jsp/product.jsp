<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	width: 100%;
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
			<div id="product-photo">
				<img
					src="http://www.stylenoriter.co.kr/web/product/medium/201505/4027_shop1_259692.jpg">
			</div>

			<div id="product-display">
				<img src="http://stylenoriter.co.kr/web/2015/15su200_01.jpg">
				<img src="http://stylenoriter.co.kr/web/2015/15su200_02.jpg">
				<img src="http://stylenoriter.co.kr/web/2015/15su200_04.jpg">
			</div>
		</div>
		<div id="product-buy-container"
			style="position: absolute; top: 0; left: 100%; margin-left: -350px; width: 350px; outline: 1px solid green;">
			<h1 class="product-name" style="margin-top: 25px; margin-left: 15px;">퓨어레스
				스커트</h1>
			<div class="product-price">19,800</div>
			<div style="margin-top: 25px; margin-left: 15px;">
				<tbody>
					<tr>
						<th>구매수량</th>
						<td>
							<div>
								<input type="text" value="1" style="width: 32px;"
									onkeypress="return isNumberKey(event);">
								<span style="display:inline-block"
									class="number-control"> <i id="up-btn"
									class="fa fa-caret-up" style="display:block"></i> <i id="down-btn"
									class="fa fa-caret-down" style="display:block"></i>
								</span>
							</div>
						</td>
					</tr>
				</tbody>
			</div>
			<div class="button-group">
				<button class="btn addtocart">장바구니</button>
				<button class="btn buyitnow">바로주문</button>
			</div>
		</div>
	</div>
	<div id="footer">footer...</div>

	<script>
		window.addEventListener('load', function() {
			document.querySelector('.addtocart').addEventListener('click', function(e) {
				addToCart(e);
			}, false);
		}, false);

		function addToCart(e) {
			var param = 'productId=1' + '&size=S' + '&quantity=1';
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
		
		 function isNumberKey(evt) {
	            var charCode = (evt.which) ? evt.which : event.keyCode
	            return !(charCode > 31 && (charCode < 48 || charCode > 57));
	     }
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>