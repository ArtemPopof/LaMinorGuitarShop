<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ page import="com.artempopov.web.beans.GuitarBean" %>


<% GuitarBean guitar = (GuitarBean) request.getAttribute("guitar"); %>
<div class="productListItem" >
			<div id="clickDiv" onclick="toProduct(<%out.print(guitar.getId()); %>)">
			<div class="vendorAndImage">
				<div class="vendorCodeContainer">
					<p><c:out value="${guitar.getVendorCode()}"/></p>
				</div>
				<figure class="productImage">
					<img src="<%out.print(guitar.getPictureURL());%>min.jpg" width="100" height="250"/>
				</figure>
			</div>
			<p class="productType">${tr.translate(guitar.getType())}</p>
			<h2 class="productName"><%out.print(guitar.getName()); %></h2>
			<p class="productPrice"><%out.print(guitar.getPrice()); %>$</p>
			</div>
			<div class="rightBlock">
				<p class="availableOrNot">${tr.translate(guitar.getAvailableStatus())}</p>
				<button class="standard_button" onclick="addItemToCart(${guitar.getId()})">${tr.translate("Add to cart")}</button>
			</div>
</div>