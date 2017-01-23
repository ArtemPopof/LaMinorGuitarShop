<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="styles/global.css"/>
	<link rel="stylesheet" href="styles/buttons.css"/>
	<link rel="stylesheet" href="styles/inputs.css"/>
	<link rel="stylesheet" href="styles/login.css"/>
	<link rel="icon" type="image/png" href="res/favicon.png?v=1"/>
	<link href="https://fonts.googleapis.com/css?family=Lora" rel="stylesheet">
	<link href="https://fonts.googleapis.com/css?family=Open+Sans+Condensed:300" rel="stylesheet"> 
	<title>Sign In</title>
</head>
<body>

	<jsp:include page="header.jsp"/>
	
	<div class="login_div">
		<p class="login_message">${tr.translate("order_auth_message")}</p>
		<form action="j_security_check" method="post" name="loginForm">
			<input type="text" name="j_username" placeholder="${tr.translate("name")}" size="20" class="standard_input"/><br>
			<label><fmt:message key="txtPassword" /></label><br>
			<input type="password" name="j_password" placeholder="${tr.translate("password")}" size="20" class="standard_input"/><br>
			<input type="submit" value="${tr.translate("Log In")}" class="standard_button"/>
		</form>
	</div>

	
	<jsp:include page="footer.jsp"/>

</body>
</html>