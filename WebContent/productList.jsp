<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.artempopov.web.beans.GuitarBean" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles/global.css"/>
<link rel="stylesheet" href="styles/productList.css"/>
<link rel="stylesheet" href="styles/productListItem.css"/>
<link rel="stylesheet" href="styles/buttons.css"/>
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> 
<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet"> 
<title>LaMinor Guitar Shop</title>
</head>
<body>
	<jsp:include page="header.jsp"/>
	
		<script type="text/javascript">
			function toProduct(index) {
				window.location.href="/GuitarShop/main?page=productCard&itemId="+index;
			}
		</script>
	
	<div id="productListContainer">
	
		<% GuitarBean[] guitars = (GuitarBean[]) request.getAttribute("guitars"); 
		   for (int i = 0; i < guitars.length; i++) {
		   
		   request.setAttribute("guitar", guitars[i]);%>
		   
		   	<jsp:include page="listItem.jsp"/>
		   
		   <% } %>
	
	</div>
	
			<div id="cartAddedHint" >
			<p> Товар успешно добавлен в корзину! </p>
		</div>
	
	<script type="text/javascript">
		function addItemToCart(id) {		
			window.location.href="/GuitarShop/main?page=productList&cartAdd="+id;
		}
	</script>
			
	<script type="text/javascript">
		function afterItemAdded() {
			document.getElementById("cartAddedHint").style.display = 'inline';
		}
		</script>
		
			<c:if test="${toCartAdded == true}">
				<script type="text/javascript">
				afterItemAdded();
				</script>
			</c:if>
	
	<jsp:include page="footer.jsp"/>
	
</body>
</html>