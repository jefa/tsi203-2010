<jsp:useBean id="sampleServicioCompuestoProxyid" scope="session" class="edu.tsi.Practico.SC.ServicioCompuestoProxy" />


<html>
<head><title>Hola MUNDO</title></head>
<body bgcolor="white">
<h2>Ingrese su nombre</h2>
<form method="get">
<input type="text" name="username" size="25">

<p>
<%
String SID = (String)session.getValue("SID");// = request.getParameter("SID"); 
String result = null;%>
Valor de SID en la session: <%=SID%>
<%if(session.getValue("SID") == null || session.getValue("SID").equals("")){
	SID = sampleServicioCompuestoProxyid.inciarSesion();
	//request.setParameter("SID",SID);
	//session = request.getSession(true);
	session.putValue("SID",SID);
}
%>
Valor de SID en la session: <%=SID%>

</p>

<%
    String username = request.getParameter("username");
	String ws1 = request.getParameter("WS1");
	String ws2 = request.getParameter("WS2");
	String fin = request.getParameter("fin");

	String respuesta = "";
	try {
		if(fin != null) {
			respuesta = sampleServicioCompuestoProxyid.finalizarSesion(SID);
		} else if ( username != null && username.length() > 0 ) {
	    	
	    	if(ws1 != null) {
	    		respuesta = sampleServicioCompuestoProxyid.invocarCombinacionWS1(SID);
	    	} else if(ws2!=null){
	    		respuesta = sampleServicioCompuestoProxyid.invocarCombinacionWS2(SID);
	    		session.putValue("SID",null);
	    	}
	    }
	} catch(org.apache.axis.AxisFault af){
		session.putValue("SID",null);
	} catch(Exception e) {
		respuesta = e.getMessage();
	}
	%>Respuesta: <%= respuesta %>
<p>
<input type="submit" name="WS1" value="Invocar WS 1">
<input type="submit" name="WS2" value="Invocar WS2">
<input type="submit" name="fin"" value="Finalizar Sesion">
</form>






</body>
</html>