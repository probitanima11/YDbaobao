<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionAdmin}">
	<c:redirect url="/admin/check" />
</c:if>
<div id="menu">
	<div style="padding:15px 15px; color:#fff; background-color:#EA6576">관리자메뉴</div>
	<ul>
		<li><a href="/admin/customers"><span>회원관리</span></a></li>
		<li><a href="/admin/add/product"><span>상품등록</span></a></li>
		<li><a href="/admin/products"><span>상품관리</span></a></li>
		<li><a href="/admin/brands"><span>브랜드관리</span></a></li>
		<li><a href="/admin/categories"><span>카테고리관리</span></a></li>
		<li><a href="/admin/manage/order"><span>주문관리</span></a></li>
		<li><a href="/admin/config"><span>관리자설정</span></a></li>
	</ul>
	<a href="/" style="text-decoration:none;"><div style="margin-top:10px; padding:15px 15px; font-size:12px; color:#fff; background-color:#EA6576">
		<i class='fa fa-home'></i>  쇼핑몰로 이동
	</div></a>
	<a href="/admin/logout" style="text-decoration:none;"><div style="margin-top:10px; padding:15px 15px; font-size:12px; color:#fff; background-color:#454545">관리자 로그아웃</div></a>
</div>    
