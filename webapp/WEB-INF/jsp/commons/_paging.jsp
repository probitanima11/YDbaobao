<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="contents-nav">
	<a href="${url}${prev}" class="prevAndNext" style="float:left"><button>prev</button></a>
		<ul>
			<c:forEach var="page" items="${range}" varStatus="status">
				<li><a href="${url}${page}">${page}</a></li>
			</c:forEach>
		</ul>
	<a href="${url}${next}" class="prevAndNext"><button>next</button></a>
</div>