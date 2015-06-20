<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="contents-nav">
	<a href="${url}${prev}" class="prevAndNext" style="float:left"><button>prev</button></a>
		<ul>
			<c:forEach var="page" items="${range}" varStatus="status">
				<c:choose>
					<c:when test="${page eq selectedIndex}">
						<li><a href="${url}${page}"><span class="selectedIndex">${page}</span></a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${url}${page}"><span>${page}</span></a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</ul>
	<a href="${url}${next}" class="prevAndNext"><button>next</button></a>
</div>