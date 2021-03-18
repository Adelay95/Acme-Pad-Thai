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
	name="ingredients" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	
		
	<spring:message code="ingredient.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="ingredient.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<spring:message code="ingredient.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="true" />

	<display:column>
<div>
	<a href="property/listHas.do?ingredientId=${row.id}">
				<spring:message	code="ingredient.property" />
	</a>
</div>
	</display:column>
	
	<display:column>
<div>
	<a href="recipe/listByIngr.do?ingredientId=${row.id}">
				<spring:message	code="ingredient.recipes" />
	</a>
</div>
	</display:column>
	
	<display:column>
	
	<!-- Enlaces -->
	
<div>
	<a href="ingredient/edit.do?ingredientId=${row.id}">
				<spring:message	code="ingredient.edit" />
	</a>
</div>
	</display:column>
</display:table>
<div>
	<a href="ingredient/create.do">
				<spring:message	code="ingredient.create" />
	</a>
</div>
