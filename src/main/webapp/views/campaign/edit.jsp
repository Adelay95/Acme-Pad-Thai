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


<form:form action="${requestURI}" modelAttribute="campaign">
<!-- Formularios -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="sponsor" />
	<form:hidden path="banners" />
	<!-- FECHA -->
	<form:label path="momentStarted">
		<spring:message code="campaign.momentStarted" />:
	</form:label>
		<security:authorize access="hasRole('SPONSOR')">
			<form:input path="momentStarted" />
			<form:errors cssClass="error" path="momentStarted" />
		</security:authorize>
		<security:authorize access="hasRole('ADMINISTRATOR')">
		<form:hidden path="momentStarted" />
			<form:input path="momentStarted" disabled="true"/>
			<form:errors cssClass="error" path="momentStarted" />
		</security:authorize>
	<br />
	<!-- FECHA -->
	<form:label path="momentFinished">
		<spring:message code="campaign.momentFinished" />:
	</form:label>
		<security:authorize access="hasRole('SPONSOR')">
			<form:input path="momentFinished" />
			<form:errors cssClass="error" path="momentFinished" />
		</security:authorize>
		<security:authorize access="hasRole('ADMINISTRATOR')">
		<form:hidden path="momentFinished" />
			<form:input path="momentFinished" disabled="true"/>
			<form:errors cssClass="error" path="momentFinished" />
		</security:authorize>
	<br />
	
	<form:label path="numBanner">
		<spring:message code="campaign.numBanner" />:
	</form:label>
		
			<form:hidden path="numBanner" />
            <form:input path="numBanner" disabled="true"/>
			<form:errors cssClass="error" path="numBanner" />
	<br />
	
	<form:label path="numMaxBanner">
		<spring:message code="campaign.numMaxBanner" />:
	</form:label>
		<security:authorize access="hasRole('SPONSOR')">
			<form:input path="numMaxBanner" />
			<form:errors cssClass="error" path="numMaxBanner" />
		</security:authorize>
		<security:authorize access="hasRole('ADMINISTRATOR')">
		<form:hidden path="numMaxBanner" />
			<form:input path="numMaxBanner" disabled="true"/>
			<form:errors cssClass="error" path="numMaxBanner" />
		</security:authorize>
	<br />
<!-- POR HACER: BOOLEAN -->
	<form:label path="star">
		<spring:message code="campaign.star" />:
	</form:label>
		<security:authorize access="hasRole('SPONSOR')">
		<form:select id="star" path="star">
			<spring:message code="campaign.yes" var="booleanyes" />
			<form:option value="true" label="${booleanyes}" />	
			<spring:message code="campaign.no" var="booleanno" />
			<form:option value="false" label="${booleanno}" />		
		</form:select>
		<form:errors cssClass="error" path="star" />
		</security:authorize>
		<security:authorize access="hasRole('ADMINISTRATOR')">
		<form:hidden path="star" />
			<form:select id="star" path="star" disabled="true">
			<spring:message code="campaign.yes" var="booleanyes" />
			<form:option value="true" label="${booleanyes}" />	
			<spring:message code="campaign.no" var="booleanno" />
			<form:option value="false" label="${booleanno}" />		
		</form:select>
		<form:errors cssClass="error" path="star" />
		</security:authorize>
	<br />
	<security:authorize access="hasRole('SPONSOR')">
	<form:label path="costBanner">
		<spring:message code="campaign.costBanner" />:
	</form:label>
							<!-- <form:input path="costBanner" disabled="true"/> -->
	<form:hidden path="costBanner" />
	<form:input path="costBanner" disabled="true"/>
	<form:errors cssClass="error" path="costBanner" />
	</security:authorize>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	<form:label path="costBanner">
		<spring:message code="campaign.costBanner" />:
	</form:label>
	<form:input path="costBanner" />
	<form:errors cssClass="error" path="costBanner" />
	</security:authorize>
	<br />

<!-- Acciones -->
	<input type="submit" name="save"
		value="<spring:message code="campaign.save" />" />&nbsp; 
		
	<security:authorize access="hasRole('SPONSOR')">
	<jstl:if test="${campaign.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="campaign.delete" />"
			onclick="return confirm('<spring:message code="campaign.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="campaign.cancel" />"
		onclick="javascript:window.location.replace('campaign/sponsor/list.do');" />
	</security:authorize>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<input type="button" name="cancel"
		value="<spring:message code="campaign.cancel" />"
		onclick="javascript: window.location.replace('campaign/administrator/list.do');" />
	</security:authorize>
	
</form:form>