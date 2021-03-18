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
import services.UserService;
import controllers.AbstractController;
import domain.User;

@Controller
@RequestMapping("/actor/user")
public class ActorUserController extends AbstractController {
	
	//Services ------------------------------------------------------------
	
	@Autowired
	private UserService userService;
	@Autowired
	private UserAccountRepository uar;
	
	//Constructors --------------------------------------------------------
	
	public ActorUserController(){
		super();
	}
	
	//Listing -------------------------------------------------------------
	
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		User user = userService.create();
		result = createEditModelAndView(user);

		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		ModelAndView result;
        User user;
		user=userService.findByPrincipal();
		Assert.notNull(user);
	    result = createEditModelAndView(user);
		return result;
		
		
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("actor")@Valid User actor, BindingResult binding){
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
				userService.save(actor);
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
				userService.save(actor);
				result = new ModelAndView("redirect:/");
			} catch(Throwable oops){
				
				result=createEditModelAndView(actor, "actor.commit.error");}
			
		}
		}
	return result;
		
	}
	
	//Ancillary methods --------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(User user){
		ModelAndView result;
		
		result = createEditModelAndView(user, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(User user, String message){
		ModelAndView result;
		result = new ModelAndView("actor/user/create");
		result.addObject("actor",user);
		result.addObject("requestURI", "actor/user/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}
