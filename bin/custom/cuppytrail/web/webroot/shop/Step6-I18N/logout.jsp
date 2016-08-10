<!-- When sent this page, the browser will close the current user session - i.e. Log the user out.-->

<%@ include file="head.jsp" %><%

sessionService.closeCurrentSession();
response.sendRedirect("index.jsp");%>

<%@ include file="tail.jsp" %>