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
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>관리자 설정</h1>
		</div>

		<form>
			<input type="hidden" id="adminConfigId" value="${adminConfig.adminConfigId}" />
			<label for="adminDisplayProducts">페이지 당 상품 갯수</label>
			<input type="number" id="adminDisplayProducts" value="${adminConfig.adminDisplayProducts}"/>
			<button>저장</button>
		</form>
	</div>
	<script>
	var form = document.body.querySelector('form');
	form.addEventListener('submit', function(e) {
		ydbaobao.ajax({
			method:'put',
			url:"/admin/config",
			param:"adminConfigId="+e.target.querySelector('#adminConfigId').value+"&adminDisplayProducts="+e.target.querySelector('#adminDisplayProducts').value,
			success: function(req) {
				debugger;
			}
		});
	}, false);
	</script>
	<script src="/js/ydbaobao.js"></script>
</body>
</html>