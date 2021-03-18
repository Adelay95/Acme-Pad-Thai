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
	name="socialIdentities" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="socialIdentity.nick" var="nickHeader" />
	<display:column property="nick" title="${nickHeader}" sortable="true" />
	
	<spring:message code="socialIdentity.socialNetwork" var="socialNetworkHeader" />
	<display:column property="socialNetwork" title="${socialNetworkHeader}" sortable="true" />
	
	<spring:message code="socialIdentity.link" var="linkHeader" />
	<display:column property="link" title="${linkHeader}" sortable="true" />
	
	<spring:message code="socialIdentity.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="false" />
    
    <display:column>
	<!-- Enlaces -->
<security:authorize access="isAuthenticated()">
<div>
	<a href="social-identity/edit.do?socialIdentityId=${row.id}">
				<spring:message	code="socialIdentity.edit" />
	</a>
</div>
</security:authorize>
	</display:column>
</display:table>
<div>
	<a href="social-identity/create.do">
				<spring:message	code="socialIdentity.create" />
	</a>
</div>
