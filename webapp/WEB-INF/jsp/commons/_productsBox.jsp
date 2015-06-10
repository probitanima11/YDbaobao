<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul>
	<c:forEach var="product" items="${productList}" varStatus="status">
		<li class="item">
			<div align="center">
				<a href="/product?productId=${product.productId}"> 
					<img src="/img/products/${product.productImage}" />
					<div class="item-info">
						<div class="item-desc">
							<c:out value="${product.brand.brandName}" />
						</div>
						<div class="item-name">
							<c:out value="${product.productName}" />
						</div>
						<div class="item-price">
							<c:out value="${product.productPrice}" />
						</div>
					</div>
				</a>
			</div>
		</li>
	</c:forEach>
</ul>