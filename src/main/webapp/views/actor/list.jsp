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
	name="actors" requestURI="${requestURI}" id="row">
	
	<!-- Atributos -->
	<spring:message code="actor.name" var="nameHeader" />
	<display:column property="name" title="${nameHeader}" sortable="true" />
	
    <spring:message code="actor.surname" var="surnameHeader" />
	<display:column property="surname" title="${surnameHeader}" sortable="true" />
	
	<jstl:if test="${requestURI!='/administrator/cook-list.do'}" >
 <spring:message code="actor.rol" var="rolHeader" />
 <display:column title="${rolHeader}" sortable="true" >
 <jstl:set var="n" value="0"/>
     <jstl:forEach var="x" items="${row.userAccount.authorities}">
        <jstl:if test="${n==0}"><jstl:out value="${x.authority}" /></jstl:if>
         <jstl:if test="${n!=0}"><jstl:out value=", ${x.authority}" /></jstl:if>
          <jstl:set var="n" value="1"/>
           </jstl:forEach>
 </display:column>
 </jstl:if>
  <jstl:if test="${requestURI=='/administrator/cook-list.do'}">
 <spring:message code="cook.masterclass.promoted" var="masterClassPromotedHeader" />
 <display:column title="${masterClassPromotedHeader}" sortable="true" >
 <jstl:out value="${row.numPromotes}" />
 </display:column>
 </jstl:if>
	
    
    <display:column>
	<!-- Enlaces -->
<div>
	<a href="message/edit.do?actorId=${row.id}">
				<spring:message	code="actor.sendMessage" />
	</a>
</div>
	</display:column>
	
</display:table>
