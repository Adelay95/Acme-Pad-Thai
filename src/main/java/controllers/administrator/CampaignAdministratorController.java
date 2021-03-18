package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CampaignService;

import controllers.AbstractController;
import domain.Campaign;

@Controller
@RequestMapping("/campaign/administrator")
public class CampaignAdministratorController extends AbstractController{
	// Services ---------------------------------------------------------------

		@Autowired
		private CampaignService campaignService;	
		
		// Constructors -----------------------------------------------------------
		
		public CampaignAdministratorController() {
			super();
		}
		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Campaign> campaigns;

			campaigns = campaignService.allCampaign();
			
			
			result = new ModelAndView("campaign/list");
			result.addObject("requestURI", "campaign/administrator/list.do");
			result.addObject("campaigns", campaigns);
			return result;
		}
		
	// Edition ----------------------------------------------------------------
		
		@RequestMapping(value = "/edit", method = RequestMethod.GET)
		public ModelAndView edit(@RequestParam int campaignId) {
			ModelAndView result;
			Campaign campaign;

			campaign = campaignService.findOne(campaignId);		
			Assert.notNull(campaign);
			result = createEditModelAndView(campaign);

			return result;
		}

		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@Valid Campaign campaign, BindingResult binding) {
			ModelAndView result;
			if (binding.hasErrors()) {
				result = createEditModelAndView(campaign);
			} else {
				try {
					campaignService.save(campaign);					
					result = new ModelAndView("redirect:list.do");
				} catch (Throwable oops) {
					result = createEditModelAndView(campaign, "campaign.commit.error");				
				}
			}

			return result;
		}
		// Ancillary methods ------------------------------------------------------
		
				protected ModelAndView createEditModelAndView(Campaign campaign) {
					ModelAndView result;
					result = createEditModelAndView(campaign, null);
					return result;
				}	
				
				protected ModelAndView createEditModelAndView(Campaign campaign, String message) {
					ModelAndView result;
					result = new ModelAndView("campaign/edit");
					result.addObject("requestURI", "campaign/administrator/edit.do");
					result.addObject("campaign", campaign);
					result.addObject("message", message);
					return result;
				}

}
