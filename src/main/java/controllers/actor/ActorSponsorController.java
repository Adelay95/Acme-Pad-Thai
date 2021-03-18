package controllers.actor;

import javax.validation.Valid;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
import security.UserAccountRepository;
import services.SponsorService;
import domain.Sponsor;

@Controller
@RequestMapping("/actor/sponsor")
public class ActorSponsorController {
	//Services ------------------------------------------------------------
	
		@Autowired
		private SponsorService sponsorService;
		@Autowired
		private UserAccountRepository uar;
		
		//Constructors --------------------------------------------------------
		
		public ActorSponsorController(){
			super();
		}
		
		//Listing -------------------------------------------------------------
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Sponsor sponsor = sponsorService.create();
			result = createEditModelAndView(sponsor);

			return result;
		}
		
		@RequestMapping(value= "/edit", method = RequestMethod.GET)
		public ModelAndView edit(){
			ModelAndView result;
			Sponsor sponsor;
			sponsor=sponsorService.findByPrincipal();
			Assert.notNull(sponsor);
		    result = createEditModelAndView(sponsor);
			return result;
			
			
		}
		
		@SuppressWarnings("deprecation")
		@RequestMapping(value= "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@ModelAttribute("actor")@Valid Sponsor actor, BindingResult binding){
			ModelAndView result;
			if(actor.getPostalAdress()==""){
				actor.setPostalAdress(null);
				}
			LocalDate hoy=LocalDate.now();
			
			
			if(actor.getPhoneNumber()=="" && binding.getErrorCount()==1){
				actor.setPhoneNumber(null);
				try{

					if(actor.getCreditCard().getExpirationYear()>hoy.getYear() || 
							(actor.getCreditCard().getExpirationYear()==hoy.getYear() && 
							actor.getCreditCard().getExpirationMonth()>hoy.getMonthOfYear())){
						
					
					if(actor.getId()==0){
					String oldPs=actor.getUserAccount().getPassword();
					Md5PasswordEncoder encoder = new Md5PasswordEncoder();
					String hash = encoder.encodePassword(oldPs, null);
		            UserAccount old=actor.getUserAccount();
		            old.setPassword(hash);
		            UserAccount newOne=uar.save(old);
		            actor.setUserAccount(newOne);
					}
					sponsorService.save(actor);
					result = new ModelAndView("redirect:/");
					}else{
						result=createEditModelAndView(actor, "actor.commit.error");
					}
				} catch(Throwable oops){
					
					result=createEditModelAndView(actor, "actor.commit.error");}
			}else{
			
			if(binding.hasErrors()){
				result= createEditModelAndView(actor);
			}else{
				try{
					if(actor.getCreditCard().getExpirationYear()>hoy.getYear() || 
							(actor.getCreditCard().getExpirationYear()==hoy.getYear() && 
							actor.getCreditCard().getExpirationMonth()>hoy.getMonthOfYear())){
					if(actor.getId()==0){
					String oldPs=actor.getUserAccount().getPassword();
					Md5PasswordEncoder encoder = new Md5PasswordEncoder();
					String hash = encoder.encodePassword(oldPs, null);
		            UserAccount old=actor.getUserAccount();
		            old.setPassword(hash);
		            UserAccount newOne=uar.save(old);
		            actor.setUserAccount(newOne);
					}
					sponsorService.save(actor);
					result = new ModelAndView("redirect:/");
					}else{
						result=createEditModelAndView(actor, "actor.commit.error");
					}
				} catch(Throwable oops){
					
					result=createEditModelAndView(actor, "actor.commit.error");}
			}
				
			}
			
			
		return result;
			
		}
		
		//Ancillary methods --------------------------------------------------------
		
		protected ModelAndView createEditModelAndView(Sponsor sponsor){
			ModelAndView result;
			
			result = createEditModelAndView(sponsor, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Sponsor sponsor, String message){
			ModelAndView result;
			result = new ModelAndView("actor/sponsor/create");
			result.addObject("actor",sponsor);
			result.addObject("requestURI", "actor/sponsor/edit.do");
			result.addObject("message",message);
			return result;
			
			
		}
		
		
}