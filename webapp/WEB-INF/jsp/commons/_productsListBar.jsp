<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="contentsNav">
	<ul>
		<c:forEach var="digit" items="${range}" varStatus="status">
			<li><a href="#">
				<span><c:out value="${digit+1}"/></span>
			</a></li>
		</c:forEach>
	</ul>
</div>