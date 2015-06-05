<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="horizontal-category-menu">
	<ul>
		<c:forEach var="category" items="${categories}" varStatus="status">
			<li><a href="/products?categoryId=${category.categoryId}">
				<span><c:out value="${category.categoryName}"/></span>
			</a></li>
		</c:forEach>
	</ul>
</div>