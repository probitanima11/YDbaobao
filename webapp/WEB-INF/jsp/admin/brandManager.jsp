<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>관리자페이지::브랜드관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>브랜드 관리</h1>
			<div style=" margin: 0 auto; ">
			<div>
				<input type="text" id="new-brand"/>
				<input type="button" id="brand-add" value="추가"/>
			</div>
			<div style="margin-left: 135px;">
				<input type="button" id="brand-delete" value="삭제" />
			</div>
			<div>
				<select id="brands-list" name="brands" size=10 style=" width: 132px;">
					<c:forEach items="${brands}" var="brand">
						<option class="brands-item" value="${brand.brandId}">${brand.brandName}</option>
					</c:forEach>
				</select>
				
			</div>
		</div>
		</div>
	</div>
	<script>
		// 브랜드 리스트에서 브랜드 선택 시 해당 정보 띄우기 위한 이벤트
		document.querySelector('#brands-list').addEventListener('click', function(e) {
			if(e.target.className === 'brands-item')
				console.log(e.target.value);
		}, false);
		
		// 브랜드 추가 이벤트 
		document.querySelector('#brand-add').addEventListener('click', function(e) {
			var brandName = document.querySelector('#new-brand').value
			ydbaobao.ajax({
				method:'post',
				param:'brandName='+brandName,
				url:'/api/admin/brandManager',
				success: function(req) {
					var optionEl = ydbaobao.createElement({
				        name: 'option',
				        attrs: {
				            'class': 'brands-item',
				            'value': req.responseText
				        }
				    });
					optionEl.innerHTML = brandName;
					document.querySelector('#brands-list').add(optionEl)
				}
			});
		}, false);
		// 브랜드 삭제 이벤트 
		document.querySelector('#brand-delete').addEventListener('click', function(e) {
			var selectEl = document.querySelector('#brands-list');
			ydbaobao.ajax({
				method:'delete',
				url:'/api/admin/brandManager/'+selectEl.selectedOptions[0].value,
				success: function(req) {
					selectEl.remove(selectEl.selectedIndex)
				}
			});
			
			if(e.target.className === 'brands-item')
				console.log(e.target.value);
		}, false);
			
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>