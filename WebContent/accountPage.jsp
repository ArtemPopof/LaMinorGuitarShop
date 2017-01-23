<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="styles/global.css"/>
		<link rel="stylesheet" href="styles/accountPage.css"/>
		<link rel="icon" type="image/png" href="res/favicon.png?v=1"/>
		<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet"> 
<title>Your account page</title>
</head>
<body>

	<jsp:include page="/header.jsp"/>
	
	<div class="account_page_label">
		<h1>${tr.translate("accPageLabel")}</h1>
	</div>
	
	<div class="account_page_container">
		<div class="account_page_blank">
	
		</div>
		<div class="account_page_card">
			<div class="card_left_part">
				<div class="account_page_picture">
					<img src="res/accountBlank.png"/>
				</div>
			</div>
			<div class="card_right_part">
				<h2 class="account_page_user_name">${userName}</h2>
				
				<c:if test='${initial_bookmark == "full"}'>
				<p  class="account_page_bookmark_info">${tr.translate("def_bookmark")} ${tr.translate("Full")} </p>
				</c:if>
				<c:if test='${initial_bookmark == "short"}}'>
				<p  class="account_page_bookmark_info">${tr.translate("def_bookmark")} ${tr.translate("Short")} </p>
				</c:if>
				<c:if test='${initial_bookmark == "feedback"}'>
				<p  class="account_page_bookmark_info">${tr.translate("def_bookmark")} ${tr.translate("Reviews")} </p>
				</c:if>	

			</div>
			
							
				<c:forEach var="order" items="${orders}">
					<div class="order_info_div">
						<p class="order_label">Заказ номер ${order.getOrder().getOrderId()}</p>
						<p class="order_date">Дата: ${order.getOrder().getOrderDate()}</p>
						<p class="order_shop">Магазин: ${order.getOrder().getShopLocation()}</p>
						
						<c:forEach var="item" items="${order.getItems()}">
							<div class="order_item">
								<p class="item_name">${item.getName()}</p>
								<p class="item_price">${item.getPrice()} $</p>
								<p class="item_type">${item.getType()}</p>
							</div>
						</c:forEach>
					</div>
				</c:forEach>
				
		</div>

		<div class="account_page_blank">
	
		</div>
	</div>
		
	<jsp:include page="/footer.jsp"/>

</body>
</html>