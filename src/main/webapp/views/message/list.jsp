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
	name="messages" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="message.subject" var="subjectHeader" />
	<display:column property="subject" title="${subjectHeader}" sortable="true" />
	
    <spring:message code="message.moment" var="momentHeader" />
	<display:column property="moment" title="${momentHeader}" sortable="true" />
	
		<spring:message code="message.body" var="bodyHeader" />
	<display:column property="body" title="${bodyHeader}" sortable="true" />
	
		<spring:message code="message.priority" var="priorityHeader" />
	<display:column property="priority" title="${priorityHeader}" sortable="true" />
	
	<spring:message code="message.sender" var="senderHeader" />
	<display:column title="${senderHeader}" sortable="true" >
	<jstl:out value="${row.sender.name} ${row.sender.surname}" />
	</display:column>
	
	<spring:message code="message.receiver" var="receiverHeader" />
	<display:column  title="${receiverHeader}" sortable="true" >
	<jstl:out value="${row.receiver.name} ${row.receiver.surname}" />
	</display:column>
	
    
    <display:column>
	<!-- Enlaces -->
<div>
	<a href="message/delete.do?messageId=${row.id}">
				<spring:message	code="message.delete" />
	</a>
</div>
</display:column>
<display:column>
<div>
	<a href="message/move.do?messageId=${row.id}">
				<spring:message	code="message.move" />
	</a>
</div>
	</display:column>
</display:table>

