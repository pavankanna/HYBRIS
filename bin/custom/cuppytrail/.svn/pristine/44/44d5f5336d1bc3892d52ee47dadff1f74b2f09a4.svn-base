<!-- 
Set the current session locale to English or German, depending on the locale parameter passed in.
Note we must use jsp:forward and not response.sendRedirect as we wish to preserve the session
-->

<%@ include file="head.jsp" %><%
	String locale = request.getParameter( "locale" );
	if (locale.equals("english")){
		i18nService.setCurrentLocale(Locale.ENGLISH);
	}
	else if (locale.equals("german")){
		i18nService.setCurrentLocale(Locale.GERMAN);
	}%>
	<jsp:forward page="index.jsp"/><%
%>