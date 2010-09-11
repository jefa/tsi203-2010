<%@page import="edu.tsi.Practico.SimpleWS1Service_ServiceLocator"%>
<%@ page import="edu.tsi.Practico.SimpleWS1Service_Service,edu.tsi.Practico.SimpleWS1Service_PortType" %>
<%  
    String resp = null;
    try {
    	SimpleWS1Service_PortType hello = new SimpleWS1Service_ServiceLocator().getSimpleWS1ServicePort();
      resp = hello.invoke(request.getParameter("username"));

    } catch (Exception ex) {
        resp = ex.toString();
    }
%>  
<h2><font color="black"><%=resp%></font></h2>
