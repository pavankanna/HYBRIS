<!-- 
The main home page.  
If the user is not logged in redirect to login.jsp 

Offer a logout link 
-->

<%@ include file="head.jsp" %><%

	/** If not yet logged in, redirect to login */
	if (userService.getCurrentUser().getUid().equals("anonymous")){
		response.sendRedirect("login.jsp"); 
	}%>

	Welcome <%=userService.getCurrentUser().getUid()%><br><br>
	
	<h3>Stadium Toys for sale:</h3>
	<ul><%
		/** Get a listing of all the products */
		List<StadiumProductDataTO> stadiumsData = (List<StadiumProductDataTO>)stadiumFacade.getAllStadiumProducts();	
		for( StadiumProductDataTO p : stadiumsData ){
			/** List the products with description, price and AddToCart link */ %>
			<li>
				<%=p.getName()%> - 
				<%=p.getDescription()%> @ 
				<%= ((Currency) modelService.getSource(i18nService.getCurrentCurrency())).format( p.getPrice() ) %>
			</li><%
		}%>
	</ul>
	
	<a href="logout.jsp">[Logout]</a>

<%@ include file="tail.jsp" %>