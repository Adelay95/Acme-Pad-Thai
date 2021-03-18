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
import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdministratorController extends AbstractController {
	
	//Services ------------------------------------------------------------
	
	@Autowired
	private AdministratorService adminService;
	@Autowired
	private UserAccountRepository uar;
	
	//Constructors --------------------------------------------------------
	
	public ActorAdministratorController(){
		super();
	}
	
	//Listing -------------------------------------------------------------
	
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
        Administrator admin;
		admin=adminService.findByPrincipal();
		Assert.notNull(admin);
	    result = createEditModelAndView(admin);
		return result;
		
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("actor")@Valid Administrator actor, BindingResult binding){
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
				adminService.save(actor);
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
				adminService.save(actor);
				result = new ModelAndView("redirect:/");
			} catch(Throwable oops){
				
				result=createEditModelAndView(actor, "actor.commit.error");}
			
		}
		}
	return result;
		
	}
	
	//Ancillary methods --------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Administrator admin){
		ModelAndView result;
		
		result = createEditModelAndView(admin, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Administrator admin, String message){
		ModelAndView result;
		result = new ModelAndView("actor/administrator/create");
		result.addObject("actor",admin);
		result.addObject("requestURI", "actor/administrator/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}