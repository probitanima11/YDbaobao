<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8" />
<title>관리자페이지::상품등록</title>
<link rel="stylesheet" href="/css/admin.css">
<link rel="stylesheet" href="/css/font-awesome.min.css">
</head>
<body>
	<div id="container">
		<%@ include file="./_adminNav.jsp"%>
		<div id="content">
			<h1>첫화면 이미지 관리</h1>
			<table id="index-list" cellspacing="0" >
				<tbody>
					<tr id="row1"></tr>
					<tr id="row2"></tr>
				</tbody>
			</table>
		</div>
	</div>
	
	<template id="index-template">
		<td>
		<div>
			<a href="#">
				<img class="imageSnap" src="/image/background-default.png" onmouseover="mouseOver(this)" style="border: solid 1px rgb(191, 191, 191);">
				<img class="imageSnapHover" onclick="mouseClick()" onmouseout="mouseOut(this)" src="/image/change_hover.png" style="display:none">
			</a>
		</div>
		<input type="file" class="imageFile" name="backgroundImage" accept="image/x-png, image/gif, image/jpeg" style="display:none;" onchange="changeImage(this)"/> 
		</td>
	</template>
	
	<script>
	window.addEventListener('load', function() {
		var orderEl = document.querySelector('#index-list');
		for (var i = 0; i < 4; i++) {
			var indexTemplate = document.querySelector("#index-template").content;
			var order = document.importNode(indexTemplate, true);
			var tr = document.querySelector("#row1");
			/* if(topOrder === undefined || topOrder !== orderList[i].orderId) {
				topOrder = orderList[i].orderId;
				order.querySelector('.order-date').textContent = orderList[i].orderUpdateDate;
				order.querySelector('.order-date').parentNode.setAttribute("class", "top-order-date-td");
				order.querySelector('.order-status').textContent = orderList[i].orderStatus;
				order.querySelector('.order-status').parentNode.setAttribute("class", "top-order-status-td");
				order.querySelector('.order-price').textContent = orderList[i].realPrice;
				order.querySelector('.order-price').parentNode.setAttribute("class", "top-order-price-td");
				order.querySelector('.order-name').parentNode.setAttribute("class", "top-order-item-td");
			}
			order.querySelector('.order-name').textContent = orderList[i].productName;
			order.querySelector('.order-detail').textContent = "수량: " + orderList[i].quantity + "/ 사이즈: "+ orderList[i].size;
			orderEl.tBodies[0].appendChild(tr);*/
			tr.appendChild(order);	
		}
		for (var i = 4; i < 8; i++) {
			var indexTemplate = document.querySelector("#index-template").content;
			var order = document.importNode(indexTemplate, true);
			var tr = document.querySelector("#row2");
			/* if(topOrder === undefined || topOrder !== orderList[i].orderId) {
				topOrder = orderList[i].orderId;
				order.querySelector('.order-date').textContent = orderList[i].orderUpdateDate;
				order.querySelector('.order-date').parentNode.setAttribute("class", "top-order-date-td");
				order.querySelector('.order-status').textContent = orderList[i].orderStatus;
				order.querySelector('.order-status').parentNode.setAttribute("class", "top-order-status-td");
				order.querySelector('.order-price').textContent = orderList[i].realPrice;
				order.querySelector('.order-price').parentNode.setAttribute("class", "top-order-price-td");
				order.querySelector('.order-name').parentNode.setAttribute("class", "top-order-item-td");
			}
			order.querySelector('.order-name').textContent = orderList[i].productName;
			order.querySelector('.order-detail').textContent = "수량: " + orderList[i].quantity + "/ 사이즈: "+ orderList[i].size;
			orderEl.tBodies[0].appendChild(tr);*/
			tr.appendChild(order);	
		}
	}, false);
	
	function mouseClick() {
		document.getElementById('imageFile').click();
	}
	function mouseOver(e) {
		e.parentElement.querySelector(".imageSnapHover").style.display = "block";
	}
	function mouseOut(e) {
		e.parentElement.querySelector(".imageSnapHover").style.display = "none";
	}
	
	function changeImage(input) {
		if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
            	document.getElementById('avatarSanp').src = e.target.result;
            	document.querySelector("#userImage").value = "changed";
            }
            reader.readAsDataURL(input.files[0]);
        }
	}

	function backToNoteList() {
	}
	</script>
</body>
</html>
