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
	name="folders" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="folder.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
    
    <display:column>
	<!-- Enlaces -->
<div>
	<a href="folder/edit.do?folderId=${row.id}">
				<spring:message	code="folder.edit" />
	</a>
</div>
</display:column>
<jstl:set var="Mensaje" value="message"/>
<jstl:if test="${requestURI.contains(Mensaje)}">
<display:column>
     <div>
	        <a href="message/defMov.do?folderId=${row.id}&messageId=${messageId}">
				<spring:message	code="folder.moveMessages" />
	        </a>
    </div>
</display:column>
</jstl:if>
<display:column>
<div>
	<a href="message/list.do?folderId=${row.id}">
				<spring:message	code="folder.messages" />
	</a>
</div>
	</display:column>
</display:table>
<div><a href="folder/create.do"><spring:message code="folder.create" /></a></div>

