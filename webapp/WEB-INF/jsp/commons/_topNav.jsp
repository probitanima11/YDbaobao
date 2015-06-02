<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id='util-container'
	style='width: 100%; border-bottom: 1px solid #ccc;'>
	<div id='util-bar' class='wrap content' style='height: 35px;'>
		<ul id='util'>
			<c:choose>
				<c:when test="${not empty sessionCustomer}">
					<li><a href='/customer/logout'><span>로그아웃</span></a></li>
					<li><a href='/customer/update'><span>개인정보수정</span></a></li>
				</c:when>
				<c:otherwise>
					<li><a href='/customer/login'><span>로그인</span></a></li>
					<li><a href='/customer/create'><span>회원가입</span></a></li>
				</c:otherwise>
			</c:choose>
			<!-- 
				<li><a href='#'><span>장바구니</span></a></li>
				<li><a href='#'><span>주문내역</span></a></li>
				<li><a href='#'><span>문의</span></a></li>-->
			<li style='font-weight: bold;'><a href='/admin'><span
					style='color: #EA6576;'>관리자</span></a></li>
		</ul>
	</div>
</div>