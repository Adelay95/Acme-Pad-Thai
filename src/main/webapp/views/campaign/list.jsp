<%--
 * list.jsp
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

<!-- Listing grid -->

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="campaigns" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="campaign.momentStarted" var="momentStartedHeader" />
	<display:column property="momentStarted" title="${momentStartedHeader}" sortable="true" />
	
	<spring:message code="campaign.momentFinished" var="momentFinishedHeader" />
	<display:column property="momentFinished" title="${momentFinishedHeader}" sortable="true" />
	
	<spring:message code="campaign.numBanner" var="numBannerHeader" />
	<display:column property="numBanner" title="${numBannerHeader}" sortable="true" />
	
	<spring:message code="campaign.numMaxBanner" var="numMaxBannerHeader" />
	<display:column property="numMaxBanner" title="${numMaxBannerHeader}" sortable="true" />
	
	<spring:message code="campaign.star" var="starHeader" /> 
	<display:column title="${starHeader}" sortable="true" >
		<jstl:if test="${row.star==true}">
		<spring:message code="campaign.yes" var="booleanyes" />
			<jstl:out value="${booleanyes}"/>
		</jstl:if>
		<jstl:if test="${row.star==false}">
		<spring:message code="campaign.no" var="booleanno" />
			<jstl:out value="${booleanno}"/>
		</jstl:if>
	</display:column>
	<spring:message code="campaign.costBanner" var="costBannerHeader" />
	<display:column property="costBanner" title="${costBannerHeader}" sortable="true" />
	<display:column>
	
	<!-- Enlaces -->
<security:authorize access="hasRole('ADMINISTRATOR')">
<div>
	<a href="campaign/administrator/edit.do?campaignId=${row.id}">
				<spring:message	code="campaign.edit" />
	</a>
</div>
</security:authorize>
<security:authorize access="hasRole('SPONSOR')">
<div>
	<a href="campaign/sponsor/edit.do?campaignId=${row.id}">
				<spring:message	code="campaign.edit" />
	</a>
</div>
<div>
	<a href="campaign/sponsor/create-banner.do?campaignId=${row.id}">
				<spring:message	code="campaign.create.banner" />
	</a>
</div>
</security:authorize>
	</display:column>
</display:table>
<security:authorize access="hasRole('SPONSOR')">
<div>
	<a href="campaign/sponsor/create.do">
				<spring:message	code="campaign.create" />
	</a>
</div>
</security:authorize>
