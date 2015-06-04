<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="contents-nav">
	<ul>
		<c:forEach var="digit" items="${range}" varStatus="status">
			<li><a href="?index=${digit+1}">
			<c:choose>
				<c:when test="${digit eq selectedIndex}">
        			<span class="selectedIndex">
    			</c:when>
    			<c:otherwise>
					<span>
			    </c:otherwise>
			</c:choose>
					<c:out value="${digit+1}"/>
				</span>
			</a></li>
		</c:forEach>
	</ul>
</div>
