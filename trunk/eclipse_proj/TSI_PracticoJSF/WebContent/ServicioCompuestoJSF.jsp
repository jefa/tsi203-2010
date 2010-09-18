<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Hola MUNDO JSF</title>
</head>
<body>
<f:view>
	<p></p>
	<h2>Bienvenido !</h2>
	<h:form>
		<p>Ingrese su nombre: <h:inputText id="nombre"
			value="#{ManagedBeanJSF.nombre}" required="true"></h:inputText>
			<h:message for="nombre" style="color:red"/>
			
			 <br>
		<h:commandButton action="#{ManagedBeanJSF.consultaWS1}"
			value="Bienvenido"></h:commandButton> <h:commandButton
			action="#{ManagedBeanJSF.consultaWS2}" value="Welcome"></h:commandButton>
		</p>

		<p>Ingrese el ZipCode: (Ej: 10001 para Manhattan) <h:inputText
			id="zipCode" value="#{ManagedBeanJSF.zip}" required="true">
			<f:validateLength minimum="5" maximum="5"/>
			</h:inputText>
			<h:message for="zipCode" style="color:red"/> <br>
		<h:commandButton action="#{ManagedBeanJSF.consultaWeather}"
			value="Consultar Temperatura"></h:commandButton></p>

		<p>Valor de SID en la session:<h:outputText value="#{ManagedBeanJSF.SID}"></h:outputText> <br>
		<h:commandButton action="#{ManagedBeanJSF.finalizarSesion}"
			value="Finalizar Sesion"></h:commandButton></p>
	</h:form>
</f:view>
</body>
</html>
