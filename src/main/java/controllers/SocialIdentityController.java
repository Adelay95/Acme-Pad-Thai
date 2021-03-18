package controllers;

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

import services.ActorService;
import services.SocialIdentityService;
import domain.Actor;
import domain.SocialIdentity;

@Controller
@RequestMapping("/social-identity")
public class SocialIdentityController extends AbstractController{
	
	@Autowired
	private SocialIdentityService sis;
	@Autowired
	private ActorService actorService;
	
	public SocialIdentityController(){
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Actor actor=actorService.findByPrincipal();
		SocialIdentity si = sis.create(actor);
		result = createEditModelAndView(si);

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<SocialIdentity> socids;
		Actor actor=actorService.findByPrincipal();
		socids=actor.getSocialIdentities();
		result= new ModelAndView("social-identity/list");
		result.addObject("socialIdentities",socids);
		result.addObject("requestURI","social-identity/list.do");
		
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int socialIdentityId){
		ModelAndView result;
		SocialIdentity socid;
		socid=sis.findOne(socialIdentityId);
		Assert.notNull(socid);
	    result = createEditModelAndView(socid);
		return result;
		
		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(SocialIdentity socialIdentity, BindingResult binding){
		ModelAndView result;
		
		try{
			sis.delete(socialIdentity);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(socialIdentity,"socialIdentity.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid SocialIdentity socialIdentity, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(socialIdentity);
		}else{
				try{
					sis.save(socialIdentity);
					result = new ModelAndView("redirect:/");}
				catch(Exception oops){
					result=createEditModelAndView(socialIdentity, "actor.commit.error");}
				
			}
			return result;
		
	}
	
	protected ModelAndView createEditModelAndView(SocialIdentity sis){
		ModelAndView result;
		
		result = createEditModelAndView(sis, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(SocialIdentity sis, String message){
		ModelAndView result;
		result = new ModelAndView("social-identity/edit");
		result.addObject("socialIdentity",sis);
		result.addObject("requestURI", "social-identity/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}
