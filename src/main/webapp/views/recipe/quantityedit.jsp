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



<!-- AÑADIR INGREDIENTE -->
			<form:form action="${requestURI}" modelAttribute="quantity">
			<form:hidden path="recipe" />
			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="ingredient" />
			<table>
			<spring:message code="recipe.ingredients" var="ingredientsHeader" />
					<spring:message code="recipe.quantities" var="quantitiyHeader" />
					<spring:message code="recipe.unit" var="unitHeader" />
			<tr><th><jstl:out value="${ingredientsHeader}" />
			</th><th><jstl:out value="${quantitiyHeader}" /></th>
			<th><jstl:out value="${unitHeader}" /></th></tr>
			<tr>
				<td>
					<jstl:out value="${quantity.ingredient.name}" />
				</td>
		
				<td><form:input path="measure.value" /><form:errors cssClass="error" path="measure.value" /></td>
				<td>
					<form:select id="measure.unit" path="measure.unit">
					<form:option value="" label="----" />	
					<jstl:forEach var="u" items="${units}">
					<form:option value="${u}" label="${u}" />
					</jstl:forEach>		
					</form:select>
					<form:errors cssClass="error" path="measure.unit" />
				</td>
			</tr>
			<tr><td><input type="submit" name="save" value="<spring:message code="recipe.save.ingredient" />" />&nbsp; 
			<input type="button" name="cancel" value="<spring:message code="recipe.cancel" />" onclick="javascript: window.location.replace('recipe/user/edit.do?recipeId=${quantity.recipe.id}');" /></td></tr>
			</table>
			</form:form>
		