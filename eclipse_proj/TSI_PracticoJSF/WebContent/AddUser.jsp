<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>Add New User Form</title>
</head>
<body>
<f:view>
		<p>
			<h:message id="errors" for="User_ID" style="color:red"/>
		</p>
	<h:form>
		<h:panelGrid border="1" columns="2">
			<h:outputText value="ID"></h:outputText>
			<h:inputText id="User_ID" value="#{userBean.id}" required="true">
				<f:validateLongRange minimum="1" maximum="500"/>
			</h:inputText>
			<h:outputText value="Name"></h:outputText>
			<h:inputText value="#{userBean.name}"></h:inputText>
			<h:commandButton action="#{userBean.addUser}"
				value="Add Customer"></h:commandButton>
		</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>
