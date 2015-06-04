<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="brand-selector" class="wrap content"
	style="height: 200px; outline: 1px solid #EA6576;">
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
				<li><span>${brand.brandName}</span></li>
			</c:forEach>
		</ul>
	</div>	
</div>