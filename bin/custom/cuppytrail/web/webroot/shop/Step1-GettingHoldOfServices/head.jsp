<!-- 
The header file for the cuppy trail shop
Its purpose is to provide instantiations of all the services needed by the logic within the body
 -->
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="de.hybris.platform.core.Registry"%>
<%@page import="de.hybris.platform.cuppytrail.web.facades.IStadiumFacade"%>

<html>
	<link rel="stylesheet" href="./css/hybris_frontpage.css" type="text/css">
	<title>Cuppy Trail JSP Shop</title>
	<body>
		<h1>Cuppy Trail JSP Shop</h1><%
		// Get Spring's Application Context from the hybris Registry 
		final ApplicationContext applicationContext = Registry.getApplicationContext();
		
		// Get the service(s) we need from the Application Context
		// You will not find an XML definition of stadiumFacade in the Application Context cuppytrail\resources\cuppytrail-spring.xml
		// because we are using component scanning instead (with the tag <context:component-scan base-package="de.hybris.platform.cuppytrail"/>)
		// Spring scans and locates all classes in the package "de.hybris.platform.cuppytrail" annotated as Spring Beans including

		final IStadiumFacade stadiumFacade = (IStadiumFacade) applicationContext.getBean("stadiumFacade");%>
		
		Bean found via Spring Application Context: <%=stadiumFacade.getClass()%>