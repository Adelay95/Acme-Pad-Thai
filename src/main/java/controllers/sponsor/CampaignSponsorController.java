package controllers.sponsor;

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

import services.BannerService;
import services.CampaignService;
import services.SponsorService;
import controllers.AbstractController;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;

@Controller
@RequestMapping("/campaign/sponsor")
public class CampaignSponsorController extends AbstractController{
	// Services ---------------------------------------------------------------
	
		@Autowired
		private SponsorService sponsorService;	
		@Autowired
		private BannerService bannerService;	
		
		@Autowired
		private CampaignService campaignService;	
		
		// Constructors -----------------------------------------------------------
		
		public CampaignSponsorController() {
			super();
		}
		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Campaign> campaigns;

			campaigns = sponsorService.campaignList();
			
			
			result = new ModelAndView("campaign/list");
			result.addObject("requestURI", "campaign/sponsor/list.do");
			result.addObject("campaigns", campaigns);
			return result;
		}
		
		// Creation ---------------------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Campaign campaign;
			Sponsor sponsor=sponsorService.findByPrincipal();
			campaign = campaignService.create(sponsor);
			result = createEditModelAndView(campaign);

			return result;
		}
		
		@RequestMapping(value = "/create-banner", method = RequestMethod.GET)
		public ModelAndView create2(@RequestParam int campaignId) {
			ModelAndView result;
			Campaign campaign;
			campaign = campaignService.findOne(campaignId);
			Banner banner=bannerService.create(campaign);
			banner.setCampaign(campaign);
			result = createEditBannerModelAndView(banner);

			return result;
		}

		protected ModelAndView createEditBannerModelAndView(Banner banner) {
			ModelAndView result;
			result =createEditBannerModelAndView(banner,null);
			return result;
		}
		private ModelAndView createEditBannerModelAndView(Banner banner,
				String message) {
			ModelAndView result;
			result= new ModelAndView("campaign/banner");
			result.addObject("banner", banner);
			result.addObject("requestURI", "campaign/sponsor/banner/create.do");
			result.addObject("message", message);
			return result;
		}
		
		@RequestMapping(value = "/banner/create", method = RequestMethod.POST, params = "save")
		public ModelAndView save2(@Valid Banner banner, BindingResult binding) {
			ModelAndView result;
			if (binding.hasErrors()) {
				result = createEditBannerModelAndView(banner);
			} else {
				try {
					bannerService.save(banner);				
					result = new ModelAndView("redirect:/campaign/sponsor/list.do");
				} catch (Throwable oops) {
					result = createEditBannerModelAndView(banner, "campaign.commit.error");				
				}
			}

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
				
		@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
		public ModelAndView delete(Campaign campaign, BindingResult binding) {
			ModelAndView result;

			try {			
				sponsorService.borrarCampaign(campaign);
				result = new ModelAndView("redirect:list.do");						
			} catch (Throwable oops) {
				result = createEditModelAndView(campaign, "campaign.commit.error");
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
			result.addObject("requestURI", "campaign/sponsor/edit.do");
			result.addObject("campaign", campaign);
			result.addObject("message", message);
			return result;
		}

}
