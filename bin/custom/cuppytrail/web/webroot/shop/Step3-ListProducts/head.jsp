<!-- 
The header file for the cuppy trail shop
Its purpose is to include all necessary interfaces and instantiate the classes needed in the jsps
 -->

<!-- Cuppy Trail specific classes-->
<%@page import="de.hybris.platform.cuppytrail.web.facades.IStadiumFacade"%>
<%@page import="de.hybris.platform.cuppytrail.web.data.StadiumProductDataTO"%>

<!-- Models -->
<%@page import="de.hybris.platform.core.model.product.ProductModel"%>
<%@page import="de.hybris.platform.core.model.order.AbstractOrderEntryModel"%>
<%@page import="de.hybris.platform.core.model.order.CartModel"%>
<%@page import="de.hybris.platform.core.model.order.OrderModel"%>

<!-- Services -->
<%@page import="de.hybris.platform.cuppytrail.services.IStadiumService"%>
<%@page import="de.hybris.platform.servicelayer.user.UserService"%>
<%@page import="de.hybris.platform.servicelayer.security.auth.AuthenticationService"%>
<%@page import="de.hybris.platform.servicelayer.session.SessionService"%>
<%@page import="de.hybris.platform.catalog.CatalogService"%>
<%@page import="de.hybris.platform.servicelayer.model.ModelService"%>
<%@page import="de.hybris.platform.servicelayer.i18n.I18NService"%>

<!-- Spring/hybris Utility classes -->
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="de.hybris.platform.core.Registry"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="de.hybris.platform.servicelayer.security.auth.InvalidCredentialsException"%>
<%@page import="java.util.List"%>

<!-- TO ASK IF WE CAN REMOVE JALO CLASSES HERE... -->
<%@page import="de.hybris.platform.jalo.c2l.Currency"%>
<%@page import="de.hybris.platform.core.PK"%>

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
		final UserService userService = (UserService) applicationContext.getBean("userService");
		final CatalogService catalogService = (CatalogService)applicationContext.getBean("catalogService");	
		final ModelService modelService = (ModelService) applicationContext.getBean("modelService");
		final I18NService i18nService = (I18NService) applicationContext.getBean("i18nService");
		
		// Get the StadiumFacade
		final IStadiumFacade stadiumFacade = (IStadiumFacade) applicationContext.getBean("stadiumFacade");
		
			
		/** Set our catalog so we can see our products */
		catalogService.addSessionCatalogVersion("Default", "Online");
		%>