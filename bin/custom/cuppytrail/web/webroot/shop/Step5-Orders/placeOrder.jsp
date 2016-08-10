<%@ include file="head.jsp" %>

Thank you <%=userService.getCurrentUser().getUid()%><br>
Your order has been registered<br><br>
	
<h3>Order</h3><%
	// set parameters for order
	String code = "Training-" + System.currentTimeMillis();
						
	// create a new order with the order service, set the code, and set "net"
	OrderModel order = orderService.placeOrder( cart, cart.getDeliveryAddress(), cart.getPaymentAddress(), cart.getPaymentInfo());
	order.setCode(code);
	order.setNet(Boolean.TRUE);
	
	// recalculate totals
	orderService.calculateOrder(order);
			
	// remove cart from session
	cartService.removeSessionCart();
		
	// display order
%>
<ul><li>Order number: <%= order.getCode() %></li></ul>

<h3>Order entries:</h3>
<ul><%
	// iterate through all order entries
	for( AbstractOrderEntryModel oe : order.getEntries() ){
		/** display quantity, unit and name of product in a order entry */ %>
		<li>
			<%=oe.getQuantity()%> x 
			<%=oe.getUnit().getName()%> 
			<%=oe.getProduct().getName() %> =
			<%= ((Currency) modelService.getSource(i18nService.getCurrentCurrency())).format( oe.getTotalPrice() ) %>
				
		</li><%
	}%>
</ul>
<h3>Total Price</h3>
<ul>
	<li><%= ((Currency) modelService.getSource(i18nService.getCurrentCurrency())).format( order.getTotalPrice().doubleValue() )  %></li>
</ul>

<a href="index.jsp">[Home]</a>
<a href="logout.jsp">[Logout]</a>
<%@ include file="tail.jsp" %>
