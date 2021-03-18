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
<jstl:set var="listSons" value="listsSons"/>
<jstl:if test="${requestURI.contains(listSons)}" >
<spring:message code="category.parent" var="ahore"/>
	
     <jstl:out value="${ahore}" /> :  <jstl:out value="${nameParent}" />
</jstl:if>
     
         
<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="categories" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="category.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
	<spring:message code="category.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="true" />
	
	<spring:message code="category.picture" var="pictureHeader" />
	<display:column property="picture" title="${pictureHeader}" sortable="true" />
	
	<spring:message code="category.tags" var="tagsHeader" />
	<display:column property="tags" title="${tagsHeader}" sortable="false" />
    
    <display:column>
<div>
	<a href="recipe/listByCategory.do?categoryId=${row.id}">
				<spring:message	code="category.recipe" />
	</a>
</div>
	</display:column>
	 <display:column>
<div>
	<a href="category/listSons.do?categoryId=${row.id}">
				<spring:message	code="category.listSons" />
	</a>
</div>
	</display:column>
    <display:column>
	<!-- Enlaces -->
<div>
	<a href="category/edit.do?categoryId=${row.id}">
				<spring:message	code="category.edit" />
	</a>
</div>
	</display:column>
		<jstl:set var="setParent" value="setParent"/>
	<jstl:if test="${sonId==null}" >
	<display:column>
<div>
	<a href="category/setSonAs.do?sonId=${row.id}">
				<spring:message	code="category.son" />
	</a>
</div>
	</display:column>
</jstl:if>
<jstl:set var="son" value="${son}"/>
<jstl:set var="setSonAs" value="setSonAs"/>
	<jstl:if test="${requestURI.contains(setSonAs)}" >
	<jstl:if test="${!row.sons.contains(son) }">
	<display:column>
<div>
	<a href="category/setParent.do?sonId=${sonId}&parentId=${row.id}">
				<spring:message	code="category.parent" />
	</a>
</div>
	</display:column>
	</jstl:if>
	<jstl:if test="${row.sons.contains(son) }">
	<display:column>
<div>
	<a href="category/quitParent.do?sonId=${sonId}&parentId=${row.id}">
				<spring:message	code="category.quitparent" />
	</a>
</div>
	</display:column>
	</jstl:if>
	</jstl:if>
</display:table>
<div>
	<a href="category/create.do">
				<spring:message	code="category.create" />
	</a>
</div>
