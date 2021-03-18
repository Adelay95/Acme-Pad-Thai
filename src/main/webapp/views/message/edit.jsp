<%--
 * edit.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>


<form:form action="${requestURI}" modelAttribute="messager">
<!-- Formularios -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sender" />
	<form:hidden path="receiver" />
	<form:hidden path="folder" />
	<form:hidden path="moment" />
	
	<form:label path="subject">
		<spring:message code="message.subject" />:
	</form:label>
			<form:input path="subject"/>
			<form:errors cssClass="error" path="subject" />
	<br />
	
	<form:label path="body">
		<spring:message code="message.body" />:
	</form:label>
			<form:textarea path="body" rows="8" cols="50"/>
			<form:errors cssClass="error" path="body" />
	<br />
	
	<form:label path="priority">
		<spring:message code="message.priority" />:
	</form:label>
	<form:select id="priority" path="priority">
     <form:option value="" label="----" /> 
     <jstl:forEach var="p" items="${priorities}">
     <form:option value="${p}" label="${p}" />
     </jstl:forEach>  
     </form:select>
			<form:errors cssClass="error" path="priority" />
	<br />
	
	
	
<!-- Acciones -->
	<input type="submit" name="save"
		value="<spring:message code="message.save" />" />&nbsp; 
	<input type="button" name="cancel"
		value="<spring:message code="message.cancel" />"
		onclick="javascript: window.location.replace('folder/list.do');" />
</form:form>