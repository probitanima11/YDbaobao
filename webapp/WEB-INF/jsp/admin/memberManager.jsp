<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>관리자페이지::회원관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<script src="/js/ydbaobao.js"></script>
<style>
 #member-table .btn {
 	border:0;
 	border-bottom:2px solid black;
 	margin:0 1px;
 	padding:5px;
 	color:#fff;
 }
</style>
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>회원관리</h1>
			<table id="member-table" style="width: 800px;">
				<tr>
					<th>회원아이디</th>
					<th>회원이름</th>
					<th>이메일</th>
					<th>등급</th>
					<th>가입일</th>
					<th>  </th>
				</tr>
			</table>
		</div>
	</div>
	<script>
		window.addEventListener('load',function(){
			ydbaobao.ajax({
				method: "get",
				url: "/customer/load/all",
				success: function(req){
					appendMembers(JSON.parse(req.responseText));
				}
			});
		},false);
		
		function appendMembers(members){
			var memberTable = document.querySelector("#member-table tbody");
			var member;
			var memberRow;
			for (member of members) {
				memberRow = ydbaobao.createElement({
					name:"tr",
					attrs:{
						id:"member-"+member.customerId,
					}
				});
				memberRow.appendChild(ydbaobao.createElement({
					name:"td",
					content:member.customerId
				}));
				memberRow.appendChild(ydbaobao.createElement({
					name:"td",
					content:member.customerName
				}));
				memberRow.appendChild(ydbaobao.createElement({
					name:"td",
					content:member.customerEmail
				}));
				memberRow.appendChild(ydbaobao.createElement({
					name:"td",
					content:member.customerGrade
				}));
				memberRow.appendChild(ydbaobao.createElement({
					name:"td",
					content:member.customerCreateDate
				}));
				memberRow.appendChild(ydbaobao.createElement({
					name:"td",
					content:'<a href="/admin/manage/member/' + member.customerId + '"><button class="btn"><i class="fa fa-info-circle"></i>  상세정보</button></a><button class="btn"><i class="fa fa-remove"></i>  삭제</button>'
				}));
				memberTable.appendChild(memberRow);	
			}
		}
	</script>
</body>
</html>