<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="icon" type="image/png" href="res/favicon.png?v=1"/>
<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet"> 
<link rel="stylesheet" href="styles/buttons.css"/>
<link rel="stylesheet" href="styles/inputs.css"/>
<link rel="stylesheet" href="styles/global.css"/>
<link rel="stylesheet" href="styles/order.css"/>
<title>Order details</title>
</head>
<body>

	<jsp:include page="header.jsp"/>

	<div class="order_container">
			<div class="account_page_blank">
			</div>
			<div class="order_div">
				<p class="order_label">${tr.translate("order_label")}</p>
				<div class="map_and_list_div">
					<div class="map">
						<div id="map" style="width: 400px; height: 400px;"></div>
						<script src="https://maps.googleapis.com/maps/api/js?key= AIzaSyA695yPetTCLvaKLl3ahYZERSy6Ks8dwqo&callback=initMap" async defer></script>
					</div>
					<div class="market_list_div">
	  						<p id="where_take_order_label">${tr.translate("where_take_order")}</p>
	   						<p><input id="0"type="radio" name="spot" value="a1" checked=true onclick="showOnMap(0)">${tr.translate("torshkovskaya15")}<Br>
						  	<input id="1" type="radio" name="spot" value="a2" onclick="showOnMap(1)">${tr.translate("popova3")}<Br>
						  	<input id="2" type="radio" name="spot" value="a3" onclick="showOnMap(2)">${tr.translate("nevskiy12")}<Br> 
  	
  							<div class="delivery_div">
		  					<p id="deliver_label">${tr.translate("manual_deliver_select")}</p>
		   					<p><input class="standard_input" id="manual_address" name="manual_delivery" placeholder="${tr.translate("address")}"><Br>
							<button id="order_button" type="submit" value="Оформить" class="standard_button" onclick="doOrder()"/>${tr.translate("proceed_order")}</button>
						</div>
					</div>
				</div>
			</div>
			<div class="account_page_blank">
			</div>
	</div>	
	
	<script>
		map;
	    function initMap() {
	      map = new google.maps.Map(document.getElementById('map'), {
	        center: {lat: 59.991410, lng: 30.320837},
	        zoom: 10
	      });
	      
	      marker = new google.maps.Marker({
	    		position: {lat: 59.991410, lng: 30.320837},
	    		map: map,
	    		title:"OBSHAGA 8!" 
	    	});
	      
	      marker2 = new google.maps.Marker({
	    	  position: {lat: 59.972451,lng: 30.323204},
	    	  map: map,
	    	  title: "СПБГЭТУ ЛЭТИ"
	      });
	      
	     marker3 = new google.maps.Marker({
	    	  position: {lat:59.936827, lng: 30.315696},
	    	  map: map,
	    	  title: "Консульство Франции"
	      });
	      
	      var contentString = '<div id="content">Ул. Торжковская 15</div>';
	      var contentString2 = '<div id="content2">Ул Профессора Попова 3</div>'
		  var contentString3 = '<div id="content2">Невский пр. 12</div>'

	      var infowindow = new google.maps.InfoWindow({
	      	content: contentString
	      });
	      
	      var infowindow2 = new google.maps.InfoWindow({
	    	  content: contentString2
	      });
	      
	      var infowindow3 = new google.maps.InfoWindow({
	    	  content: contentString3
	      });
	      
	      google.maps.event.addListener(marker, 'click', function() {
	      	infowindow.open(map,marker);
	      });
	      
	      google.maps.event.addListener(marker2, 'click', function() {
	    	  infowindow2.open(map, marker2);
	      });
	      
	      google.maps.event.addListener(marker3, 'click', function() {
	    	  infowindow3.open(map, marker3);
	      });
	      
	      map.zoom = 15;
	    }
	    
	    function showOnMap(index) {
	    	
	    	if (index == 0) {
	    		map.panTo(marker.getPosition());
	    	} else if (index == 1) {
	    		map.panTo(marker2.getPosition());
	    	} else if (index == 2) {
	    		map.panTo(marker3.getPosition());
	    	}
	    }
	    
	    function doOrder() {
	    	if (document.getElementById('manual_address').value.trim() != '') {
				window.location.href=("/GuitarShop/order?action=orderManual&address="+document.getElementById('manual_address').value.trim());
	    	} else {
	    		var index = document.querySelector('input[name=spot]:checked').id;
	    		var address;
	    		
	    		if (index == 0) {
	    			address = "torshkovkaya15";
	    		} else if (index == 1) {
	    			address = "popova3";
	    		} else {
	    			address = "nevskiy12"
	    		}
				window.location.href=("/GuitarShop/order?action=orderExist&address="+address);
	    	}
	    }
	 
	    
	</script>

	<jsp:include page="footer.jsp"/>
</body>
</html>