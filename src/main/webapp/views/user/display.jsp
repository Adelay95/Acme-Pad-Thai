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

<div id="contenidos" class="mostrar">
		<div id="user">
		<ul>
		<spring:message code="user.nameandsurname" var="nameandsurnameHeader" />
		<li><b><jstl:out value="${nameandsurnameHeader}: " /></b><jstl:out value="${user.name} ${user.surname}" /></li>
		<spring:message code="user.emailAdress" var="emailAdressHeader" />
		<li><b><jstl:out value="${emailAdressHeader}: " /></b><jstl:out value="${user.emailAdress}" /></li>
		<spring:message code="user.postalAdress" var="postalAdressHeader" />
		<li><b><jstl:out value="${postalAdressHeader}: " /></b><jstl:out value="${user.postalAdress}" /></li>
		<spring:message code="user.phoneNumber" var="phoneNumberHeader" />
		<li><b><jstl:out value="${phoneNumberHeader}: " /></b><jstl:out value="${user.phoneNumber}" /></li>
		<spring:message code="user.followed" var="followedHeader" />
		<li><b><jstl:out value="${followedHeader}: " /></b><jstl:out value="${followers}" /></li>
		<spring:message code="user.recipe" var="recipeHeader" />
		<li><b><jstl:out value="${recipeHeader}: " /></b><a href="recipe/list-by-author.do?userId=${user.id}"><spring:message code="user.recipe"/></a>
	
		</ul>
		
		</div>
		
	<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="socialIdentities" requestURI="${requestURI}" id="row">
	<spring:message code="socialIdentitie" var="socialIdentitieHeader" />
	<display:column title="${socialIdentitieHeader}" sortable="false" >
	<table>
	<tr>
			<td rowspan="2">
			<jstl:if test="${row.picture != null}">
				<img WIDTH=60 HEIGHT=60 src="<jstl:out value="${row.picture}" />" />
			</jstl:if>
			<jstl:if test="${row.picture == null}">
				<img WIDTH=60 HEIGHT=60 src="images/avatar.png" />
			</jstl:if>
			</td>
	
	<spring:message code="socialIdentitie.nick" var="nickHeader" />
	<spring:message code="socialIdentitie.socialNetwork" var="socialNetworkHeader" />
	<spring:message code="socialIdentitie.link" var="linkHeader" />
	<td><jstl:out value="${nickHeader}: " /><jstl:out value="${row.nick}" /></td>
	<td><jstl:out value="${socialNetworkHeader}: " /><jstl:out value="${row.socialNetwork}" /></td>
	<tr>
	<td colspan="2"><jstl:out value="${linkHeader}: " /><a href="<jstl:out value="${row.link}" />"><jstl:out value="${row.link}" /></a></td>
	
	</tr>
	</table>
	</display:column>
	</display:table>
	
		
</div>
