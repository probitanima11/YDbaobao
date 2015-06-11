<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="brand-selector" class="wrap content"
	style="outline: 1px solid #EA6576;">
	<div id="brand-initial-tab">
		<label>브랜드 선택</label>
		<ul>
			<c:forEach var="firstLetter" items="${firstLetterList}">
				<li class="first-letter"><span>${firstLetter}</span></li>
			</c:forEach>
		</ul>
	</div>
	<div id="brand-list">
		<ul>
			<c:forEach var="brand" items="${brands}">
				<li><a href="/brand?brandId=${brand.brandId}"><span>${brand.brandName}</span></a></li>
			</c:forEach>
		</ul>
	</div>	
</div>