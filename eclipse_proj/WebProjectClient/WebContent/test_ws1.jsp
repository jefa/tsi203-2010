<html>
<head><title>Hello</title></head>
<body bgcolor="white">
<img src="duke.waving.gif"> 
<h2>Hello, my name is Duke. What's yours?</h2>
<form method="get">
<input type="text" name="username" size="25">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>

<%
    String username = request.getParameter("username");
    if ( username != null && username.length() > 0 ) {
%>
    <%@include file="response_ws1.jsp" %>
<%
    }
%>
</body>
</html>
