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


<form:form action="${requestURI}" modelAttribute="actor">
<!-- Formularios -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="userAccount.authorities" />
	<form:hidden path="messageSent" />
	<form:hidden path="messageReceived" />
	<form:hidden path="folders" />
	<form:hidden path="userAccount" />
	<form:hidden path="userAccount.id" />
	<form:hidden path="userAccount.version" />
	<form:hidden path="socialIdentities" />
	<jstl:set var="Usuario" value="user"/>
	<jstl:set var="Nutricionista" value="nutritionist"/>
	<jstl:set var="Administrador" value="administrator"/>
	<jstl:set var="Sponsor" value="sponsor"/>
	<jstl:set var="Cocinero" value="cook"/>
	<jstl:set var="Crear" value="create" />
	<jstl:if test="${requestURI.contains(Usuario)}">
	<form:hidden path="numOriginalRecipes" />
	<form:hidden path="followed" />
	<form:hidden path="comments"/>
	<form:hidden path="tastes"/>
	<form:hidden path="recipes" />
	</jstl:if>
	<jstl:if test="${requestURI.contains(Nutricionista)}">
	<form:hidden path="followed" />
	<form:hidden path="comments"/>
	<form:hidden path="tastes"/>
	<form:hidden path="curricula" />
	</jstl:if>
	<jstl:if test="${requestURI.contains(Administrador)}">
	<form:hidden path="masterClass" />
	</jstl:if>
	<jstl:if test="${requestURI.contains(Sponsor)}">
	<form:hidden path="monthlyBills" />
	<form:hidden path="campaigns" />
	<form:hidden path="active" />
	</jstl:if>
	<jstl:if test="${requestURI.contains(Cocinero)}">
	<form:hidden path="masterClass" />
	</jstl:if>
	
	<form:label path="name">
		<spring:message code="actor.name" />:
	</form:label>
			<form:input path="name" />
			<form:errors cssClass="error" path="name" />
	<br />
	
	
	<form:label path="surname">
		<spring:message code="actor.surname" />:
	</form:label>
			<form:input path="surname" />
			<form:errors cssClass="error" path="surname" />
	<br />
	
	<form:label path="emailAdress">
		<spring:message code="actor.emailAdress" />:
	</form:label>
			<form:input path="emailAdress" />
			<form:errors cssClass="error" path="emailAdress" />
	<br />
	
	<form:label path="postalAdress">
		<spring:message code="actor.postalAdress" />:
	</form:label>
			<form:input path="postalAdress" />
			<form:errors cssClass="error" path="postalAdress" />
	<br />
	
	<form:label path="phoneNumber">
		<spring:message code="actor.phoneNumber" />:
	</form:label>
			<form:input path="phoneNumber" />
			<form:errors cssClass="error" path="phoneNumber" />
	<br />
	
	
	<security:authorize access="isAnonymous()">
	<form:label path="userAccount.username">
		<spring:message code="actor.username" />:
	</form:label>
			<form:input path="userAccount.username" />
			<form:errors cssClass="error" path="userAccount.username" />
	<br />
	
	<form:label path="userAccount.password">
		<spring:message code="actor.password" />:
	</form:label>
			<form:input path="userAccount.password" type="password" />
			<form:errors cssClass="error" path="userAccount.username" />
	<br />
	</security:authorize>
	<security:authorize access="hasRole('ADMINISTRATOR')">
	<jstl:if test="${requestURI.contains(Cocinero)}">
	<form:label path="userAccount.username">
		<spring:message code="actor.username" />:
	</form:label>
			<form:input path="userAccount.username" />
			<form:errors cssClass="error" path="userAccount.username" />
	<br />
	
	<form:label path="userAccount.password">
		<spring:message code="actor.password" />:
	</form:label>
			<form:input path="userAccount.password" type="password" />
			<form:errors cssClass="error" path="userAccount.username" />
	<br />
	</jstl:if>
	</security:authorize>
	
	
	<jstl:if test="${requestURI.contains(Sponsor)}">
	<form:label path="companyName">
		<spring:message code="actor.companyName" />:
	</form:label>
			<form:input path="companyName" />
			<form:errors cssClass="error" path="companyName" />
	<br />
	<fieldset name="Credit Card" >
	<legend><b><spring:message code="actor.creditCard" /></b></legend>
	<form:label path="creditCard.holderName">
		<spring:message code="actor.holderName" />:
	</form:label>
			<form:input path="creditCard.holderName" />
			<form:errors cssClass="error" path="creditCard.holderName" />
	<br />
	
	<form:label path="creditCard.brandName">
		<spring:message code="actor.brandName" />:
	</form:label>
			<form:input path="creditCard.brandName" />
			<form:errors cssClass="error" path="creditCard.brandName" />
	<br />
	
	<form:label path="creditCard.number">
		<spring:message code="actor.number" />:
	</form:label>
			<form:input path="creditCard.number" />
			<form:errors cssClass="error" path="creditCard.number" />
	<br />
	
	<form:label path="creditCard.expirationMonth">
		<spring:message code="actor.expirationMonth" />:
	</form:label>
			<form:input path="creditCard.expirationMonth" />
			<form:errors cssClass="error" path="creditCard.expirationMonth" />
	<br />
	
	<form:label path="creditCard.expirationYear">
		<spring:message code="actor.expirationYear" />:
	</form:label>
			<form:input path="creditCard.expirationYear" />
			<form:errors cssClass="error" path="creditCard.expirationYear" />
	<br />
	
	<form:label path="creditCard.cVV">
		<spring:message code="actor.cVV" />:
	</form:label>
			<form:input path="creditCard.cVV" />
			<form:errors cssClass="error" path="creditCard.cVV" />
	<br />
	</fieldset>
	
	
	</jstl:if>
	
	
<!-- Acciones -->
	<input type="submit" name="save"
		value="<spring:message code="actor.save" />" />&nbsp; 
		
	
	<input type="button" name="cancel"
		value="<spring:message code="actor.cancel" />"
		onclick="javascript: window.location.replace('');" />
	
</form:form>