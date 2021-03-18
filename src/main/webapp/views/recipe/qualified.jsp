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
	name="contests" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="contest.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="contest.openingTime" var="openingTimeHeader" />
	<display:column property="openingTime" title="${openingTimeHeader}" sortable="true" />
	
	<spring:message code="contest.closingTime" var="closingTimeHeader" />
	<display:column property="closingTime" title="${closingTimeHeader}" sortable="true" />
	
	
	<!-- Enlaces -->
	<spring:message code="contest.qualifiedRecipes" var="qualifiedRecipesHeader" />
	<display:column>
    <div>
	<a href="recipe/user/savequalified.do?recipeId=${recipe.id}&contestId=${row.id}">
				<spring:message	code="recipe.qualified" />
	</a>
    </div>
    </display:column>
</display:table>

<div>
<input type="button" name="cancel" value="<spring:message code="recipe.cancel" />" onclick="javascript: window.location.replace('recipe/user/list.do');" />
</div>

