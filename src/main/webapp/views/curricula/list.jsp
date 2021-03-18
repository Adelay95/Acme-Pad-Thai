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

<display:table pagesize="5" class="displaytag" keepStatus="true"
	name="curriculas" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="curricula.photo" var="photoHeader" />
	<display:column property="photo" title="${photoHeader}" sortable="false" />
	
	<spring:message code="curricula.educationSection" var="educationSectionHeader" />
	<display:column property="educationSection" title="${educationSectionHeader}" sortable="true" />
	
	<spring:message code="curricula.experience" var="experienceHeader" />
	<display:column property="experience" title="${experienceHeader}" sortable="true" />
	
	
	<spring:message code="curricula.hobby" var="hobbiesHeader" />
	<display:column property="hobby" title="${hobbiesHeader}" sortable="false" />

  <display:column>
		<a href="endorser/list.do?curriculaId=${row.id}">
			<spring:message	code="curricula.endorser" />
		</a>
	 </display:column>

	<display:column>
	
	<!-- Enlaces -->
<div>
	<a href="curricula/edit.do?curriculaId=${row.id}">
				<spring:message	code="curricula.edit" />
	</a>
</div>
	</display:column>
</display:table>
<div>
	<a href="curricula/create.do">
				<spring:message	code="curricula.create" />
	</a>
</div>
