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

<form:form action="master-class/cook/edit.do" modelAttribute="masterClass">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="cook" />
	<form:hidden path="promote" />
	<form:hidden path="alums" />
	<form:hidden path="learningMaterials" />
	

	<form:label path="title">
		<spring:message code="masterClass.title" />:
	</form:label>
	<form:input path="title" />
	<form:errors cssClass="error" path="title" />
	<br />

	<form:label path="description">
		<spring:message code="masterClass.description" />:
	</form:label>
	<form:input path="description" />
	<form:errors cssClass="error" path="description" />
	<br />
	
	
	<input type="submit" name="save"
		value="<spring:message code="masterClass.save" />" />&nbsp; 
	<jstl:if test="${masterClass.id != 0}">
		<input type="submit" name="delete"
			value="<spring:message code="masterClass.delete" />"
			onclick="return confirm('<spring:message code="masterClass.confirm.delete" />')" />&nbsp;
	</jstl:if>
	<input type="button" name="cancel"
		value="<spring:message code="masterClass.cancel" />"
		onclick="javascript: window.location.replace('master-class/cook/list.do');" />
	<br />

</form:form>
