<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>관리자페이지::브랜드관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<style>
#brand-table input[type='text'], input[type='text'] {
	width:25px;
	margin-right:5px;
}

#brand-table td{
	font-size:9px;
	text-align:center;
}

#brand-table input.brandName {
	width: 150px;
	font-size: 15px;
}

#brand-table .brand_sizes input{
	width:100px;
}
</style>
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>브랜드 관리</h1>
			<input id="search-brand" type="text" style="width:150px;">
			<button class="search-brand-btn">검색</button>
			<table id="brand-table" style="width: 780px;">
				<tr>
					<th style="width:150px">브랜드명</th>
					<th style="width:320px">할인율</th>
					<th style="width:100px">사이즈</th>
					<th style="width:80px"></th>
				</tr>
				<c:forEach var="brand" items="${brands}">
					<tr id="brand_${brand.brandId}">
						<td><input class="brandName" type="text" value="${brand.brandName}"
							data-id="${brand.brandId}"></td>
						<td class='discount_table'>
							1등급:<input type="text" value="${brand.discount_1}">
							2등급:<input type="text" value="${brand.discount_2}">
							3등급:<input type="text" value="${brand.discount_3}">
							4등급:<input type="text" value="${brand.discount_4}">
							5등급:<input type="text" value="${brand.discount_5}">
						</td>
						<td class='brand_sizes'>
							<input type="text" value="${brand.brandSize}">
						</td>	
						<td>
							<button class="update-brand-btn">수정</button>
							<button class="delete-brand-btn">삭제</button>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<td><input class="brandName" id="new-brand" type="text"></td>
					<td></td>
					<td></td>
					<td>
						<button class="create-brand-btn">추가</button>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<script>
		window.addEventListener('load', function() {
			setBrandEvent();
		}, false);

		function setBrandEvent() {
			var updateBtn = document.querySelectorAll('.update-brand-btn');
			var deleteBtn = document.querySelectorAll('.delete-brand-btn');
			var createBtn = document.querySelector('.create-brand-btn');
			var searchBtn = document.querySelector('.search-brand-btn');

			// for문 조건문용 변수
			var length;

			for (var i = 0, length = updateBtn.length; i < length; i++) {
				updateBtn[i].addEventListener('click', function(e) {
					updateBrand(e);
				}, false);
			}

			for (var j = 0, length = deleteBtn.length; j < length; j++) {
				deleteBtn[j].addEventListener('click', function(e) {
					deleteBrand(e);
				}), false;
			}

			createBtn.addEventListener('click', createBrand, false);
			searchBtn.addEventListener('click', searchBrand, false);

		}

		function updateBrand(e) {
			var brandId = e.target.parentElement.parentElement.id.split("brand_")[1];
			var brandName = document.querySelector('input[data-id="' + brandId + '"]').value;
			var discountTable = document.querySelectorAll('#brand_'+brandId+' .discount_table input');
			var value = 0;
			var param = "brandName="+brandName+"&discount_1="+discountTable[0].value;
			for (var i = 1; i < discountTable.length; i++) {
				value = discountTable[i].value*1;
				if (value > 100 || value < 0){
					value = 0;
					document.querySelector("#brand_"+brandId+" .discount_table input:nth-child("+(i+1)+")").value = 0;
				}
				param += "&discount_"+(i+1)+"="+value;
			}
			var brandSize = document.querySelector("#brand_"+brandId+" .brand_sizes input").value;
			param += "&brandSize="+brandSize;
			ydbaobao.ajax({
				method : 'post',
				url : '/api/brands/' + brandId,
				param : param,
				success : function(req) {
					alert('브랜드 정보가 수정되었습니다')
				}
			});
		}

		function deleteBrand(e) {
			var brandId = e.target.parentElement.parentElement.id;
			if (confirm('정말 브랜드를 삭제하시겠습니까?') === true) {
				ydbaobao.ajax({
					method : 'delete',
					url : '/api/brands/' + brandId,
					success : function(req) {
						alert('브랜드가 삭제되었습니다');
						document.getElementById(brandId).remove();
					}
				});
				e.target.removeEventListener('click', deleteBrand, false);
			}
		}

		function createBrand() {
			var brandName = document.querySelector('#new-brand').value;

			if(brandName === '') {
				alert('브랜드 명을 입력해주세요');
				return;
			}

			ydbaobao.ajax({
				method : 'post',
				url : '/api/brands/',
				param : 'brandName=' + brandName,
				success : function(req) {
					alert('브랜드가 추가되었습니다');
					location.reload();
				}
			});
		}

		function searchBrand() {
			var searchValue = document.querySelector('#search-brand').value;
			ydbaobao.ajax({
				method : 'get',
				url : '/api/brands/find?searchValue=' + searchValue,
				success : function(req) {
					appendBrandList(JSON.parse(req.responseText));
				}
			});
		}

		function appendBrandList(json) {
			var table = document.querySelector('#brand-table');
			while (table.rows.length > 2)
				table.deleteRow(1);
			var brandsLength = json.length;
			var cell;
			for (var i = 0; i < brandsLength; i++) {
				row = table.insertRow(1);
				row.id = "brand_"+json[i].brandId;
				row.insertCell(0).innerHTML = "<td><input class='brandName' type='text' value='"+json[i].brandName+"' data-id='"+json[i].brandId+"'></td>";
				cell = row.insertCell(1);
				cell.className = "discount_table";
				cell.innerHTML = "<td>1등급:<input type='text' value='"+json[i].discount_1+"'>2등급:<input type='text' value='"+json[i].discount_2+"'>3등급:<input type='text' value='"+json[i].discount_3+"'>4등급:<input type='text' value='"+json[i].discount_4+"'>5등급:<input type='text' value='"+json[i].discount_5+"'></td>";
				cell = row.insertCell(2);
				cell.className = "brand_sizes";
				cell.innerHTML = "<td class='brand_sizes'><input type='text' value='"+json[i].brandSize+"'></td>";
				row.insertCell(3).innerHTML = "<td><button class='update-brand-btn'>수정</button><button class='delete-brand-btn'>삭제</button></td>";
			}
			setBrandEvent();
		}
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>