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


<form:form action="${requestURI}" modelAttribute="contest">
<!-- Formularios -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="qualifiedRecipes" />
	<form:hidden path="winnerRecipes" />
	<form:hidden path="originalRecipes" />
	
	<jstl:set var="isActive" value="isActive"/>
	<jstl:set var="hasRecipes" value="hasRecipes"/>
	<jstl:set var="isNormal" value="isNormal"/>
	<jstl:choose>
	<jstl:when test="${requestURI.contains(isNormal) || contest.id==0}" >
	<form:label path="title">
		<spring:message code="contest.title" />:
	</form:label>
			<form:input path="title" />
			<form:errors cssClass="error" path="title" />
	<br />
	<form:label path="openingTime">
		<spring:message code="contest.openingTime" />:
	</form:label>
			<form:input path="openingTime" />
			<form:errors cssClass="error" path="openingTime" />
	<br />
	
	<form:label path="closingTime">
		<spring:message code="contest.closingTime" />:
	</form:label>
			<form:input path="closingTime" />
			<form:errors cssClass="error" path="closingTime" />
		
	<br />
	</jstl:when>
	
	<jstl:when test="${requestURI.contains(isActive) }" >
	<form:hidden path="title" />
	<form:hidden path="openingTime" />
	<form:label path="title">
		<spring:message code="contest.title" />:
	</form:label>
			<form:input disabled="true" path="title" />
			<form:errors cssClass="error" path="title" />
	<br />
	<form:label path="openingTime">
		<spring:message code="contest.openingTime" />:
	</form:label>
			<form:input disabled="true" path="openingTime" />
			<form:errors cssClass="error" path="openingTime" />
	<br />
	
	<form:label path="closingTime">
		<spring:message code="contest.closingTime" />:
	</form:label>
			<form:input path="closingTime" />
			<form:errors cssClass="error" path="closingTime" />
		
	<br />
	</jstl:when>
	<jstl:when test="${requestURI.contains(hasRecipes)}" >
	<form:hidden path="title" />
	<form:hidden path="openingTime" />
	<form:hidden path="closingTime" />
	<form:label path="title">
		<spring:message code="contest.title" />:
	</form:label>
			<form:input disabled="true" path="title" />
			<form:errors cssClass="error" path="title" />
	<br />
	<form:label path="openingTime">
		<spring:message code="contest.openingTime" />:
	</form:label>
			<form:input disabled="true" path="openingTime" />
			<form:errors cssClass="error" path="openingTime" />
	<br />
	
	<form:label path="closingTime">
		<spring:message code="contest.closingTime" />:
	</form:label>
			<form:input disabled="true" path="closingTime" />
			<form:errors cssClass="error" path="closingTime" />
		
	<br />
	</jstl:when>
	</jstl:choose>

	
<!-- Acciones -->
	<input type="submit" name="save"
		value="<spring:message code="contest.save" />" />&nbsp; 
	<jstl:if test="${contest.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="contest.delete" />"
			onclick="return confirm('<spring:message code="contest.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="contest.cancel" />"
		onclick="javascript: window.location.replace('contest/list.do');"" />
</form:form>