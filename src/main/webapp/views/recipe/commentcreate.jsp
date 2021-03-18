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

<!-- AÑADIR COMENTARIO -->
	<table>
	<spring:message code="comment.tittle" var="tittleHeader" />
	<spring:message code="comment.stars" var="starsHeader" />
	<spring:message code="comment.creationDate" var="creationDateHeader" />
	<spring:message code="comment.client" var="clientHeader" />
	<form:form action="${requestURI}" modelAttribute="comment">
			<form:hidden path="recipe" />
			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="client" />
			<form:hidden path="creationDate" />
			<tr>
				<td><b><jstl:out value="${tittleHeader}: " /></b><form:input path="tittle" /><form:errors cssClass="error" path="tittle" /></td>
				<td><b><jstl:out value="${starsHeader}: " /></b><form:input path="stars" /><form:errors cssClass="error" path="stars" /></td>
				<td><b><jstl:out value="${creationDateHeader}: " /></b><jstl:out value="${comment.creationDate}" /></td>
			</tr>
			<tr>
				<td colspan="3"><form:textarea path="text" /><form:errors cssClass="error" path="text" /></td>
			</tr>
			<tr><td><input type="submit" name="save" value="<spring:message code="recipe.save.comment" />" />&nbsp; 
			<input type="button" name="cancel" value="<spring:message code="recipe.cancel" />" onclick="javascript: window.location.replace('recipe/display.do?recipeId=${comment.recipe.id}');" /></td></tr>
	</form:form>
	</table>

