/**
 * brand 이름 클릭 시 해당 브랜드만 출력하기 위한 javascript
 * @func setBrandSearchEvent : A~Z 첫 글자에 click event 설정
 * @func searchBrand : 클릭된 글자(A~Z)에 해당되는 브랜드 리스트 요청
 * @func changeBrandList : 검색 된 결과를 페이지에 출력
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
	ydbaobao.ajax({
		method:'get',
			url:'/brand/search/' + target.textContent,
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
		li.innerHTML += '<a href="/brand/products/' + brands[i].brandId + '"><i class="fa fa-bookmark"></i>  <span>' + brands[i].brandName + '</span></a>';
		ul.appendChild(li);
	}
}