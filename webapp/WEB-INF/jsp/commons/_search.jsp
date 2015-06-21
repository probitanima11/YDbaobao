<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="brand-search-container">
	<div id="brand-search-bar" class="wrap content">
		<div id="search-bar-container">
			<a href="/"> <img width="120" height="80" src="/image/logo_1.png" style="margin-left: 50px;"/>
			</a>
			<form id="search-form">
				<div id="search-bar">
					<select id="select">
						<option class="selected">상품명</option>
						<option>브랜드명</option>
					</select> 
					<input id="search-terms" type="text" />
				</div>
				<button id="search-btn" class="btn" type="submit">검색</button>
			</form>
		</div>
	</div>
</div>
<script>
	window.addEventListener('load', function() {
		document.querySelector('#select').addEventListener('change', function(e) {
			document.querySelector('.selected').classList.remove('selected');
			for(var i = 0 ; i < e.target.options.length; i++) {
				if(e.target.options[i].value === e.target.value) {
					e.target.options[i].classList.add('selected');
				}	
			}
		}, false);
		
		document.querySelector("#search-form").addEventListener('submit', function(e) {
			e.preventDefault();
			var selected = document.querySelector('.selected').value;
			var terms = document.querySelector('#search-terms').value;
			if(selected === '상품명') {
				window.location.href = '/search/products?terms=' + terms + '&page=1';
			}
			if(selected === '브랜드명') {
				window.location.href = '/search/brands?terms=' + terms + '&page=1';
			}
		}, false);
	}, false);
</script>