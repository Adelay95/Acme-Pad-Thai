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

<!-- AÑADIR COOKSTEPS -->
	<table>
	<spring:message code="cookSteps.hints" var="hintsHeader" />
	<spring:message code="cookSteps.description" var="descriptionHeader" />
	<spring:message code="recipe.picture" var="pictureHeader" />
	<form:form action="${requestURI}" modelAttribute="cookStep">
			<form:hidden path="recipe" />
			<form:hidden path="id" />
			<form:hidden path="version" />
			<tr>
				<td><b><jstl:out value="${pictureHeader}:" /></b><form:input path="picture" /><form:errors cssClass="error" path="picture" /></td>
				<td><b><jstl:out value="${hintsHeader}:" /></b><form:input path="hints" /><form:errors cssClass="error" path="hints" /></td>
				<td><b><jstl:out value="${descriptionHeader}:" /></b><form:input path="description" /><form:errors cssClass="error" path="description" /></td>
			</tr>
			<tr><td><input type="submit" name="save" value="<spring:message code="recipe.save.cookstep" />" />&nbsp; 
			<input type="button" name="cancel" value="<spring:message code="recipe.cancel" />" onclick="javascript: window.location.replace('recipe/user/edit.do?recipeId=${cookStep.recipe.id}');" /></td></tr>
	</form:form>
	</table>

