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

<jstl:if test="${(requestURI=='recipe/list.do')||(requestURI=='recipe/search.do')}">
<form method="GET" action="recipe/search.do">
<label id="search">
		<spring:message code="recipe.search1" />:
	</label>
			<input name="search" value="${search}" /><input type="submit" name="searchs"
		value="<spring:message code="recipe.search2" />" />&nbsp; 
</form>
</jstl:if>
<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="recipes" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="recipe.ticker" var="tickerHeader" />
	<display:column property="ticker" title="${tickerHeader}" sortable="true" />
	
	<spring:message code="recipe.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true" />
	
	<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
	<display:column property="momentAuthored" title="${momentAuthoredHeader}" sortable="true" />
	
	<spring:message code="recipe.lastMomentUpdated" var="lastMomentUpdatedHeader" />
	<display:column property="lastMomentUpdated" title="${lastMomentUpdatedHeader}" sortable="true" />
	
	<spring:message code="recipe.category" var="categoryHeader" />
	<display:column title="${categoryHeader}" sortable="true" >
	<jstl:set var="n" value="0"/>
	<jstl:forEach var="x" items="${row.categories}">
	<jstl:if test="${n==0}"><jstl:out value="${x.name}" /></jstl:if>
	<jstl:if test="${n!=0}"><jstl:out value=", ${x.name}" /></jstl:if>
	<jstl:set var="n" value="1"/>
	</jstl:forEach>
	</display:column>
	
	<!-- ********* -->
	<spring:message code="recipe.author" var="authorHeader" />
	<display:column title="${authorHeader}" sortable="true">
	<a href="user/display.do?userId=${row.user.id}">
			<jstl:out value="${row.user.name} ${row.user.surname}" />
	</a>
	</display:column>
	
	
	<!-- Enlaces -->
<jstl:if test="${requestURI=='recipe/user/list.do'}">
<security:authorize access="hasRole('USER')">
	<display:column>
	<a href="recipe/user/edit.do?recipeId=${row.id}">
				<spring:message	code="recipe.edit" />
	</a>
	</display:column>
	<display:column>
	<a href="recipe/user/qualified.do?recipeId=${row.id}">
				<spring:message	code="recipe.qualified" />
	</a>
	</display:column>
</security:authorize>
</jstl:if>

	<display:column>
	<a href="recipe/display.do?recipeId=${row.id}">
				<spring:message	code="recipe.display" />
	</a>
	</display:column>

	
</display:table>
<security:authorize access="hasRole('USER')">
<div>
	<a href="recipe/user/create.do">
				<spring:message	code="recipe.create" />
	</a>
</div>	
</security:authorize>

