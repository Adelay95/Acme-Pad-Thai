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
	name="properties" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	
		
	<spring:message code="property.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
<jstl:set var="listIng" value="listIng"/>
	<display:column>
	
	<!-- Enlaces -->
<div>
	<a href="property/edit.do?propertyId=${row.id}">
				<spring:message	code="property.edit" />
	</a>
</div>
</display:column>
<display:column>

<div>
	<a href="ingredient/listByProp.do?propertyId=${row.id}">
				<spring:message	code="property.ingredients" />
	</a>
</div>
</display:column>
</display:table>
<div>
	<a href="property/create.do">
				<spring:message	code="property.create" />
	</a>
</div>
