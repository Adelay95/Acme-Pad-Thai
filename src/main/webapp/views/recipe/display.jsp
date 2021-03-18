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

<jstl:if test="${banner != null}">
	<div style="position: relative; width: 0px; height: 80px; margin-left:0; margin-right:auto;">
		<img  WIDTH=300 HEIGHT=80 src="<jstl:out value="${banner}" />" />
	
	</div>		
</jstl:if>

<div id="contenidos" class="mostrar">
		<div id="recipe">
		<fieldset>
		<spring:message code="recipe.recipe" var="recipeHeader" />
			<legend><b><jstl:out value="${recipeHeader}:" />&nbsp;<jstl:out value="${recipe.ticker}" />  </b></legend>
		<table><tr>
		<spring:message code="recipe.title" var="titleHeader" />
		<spring:message code="recipe.author" var="authorHeader" />
			<th><jstl:out value="${titleHeader}:" /></th><td><jstl:out value="${recipe.title}" /></td><td></td><th><jstl:out value="${authorHeader}:" /></th><td><jstl:out value="${recipe.user.name} ${recipe.user.surname}" /></td>
			</tr>
			<tr>
			<spring:message code="recipe.momentAuthored" var="momentAuthoredHeader" />
			<spring:message code="recipe.lastMomentUpdated" var="lastMomentUpdatedHeader" />
			<th><jstl:out value="${momentAuthoredHeader}:" /></th><td><jstl:out value="${recipe.momentAuthored}" /></td><td></td>	<th><jstl:out value="${lastMomentUpdatedHeader}:" /></th><td><jstl:out value="${recipe.lastMomentUpdated}" /></td>
			</tr>
			<tr>
			<spring:message code="recipe.category" var="categoryHeader" />
			<td colspan="5"><b><jstl:out value="${categoryHeader}: " /></b>
				<jstl:set var="n" value="0"/>
					<jstl:forEach var="x" items="${recipe.categories}">
					<jstl:if test="${n==0}"><jstl:out value="${x.name}" /></jstl:if>
					<jstl:if test="${n!=0}"><jstl:out value=", ${x.name}" /></jstl:if>
					<jstl:set var="n" value="1"/>
				</jstl:forEach>
			</td>
			</tr>
			<tr>
			<spring:message code="recipe.hints" var="hintsHeader" />
			<td colspan="5"><b><jstl:out value="${hintsHeader}: " /></b><jstl:out value="${recipe.hints}" /></td>
			</tr>
			<tr>
			<td colspan="5">
			<jstl:if test="${recipe.picture != null}">
			<img WIDTH=400 HEIGHT=240 src="<jstl:out value="${recipe.picture}" />" />
			</jstl:if>
			<jstl:if test="${recipe.picture == null}">
			<img WIDTH=400 HEIGHT=240 src="images/recipe.png" />
			</jstl:if>
			</td>
			</tr>
			<tr>
			<spring:message code="recipe.summary" var="summaryHeader" />
			<td colspan="5"><b><jstl:out value="${summaryHeader}: " /></b><jstl:out value="${recipe.summary}" /></td>
			</tr>
			<tr><td colspan="5">
					<spring:message code="recipe.ingredients" var="ingredientsHeader" />
					<spring:message code="recipe.quantities" var="quantitiyHeader" />
					<spring:message code="recipe.unit" var="unitHeader" />
			<table><tr><th><jstl:out value="${ingredientsHeader}" />
			</th><th><jstl:out value="${quantitiyHeader}" /></th>
			<th><jstl:out value="${unitHeader}" /></th></tr>
			<jstl:forEach var="y" items="${recipe.quantities}">
			<tr>
					<td><jstl:out value="${y.ingredient.name}" /></td>
					<td><jstl:out value="${y.measure.value}" /></td>
					<td><jstl:out value="${y.measure.unit}" /></td>
			</tr>
			</jstl:forEach>
			</table>
			</td>
			</tr>
			<security:authorize access="!hasAnyRole('USER','NUTRITIONIST')">
				<tr>
				<td><img src="images/like.png" ><jstl:out value="${likes}" /></td><td><img src="images/dislike.png" ><jstl:out value="${dislikes}" /></td>
				</tr>
				</security:authorize>
			<security:authorize access="hasAnyRole('USER','NUTRITIONIST')">
    <jstl:if test="${(recipe.qualifiedContests==null)&&(recipe.winnerContests==null)}">
    <tr>
    <td><a href="recipe/taste.do?recipeId=${recipe.id}&like=${true}"><img src="images/like.png" ></a><jstl:out value="${likes}" /></td>
    <td><a href="recipe/taste.do?recipeId=${recipe.id}&like=${false}"><img src="images/dislike.png" ></a><jstl:out value="${dislikes}" /></td>
    </tr>
   </jstl:if>
   <jstl:if test="${(recipe.qualifiedContests!=null)||(recipe.winnerContests!=null)}">
   <tr>
    <td><img src="images/like.png" ><jstl:out value="${likes}" /></td><td><img src="images/dislike.png" ><jstl:out value="${dislikes}" /></td>
    </tr>
   </jstl:if>
   </security:authorize>
		</table>
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

	
	<td colspan="2"><jstl:out value="${hintsHeader}: " /><jstl:out value="${row.hints}" /></td>
	<tr>
	<td colspan="2"><jstl:out value="${descriptionHeader}: " /><jstl:out value="${row.description}" /></td>
	
	</tr>
	</table>
	</display:column>
	</display:table>
		
	<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="comments" requestURI="${requestURI}" id="row">
	<spring:message code="comment" var="commentHeader" />
	<display:column title="${commentHeader}" sortable="false" >
	<table>
	<spring:message code="comment.tittle" var="tittleHeader" />
	<spring:message code="comment.stars" var="starsHeader" />
	<spring:message code="comment.creationDate" var="creationDateHeader" />
	<spring:message code="comment.client" var="clientHeader" />
	<tr>
	<td><jstl:out value="${clientHeader}: " /><jstl:out value="${row.client.name} ${row.client.surname}" /></td>
	<td><jstl:out value="${tittleHeader}: " /><jstl:out value="${row.tittle}" /></td>
	<td><jstl:out value="${starsHeader}: " /><jstl:out value="${row.stars}" /></td>
	<td><jstl:out value="${creationDateHeader}: " /><jstl:out value="${row.creationDate}" /></td>
	</tr>
	<tr>
			<td colspan="5" rowspan="3"><jstl:out value="${row.text}" /></td>
	</tr>
	</table>
	</display:column>
	</display:table>
	<security:authorize access="hasAnyRole('USER','NUTRITIONIST')">
		<div>
			<a href="recipe/comment/create.do?recipeId=${recipe.id}">
						<spring:message	code="recipe.create.comment" />
			</a>
		</div>	
	</security:authorize>
	</fieldset>
		</div>

	</div>
