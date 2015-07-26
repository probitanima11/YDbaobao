<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="util-container" style="width: 100%; border-bottom: 1px solid #ccc;">
	<div id="util-bar" class="wrap content" style="height: 35px;">
		<div class="home"><a href="/"><i class="fa fa-home"></i></a></div>
		<ul id="util">
			<c:choose>
				<c:when test="${not empty sessionCustomer}">
					<li><a href="/shop/logout"><span>로그아웃</span></a></li>
					<li><a href="/shop/customers/updateForm"><span>개인정보수정</span></a></li>
					<li><a href="/shop/carts"><span>장바구니</span></a></li>
					<li><a href="/shop/orders"><span>주문내역</span></a></li>
					<li><a href="/shop/payments"><span>결제조회</span></a></li>
				</c:when>
				<c:when test="${not empty sessionAdmin}">
					<li class="logout"><a href="/admin/customers?page=1"><span style="color: #EA6576;">[관리자 페이지]</span></a></li>
					<li class="logout"><a href="/admin/adminLogout"><span style="color: #EA6576;">[관리자 로그아웃]</span></a></li>
				</c:when>
				<c:otherwise>
					<li><a href="/shop/loginForm"><span>로그인</span></a></li>
					<li><a href="/shop/joinForm"><span>회원가입</span></a></li>
					<li style="font-weight: bold;"><a href="/admin"><span style="color: #EA6576;">관리자</span></a></li>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
