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
				<a href="addToCart.jsp?pk=<%=p.getPk()%>">[Add to cart]</a>
			</li><%
		}%>
	</ul>
	<h3>Cart Contents</h3>
	<ul><%
		/** List the contents of the cart */ 
		for (final AbstractOrderEntryModel aoem : cart.getEntries()){%>
			<li><%=aoem.getQuantity()%> x <%=aoem.getProduct().getName()%> - (<%=aoem.getProduct().getDescription()%></li><%
		}%>
	</ul>
	<h3>Total Price</h3>
	<ul><li><%= ((Currency) modelService.getSource(i18nService.getCurrentCurrency())).format( cart.getTotalPrice().doubleValue() ) %></li></ul>
	
	<a href="addToCart.jsp?pk=-1">[Clear Cart]</a> 
	<a href="placeOrder.jsp">[Order]</a>
	<a href="logout.jsp">[Logout]</a>

<%@ include file="tail.jsp" %>