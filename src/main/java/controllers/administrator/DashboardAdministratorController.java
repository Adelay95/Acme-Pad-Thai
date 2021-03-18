package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

import controllers.AbstractController;
import domain.Contest;
import domain.Cook;
import domain.Sponsor;
import domain.User;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController extends AbstractController{
	// Services ---------------------------------------------------------------

		@Autowired
		private AdministratorService administratorService;	
		
		// Constructors -----------------------------------------------------------
		
		public DashboardAdministratorController() {
			super();
		}
		// Listing ----------------------------------------------------------------
		
		
		@RequestMapping(value = "/popularity-list", method = RequestMethod.GET)
		public ModelAndView popularityList() {
			ModelAndView result;
			Collection<User> users;
			users=administratorService.popularityListing();
			result = new ModelAndView("user/list");
			result.addObject("requestURI", "/administrator/popularity-list.do");
			result.addObject("users", users);
			return result;
		}
		
		@RequestMapping(value = "/taste-list", method = RequestMethod.GET)
		public ModelAndView tasteList() {
			ModelAndView result;
			Collection<User> users;
			users=administratorService.tastesUsers();
			result = new ModelAndView("user/list");
			result.addObject("requestURI", "/administrator/taste-list.do");
			result.addObject("users", users);
			return result;
		}
		
		@RequestMapping(value = "/cook-list", method = RequestMethod.GET)
		public ModelAndView cookList() {
			ModelAndView result;
			Collection<Cook> actors;
			actors=administratorService.rankingMasterClassPromeoted();
			result = new ModelAndView("actor/list");
			result.addObject("requestURI", "/administrator/cook-list.do");
			result.addObject("actors", actors);
			return result;
		}
		
		@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
		public ModelAndView dashboard() {
			ModelAndView result;
			Integer minimunRecipeUser=administratorService.minRecipesUser();
			Double averageRecipeUser=administratorService.avgRecipesUser();
			Integer maximumRecipeUser=administratorService.maxRecipesUser();
			Integer minimunRecipeContest=administratorService.minRecipesContests();
			Double averageRecipeContest=administratorService.avgRecipesContests();
			Integer maximumRecipeContest=administratorService.maxRecipesContests();
			Collection<User> userMoreAuthored=administratorService.userMaxRecipes();
			Collection<Contest> contestMoreQualified=administratorService.getcontestMaxRecipesQualificated();
			Double averageRecipeSteps=administratorService.avgStepsRecipes();
			Double deviationRecipeSteps=administratorService.devStepsRecipes();
			Double averageRecipeIngredients=administratorService.avgQuantitiesRecipes();
			Double deviationRecipeIngredients=administratorService.devQuantitiesRecipes();
			
			Integer minimumCampaignSponsor=administratorService.minCampaignSponsor();
			Double averageCampaignSponsor=administratorService.avgCampaignSponsor();
			Integer maximumCampaignSponsor=administratorService.maxCampaignSponsor();
			Integer minimunCampaignSponsorActives=administratorService.minCampaignSponsorActives();
			Double averageCampaignSponsorActives=administratorService.avgCampaignSponsorActives();
			Integer maximumCampaignSponsorActives=administratorService.maxCampaignSponsorActives();
			Double averageBillsPaid=administratorService.AvgPaidBills();
			Double deviationBillsPaid=administratorService.DevPaidBills();
			Double averageBillsUnPaid=administratorService.avgCampaignSponsorActives();
			Double deviationBillsUnPaid=administratorService.DevUnpaidBills();
			Collection<Sponsor> sponsorsNoActive=administratorService.sponsorNoActive();
			Collection<String> companiesSpentAverage=administratorService.campaignNoActive();
			Collection<String> companiesSpent90=administratorService.campaign90();
			
			Collection<String> rankingCompanyCampaign=administratorService.rankingCompanyName();
			Collection<String> rankingCompanyBills=administratorService.rankingMonthlyBills();
			Integer minMasterClassCook=administratorService.minMasterClassCook();
			Integer maxMasterClassCook=administratorService.maxMasterClassCook();
			Double avgMasterClassCook=administratorService.avgMasterClassCook();
			Double devMasterClassCook=administratorService.devMasterClassCook();
			Double avgPresentationMasterClass=administratorService.avgPresentationMasterClass();
			Double avgTextMasterClass=administratorService.avgTextMasterClass();
			Double avgVideosMasterClass=administratorService.avgVideosMasterClass();
			Integer numberMasterClassPromoted=administratorService.numMasterClassPromoted();
			Double avgCookPromoted=administratorService.avgCookNumPromoted();
			Double avgCookDemoted=administratorService.avgCookNumDemoted();
			
			result = new ModelAndView("administrator/dashboard");
			result.addObject("requestURI", "administrator/dashboard.do");
			result.addObject("minimunRecipeUser", minimunRecipeUser);
			result.addObject("averageRecipeUser", averageRecipeUser);
			result.addObject("maximumRecipeUser", maximumRecipeUser);
			result.addObject("minimunRecipeContest", minimunRecipeContest);
			result.addObject("averageRecipeContest", averageRecipeContest);
			result.addObject("maximumRecipeContest", maximumRecipeContest);
			result.addObject("userMoreAuthored", userMoreAuthored);
			result.addObject("contestMoreQualified", contestMoreQualified);
			result.addObject("averageRecipeSteps", averageRecipeSteps);
			result.addObject("deviationRecipeSteps", deviationRecipeSteps);
			result.addObject("averageRecipeIngredients", averageRecipeIngredients);
			result.addObject("deviationRecipeIngredients", deviationRecipeIngredients);
			
			result.addObject("minimumCampaignSponsor", minimumCampaignSponsor);
			result.addObject("averageCampaignSponsor", averageCampaignSponsor);
			result.addObject("maximumCampaignSponsor", maximumCampaignSponsor);
			result.addObject("minimunCampaignSponsorActives", minimunCampaignSponsorActives);
			result.addObject("averageCampaignSponsorActives", averageCampaignSponsorActives);
			result.addObject("maximumCampaignSponsorActives", maximumCampaignSponsorActives);
			result.addObject("averageBillsPaid", averageBillsPaid);
			result.addObject("deviationBillsPaid", deviationBillsPaid);
			result.addObject("averageBillsUnPaid", averageBillsUnPaid);
			result.addObject("deviationBillsUnPaid", deviationBillsUnPaid);
			result.addObject("sponsorsNoActive", sponsorsNoActive);
			result.addObject("companiesSpentAverage", companiesSpentAverage);
			result.addObject("companiesSpent90", companiesSpent90);
			
			result.addObject("minMasterClassCook", minMasterClassCook);
			result.addObject("maxMasterClassCook", maxMasterClassCook);
			result.addObject("avgMasterClassCook", avgMasterClassCook);
			result.addObject("devMasterClassCook", devMasterClassCook);
			result.addObject("avgPresentationMasterClass", avgPresentationMasterClass);
			result.addObject("avgTextMasterClass", avgTextMasterClass);
			result.addObject("avgVideosMasterClass", avgVideosMasterClass);
			result.addObject("numberMasterClassPromoted", numberMasterClassPromoted);
			result.addObject("avgCookPromoted", avgCookPromoted);
			result.addObject("avgCookDemoted", avgCookDemoted);
			result.addObject("rankingCompanyCampaign", rankingCompanyCampaign);
			result.addObject("rankingCompanyBills", rankingCompanyBills);
			return result;
		}
		
}