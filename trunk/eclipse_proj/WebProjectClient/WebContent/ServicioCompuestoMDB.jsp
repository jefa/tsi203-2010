<%@page import="edu.tsi.Practico.MDB.*" %>
<html>
<head><title>Hola MUNDO</title></head>
<body bgcolor="white">

<form>

<h2>Bienvenido !</h2>

<%
String ws1 = "Bienvenido";
String ws2 = "Welcome";
String ws3 = "Consultar Temperatura";
String fin = "Finalizar Sesion";

String SID = (String)session.getAttribute("SID");// = request.getParameter("SID"); 
%>


<p>
Ingrese su nombre: 
<input type="text" name="username" size="25">
<br>
<input type="submit" name="action" value="<%=ws1%>">
<input type="submit" name="action" value="<%=ws2%>">
</p>

<p>
Ingrese el ZipCode: (Ej: 10001 para Manhattan)
<input type="text" name="zipCode" size="25">
<br>
<input type="submit" name="action" value="<%=ws3%>">
</p>

<p>
Valor de SID en la session: <%=SID%>
<br>
<input type="submit" name="action" value="<%=fin%>">
</p>


<%

if(session.getAttribute("SID") == null || session.getAttribute("SID").equals("")){
	//SID = sampleServicioCompuestoProxyid.inciarSesion();
	SID = new MDBClient().iniciarSesion();
	//request.setParameter("SID",SID);
	//session = request.getSession(true);
	session.setAttribute("SID",SID);
}
%>
<%
    String username = request.getParameter("username");
	String zipCode = request.getParameter("zipCode");
	String action =	request.getParameter("action");

	String error = "";
	String respuesta = "";
	try {
		if(action.equals(fin)) {
			respuesta = new MDBClient().finalizarSesion(SID);
			session.removeAttribute("SID");
		} else if (action.equals(ws1)) {
			if ( username != null && username.length() > 0 ) {
	    		respuesta = new MDBClient().invocarWS1(SID, username);				
			} else {
		    	error = "Debe ingresar su nombre en el cuadro de texto";				
			}
		} else if (action.equals(ws2)) {
			if ( username != null && username.length() > 0 ) {
	    		respuesta = new MDBClient().invocarWS2(SID, username);				
			} else {
		    	error = "Debe ingresar su nombre en el cuadro de texto";				
			}
		} else if (action.equals(ws3)) {
			if (zipCode != null && zipCode.length() > 0 && username != null && username.length() > 0 ) {
	    		respuesta = new MDBClient().invocarWS3(SID, username, zipCode);				
			} else {
		    	error = "Debe ingresar su nombre y su zipCode en los cuadros de texto";								
			}
		}				
	} catch(Exception e) {
		respuesta = e.getMessage();
	}
	%> 
	
	<%if (error != null && error != "" ) {%>
		<h3><%= error %></h3>		
	<%} %>
	
	
	<%if (respuesta != null) {%>
		<h3><%= respuesta %></h3>	
	<%} %>

</form>
</body>
</html>