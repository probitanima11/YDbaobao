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
	<div id='header' style='width: 100%;'>
		<!-- 상단 navigator -->
		<%@ include file="./commons/_topNav.jsp"%>
		<!-- 브랜드/제품 검색바 -->
		<%@ include file="./commons/_search.jsp"%>
	</div>
	
	<div>
		<div>
			<ul id='cart-list'>
			</ul>
		</div>
	</div>
	
	<div id="footer">footer...</div>
	<script>
	window.addEventListener('load', function() {
		var itemList = ${list};
		loadCartList(itemList);
	}, false);

	function loadCartList(itemList) {
		var ul = document.querySelector("#cart-list");
		for (item of itemList) {
			var el = ydbaobao.createElement({
		        name: "li",
		        attrs: {
		            /* 'class': "brands-item", */
		            'value': item.product.productId
		        }
		    });
			ul.appendChild(el);
		}
	}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>