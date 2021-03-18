<%--
 * header.jsp
 *
 * Copyright (C) 2016 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<a title="Home" href=""><img src="images/cabecera.png" alt="Acme-Pad-Thai Co., Inc." /> </a>
</div>

<div>
	<ul id="jMenu">
	
	    <li><a title="Home" href=""><img src="images/home.png" alt="Home" /> </a></li>
		
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
		
		<li><a class="fNiv"><spring:message	code="master.page.register" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/user/create.do"><spring:message code="master.page.registerUser" /></a></li>
					<li><a href="actor/nutritionist/create.do"><spring:message code="master.page.registerNutritionist" /></a></li>
					<li><a href="actor/sponsor/create.do"><spring:message code="master.page.registerSponsor" /></a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/list.do"><spring:message code="master.page.profile.actorList" /></a></li>
					<li><a href="social-identity/list.do"><spring:message code="master.page.profile.social-identity-list" /></a></li>
					<li><a href="folder/list.do"><spring:message code="master.page.profile.folderList" /></a></li>										
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="hasRole('USER')">
			<li><a class="fNiv"><spring:message	code="master.page.user1" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="recipe/user/list.do"><spring:message code="master.page.user.recipe" /></a></li>	
					<li><a href="recipe/user/create.do"><spring:message code="master.page.user.recipe.create" /></a></li>
					<li><a href="actor/user/edit.do"><spring:message code="master.page.user.edit" /></a></li>	
					<li><a href="actor/listClients.do"><spring:message code="master.page.listClients" /></a></li>	
					<li><a href="recipe/listByFollowed.do"><spring:message code="master.page.listByFollowed" /></a></li>
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="hasRole('NUTRITIONIST')">
			<li><a class="fNiv"><spring:message	code="master.page.nutri" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/nutritionist/edit.do"><spring:message code="master.page.nutritionist.edit" /></a></li>
					<li><a href="curricula/list.do"><spring:message code="master.page.nutritionist.curriculaList" /></a></li>
					<li><a href="ingredient/list.do"><spring:message code="master.page.nutritionist.ingredientList" /></a></li>	
					<li><a href="property/list.do"><spring:message code="master.page.nutritionist.propertyList" /></a></li>
					<li><a href="actor/listClients.do"><spring:message code="master.page.listClients" /></a></li>
					<li><a href="recipe/listByFollowed.do"><spring:message code="master.page.listByFollowed" /></a></li>		
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('COOK')">
			<li><a class="fNiv"><spring:message	code="master.page.cook" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/cook/edit.do"><spring:message code="master.page.cook.edit" /></a></li>	
					<li><a href="master-class/cook/list.do"><spring:message code="master.page.cook.masterclass.list" /></a></li>			
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>	
					<li><a href="campaign/administrator/list.do"><spring:message code="master.page.administrator.campaign.list" /></a></li>	
					<li><a href="actor/administrator/edit.do"><spring:message code="master.page.administrator.edit" /></a></li>
					<li><a href="actor/cook/create.do"><spring:message code="master.page.administrator.cook" /></a></li>
					<li><a href="spam/list.do"><spring:message code="master.page.administrator.spamList" /></a></li>
					<li><a href="category/list.do"><spring:message code="master.page.administrator.categoryList" /></a></li>	
					<li><a href="sponsor/list.do"><spring:message code="master.page.administrator.sponsorList" /></a></li>	
				</ul>
			</li>
		</security:authorize>
		<security:authorize access="hasRole('SPONSOR')">
			<li><a class="fNiv"><spring:message	code="master.page.sponsor" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="campaign/sponsor/list.do"><spring:message code="master.page.sponsor.campaign.list" /></a></li>
					<li><a href="campaign/sponsor/create.do"><spring:message code="master.page.sponsor.campaign.create" /></a></li>		
					<li><a href="actor/sponsor/edit.do"><spring:message code="master.page.sponsor.edit" /></a></li>	
					<li><a href="monthly-bill/list.do"><spring:message code="master.page.monthly-bill" /></a></li>						
				</ul>
			</li>
		</security:authorize>
		
		
		
		
		<li><a class="fNiv"><spring:message	code="master.page.catalogue" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="recipe/list.do"><spring:message code="master.page.recipe" /></a></li>
					<li><a href="user/list.do"><spring:message code="master.page.user" /></a></li>			
					<li><a href="contest/list.do"><spring:message code="master.page.contest" /></a></li>	
					<li><a href="master-class/list.do"><spring:message code="master.page.master-class" /></a></li>				
				</ul>
			</li>
			
			
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		
		<li><a class="fNiv"><spring:message	code="master.page.idioms"/>   </a>
	<ul>
	<li class="arrow"></li>
	<li><a href="?language=en"><img src="images/in.png" >   <spring:message code="master.page.idiom.english" /></a></li>
	<li><a href="?language=es"><img src="images/es.png" >   <spring:message code="master.page.idiom.spanish" /></a></li>
	</ul>
			</li>
	</ul>
</div>


