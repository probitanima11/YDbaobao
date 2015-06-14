<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::상품관리</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
<style>
#product-table td {
	font-size:12px;
	text-align:center;
}
</style>
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>상품 관리</h1>
			<table id="product-table" style="width: 800px;">
				<tr>
					<th width="50px">상품ID</th>
					<th width="150px">이미지</th>
					<th colspan="6">상세내역</th>
					<th width="50px">선택</th>
				</tr>
				<c:forEach var="product" items="${productList}">
				<form:form class="productUpdate" method="post" action="/product/update" modelAttribute="product">
				<tr>
					<td rowspan="4">${product.productId}
					<form:input path="productId" type="hidden" value="${product.productId}"/>
					</td>
					<td rowspan="4"><img class="productImg" src="/img/products/${product.productImage}" width="150">
					<form:input path="productImage" type="hidden" value="${product.productImage}"/>	</td>
					<td width="70px">카테고리 :</td>
					<td>
						<select name="category.categoryId">
							<c:forEach var="category" items="${categoryList}">
										<c:choose>
											<c:when test="${category.categoryId eq product.category.categoryId}">
												<option value="${category.categoryId}" label="${category.categoryName}" selected="selected"/>
											</c:when>
											<c:otherwise>
												<option value="${category.categoryId}" label="${category.categoryName}" />
											</c:otherwise>
										</c:choose>
										
							</c:forEach>
						</select>
					</td>
					<td width="50px">브랜드 :</td>
					<td>
						<select name="brand.brandId">
							<c:forEach var="brand" items="${brandList}">
										<c:choose>
											<c:when test="${brand.brandId eq product.brand.brandId}">
												<option value="${brand.brandId}" label="${brand.brandName}" selected="selected"/>
											</c:when>
											<c:otherwise>
												<option value="${brand.brandId}" label="${brand.brandName}" />
											</c:otherwise>
										</c:choose>
							</c:forEach>
						</select>
					</td>
					<td width="50px">가격 :</td>
					<td><form:input type="number" path="productPrice" value="${product.productPrice}" min="0" style="width:90px"/></td>
					<td rowspan="4"><input type="submit" class="update-product-btn" value="수정" onclick="alert('상품 정보가 수정되었습니다');" />
					</td>
				</tr>
				<tr>
					<td>제품명 :</td>
					<td><form:input path="productName" class="productName" value="${product.productName}"/></td>
					<td>사이즈 :</td>
					<td colspan="3" style="text-align:left; padding-left:10px;">
						<div id="stocks" class="${product.productId}">
							<form:input path="productSize" type="text" id="product-size-input" value="${product.productSize}" style="width:80px" />
							<c:forEach var="i" begin="1" end="20">
							<div id="stock-add" class = "" style="display:none">
									<form:input path="" type="hidden" id="stockId" value="0"/>
									<form:input path="" type="text" id="stock-size-input" value="" style="width:80px"/>
									<form:input path="" type="number" id="stock-quantity-input" value="0" min="0" style="width:80px"/>
									<button type="button" class="delete-size_quantity-btn" value="${product.productId}">삭제</button>
							</div>
							</c:forEach>
						</div>
						<!-- <button type="button" class="add-size_quantity-btn" value="${product.productId}">추가</button>   -->
					</td>
				</tr>
				<tr>
					<td>상품소개 :</td>
					<td colspan="5">
					<textarea name="productDescription" rows="2" cols="70">${product.productDescription}</textarea>
					</td>
				</tr>
				<tr>
					<td>이미지 등록 :</td>
					<td colspan="5" style="text-align:left; padding-left:10px;">구현해야됨
					</td>
				</tr>
				</form:form>
				</c:forEach>
			</table>
		</div>
	</div>

	<script>
		window.addEventListener('load', function(e) {
			setStockAddEvent();
			setStockDeleteEvent();
		}, false);

		function setStockAddEvent() {
			var addBtn = document.querySelectorAll('.add-size_quantity-btn');
			for (var i = 0; i < addBtn.length; i++) {
				addBtn[i].addEventListener('click', function(e) {
					addSizeAndQuentity(e.target.value);
					setStockDeleteEvent();
				}, false);
			}
		}
		
		function setStockDeleteEvent() {
			var deleteBtn = document.querySelectorAll('.delete-size_quantity-btn');
			for (var j = 0; j < deleteBtn.length; j++) {
				deleteBtn[j].addEventListener('click', function(e) {
					deleteSizeAndQuentity(e.target.value, e.target.parentNode.className);
				}), false;
			}
		}

		function deleteSizeAndQuentity(productId, stockIndex) {
			var stocksDiv = document.body.querySelectorAll('#stocks');
			
			for (var i = 0; i < stocksDiv.length; i++) {
				if (stocksDiv[i].className === productId) {
					var stocks = stocksDiv[i].children;
					for(var k=0; k<stocks.length; k++){
						if(stocks[k].className === stockIndex){
							stocks[k].remove(stocks[k]);
							return;
						}
					}
				}
			}
		}

		function addSizeAndQuentity(productId) {
			var stocksDiv = document.body.querySelectorAll('#stocks');
			var el = undefined;
			for (var i = 0; i < stocksDiv.length; i++) {
				if (stocksDiv[i].className === productId) {
					
					for(var k=0; k<stocksDiv[i].childElementCount; k++){
						if(stocksDiv[i].children[k].style.display==='none'){
							var index = stocksDiv[i].children[k-1].className*1+1;
							stocksDiv[i].children[k].style.display='';
							stocksDiv[i].children[k].className = index;
							stocksDiv[i].children[k].children[0].name = "stockList["+index+"].stockId";
							stocksDiv[i].children[k].children[1].name = "stockList["+index+"].size";
							stocksDiv[i].children[k].children[2].name = "stockList["+index+"].quantity";
							return;
						}
					}
				}
			}
		}
	</script>

</body>
</html>
