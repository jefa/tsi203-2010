<jsp:useBean id="sampleServicioCompuestoProxyid" scope="session" class="edu.tsi.Practico.SC.ServicioCompuestoProxy" />


<html>
<head><title>Hola MUNDO</title></head>
<body bgcolor="white">
<h2>Ingrese su nombre</h2>
<form>
<input type="text" name="username" size="25">

<p>
<%
String ws1 = "Invocar WS1";
String ws2 = "Invocar WS2";
String fin = "Finalizar Sesion";

String SID = (String)session.getAttribute("SID");// = request.getParameter("SID"); 

if(session.getAttribute("SID") == null || session.getAttribute("SID").equals("")){
	SID = sampleServicioCompuestoProxyid.inciarSesion();
	//request.setParameter("SID",SID);
	//session = request.getSession(true);
	session.setAttribute("SID",SID);
}
%>
Valor de SID en la session: <%=SID%>

</p>

<%
    String username = request.getParameter("username");
	String action =	request.getParameter("action");

	String respuesta = "";
	try {
		if(action.equals(fin)) {
			respuesta = sampleServicioCompuestoProxyid.finalizarSesion(SID);
			session.removeAttribute("SID");
		} else if ( username != null && username.length() > 0 ) {
	    	
	    	if(action.equals(ws1)) {
	    		respuesta = sampleServicioCompuestoProxyid.invocarCombinacionWS1(SID + "_" + username);
	    	} else if( action.equals(ws2)){
	    		respuesta = sampleServicioCompuestoProxyid.invocarCombinacionWS2(SID + "_" + username);
	    		
	    	}
	    }
	} catch(Exception e) {
		respuesta = e.getMessage();
	}
	%>Respuesta: <%= respuesta %>
<p>
<input type="submit" name="action" value="<%=ws1%>">
<input type="submit" name="action" value="<%=ws2%>">
<input type="submit" name="action" value="<%=fin%>">
</form>
</body>
</html>