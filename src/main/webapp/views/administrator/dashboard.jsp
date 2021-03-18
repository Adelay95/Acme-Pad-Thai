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

<div id="contenidos" class="mostrar">
		<div id="estadisticas">
		<fieldset>
		<spring:message code="admin.statistics" var="statisticsHeader" />
			<legend><b><jstl:out value="${statisticsHeader}" /></b></legend>
		<table>
		<tr><td><table>
				<spring:message code="admin.minimum" var="minimumHeader" />
				<spring:message code="admin.average" var="averageHeader" />
				<spring:message code="admin.maximum" var="maximumHeader" />
				<tr>
					<th>&nbsp;</th><th><jstl:out value="${minimumHeader}" /></th>
					<th><jstl:out value="${averageHeader}" /></th>
					<th><jstl:out value="${maximumHeader}" /></th>
				</tr>
				<spring:message code="admin.recipe.author" var="recipeUserHeader" />
				<tr>
					<th><jstl:out value="${recipeUserHeader}: " /></th>
					<td><jstl:out value="${minimunRecipeUser}" /></td>
					<td><jstl:out value="${averageRecipeUser}" /></td>
					<td><jstl:out value="${maximumRecipeUser}" /></td>
				</tr>
				<spring:message code="admin.recipe.contest" var="recipeContestHeader" />
				<tr>
					<th><jstl:out value="${recipeContestHeader}: " /></th>
					<td><jstl:out value="${minimunRecipeContest}" /></td>
					<td><jstl:out value="${averageRecipeContest}" /></td>
					<td><jstl:out value="${maximumRecipeContest}" /></td>
				</tr>
				<spring:message code="admin.campaign.sponsor" var="campaignSponsorHeader" />
				<tr>
					<th><jstl:out value="${campaignSponsorHeader}: " /></th>
					<td><jstl:out value="${minimumCampaignSponsor}" /></td>
					<td><jstl:out value="${averageCampaignSponsor}" /></td>
					<td><jstl:out value="${maximumCampaignSponsor}" /></td>
				</tr>
				<spring:message code="admin.campaign.sponsor.actives" var="campaignSponsorActivesHeader" />
				<tr>
					<th><jstl:out value="${campaignSponsorActivesHeader}: " /></th>
					<td><jstl:out value="${minimunCampaignSponsorActives}" /></td>
					<td><jstl:out value="${averageCampaignSponsorActives}" /></td>
					<td><jstl:out value="${maximumCampaignSponsorActives}" /></td>
				</tr>
			</table></td></tr>
			<tr>
			<spring:message code="admin.user.MoreAuthored" var="userMoreAuthoredHeader" />
				<td><b><jstl:out value="${userMoreAuthoredHeader}: " /></b>
				<jstl:set var="n" value="0"/>
					<jstl:forEach var="x" items="${userMoreAuthored}">
					<jstl:if test="${n==0}">
						<a href="user/display.do?userId=${x.id}"><jstl:out value="${x.name} ${x.surname}" /></a></jstl:if>
					<jstl:if test="${n!=0}">
					<a href="user/display.do?userId=${x.id}"><jstl:out value=", ${x.name} ${x.surname}" /></a>
					</jstl:if>
					<jstl:set var="n" value="1"/>
				</jstl:forEach>
				</td>
			</tr>
			<tr>
			<spring:message code="admin.contest.MoreQualified" var="contestMoreQualifiedHeader" />
				<td><b><jstl:out value="${contestMoreQualifiedHeader}: " /></b>
				<jstl:set var="n" value="0"/>
					<jstl:forEach var="x" items="${contestMoreQualified}">
					<jstl:if test="${n==0}">
						<jstl:out value="${x.title}" /></jstl:if>
					<jstl:if test="${n!=0}">
					<jstl:out value=", ${x.title}" />
					</jstl:if>
					<jstl:set var="n" value="1"/>
				</jstl:forEach>
				</td>
			</tr>
			<tr>
			<spring:message code="admin.sponsor.noactive" var="sponsorNoActiveHeader" />
				<td><b><jstl:out value="${sponsorNoActiveHeader}: " /></b>
				<jstl:set var="n" value="0"/>
					<jstl:forEach var="x" items="${sponsorsNoActive}">
					<jstl:if test="${n==0}">
						<jstl:out value="${x.name} ${x.surname}" /></jstl:if>
					<jstl:if test="${n!=0}">
					<jstl:out value=", ${x.name} ${x.surname}" />
					</jstl:if>
					<jstl:set var="n" value="1"/>
				</jstl:forEach>
				</td>
			</tr>
				<tr>
			<spring:message code="admin.companies.spent.average" var="companiesSpentAverageHeader" />
				<td><b><jstl:out value="${companiesSpentAverageHeader}: " /></b>
				<jstl:set var="n" value="0"/>
					<jstl:forEach var="x" items="${companiesSpentAverage}">
					<jstl:if test="${n==0}">
						<jstl:out value="${x}" /></jstl:if>
					<jstl:if test="${n!=0}">
					<jstl:out value=", ${x}" />
					</jstl:if>
					<jstl:set var="n" value="1"/>
				</jstl:forEach>
				</td>
			</tr>
			<tr>
			<spring:message code="admin.companies.spent.90" var="companiesSpent90Header" />
				<td><b><jstl:out value="${companiesSpent90Header}: " /></b>
				<jstl:set var="n" value="0"/>
					<jstl:forEach var="x" items="${companiesSpent90}">
					<jstl:if test="${n==0}">
						<jstl:out value="${x}" /></jstl:if>
					<jstl:if test="${n!=0}">
					<jstl:out value=", ${x}" />
					</jstl:if>
					<jstl:set var="n" value="1"/>
				</jstl:forEach>
				</td>
			</tr>
			<tr><td><table>
				<spring:message code="admin.average" var="averageHeader" />
				<spring:message code="admin.deviation" var="deviationHeader" />
				<tr>
					<th>&nbsp;</th>
					<th><jstl:out value="${averageHeader}" /></th>
					<th><jstl:out value="${deviationHeader}" /></th>
				</tr>
				<spring:message code="admin.recipe.steps" var="recipeStepsHeader" />
				<tr>
					<th><jstl:out value="${recipeStepsHeader}: " /></th>
					<td><jstl:out value="${averageRecipeSteps}" /></td>
					<td><jstl:out value="${deviationRecipeSteps}" /></td>
				</tr>
				<spring:message code="admin.recipe.ingredients" var="recipeIngredientsHeader" />
				<tr>
					<th><jstl:out value="${recipeIngredientsHeader}: " /></th>
					<td><jstl:out value="${averageRecipeIngredients}" /></td>
					<td><jstl:out value="${deviationRecipeIngredients}" /></td>
				</tr>
				<spring:message code="admin.bills.paid" var="billSpaidHeader" />
				<tr>
					<th><jstl:out value="${billSpaidHeader}: " /></th>
					<td><jstl:out value="${averageBillsPaid}" /></td>
					<td><jstl:out value="${deviationBillsPaid}" /></td>
				</tr>
				<spring:message code="admin.bills.unpaid" var="billsUnpaidHeader" />
				<tr>
					<th><jstl:out value="${billsUnpaidHeader}: " /></th>
					<td><jstl:out value="${averageBillsUnPaid}" /></td>
					<td><jstl:out value="${deviationBillsUnPaid}" /></td>
				</tr>
			</table></td></tr>
			
			<spring:message code="admin.users.popularity" var="popularityListHeader" />
			<spring:message code="admin.users.taste" var="tasteListHeader" />
			<tr><td><h4><a href="administrator/popularity-list.do"><jstl:out value="${popularityListHeader}" /></a></h4>
			</td></tr><tr><td><h4><a href="administrator/taste-list.do"><jstl:out value="${tasteListHeader}" /></a></h4>
			</td>
			</tr>
<%-- COMPAÑIAS  --%>
			<tr><td><table>
				<spring:message code="admin.ranking.company.campaign" var="rankingCompanyCampaignHeader" />
				<spring:message code="admin.ranking.company.bills" var="rankingCompanyBillsHeader" />
				<tr>
					<th><jstl:out value="${rankingCompanyCampaignHeader}" /></th>
					<th><jstl:out value="${rankingCompanyBillsHeader}" /></th>
				</tr>
				
				<tr>
					
					<td>
						<display:table pagesize="5" class="displaytag" keepStatus="false"
						name="rankingCompanyCampaign" requestURI="${requestURI}" id="row">
						<spring:message code="admin.company.name" var="nameHeader" />
						<display:column title="${nameHeader}" sortable="true" >
						<jstl:out value="${row}" />
						</display:column>
						</display:table>
					</td>
					<td>
						<display:table pagesize="5" class="displaytag" keepStatus="false"
						name="rankingCompanyBills" requestURI="${requestURI}" id="row2">
						<spring:message code="admin.company.name" var="nameHeader" />
						<display:column title="${nameHeader}" sortable="true" >
						<jstl:out value="${row2}" />
						</display:column>
						</display:table>
					</td>
				</tr>
				</table></td></tr>
			<tr><td><table>
				<tr>
					<th>&nbsp;</th><th><jstl:out value="${minimumHeader}" /></th>
					<th><jstl:out value="${averageHeader}" /></th>
					<th><jstl:out value="${maximumHeader}" /></th>
					<th><jstl:out value="${deviationHeader}" /></th>
				</tr>
				<spring:message code="admin.masterclass.cook" var="masterClassCookHeader" />
				<tr>
					<th><jstl:out value="${masterClassCookHeader}: " /></th>
					<td><jstl:out value="${minMasterClassCook}" /></td>
					<td><jstl:out value="${avgMasterClassCook}" /></td>
					<td><jstl:out value="${maxMasterClassCook}" /></td>
					<td><jstl:out value="${devMasterClassCook}" /></td>
				</tr>
				</table></td></tr>
				
				<tr><td><table>
				<spring:message code="admin.videos" var="videosHeader" />
				<spring:message code="admin.texts" var="textsHeader" />
				<spring:message code="admin.presentations" var="presentationsHeader" />
				<tr>
					<th>&nbsp;</th><th><jstl:out value="${videosHeader}" /></th>
					<th><jstl:out value="${textsHeader}" /></th>
					<th><jstl:out value="${presentationsHeader}" /></th>
				</tr>
				<spring:message code="admin.materials.masterclass" var="materialsMasterClassHeader" />
				<tr>
					<th><jstl:out value="${materialsMasterClassHeader}: " /></th>
					<td><jstl:out value="${avgVideosMasterClass}" /></td>
					<td><jstl:out value="${avgTextMasterClass}" /></td>
					<td><jstl:out value="${avgPresentationMasterClass}" /></td>
				</tr>
				</table></td></tr>
			<tr>
			<spring:message code="admin.masterclass.promoted" var="masterclassPromotedHeader" />
				<td><b><jstl:out value="${masterclassPromotedHeader}: " /></b>
				<jstl:out value="${numberMasterClassPromoted}" />
				</td>
			</tr>
			<tr>
			<spring:message code="admin.avgcook.promoted" var="avgCookPromotedHeader" />
				<td><b><jstl:out value="${avgCookPromotedHeader}: " /></b>
				<jstl:out value="${avgCookPromoted}" />
			</td>
			</tr>
			<tr>
			<spring:message code="admin.avgcook.demoted" var="avgCookDemotedHeader" />
				<td><b><jstl:out value="${avgCookDemotedHeader}: " /></b>
				<jstl:out value="${avgCookDemoted}" />
			</td>
			</tr>
			<spring:message code="admin.ranking.cook" var="rankingCookHeader" />
			<tr><td><h4><a href="administrator/cook-list.do"><jstl:out value="${rankingCookHeader}" /></a></h4>
			</td></tr>
			</table>
			</fieldset>
			</div>
		</div>
