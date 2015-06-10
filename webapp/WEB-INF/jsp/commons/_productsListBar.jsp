<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="contents-nav">
	<ul>
		<c:forEach var="digit" items="${range}" varStatus="status">
			<c:if test="${(digit ne 0) && (status.first) && (not empty category)}" >
				<a href="?index=${digit}&categoryId=${category.categoryId}" class="prevAndNext"><button>prev</button></a>
			</c:if>
			<c:if test="${(digit ne 0) && (status.first) && (not empty query)}" >
				<a href="?index=${digit}&terms=${query}&select=${select}" class="prevAndNext"><button>prev</button></a>
			</c:if>
			<c:if test="${(digit ne 0) && (status.first) && (empty select) && (empty query) && (empty category)}" >
				<a href="?index=${digit}" class="prevAndNext"><button>prev</button></a>
			</c:if>
			<c:if test="${not empty category}" >
				<li><a href="?index=${digit+1}&categoryId=${category.categoryId}">
			</c:if>
			<c:if test="${(not empty select) && (not empty query)}" >
				<li><a href="?index=${digit+1}&terms=${query}&select=${select}">
			</c:if>
			<c:if test="${(empty select) && (empty query) && (empty category)}" >
				<li><a href="?index=${digit+1}">
			</c:if>
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
			<c:if test="${(not empty nextBtn) && (status.last) && (not empty category)}" >
				<a href="?index=${digit+2}&categoryId=${category.categoryId}" class="prevAndNext"><button>next</button></a>
			</c:if>
			<c:if test="${(not empty nextBtn) && (status.last) && (not empty query)}" >
				<a href="?index=${digit+2}&terms=${query}&select=${select}" class="prevAndNext"><button>next</button></a>
			</c:if>
			<c:if test="${(not empty nextBtn) && (status.last) && (empty select) && (empty query) && (empty category)}" >
				<a href="?index=${digit+2}" class="prevAndNext"><button>next</button></a>
			</c:if>
		</c:forEach>
	</ul>
</div>
