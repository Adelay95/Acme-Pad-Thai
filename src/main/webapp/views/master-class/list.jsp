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

<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="masterClasses" requestURI="${requestURI}" id="row">
	
	<!-- Action links -->

<!-- ---------COOK--------- -->

    <jstl:set var="playita" value="cook"/>
	<jstl:if test="${requestURI.contains(playita)}">
	<security:authorize access="hasRole('COOK')">
		<display:column>
		<jstl:if test="${row.cook == actor}">
			<a href="master-class/cook/edit.do?masterClassId=${row.id}">
				<spring:message	code="masterClass.edit" />
			</a>
			</jstl:if>	
		</display:column>	
	</security:authorize>
	</jstl:if>
	
<!-- ---------ADMINISTRATOR----- -->

   <jstl:set var="playita" value="cook"/>
	<jstl:if test="${!requestURI.contains(playita)}">
	<security:authorize access="isAuthenticated()">
	
	<display:column>
	<jstl:if test="${row.cook != actor}">
    <jstl:if test="${!row.alums.contains(actor)}">
			<a href="master-class/attend.do?masterClassId=${row.id}">
				<spring:message	code="masterClass.attend" />
			</a>
			</jstl:if>
			</jstl:if>
		</display:column>	
		
	</security:authorize>
	</jstl:if>
	<!-- Attributes -->
	<spring:message code="masterClass.cook" var="cookHeader" />
	<display:column title="${cookHeader}" sortable="true">
			<jstl:out value="${row.cook.name} ${row.cook.surname}" />
	</display:column>

	<spring:message code="masterClass.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true"/>

	<spring:message code="masterClass.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<security:authorize access="isAuthenticated()">
	<display:column>
	<jstl:if test="${row.alums.contains(actor) || row.cook == actor}">
	<a href="learning-material/list-learning-material.do?masterClassId=${row.id}">
				<spring:message	code="masterClass.learningMaterial" />
			</a>
			  </jstl:if>
			</display:column>
			
	
	
	
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
 <display:column>
    <jstl:if test="${row.promote == false}">
   <a href="master-class/promote.do?masterClassId=${row.id}">
    <spring:message code="masterClass.promote" />
   </a>
   </jstl:if>
 <jstl:if test="${row.promote == true}">
   <a href="master-class/demote.do?masterClassId=${row.id}">
    <spring:message code="masterClass.depromote" />
   </a>
   </jstl:if>
  </display:column>
  </security:authorize>
	
	
	<jstl:set var="playita" value="cook"/>
	<jstl:if test="${requestURI.contains(playita)}">
	<display:column>
	<jstl:if test="${row.cook == actor}">
	<a href="learning-material/addlearning-material.do?masterClassId=${row.id}">
				<spring:message	code="learningMaterial.add" />
			</a>
	</jstl:if>
	</display:column> 
	</jstl:if>
	</security:authorize>
 
</display:table>

<!-- Action links -->

<!-- ---------COOK--------- -->

<security:authorize access="hasRole('COOK')">
	<div>
		<a href="master-class/cook/create.do"> <spring:message
				code="masterClass.create" />
		</a>
	</div>
</security:authorize>