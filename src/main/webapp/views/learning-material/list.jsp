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

    
    <div style ="font-size: 35pt " ><img src="images/texto.png" > <spring:message code="learningMaterial.text" /></div>

    <display:table pagesize="5" class="displaytag" keepStatus="false"
	name="text" requestURI="${requestURI}" id="text">
	
	<spring:message code="learningMaterial.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true"/>

    <spring:message code="learningMaterial.abstracts" var="abstractsHeader" />
	<display:column property="abstracts" title="${abstractsHeader}" sortable="false" />
	
	<spring:message code="learningMaterial.attachment" var="attachmentHeader" />
	<display:column property="attachment" title="${attachmentHeader}" sortable="false" />
	
	<spring:message code="text.htmlText" var="htmlTextHeader" />
	<display:column property="htmlText" title="${htmlTextHeader}" sortable="false" />
	
	
	<jstl:set var="playita" value="addlearning-material"/>
	<jstl:if test="${requestURI.contains(playita)}">
	<display:column>
	<jstl:if test="${!master.contains(text) }">
	<a href="master-class/cook/add-lm.do?masterClassId=${mci}&lmId=${text.id}">
				<spring:message	code="learningMaterial.anadir" />
			</a>
	</jstl:if>
	<jstl:if test="${master.contains(text) }">
	<a href="master-class/cook/remove-lm.do?masterClassId=${mci}&lmId=${text.id}">
			<spring:message	code="learningMaterial.delette" />
		</a>
	</jstl:if>
	</display:column>
	</jstl:if>
	
	
	<security:authorize access="hasRole('COOK')">
    <display:column>
    <div>
	<a href="learning-material/text/edit.do?textId=${text.id}">
				<spring:message	code="learningMaterial.edit" />
	</a>
    </div>
    </display:column>
    </security:authorize>
	
	</display:table>
	
	<security:authorize access="hasRole('COOK')">
	<div>
		<a href="learning-material/text/create.do"> <spring:message
				code="learningMaterial.create" />
		</a>
	</div>
</security:authorize>
	
	
    <div style ="font-size: 35pt "><img src="images/video.png" > <spring:message code="learningMaterial.video"/> </div>
    <display:table pagesize="5" class="displaytag" keepStatus="false"
	name="videos" requestURI="${requestURI}" id="videos">
	
	<spring:message code="learningMaterial.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true"/>

    <spring:message code="learningMaterial.abstracts" var="abstractsHeader" />
	<display:column property="abstracts" title="${abstractsHeader}" sortable="false" />
	
	<spring:message code="learningMaterial.attachment" var="attachmentHeader" />
	<display:column property="attachment" title="${attachmentHeader}" sortable="false" />
	
	<spring:message code="videos.youtubeIdentifier" var="youtubeIdentifierHeader" />
	<display:column property="youtubeIdentifier" title="${youtubeIdentifierHeader}" sortable="false" />
	
	
	<jstl:set var="playita" value="addlearning-material"/>
	<jstl:if test="${requestURI.contains(playita)}">
	<display:column>
	<jstl:if test="${!master.contains(videos)}">
	<a href="master-class/cook/add-lm.do?masterClassId=${mci}&lmId=${videos.id}">
				<spring:message	code="learningMaterial.anadir" />
			</a>
	</jstl:if>
	<jstl:if test="${master.contains(videos) }">
	<a href="master-class/cook/remove-lm.do?masterClassId=${mci}&lmId=${videos.id}">
			<spring:message	code="learningMaterial.delette" />
		</a>
	</jstl:if>
	</display:column>
	</jstl:if>
	
	
	<security:authorize access="hasRole('COOK')">
    <display:column>
    <div>
	<a href="learning-material/videos/edit.do?videosId=${videos.id}">
				<spring:message	code="learningMaterial.edit" />
	</a>
    </div>
    </display:column>
    </security:authorize>
	
	</display:table>
	
	<security:authorize access="hasRole('COOK')">
	<div>
		<a href="learning-material/videos/create.do"> <spring:message
				code="learningMaterial.create" />
		</a>
	</div>
</security:authorize>
	
	
	<div style ="font-size: 35pt "><img src="images/presentacion.png" > <spring:message code="learningMaterial.presentation"/> </div>
	<display:table pagesize="5" class="displaytag" keepStatus="false"
	name="presentation" requestURI="${requestURI}" id="presentation">
	
	<spring:message code="learningMaterial.title" var="titleHeader" />
	<display:column property="title" title="${titleHeader}" sortable="true"/>

    <spring:message code="learningMaterial.abstracts" var="abstractsHeader" />
	<display:column property="abstracts" title="${abstractsHeader}" sortable="false" />
	
	<spring:message code="learningMaterial.attachment" var="attachmentHeader" />
	<display:column property="attachment" title="${attachmentHeader}" sortable="false" />
	
	<spring:message code="presentation.slideShareNetPath" var="slideShareNetPathHeader" />
	<display:column property="slideShareNetPath" title="${slideShareNetPathHeader}" sortable="false" />
	
	
	<jstl:set var="playita" value="addlearning-material"/>
	<jstl:if test="${requestURI.contains(playita)}">
	<display:column>
	<jstl:if test="${!master.contains(presentation) }">
	<a href="master-class/cook/add-lm.do?masterClassId=${mci}&lmId=${presentation.id}">
				<spring:message	code="learningMaterial.anadir" />
			</a>
	</jstl:if>
	<jstl:if test="${master.contains(presentation) }">
	<a href="master-class/cook/remove-lm.do?masterClassId=${mci}&lmId=${presentation.id}">
			<spring:message	code="learningMaterial.delette" />
		</a>
	</jstl:if>
	</display:column>
	</jstl:if>
	
	
	<security:authorize access="hasRole('COOK')">
    <display:column>
    <div>
	<a href="learning-material/presentation/edit.do?presentationId=${presentation.id}">
				<spring:message	code="learningMaterial.edit" />
	</a>
    </div>
    </display:column>
    </security:authorize>
	
	</display:table>
	
	<security:authorize access="hasRole('COOK')">
	<div>
		<a href="learning-material/presentation/create.do"> <spring:message
				code="learningMaterial.create" />
		</a>
	</div>
</security:authorize>


<!-- Action links -->

<!-- ---------COOK--------- -->
