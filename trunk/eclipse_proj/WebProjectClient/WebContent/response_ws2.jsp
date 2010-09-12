<%@page import="edu.tsi.Practico.Proxy.SimpleWS2Service_ServiceLocator"%>
<%@page import="edu.tsi.Practico.Proxy.SimpleWS2Service_Service,edu.tsi.Practico.Proxy.SimpleWS2Service_PortType" %>
<%   
    String resp = null;  
    try {
    	edu.tsi.Practico.Proxy.SimpleWS2Service_PortType hello = new edu.tsi.Practico.Proxy.SimpleWS2Service_ServiceLocator().getSimpleWS2ServicePort();
        resp = hello.invoke(request.getParameter("username"));
    	
    } catch (Exception ex) {
        resp = ex.toString();
    } 
%>
<h2><font color="black"><%=resp%></font></h2>
