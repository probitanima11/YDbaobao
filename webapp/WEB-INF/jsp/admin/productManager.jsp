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
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp" %>
		<div id="content">
			<h1>상품 관리</h1>
			<div id="confirm">
				<button>저장</button>
				<button>취소</button>
			</div>

			<table  id="categories" style="width: 800px;">
				<tr>
					<th width="50px">상품ID</th>
					<th width="150px">상품이미지</th>
					<th colspan="5">상세내역</th>
					<th width="50px">선택</th>
				</tr>
				<c:forEach var="product" items="${productList}">
				
				<%-- <form:form class="productUpdate" method="post" action="/products/update" modelAttribute="product" enctype="multipart/form-data"> --%>
				<tr>
				<td rowspan="4">${product.productId}</td>
					<td rowspan="4"><img class="productImg" src="/img/products/${product.productImage}" width="150"></td>
					<td>카테고리 : </td>
					<td><select>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.categoryId}" label="${category.categoryName}" />
							</c:forEach>
						</select></td>
					<td>제품명 : </td>
					<td colspan="2"><input type="text" value="" data-id="${product.productName}" ></td>
					<td rowspan="4"><input type="checkbox" class="checkBtn"></td>
				</tr>
				<tr>
					<td>브랜드 : </td>
					<td>${product.productName}</td>
					<td>재고량 : </td>
					<td>사이즈</td>
					<td>수 량</td>
				</tr>
				<tr>
					<td>가 격 : </td>
					<td><input type="number" value="${product.productPrice}" data-id="${product.productPrice}"></td>
					<td>
					<button class="add-size_quantity-btn" value="${product.productId}">추가</button>
					<button class="delete-size_quantity-btn" value="${product.productId}">삭제</button>
					</td>
					<td id='stock-size' class="${product.productId}"><input type="text" id="product-size-input" class="${product.productId}" name ='size'></td>
					<td id='stock-quantity' class="${product.productId}"><input type="number" id="product-quantity-input" class="${product.productId}" name = 'quantity' ></td>
				</tr>
				<tr>
					<td>상품소개 : </td>
					<td colspan="4"><textarea rows="10" cols="75"></textarea></td>
				</tr>
				<%-- </form:form> --%>
				</c:forEach>
			</table>
		</div>
	</div>

	<script>
	
	window.addEventListener('load', function(e) {
		setStocksEvent();
	}, false);
	

	function setStocksEvent(){
		var addBtn = document.querySelectorAll('.add-size_quantity-btn');
		var deleteBtn = document.querySelectorAll('.delete-size_quantity-btn');
		
		for(var i = 0; i < addBtn.length; i++) {
			addBtn[i].addEventListener('click', function(e) {
				addSizeAndQuentity(e.target.value);
			}, false);
		}

		for(var j = 0; j < deleteBtn.length; j++) {
			deleteBtn[j].addEventListener('click', function(e) {
				deleteSizeAndQuentity(e.target.value);
			}), false;
		}
	}
		
	function deleteSizeAndQuentity(targetValue) {
		var sizeTable = document.body.querySelectorAll('#stock-size');
		var quantityTable = document.body.querySelectorAll('#stock-quantity');
		removeInputBox('product-size-input', sizeTable, targetValue);
		removeInputBox('product-quantity-input', quantityTable, targetValue);
	}
		
		
	function addSizeAndQuentity(targetValue) {
		var sizeTable = document.body.querySelectorAll('#stock-size');
		var quantityTable = document.body.querySelectorAll('#stock-quantity');
		appendInputBox('product-size-input', 'text','size', sizeTable, targetValue);
		appendInputBox('product-quantity-input', 'number','quantity', quantityTable, targetValue);
	}

	function appendInputBox(idName, type, name, targetTable, targetValue) {
		var el=undefined;
		var br=document.createElement('br')
		
		for(var i=0; i<targetTable.length; i++){
			if(targetTable[i].className === targetValue){
				el=document.createElement('input');
				el.setAttribute('type', type);
				el.setAttribute('id', idName);
				el.setAttribute('class', targetValue);
				el.setAttribute('name', name);
				targetTable[i].appendChild(br);
				targetTable[i].appendChild(el);
				break;
			}
		}
	}
	
	function removeInputBox(idName, targetTable, targetValue) {
		var el=undefined;
		var length=targetTable.length;
		for(var i=length-1; i>=0; i--){
			
			var target = targetTable[i];
			
			if(target.className === targetValue && target.childElementCount>1){
				target.removeChild(target.lastChild);	// inputBox삭제
				target.removeChild(target.lastChild);	// <br/> 삭제
				break;
			}
		}
	}
		
	</script>

</body>
</html>
