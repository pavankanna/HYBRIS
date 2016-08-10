<!-- 
The main home page.  
If the user is not logged in redirect to login.jsp 
Offer a logout link 
-->

<%@ include file="head.jsp" %><%

if (userService.getCurrentUser().getUid().equals("anonymous")){
	response.sendRedirect("login.jsp"); 
}%>

You are logged in as <%=userService.getCurrentUser().getUid()%><br>

<a href="logout.jsp">Logout</a>

<%@ include file="tail.jsp" %>