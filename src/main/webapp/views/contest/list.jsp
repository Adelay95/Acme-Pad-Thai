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
	<a href="recipe/list-contest-qualified.do?contestId=${row.id}">
				<spring:message	code="contest.qualifiedRecipes" />
	</a>
    </div>
    </display:column>

    <spring:message code="contest.winnerRecipes" var="winnerRecipesHeader" />
	<display:column>
    <div>
	<a href="recipe/list-contest-winner.do?contestId=${row.id}">
				<spring:message	code="contest.winnerRecipes" />
	</a>
    </div>
    </display:column>
	
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	<display:column>
	<a href="contest/administrator/edit.do?contestId=${row.id}">
				<spring:message	code="contest.edit" />
	</a>
    </display:column>
    </security:authorize>
	


</display:table>
<security:authorize access="hasRole('ADMINISTRATOR')">
<div>
	<a href="contest/administrator/create.do">
				<spring:message	code="contest.create" />
	</a>
</div>
</security:authorize>
<security:authorize access="hasRole('ADMINISTRATOR')">
<div>
	<a href="contest/administrator/set-winner-contest.do">
				<spring:message	code="contest.winner" />
	</a>
</div>
</security:authorize>
