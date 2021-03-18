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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="endorser" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="endorser.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="false" />
	
	<spring:message code="endorser.url" var="urlHeader" />
	<display:column property="url" title="${urlHeader}" sortable="true" />

	<display:column>
	<!-- Enlaces -->
<div>
	<a href="endorser/edit.do?endorserId=${row.id}">
				<spring:message	code="endorser.edit" />
	</a>
</div>
	</display:column>
</display:table>
<div>
	<a href="endorser/create.do?curriculaId=${curriculaId}">
				<spring:message	code="endorser.create" />
	</a>
</div>
