<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id='category-menu'
	style='width: 20%; height: 100%; display: table; float: left;'>
	<div id='category-menu-header'>카테고리</div>
	<ul>
		<c:forEach var="category" items="${categoryList}" varStatus="status">
			<li><a href="/products?category=${category.categoryId}"><span><c:out value="${category.categoryName}"/></span></a></li>
		</c:forEach>
	</ul>
</div>