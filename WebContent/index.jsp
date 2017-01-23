<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
	<head>
		<title>LaMinor Guitar Shop</title>
		<meta charset="UTF-8"/>
		<link rel="stylesheet" href="styles/global.css"/>
		<link rel="icon" type="image/png" href="res/favicon.png?v=1"/>
		<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
		<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet"> 
	</head>
	<body>
		<jsp:include page="header.jsp"/>
		<div id="where_are_we">
			<p>${tr.translate("Guitars - Electric - 6 strings")}</p>		
		</div>
		<section class="item_card">
			<div id="item_card_header">
				<h2><c:out value="${product.getName()}"/></h2>			
			</div>		
			<div id="item_card_stars">
				<div id="stars_images">
					<img src="res/star-on.png" alt="+1"/>		
					<img src="res/star-on.png" alt="+1"/>				
					<img src="res/star-on.png" alt="+1"/>		
					<img src="res/star-on.png" alt="+1"/>
					<img src="res/star-off.png" alt="+0"/>			
				</div>
				<p>(20 ${tr.translate("reviews")})</p>			
			</div>
			<div id="item_card_bookmarks">


			
			<script type="text/javascript">
				function show_bookmark(bookmark, card) {
					document.getElementById("item_card_content_short").style.display = 'none';
					document.getElementById("item_card_content_full").style.display = 'none';
					document.getElementById("item_card_content_feedback").style.display = 'none';
					
					document.getElementById("item_card_content_"+card).style.display = 'flex';
					
					
					document.getElementById("full_info_bookmark").style.background = '#ffffff';
					document.getElementById("feedback_bookmark").style.background = '#ffffff';
					document.getElementById("short_info_bookmark").style.background = '#ffffff';
					
					document.getElementById(bookmark).style.background = '#2f4a48';

					document.getElementById("full_info_bookmark").style.color = '#19302e';
					document.getElementById("short_info_bookmark").style.color = '#19302e'
					document.getElementById("feedback_bookmark").style.color = '#19302e';
					
					document.getElementById(bookmark).style.color = '#efefef';

					
				}
			</script>
		
				<ul>
					<li id="short_info_bookmark" onclick='show_bookmark("short_info_bookmark", "short")'>${tr.translate("Short")}</li>
					<li id="full_info_bookmark" onclick='show_bookmark("full_info_bookmark", "full")'>${tr.translate("Full")}</li>
					<li id="feedback_bookmark" onclick='show_bookmark("feedback_bookmark", "feedback")'>${tr.translate("Reviews")}</li>				
				</ul>			
			</div>
			<div id="item_card_content_short">
			
			<script type="text/javascript">
				function showImage(index) {
					document.getElementById("item_picture").src="<c:out value="${product.getPictureURL()}"/>"+index+".jpg";
				}
			</script>
			
				<div id="picture_block">
					<figure>
						<img id="item_picture" src="<c:out value="${product.getPictureURL()}"/>1.jpg" alt="" height=450 />					
					</figure>
					<div id="preview_block">
						<figure onclick="showImage(1)">	
							<img src="<c:out value="${product.getPictureURL()}"/>1.jpg"  height=100/>		
						</figure>	
						<figure onclick="showImage(2)">	
							<img src="<c:out value="${product.getPictureURL()}"/>2.jpg" width=100/>		
						</figure>
						<figure onclick="showImage(3)">	
							<img src="<c:out value="${product.getPictureURL()}"/>3.jpg" width=100/>		
						</figure>	
						<figure onclick="showImage(4)">	
							<img src="<c:out value="${product.getPictureURL()}"/>4.jpg" width=100/>		
						</figure>	
					</div>				
				</div>
				<div id="info_block">
					<p id="price"><c:out value="${product.getPrice()}"/>$</p>	
					<div id="button_block">
						<button id="add_to_cart_button" onclick="addItemToCart()">${tr.translate("Add to cart")}</button>
						<button id="fast_order_button" value="">${tr.translate("Order now")}</button>										
					</div>
					<div id="review_block">
						<h2>${tr.translate("Description")}</h2>
						<p id="review_text">${product.getReview(tr)}</p>
					</div>			
					<div id="specs_block">
						<h2>${tr.translate("Specifications")}</h2>	
						<div id="specs_table">	
						<div id="specs_titles">
							<ul>
								<li>${tr.translate("Type")}</li>
								<li>${tr.translate("Manufacturer")}</li>
								<li>${tr.translate("Made in")}</li>
								<li>${tr.translate("Pickup's")}</li>
								<li>${tr.translate("Color")}</li>					
							</ul>	
						</div>
						<div id="values" >
								<ul>
									<li>${tr.translate(product.getType())}</li>
									<li>${product.getManufacturer()}</li>
									<li>${tr.translate(product.getCountry())}</li>
									<li>${tr.translate(product.getPickupsInfo())}</li>
									<li>${tr.translate(product.getColor())}</li>								
								</ul>	
						</div>				
						</div>			
					</div>
				</div>	
			</div>
			<script type="text/javascript">
				function addItemToCart() {		
					window.location.href="/GuitarShop/main?page=productCard&cartAdd=true";
				}
			</script>
			<div id="item_card_content_full">
					<div id="review_block">
						<h2>${tr.translate("Description")}</h2>
						<p id="review_text">${product.getReview(tr)}</p>
					</div>			
					<div id="specs_block">
						<h2>${tr.translate("Specifications")}</h2>	
						<div id="specs_table">	
						<div id="specs_titles">
							<ul>
								<li>${tr.translate("Type")}</li>
								<li>${tr.translate("Manufacturer")}</li>
								<li>${tr.translate("Made in")}</li>
								<li>${tr.translate("Pickup's")}</li>
								<li>${tr.translate("Color")}</li>					
							</ul>	
						</div>
						<div id="values" >
								<ul>
									<li>${tr.translate(product.getType())}</li>
									<li>${product.getManufacturer()}</li>
									<li>${tr.translate(product.getCountry())}</li>
									<li>${tr.translate(product.getPickupsInfo())}</li>
									<li>${tr.translate(product.getColor())}</li>				
								</ul>	
						</div>				
						</div>			
					</div>
		</div>
		<div id="item_card_content_feedback">
			<p>Василий Сидоров: Замечательная гитара. Рекоммендую!</p>
		</div>
		</section>
		
			<c:if test="${initBookmark == 'full'}">
				<script type="text/javascript">
					show_full();
				</script>
			</c:if>
			<c:if test="${initBookmark == 'feedback'}">
				<script type="text/javascript">
					show_feedback();
				</script>
			</c:if>			
			<c:if test="${initBookmark == 'short'}">
				<script type="text/javascript">
					show_short();
				</script>
			</c:if>
			
		<div id="cartAddedHint" >
			<p> Товар успешно добавлен в корзину! </p>
		</div>
		
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
