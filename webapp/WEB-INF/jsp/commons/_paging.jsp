<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="contents-nav">
	<ul>
	</ul>
</div>
<script>
	for(var i = 1; i <= ${totalPage}; i++) {
		var li = document.createElement('li');
		li.innerHTML = '<a href="?page=' + i + '">' + i + '</a>';
		document.querySelector('.contents-nav ul').appendChild(li);
	}
</script>