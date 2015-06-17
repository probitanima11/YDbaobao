<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::설정</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>관리자 설정</h1>
		</div>

		<div class="config" action="/admin/config" method="put" modelAttribute="adminConfig">
			<form:input path="adminConfigId" type="hidden" value="${adminConfig.adminConfigId}" />
			<label for="adminDisplayProducts">페이지 당 상품 갯수</label>
			<form:input path="adminDisplayProducts" type="number" value="${adminConfig.adminDisplayProducts}" />
			<input type="submit" value="저장" />
		</div>
	</div>
	<script>
	
	window.addEventListener('load', fucntion() {
		
		document.body.querySelector('#config').addEventListener('', function(e) {
			
			ydbaobao.ajax({
				method:'put',
				url:'/admin/config/',
				success: function(req){
					if(req.responseText === 'success') {
						alert('회원 등급이 변경되었습니다');
					}
				}
			});			
		}, false);
	}, false);
	
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>
