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
<jstl:if test="${(requestURI=='user/list.do')||(requestURI=='user/search.do')}">
<form method="GET" action="user/search.do">
<label id="search">
		<spring:message code="user.search1" />:
	</label>
			<input name="search" value="${search}" /><input type="submit" name="searchs"
		value="<spring:message code="user.search2" />" />&nbsp; 
</form>
</jstl:if>

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="users" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="user.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="user.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" sortable="true" />
	
	<spring:message code="user.emailAdress" var="emailAdressHeader" />
	<display:column property="emailAdress" title="${emailAdressHeader}" sortable="true" />
	
	<spring:message code="user.postalAdress" var="postalAdressHeader" />
	<display:column property="postalAdress" title="${postalAdressHeader}" sortable="false" />
	
	<spring:message code="user.phoneNumber" var="phoneNumberHeader" />
	<display:column property="phoneNumber" title="${phoneNumberHeader}" sortable="true" />
    
    <jstl:if test="${requestURI=='/administrator/popularity-list.do'}">
     <spring:message code="user.followed" var="followedHeader" />
     <display:column title="${followedHeader}" sortable="true" >
     <jstl:out value="${row.followed.size()}" />
     </display:column>
    </jstl:if>
	<!-- Enlaces -->
<security:authorize access="hasAnyRole('NUTRITIONIST','USER')">
	<display:column>
	<a href="user/client/follow.do?userId=${row.id}">
				<spring:message	code="user.follow" />
	</a>
	</display:column>
</security:authorize>
	<spring:message code="user.recipe" var="recipeHeader" />
<display:column title="${recipeHeader}" sortable="false">

		<div>
		<a href="recipe/list-by-author.do?userId=${row.id}">
		     <spring:message code="user.recipe"/>
		</a>
		</div>
</display:column>
<spring:message code="user.display" var="displayHeader" />
	<display:column sortable="false">
	<a href="user/display.do?userId=${row.id}">
			<jstl:out value="${displayHeader}" />
	</a>
	</display:column>
</display:table>
