<!-- 
The header file for the cuppy trail shop
Its purpose is to provide instantiations of all the services needed by the logic within the body
 -->
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="de.hybris.platform.core.Registry"%>
<%@page import="org.apache.commons.lang.StringUtils"%>

<%@page import="de.hybris.platform.servicelayer.user.UserService"%>
<%@page import="de.hybris.platform.servicelayer.security.auth.AuthenticationService"%>
<%@page import="de.hybris.platform.servicelayer.session.SessionService"%>

<%@page import="de.hybris.platform.servicelayer.security.auth.InvalidCredentialsException"%>
<html>
	<link rel="stylesheet" href="./css/hybris_frontpage.css" type="text/css">
	<title>Cuppy Trail JSP Shop</title>
	<body>
		<h1>Cuppy Trail JSP Shop</h1><%
		// Get Spring's Application Context from the hybris Registry 
		final ApplicationContext applicationContext = Registry.getApplicationContext();
		
		// Get the service(s) we need from the Application Context both from xml and component scanning
		final AuthenticationService authenticationService = (AuthenticationService)applicationContext.getBean("authenticationService");
		final SessionService sessionService = (SessionService)applicationContext.getBean("sessionService");
		final UserService userService = (UserService) applicationContext.getBean("userService");%>