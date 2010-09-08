<%@page import="edu.tsi.Practico.TestWSService_ServiceLocator"%>
<%@ page import="edu.tsi.Practico.TestWSService_Service,edu.tsi.Practico.TestWSService_PortType" %>
<%  
    String resp = null;
    try {
    	TestWSService_PortType hello = new TestWSService_ServiceLocator().getTestWSServicePort();
      resp = hello.greet(request.getParameter("username"));

    } catch (Exception ex) {
        resp = ex.toString();
    }
%>
<h2><font color="black"><%=resp%></font></h2>














