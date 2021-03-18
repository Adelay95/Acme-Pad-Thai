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


<form:form action="${requestURI}" modelAttribute="ingredient">
<!-- Formularios -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="quantities" />
	<form:hidden path="value" />
	
	
	<form:label path="name">
		<spring:message code="ingredient.name" />:
	</form:label>
			<form:input path="name" />
			<form:errors cssClass="error" path="name" />
	<br />
	
	
	<form:label path="description">
		<spring:message code="ingredient.description" />:
	</form:label>
			<form:textarea path="description" rows="8" cols="50"/>
			<form:errors cssClass="error" path="description" />
	<br />
	
	
	<form:label path="picture">
		<spring:message code="ingredient.picture" />:
	</form:label>
			<form:input path="picture" />
			<form:errors cssClass="error" path="picture" />
	<br />
	
	<input type="submit" name="save"
		value="<spring:message code="ingredient.save" />" />&nbsp;
	<jstl:if test="${ingredient.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="ingredient.delete" />"
			onclick="return confirm('<spring:message code="ingredient.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="ingredient.cancel" />"
		onclick="javascript: window.location.replace('ingredient/list.do');" />
			
	
	
    
<!-- Acciones -->
	
	
</form:form>