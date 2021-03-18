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
		<div id="recipe">
		<form:form action="${requestURI}" modelAttribute="recipe">
		<fieldset>
		<spring:message code="recipe.recipe" var="recipeHeader" />
			<legend><b><jstl:out value="${recipeHeader}:" />&nbsp;<jstl:out value="${recipe.ticker}" />  </b></legend>
		<table>
		<!-- Formularios -->
			<form:hidden path="id" />
			<form:hidden path="version" />
			<form:hidden path="user" />
			<form:hidden path="cookSteps" />
			<form:hidden path="qualifiedContests" />
			<form:hidden path="winnerContests" />
			<form:hidden path="quantities" />
			<form:hidden path="comments" />
			<form:hidden path="tastes" />
			<form:hidden path="categories" />
			<form:hidden path="ticker" />
			<form:hidden path="momentAuthored" />
			<form:hidden path="lastMomentUpdated" />
		<tr>
<!-- DATOS RECETA -->
		<spring:message code="recipe.title" var="titleHeader" />
		<spring:message code="recipe.author" var="authorHeader" />
			<th><jstl:out value="${titleHeader}:" /></th><td>
			<form:input path="title" />
			<form:errors cssClass="error" path="title" />
			</td><td></td><th><jstl:out value="${authorHeader}:" /></th><td><jstl:out value="${recipe.user.name} ${recipe.user.surname}" /></td>
			</tr>
			<tr>
			<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
			<spring:message code="recipe.lastMomentUpdated" var="lastMomentUpdatedHeader" />
			<th><jstl:out value="${momentAuthoredHeader}:" /></th><td><jstl:out value="${recipe.momentAuthored}" /></td><td></td>	<th><jstl:out value="${lastMomentUpdatedHeader}:" /></th><td><jstl:out value="${recipe.lastMomentUpdated}" /></td>
			</tr>
			<tr>
			<spring:message code="recipe.hints" var="hintsHeader" />
			<td colspan="5"><b><jstl:out value="${hintsHeader}: " /></b><form:input path="hints" />
			<form:errors cssClass="error" path="hints" /></td>
			</tr>
			<tr>
			<spring:message code="recipe.picture" var="pictureHeader" />
			<td colspan="5">
			<b><jstl:out value="${pictureHeader}: " /></b>
			<form:input path="picture" />
			<form:errors cssClass="error" path="picture" />
			</td>
			</tr>
			<tr>
			<spring:message code="recipe.summary" var="summaryHeader" />
			<td colspan="5"><b><jstl:out value="${summaryHeader}: " /></b><form:input path="summary" />
			<form:errors cssClass="error" path="summary" /></td>
			</tr>
			<!-- AÑADIR CATEGORIA -->
			
			<tr>
				<td>
					<select id="idcategory" name="idcategory">	
					<jstl:forEach var="c" items="${addcategories}">
					 <option value="${c.id}"><jstl:out value="${c.name}" /></option>
					 </jstl:forEach>
					</select>
				</td>
				</tr>
			<tr><td>
<!-- Acciones recetas -->
			<input type="submit" name="save" value="<spring:message code="recipe.save" />" />&nbsp; 
			<input type="button" name="cancel" value="<spring:message code="recipe.cancel" />" onclick="javascript: window.location.replace('recipe/user/list.do');" />
			</td></tr>
			
			<tr><td><br></td></tr>
		</table>
		</fieldset>
		</form:form>
		</div>

	</div>
