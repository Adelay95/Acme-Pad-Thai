package controllers.actor;

import javax.validation.Valid;


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
import services.NutritionistService;
import domain.Nutritionist;


@Controller
@RequestMapping("/actor/nutritionist")
public class ActorNutritionistController {
	//Services ------------------------------------------------------------
	
		@Autowired
		private NutritionistService nutriService;
		@Autowired
		private UserAccountRepository uar;
		
		//Constructors --------------------------------------------------------
		
		public ActorNutritionistController(){
			super();
		}
		
		//Listing -------------------------------------------------------------
		
		
		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			ModelAndView result;
			Nutritionist nutri = nutriService.create();
			result = createEditModelAndView(nutri);

			return result;
		}
		
		@RequestMapping(value= "/edit", method = RequestMethod.GET)
		public ModelAndView edit(){
			ModelAndView result;
			Nutritionist nutri;
			nutri=nutriService.findByPrincipal();
			Assert.notNull(nutri);
		    result = createEditModelAndView(nutri);
			return result;
			
			
		}
		
		@RequestMapping(value= "/edit", method = RequestMethod.POST, params = "save")
		public ModelAndView save(@ModelAttribute("actor")@Valid Nutritionist actor, BindingResult binding){
			ModelAndView result;
			if(actor.getPostalAdress()==""){
				actor.setPostalAdress(null);
				}
			if(actor.getPhoneNumber()=="" && binding.getErrorCount()==1){
				actor.setPhoneNumber(null);
				try{
					if(actor.getId()==0){
					String oldPs=actor.getUserAccount().getPassword();
					Md5PasswordEncoder encoder = new Md5PasswordEncoder();
					String hash = encoder.encodePassword(oldPs, null);
		            UserAccount old=actor.getUserAccount();
		            old.setPassword(hash);
		            UserAccount newOne=uar.save(old);
		            actor.setUserAccount(newOne);
					}
					nutriService.save(actor);
					result = new ModelAndView("redirect:/");
				} catch(Throwable oops){
					
					result=createEditModelAndView(actor, "actor.commit.error");}
			}else{
			if(binding.hasErrors()){
				result= createEditModelAndView(actor);
			}else{
				try{
					if(actor.getId()==0){
				
					String oldPs=actor.getUserAccount().getPassword();
					Md5PasswordEncoder encoder = new Md5PasswordEncoder();
					String hash = encoder.encodePassword(oldPs, null);
		            UserAccount old=actor.getUserAccount();
		            old.setPassword(hash);
		            UserAccount newOne=uar.save(old);
		            actor.setUserAccount(newOne);
					}
					nutriService.save(actor);
					result = new ModelAndView("redirect:/");
				} catch(Throwable oops){
					
					result=createEditModelAndView(actor, "actor.commit.error");}
				
			}
			}
		return result;
			
		}
		
		//Ancillary methods --------------------------------------------------------
		
		protected ModelAndView createEditModelAndView(Nutritionist nutri){
			ModelAndView result;
			
			result = createEditModelAndView(nutri, null);
			
			return result;
		}
		
		protected ModelAndView createEditModelAndView(Nutritionist nutri, String message){
			ModelAndView result;
			result = new ModelAndView("actor/nutritionist/create");
			result.addObject("actor",nutri);
			result.addObject("requestURI", "actor/nutritionist/edit.do");
			result.addObject("message",message);
			return result;
			
			
		}
		
		
}
