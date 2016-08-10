<!-- This page is responsible for logging a user in -->
<%@ include file="head.jsp" %><%

if( StringUtils.equals( userService.getCurrentUser().getUid(), "anonymous" ) ){
	/** If the user is currently not logged in and therefore "anonymous"  ask for their credentials */
	String userStr = request.getParameter("login");
	String pwdStr = request.getParameter("pwd");
	
	if (userStr == null || pwdStr == null ){
		/** If we do not have user or password yet.. */ %>
		<form method="post" action="login.jsp">
				<table>
					<tr>
						<td>Username:</td>
						<td><input name="login"></td>
					</tr>
					<tr>
						<td>Password:</td>
						<td><input name="pwd" type="password"></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="submit" value="Login" name="login"> (You can use admin, nimda)</td> 
					</tr>
				</table>		
		</form><%
	} 
	else
	{	
		/**  If we have credentials, check if they are valid.. */
		try {
			authenticationService.login(userStr, pwdStr);
			/**  and if no exception was thrown we are now logged in.. */
			response.sendRedirect("index.jsp");
		}
		catch (InvalidCredentialsException e)  {
			/**  If not valid we get an exception, and redirect them to the login page */
			response.sendRedirect("login.jsp");
		}
	}
} 
else 
{
	/**  If the current user is not anonymous, we are already logged in so go to the index page */
	response.sendRedirect("index.jsp");
}%>
<%@ include file="tail.jsp" %>