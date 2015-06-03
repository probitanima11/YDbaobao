<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::카테고리관리</title>
<link rel="stylesheet" href="/css/admin.css">
</head>
<body>
	<div id="container">
		<%@ include file="./commons/_adminNav.jsp" %>
		<div id="content">
			<h1>카테고리 관리</h1>
			<table style="width: 800px;">
				<tr>
					<th>카테고리명</th>
					<th></th>
				</tr>
				<tr>
					<td>상의 일반</td>
					<td>
						<button>수정</button>
						<button>삭제</button>
					</td>
				</tr>
				<tr>
					<td>하의 일반</td>
					<td>
						<button>수정</button>
						<button>삭제</button>
					</td>
				</tr>
				<tr>
					<td>티셔츠</td>
					<td>
						<button>수정</button>
						<button>삭제</button>
					</td>
				</tr>
				<tr>
					<td>아우터</td>
					<td>
						<button>수정</button>
						<button>삭제</button>
					</td>
				</tr>
				<tr>
					<td>반바지</td>
					<td>
						<button>수정</button>
						<button>삭제</button>
					</td>
				</tr>
				<tr>
					<td>
						<button>추가</button>
					</td>
				</tr>
			</table>
		</div>
		<div id="confirm">
			<button>저장</button>
			<button>취소</button>
		</div>
	</div>
</body>
</html>