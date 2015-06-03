<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome YDbaobao!</title>
<meta charset="utf-8" />
<link href="http://fonts.googleapis.com/css?family=Lobster"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="/css/main.css" />
</head>
<body>
	<div id='header' style='width: 100%;'>
		<!-- 상단 navigator -->
		<%@ include file="./commons/_topNav.jsp"%>
		<!-- 브랜드/제품 검색바 -->
		<%@ include file="./commons/_search.jsp" %>
		
		<div id='main-container'>
			<div id='first-section' class='wrap content' style='height: 500px;'>
				<!-- 카테고리 메뉴 -->
				<%@ include file="./commons/_category.jsp" %>
				
				<div id=''
					style='width: 80%; height: 100%; outline: 1px solid #ccc; display: table; float: left;'>
					아마도 신상품 페이지</div>
			</div>
			<div id='second-section' style="padding-top: 25px;">
				<div id='brand-selector' class='wrap content'
					style='height: 200px; outline: 1px solid #EA6576;'>
					<div id='brand-initial-tab'>
						<label>브랜드 선택</label>
						<ul>
							<li class='active'><span>A</span></li>
							<li><span>B</span></li>
							<li><span>C</span></li>
							<li><span>D</span></li>
							<li><span>E</span></li>
							<li><span>F</span></li>
							<li><span>G</span></li>
							<li><span>H</span></li>
							<li><span>I</span></li>
							<li><span>J</span></li>
							<li><span>K</span></li>
							<li><span>L</span></li>
							<li><span>M</span></li>
							<li><span>N</span></li>
							<li><span>O</span></li>
							<li><span>P</span></li>
							<li><span>Q</span></li>
							<li><span>R</span></li>
							<li><span>S</span></li>
							<li><span>T</span></li>
							<li><span>U</span></li>
							<li><span>V</span></li>
							<li><span>W</span></li>
							<li><span>X</span></li>
							<li><span>Y</span></li>
							<li><span>Z</span></li>
							<li><span>#</span></li>
						</ul>
					</div>
				</div>
				<div id='item-container' class='wrap content'>
					<ul>
						<li class='item'><img
							src="http://img33.makeshop.co.kr/shopimages/iloveje/0160020001013.jpg" />
						</li>
						<li class='item'><img
							src="http://1.226.84.96/img_item/2015/05/29/PTDP50529023.jpg" />
						</li>
						<li class='item'><img
							src="http://www.stylenoriter.co.kr/web/product/tiny/201504/4002_shop1_903103.jpg" />
						</li>
						<li class='item'><img
							src="http://www.stylenoriter.co.kr/web/product/tiny/201505/4137_shop1_857453.jpg" />
						</li>
						<li class='item'><img
							src="http://www.stylenoriter.co.kr/web/product/tiny/201505/4102_shop1_319639.jpg" />
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>