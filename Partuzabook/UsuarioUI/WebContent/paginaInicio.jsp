<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>paginaInicio</title>
</head>
<body>
<f:view>
	<a4j:form>
		<rich:panel header="panel" style="width: 315px">
			<h:outputText value="Your name: " />
			<h:inputText value="#{paginaInicioMB.name}">
				<f:validateLength minimum="1" maximum="30" />
			</h:inputText>

			<a4j:commandButton value="Get greeting" reRender="greeting" />

			<h:panelGroup id="greeting">
				<h:outputText value="Hello, " rendered="#{not empty paginaInicioMB.name}" />
				<h:outputText value="#{paginaInicioMB.name}" />
				<h:outputText value="!" rendered="#{not empty paginaInicioMB.name}" />
			</h:panelGroup>
		</rich:panel>
	</a4j:form>
</f:view>
</body>
</html>