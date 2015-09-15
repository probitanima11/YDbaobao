<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${not empty products}">
	<div class="contents-nav">
		<a href="${url}${navigator.prevBlock}" class="prevAndNext" style="float:left;"><button><i class="fa fa-chevron-left"></i></button></a>
			<ul style="display:inline">
				<c:forEach var="page" items="${navigator.range}" varStatus="status">

					<c:choose>
						<c:when test="${page eq navigator.selected}">
							<li style="display:inline"><a href="${url}${page}"><span class="selectedIndex">${page}</span></a></li>
						</c:when>
						<c:otherwise>
							<li style="display:inline"><a href="${url}${page}"><span>${page}</span></a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</ul>
		<a href="${url}${navigator.nextBlock}" class="prevAndNext"  style="float:right;"><button><i class="fa fa-chevron-right"></i></button></a>
	</div>
</c:if>