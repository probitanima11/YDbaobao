<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id='brand-selector' class='wrap content'
	style='height: 200px; outline: 1px solid #EA6576;'>
	<div id='brand-initial-tab'>
		<label>브랜드 선택</label>
		<ul>
			<c:forEach var="firstLetter" items="${firstLetterList}">
				<li class="first-letter"><span>${firstLetter}</span></a></li>
			</c:forEach>

			<!-- <li class='active'><span>A</span></li>
			<li><a href="#"><span>B</span></a></li>
			<li><span>C</span></li>
			<li><span>D</span></li>
			<li><span>E</span></li>
			<li><span>F</span></li>
			<li><span>G</span></li>
			<li><span>H</span></li>
			<li><span>I</span></li>
			<li><span>J</span></li>
			<li><span>K</span></li>
			<li><span>L</span></li>
			<li><span>M</span></li>
			<li><span>N</span></li>
			<li><span>O</span></li>
			<li><span>P</span></li>
			<li><span>Q</span></li>
			<li><span>R</span></li>
			<li><span>S</span></li>
			<li><span>T</span></li>
			<li><span>U</span></li>
			<li><span>V</span></li>
			<li><span>W</span></li>
			<li><span>X</span></li>
			<li><span>Y</span></li>
			<li><span>Z</span></li>
			<li><span>#</span></li> -->
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