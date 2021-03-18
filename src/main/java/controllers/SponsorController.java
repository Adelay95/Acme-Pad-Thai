package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.MonthlyBillService;
import services.SponsorService;
import domain.MonthlyBill;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController{
	
	@Autowired
	private SponsorService sponsorService;
	@Autowired
	private AdministratorService administratorService;
	@Autowired
	private MonthlyBillService monthlyBillService;

	public SponsorController(){
		super();
	}
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Sponsor> sponsors;
		sponsors=sponsorService.allSponsors();
		Collection<Sponsor> noMonthlyBill=sponsorService.sponsorMorosos2();
		result= new ModelAndView("sponsor/list");
		result.addObject("sponsors",sponsors);
		result.addObject("requestURI","sponsor/list.do");
		result.addObject("noMonthlyBill",noMonthlyBill);
		return result;
	}
	
	@RequestMapping(value= "/sendMoroso", method = RequestMethod.GET)
	public ModelAndView send(){
		ModelAndView result;
		try{
		Collection<Sponsor> morosos=sponsorService.sponsorMorosos();
		administratorService.messageMoroso(morosos);
	    result = list();
		}catch(Exception oops){
			result = list();
			result.addObject("message","sponsor.commit.error");
		}
		return result;
		
		
	}
	
	@RequestMapping(value= "/setMonthlyBill", method = RequestMethod.GET)
	public ModelAndView set(@RequestParam int sponsorId){
		ModelAndView result;
		Sponsor sponsor;
		Collection<Sponsor> noMonthlyBill=sponsorService.sponsorMorosos2();
		try{
		sponsor=sponsorService.findOne(sponsorId);
		Assert.isTrue(!noMonthlyBill.contains(sponsor));
		MonthlyBill monthlyBill=monthlyBillService.create(sponsor);
		monthlyBillService.save(monthlyBill);
	    result = list();
		} catch(Exception oops){
			result = list();
			result.addObject("message","sponsor.commit.error");
		}
		return result;
		
		
	}
	
	
}
	
	

