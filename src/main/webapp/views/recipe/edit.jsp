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
		
		<fieldset>
		<spring:message code="recipe.recipe" var="recipeHeader" />
			<legend><b><jstl:out value="${recipeHeader}:" />&nbsp;<jstl:out value="${recipe.ticker}" />  </b></legend>
		<table>
		<form:form action="${requestURI}" modelAttribute="recipe">
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
			<tr><td>
<!-- Acciones recetas -->
			<input type="submit" name="save" value="<spring:message code="recipe.save" />" />&nbsp; 
			<jstl:if test="${recipe.id != 0}">
			<input type="submit" name="delete" value="<spring:message code="recipe.delete" />" onclick="return confirm('<spring:message code="recipe.confirm.delete" />')" />&nbsp;
			</jstl:if>
			<input type="button" name="cancel" value="<spring:message code="recipe.cancel" />" onclick="javascript: window.location.replace('recipe/user/list.do');" />
			</td></tr>
			</form:form>
			<tr><td><br></td></tr>
			
<!-- CATEGORIAS -->
			<tr>
			<spring:message code="recipe.category" var="categoryHeader" />
			<td colspan="5"><b><jstl:out value="${categoryHeader}: " /></b>
				<jstl:set var="n" value="0"/>
					<jstl:forEach var="x" items="${recipe.categories}">
					<form method="POST" action="${requestURI}">
					<jstl:out value=" ${x.name} " />
					
					<input type="hidden" id="idcategory" name="idcategory" value="${x.id}" />
					<input type="hidden" id="idrecipe" name="idrecipe" value="${recipe.id}" />
					<input type="submit" name="deletecategory" onclick="return confirm('<spring:message code="category.confirm.delete" />')" value="<spring:message code="recipe.delete.category" />" />
					</form>
					<jstl:set var="n" value="1"/>
				</jstl:forEach>
			</td>
			</tr>
<!-- AÑADIR CATEGORIA -->
			
			<tr>
				<td><form method="POST" action="${requestURI}">
					<select id="idcategory" name="idcategory">
					<option value="" label="----" />	
					<jstl:forEach var="c" items="${addcategories}">
					 <option value="${c.id}"><jstl:out value="${c.name}" /></option>
					 </jstl:forEach>
					</select>
					<input type="hidden" id="idrecipe" name="idrecipe" value="${recipe.id}" />
					<br>
					<input type="submit" name="savecategory" value="<spring:message code="recipe.save.category" />" />
					</form>
				</td>
				</tr>
			
			
			
<!-- DATOS INGREDIENTES -->
			<tr>
			<td colspan="5">
					<spring:message code="recipe.ingredients" var="ingredientsHeader" />
					<spring:message code="recipe.quantities" var="quantitiyHeader" />
					<spring:message code="recipe.unit" var="unitHeader" />
			<table>
			<tr><th><jstl:out value="${ingredientsHeader}" />
			</th><th><jstl:out value="${quantitiyHeader}" /></th>
			<th><jstl:out value="${unitHeader}" /></th></tr>
			<jstl:forEach var="y" items="${recipe.quantities}">
			<tr>
					<td><jstl:out value="${y.ingredient.name}" /></td>
					<td><jstl:out value="${y.measure.value}" /></td>
					<td><jstl:out value="${y.measure.unit}" /></td>
					<td><a href="recipe/quantity/user/edit.do?quantityId=${y.id}">
						<spring:message	code="recipe.edit" />
					</a></td>
					<td>
			<form method="POST" action="${requestURI}">
			<input type="hidden" id="idingredient" name="idingredient" value="${y.id}" />
			<input type="submit" name="deleteingredient" onclick="return confirm('<spring:message code="quantity.confirm.delete" />')" value="<spring:message code="recipe.delete.ingredient" />" />
			</form>
			</td>
			</tr>
			</jstl:forEach>
<!-- AÑADIR INGREDIENTE -->
			<form:form action="${requestURI}" modelAttribute="quantity">
			<form:hidden path="recipe" />
			<form:hidden path="id" />
			<form:hidden path="version" />
			<tr>
				<td>
					<form:select id="ingredient" path="ingredient">
					<form:option value="" label="----" />	
					<form:options items="${ingredients}" itemValue="id" itemLabel="name" />	
					</form:select>
					<form:errors cssClass="error" path="ingredient" />
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
			<tr><td><input type="submit" name="saveingredient" value="<spring:message code="recipe.add.ingredient" />" />&nbsp; </td></tr>
			</form:form>
			</table>
			</td>
			</tr>
		</table>
<!-- DATOS COOKSTEPS -->
	<display:table class="displaytag" keepStatus="false"
	name="cookSteps" requestURI="${requestURI}" id="row">
	<spring:message code="cookSteps" var="cookStepsHeader" />
	<display:column title="${cookStepsHeader}" sortable="false" >
	<table>
	<tr>
	<td rowspan="2">
	<jstl:if test="${row.picture != null}">
		<img WIDTH=60 HEIGHT=60 src="<jstl:out value="${row.picture}" />" />
	</jstl:if>
	<jstl:if test="${row.picture == null}">
		<img WIDTH=60 HEIGHT=60 src="images/cookstep.png" />
	</jstl:if>
	</td>
	
	<spring:message code="cookSteps.hints" var="hintsHeader" />
	<spring:message code="cookSteps.description" var="descriptionHeader" />

	
	<td><jstl:out value="${hintsHeader}: " /><jstl:out value="${row.hints}" /></td>
	<td><a href="recipe/cookstep/user/edit.do?cookstepId=${row.id}">
				<spring:message	code="recipe.edit" />
	</a></td>
	<td>
		<form method="POST" action="${requestURI}">
			<input type="hidden" id="idcookstep" name="idcookstep" value="${row.id}" />
			<input type="submit" name="deletecookstep" onclick="return confirm('<spring:message code="cookstep.confirm.delete" />')" value="<spring:message code="recipe.delete.cookstep" />" />
			</form>
	</td>
	<tr>
	<td colspan="2"><jstl:out value="${descriptionHeader}: " /><jstl:out value="${row.description}" /></td>
	
	</tr>
	</table>
	</display:column>
	</display:table>
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
			<tr><td><input type="submit" name="savecookstep" value="<spring:message code="recipe.add.cookstep" />" />&nbsp; </td></tr>
			</form:form>
		</table>
		</fieldset>
		</div>

	</div>
