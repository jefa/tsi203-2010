<%@page import="edu.tsi.Practico.SimpleWS2ServiceLocator"%>
<%@ page import="edu.tsi.Practico.SimpleWS2" %>
<%  
    String resp = null;
    try {
    	SimpleWS2 hello = new SimpleWS2ServiceLocator().getSimpleWS2();
      resp = hello.invoke(request.getParameter("username"));

    } catch (Exception ex) {
        resp = ex.toString();
    }
%>
<h2><font color="black"><%=resp%></font></h2>
