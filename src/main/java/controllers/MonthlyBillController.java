package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MonthlyBillService;
import services.SponsorService;
import domain.MonthlyBill;
import domain.Sponsor;

@Controller
@RequestMapping("/monthly-bill")
public class MonthlyBillController {
	
	@Autowired 
	private SponsorService sponsorService;
	@Autowired 
	private MonthlyBillService monthlyBillService;
	
	
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			
			Sponsor sponsor= sponsorService.findByPrincipal();
			Collection<MonthlyBill> monthlybills= sponsor.getMonthlyBills();
			
			result = new ModelAndView("monthly-bill/list");
			result.addObject("requestURI", "monthly-bill/list.do");
			result.addObject("monthlybills", monthlybills);
		
			
			return result;
		}

		@RequestMapping(value = "/paid", method = RequestMethod.GET)
		public ModelAndView paid(@RequestParam int monthlyBillId) {
			ModelAndView result;
			MonthlyBill m= monthlyBillService.findOne(monthlyBillId);
			sponsorService.payMonthlyBills(m);
			
			result = new ModelAndView("redirect:list.do");
		
			
			return result;
		}
}
