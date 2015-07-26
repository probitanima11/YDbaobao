<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="brand-selector" class="wrap content">
	<div id="brand-initial-tab">
		<label>브랜드 선택</label>
		<ul>
			<li class="first-letter"><span>#</span><li>
			<c:forEach var="firstLetter" items="${firstLetterList}">
				<li class="first-letter"><span>${firstLetter}</span></li>
			</c:forEach>
		</ul>
	</div>
	<div id="brand-list">
		<ul>
			<c:forEach var="brand" items="${brands}">
				<c:choose>
					<c:when test="${not empty category.categoryId}">
						<li><a href="/shop/categories/${category.categoryId}/brands/${brand.brandId}/products?page=1"><i class='fa fa-bookmark'></i><span>${brand.brandName}(${brand.brandCount})</span></a></li>
					</c:when>		
					<c:otherwise>
						<li><a href="/shop/brands/${brand.brandId}/products?page=1"><i class='fa fa-bookmark'></i>  <span>${brand.brandName}(${brand.brandCount})</span></a></li>
					</c:otherwise>			
				</c:choose>				
			</c:forEach>
		</ul>
	</div>	
</div>
<script>
/**
 * TODO brand.js 와 중복 제거할 것
 * changeBrandList만 다름
 */ 

window.addEventListener('load', function() {
	setBrandSearchEvent();
}, false)

function setBrandSearchEvent() {
	var firstLetterList = document.querySelectorAll('.first-letter');
			
	for(var i = 0, length = firstLetterList.length; i < length; i++) {
		firstLetterList[i].addEventListener('click', function(e) {
			var selectedBrand = document.querySelector(".active");
			if (selectedBrand !== null) {
				selectedBrand.className = selectedBrand.className.replace(" active","");
			}
			this.className += " active";
			searchBrand(e.target);
		}, false);
	}
}

function searchBrand(target) {
	var firstLetter = target.textContent;
	if(target.textContent === '#') {
		firstLetter = 'all'
	}
	ydbaobao.ajax({
		method:'get',
			url:'/shop/brands/' + firstLetter,
			success: function(req) {
				changeBrandList(JSON.parse(req.responseText));
			}
	});
}

function changeBrandList(brands) {
	// 기존 brand list 삭제
	var ul = document.querySelector('#brand-list > ul');
	while(ul.firstChild) {
		ul.removeChild(ul.firstChild);
	}

	// 검색된 브랜드 리스트 출력
	for(var i = 0, length = brands.length; i < length; i++) {
		var li = document.createElement('li');
		li.innerHTML += '<a href="/shop/categories/${category.categoryId}/brands/' + brands[i].brandId + '/products?page=1"><i class="fa fa-bookmark"></i>  <span>' + brands[i].brandName +'('+brands[i].brandCount+')'+ '</span></a>';
		ul.appendChild(li);
	}
}
</script>