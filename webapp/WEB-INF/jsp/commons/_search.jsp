<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="brand-search-container">
	<div id="brand-search-bar" class="wrap content">
		<div id="search-bar-container">
			<a href="/"> <img
				src="/img/yd_logo.gif" />
			</a>
			<form action="/search" method="get" id="search-form">
				<div id="search-bar">
					<select name="select">
						<option value="product-name">상품명</option>
						<option value="brand-name">브랜드명</option>
					</select> <input type="text" name="terms" />
				</div>
				<button class="btn">검색</button>
			</form>
		</div>
	</div>
</div>