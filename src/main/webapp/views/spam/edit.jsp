<%--
 * edit.jsp
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


<form:form action="${requestURI}" modelAttribute="spam">
<!-- Formularios -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:label path="terms">
		<spring:message code="spam.terms" />:
	</form:label>
			<form:input path="terms" />
			<form:errors cssClass="error" path="terms" />
	<br />
	
<!-- Acciones -->
	<input type="submit" name="save"
		value="<spring:message code="spam.save" />" />&nbsp; 
	<jstl:if test="${spam.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="spam.delete" />"
			onclick="return confirm('<spring:message code="spam.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="spam.cancel" />"
		onclick="javascript:  window.location.replace('spam/list.do');" />
</form:form>