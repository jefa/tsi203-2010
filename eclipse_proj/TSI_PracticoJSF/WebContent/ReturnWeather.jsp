<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Respuesta Weather</title>
</head>
<body>
<f:view>
	<h:form>
		<h:outputText value="#{ManagedBeanJSF.respuesta}.">
		</h:outputText>
		<br><h:outputLink value="ServicioCompuestoJSF.jsf"><h:outputText value="Volver"/></h:outputLink>
	</h:form>
</f:view>
</body>
</html>
