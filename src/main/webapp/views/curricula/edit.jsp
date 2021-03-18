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


<form:form action="${requestURI}" modelAttribute="curricula">
<!-- Formularios -->
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="endorser" />
	<form:hidden path="nutritionist" />
	
	<form:label path="photo">
		<spring:message code="curricula.photo" />:
	</form:label>
			<form:input path="photo" />
			<form:errors cssClass="error" path="photo" />
	<br />

	<form:label path="educationSection">
		<spring:message code="curricula.educationSection" />:
	</form:label>
			<form:input path="educationSection" />
			<form:errors cssClass="error" path="educationSection" />
	<br />
	
	<form:label path="experience">
		<spring:message code="curricula.experience" />:
	</form:label>
			<form:input path="experience" />
			<form:errors cssClass="error" path="experience" />
	<br />
	
	
	
	<form:label path="hobby">
		<spring:message code="curricula.hobby" />:
	</form:label>
	<form:input path="hobby" />
	<form:errors cssClass="error" path="hobby" />
	<br />
    <br />
    
    
<!-- Acciones -->
	<input type="submit" name="save"
		value="<spring:message code="curricula.save" />" />&nbsp;
	<jstl:if test="${curricula.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="curricula.delete" />"
			onclick="return confirm('<spring:message code="curricula.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="curricula.cancel" />"
		onclick="javascript: window.location.replace('curricula/list.do');" />
	
</form:form>