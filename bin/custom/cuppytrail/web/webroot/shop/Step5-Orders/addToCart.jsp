<%@ include file="head.jsp" %><%
	String pk = request.getParameter( "pk" );
	if (pk==null || pk.equals("")){
		response.sendRedirect("index.jsp");
	}
	
	if (pk.equals("-1")){
		// Clear cart ..
		// Remove cart from the session and create a new one
		cartService.removeSessionCart();
		cart = cartService.getSessionCart();
		
		// Set cart to net
		cart.setNet(Boolean.TRUE);
	
		// Calculate the cart
		cartService.calculateCart(cart);
	}
	else {
		// Add the product to the cart..
		// Retrieve the product
		ProductModel p = modelService.get(PK.parse(pk));
		
		// Add to existing cart entry
		cartService.addToCart(cart, p, 1, p.getUnit());
			
		// Calculate the cart
		cartService.calculateCart(cart);
	}
	response.sendRedirect("index.jsp");	
%>