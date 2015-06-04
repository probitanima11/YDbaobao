<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="contents-nav">
	<ul>
		<c:forEach var="digit" items="${range}" varStatus="status">
			<c:if test="${(digit ne 0) && (status.first)}" >
				<a href="?index=${digit}&categoryId=${category.categoryId}" class="prevAndNext"><button>prev</button></a>
			</c:if>
			<li><a href="?index=${digit+1}&categoryId=${category.categoryId}">
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
			<c:if test="${(not empty nextBtn) && (status.last)}" >
				<a href="?index=${digit+2}&categoryId=${category.categoryId}" class="prevAndNext"><button>next</button></a>
			</c:if>
		</c:forEach>
	</ul>
</div>
