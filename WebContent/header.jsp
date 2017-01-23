<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="styles/header.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<!--
 * <a class='list-trigger' target='mylist'></a>
 * <div id='mylist' class='list-body'>
 *   <p>Imma noob</p>
 * </div> -->
 
<header>
			<figure id="logo">
				<a href="/GuitarShop/main?page=itemList"><img src="res/logov2.png" alt="logo image" height=60 /></a>
			</figure>
			<div id="header_content">
				<div id="bottom_content">
					<div id="search_div">
						<input id="search_field" type="text" value='${tr.translate("Search")}'/>
						<a onclick="searchItems()"><img src="res/search.png" alt="search" value="Search"/></a>
					</div>
					<div id="country_select">
						<ul>
							<li><a href="/GuitarShop/${page}?locale=ru">ru</a></li>
							<li><a href="/GuitarShop/${page}?locale=en">en</a></li>
							<li><a href="/GuitarShop/${page}?locale=gr">gr</a></li>
						</ul>
					</div>
					<div id="cart_div">
						<a href="/GuitarShop/main?page=cart"><img src="res/cart.png" alt="cart" width=50></a>
						<!-- 
							<c:if test="${!isCartEmpty}">
								<button id="order_button" onclick="proceedOrder()">${tr.translate("proceed_order")}</button>
							</c:if>
						 -->
					</div>
					<c:if test="${logged == true}">
						<div id="personal_info_div" >
							<div class='list-trigger' target='mylist' name='accountInfo'>
								<select id="mylist">
									<option  onclick="goToAccountCard()" value='accountCard'>${tr.translate("goToAccCard")}</option>
									<option  onclick="goToBuyHistory()" value='buyHistory'>${tr.translate("buyHistory")}</option>
									<option onclick="exitFromAccount()" value='exit'>${tr.translate("exitFromAcc")}</option>
								</select>
							</div>
						</div>
					</c:if>
					<c:if test="${logged == null || logged == false}">
						<div id="login_div" >
							<button id="login_button" onclick="proceedOrder()">${tr.translate("Log In")}</button>
						</div>
					</c:if>
					<div id="acc_menu_div">
						<a><img src="res/menu.png" alt="menu"></a>
					</div>
				</div>
			</div>
			
			<script type="text/javascript">
				function searchItems() {
					var searchQuery = document.getElementById("search_field").value;
					
					var requestQuery = "/GuitarShop/main?search=" + searchQuery;
					
					document.location.href=requestQuery;
				}
				function proceedOrder() {
					document.location.href="/GuitarShop/order";
				}
				function exitFromAccount() {
					document.location.href="/GuitarShop/main?action=exit"
				}
				function goToAccountCard() {
					document.location.href="/GuitarShop/account";
				}
				
				var frontex = new FronteX();
				
				window.on("load", function() {
					frontex.init();
				});
			</script>
</header>

</body>
</html>