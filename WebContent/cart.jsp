<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/buttons.css"/>
<link rel="stylesheet" href="styles/global.css"/>
<link rel="stylesheet" href="styles/cart.css"/>
<link rel="icon" type="image/png" href="res/favicon.png?v=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Your cart</title>
</head>
<jsp:include page="header.jsp"/>

<body>

	<h1 id="cart_label">${tr.translate("Cart")}</h1>
	
<c:if test="${cartItems.size() > 0}">
	
<div id="shopping_cart_div">

	<div class="before_items_label">
		<p>${tr.translate("before_items_cart_label")}</p>
	</div>

	<c:forEach var="item" items="${cartItems}">
		<div class="cart_list_item">
			<figure class="list_item_picture"><img src="<c:out value="${item.getPictureURL()}"/>min.jpg" height="100"/></figure>
				<div class="name_with_code">
					<p class="list_item_name"><c:out value="${item.getName()}" /></p>			
					<p class="list_item_code"><c:out value="${item.getVendorCode()}" /></p>
				</div>
				<div class="list_touchspin">
					<button class="standard_button_inversed" onclick="incCount(${item.getId()})" class="touchspin_plus_button">+</button>
					<input class="touchspin_input" value="${cartItems.getItemCount(item)}"/>
					<button class="standard_button_inversed" onclick="decCount(${item.getId()})" id="touchspin_minus_button">-</button>
				</div>
				<p class="list_item_price"><c:out value="${item.getPrice()} $"/></p>
				<div class="delete_list_item_button">
					<button class="standard_button_inversed" onclick="deleteItem(${item.getId()})">×</button>
				</div>
			</p>
		</div>
	</c:forEach>
	
	<div class="after_list_container">
		<div class="total_amount_div">
				<p class="total_amount">${tr.translate("total_amount")}</p>
			<p class="total_amount_value">${cartItems.getTotalPrice()}$</p>
		</div>
		<c:if test="${isLogged == true}">
			<button class="standard_button" id="proceed_order_button" onclick="proceedOrder()">${tr.translate("proceed_order")}</button>
		</c:if>
		<c:if test="${isLogged == false}">
			<button class="standard_button" id="login_button" onclick="proceedOrder()">${tr.translate("proceed_order")}</button>
		</c:if>
	</div>
	
</div>
</c:if>


	<script type="text/javascript">
		function decCount(index) {
			window.location.href="/GuitarShop/main?page=cart&dec="+index;
		}
		function incCount(index) {
			window.location.href="/GuitarShop/main?page=cart&inc="+index;
		}
		function deleteItem(index) {
			window.location.href="/GuitarShop/main?page=cart&del="+index;
		}
		function proceedOrder() {
			window.location.href="/GuitarShop/order";
		}
	</script>

<c:if test="${cartItems.size() == 0}">

	<div id="empty_cart_div" >
	<h2>${tr.translate("empty_cart_message")}</h2>
	<a href="/GuitarShop/main?page=list"><button class="standard_button">${tr.translate("Go and buy things")}</button></a>
	
	<figure><img src="res/emptyCart.png" height="500"/></figure>

	</div>

</div>
</c:if>

</body>
<jsp:include page="footer.jsp"/>

</html>