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
	name="sponsors" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="sponsor.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
    <spring:message code="sponsor.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" sortable="true" />
	
	<spring:message code="sponsor.companyName" var="companyNameHeader" />
	<display:column property="companyName" title="${companyNameHeader}" sortable="true" />
	
	<spring:message code="sponsor.paid" var="paidHeader" />
	<display:column title="${paidHeader}" sortable="true" >
	<jstl:if test="${!morosos.contains(row)}"><spring:message code="sponsor.normal"/></jstl:if>
	<jstl:if test="${morosos.contains(row)}"><spring:message code="sponsor.moroso"/></jstl:if>
	</display:column>
	
	
    
    
	<display:column>
	<jstl:if test="${!noMonthlyBill.contains(row)}">
	<div>
	<a href="sponsor/setMonthlyBill.do?sponsorId=${row.id}">
				<spring:message	code="sponsor.monthlyBill" />
	</a>
</div>
</jstl:if>
</display:column>
</display:table>


	<!-- Enlaces -->
<div>
	<a href="sponsor/sendMoroso.do">
				<spring:message	code="sponsor.sendMessage" />
	</a>
</div>


