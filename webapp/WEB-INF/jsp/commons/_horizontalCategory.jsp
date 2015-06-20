<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${empty sessionCustomer}">
	<c:redirect url="/loginForm" />
</c:if>
<div id="horizontal-category-menu">
	<ul>
		<li><a href="/products/?page=1">전체상품</a></li>
		<c:forEach var="category" items="${categories}" varStatus="status">	
			<li><a href="/categories/${category.categoryId}/products?page=1">
				<span><c:out value="${category.categoryName}"/></span>
			</a></li>
		</c:forEach>
	</ul>
</div>