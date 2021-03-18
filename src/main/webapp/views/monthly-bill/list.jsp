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
	name="monthlybills" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="monthly-bill.momentCreated" var="momentCreatedHeader" />
	<display:column property="momentCreated" title="${momentCreatedHeader}" sortable="true" />
	
	<spring:message code="monthly-bill.momentPaid" var="momentPaidHeader" />
	<display:column property="momentPaid" title="${momentPaidHeader}" sortable="true" />
	
	<spring:message code="monthly-bill.cost" var="costHeader" />
	<display:column property="cost" title="${costHeader}" sortable="true" />
	
	<spring:message code="monthly-bill.description" var="descriptionHeader" />
	<display:column property="description" title="${descriptionHeader}" sortable="false" />
	
	<display:column>
	<jstl:if test="${row.momentPaid == null}">
			<a href="monthly-bill/paid.do?monthlyBillId=${row.id}">
				<spring:message	code="monthly-bill.paid" />
			</a>
			</jstl:if>
		</display:column>	
</display:table>


