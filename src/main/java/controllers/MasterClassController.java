package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import services.MasterClassService;
import domain.Actor;
import domain.MasterClass;


@Controller
@RequestMapping("/master-class")
public class MasterClassController extends AbstractController{
	
	// Services ---------------------------------------------------------------

			@Autowired
			private MasterClassService masterClassService;
			@Autowired
			private ActorService actorService;
			@Autowired
			private AdministratorService administratorService;
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
	
		Collection<MasterClass> masterClasses;
		masterClasses =masterClassService.allMasterClass();
		try{
		Actor actor= actorService.findByPrincipal();
			
		result = new ModelAndView("master-class/list");
		result.addObject("requestURI", "master-class/list.do");
		result.addObject("masterClasses", masterClasses);
		result.addObject("actor", actor);
		}catch(Exception oops){
			
			result = new ModelAndView("master-class/list");
			result.addObject("requestURI", "master-class/list.do");
			result.addObject("masterClasses", masterClasses);
			result.addObject("actor", null);
		}
		
		return result;
	}
	
	
	@RequestMapping(value = "/attend", method = RequestMethod.GET)
	public ModelAndView attend(@RequestParam int masterClassId) {
		ModelAndView result;
		
		MasterClass a= masterClassService.findOne(masterClassId);
		Actor actor= actorService.findByPrincipal();

		actorService.registerMC(a, actor);
		
		result = new ModelAndView("redirect:list.do");
		
		return result;
	}
	@RequestMapping(value = "/promote", method = RequestMethod.GET)
	 public ModelAndView promote(@RequestParam int masterClassId) {
	  ModelAndView result;
	  
	  administratorService.promote(masterClassId);
	  result = new ModelAndView("redirect:list.do");
	  
	  return result;
	 }
	 
	 @RequestMapping(value = "/demote", method = RequestMethod.GET)
	 public ModelAndView depromote(@RequestParam int masterClassId) {
	  ModelAndView result;
	  
	  administratorService.depromote(masterClassId);
	  result = new ModelAndView("redirect:list.do");
	  
	  return result;
	 }
	
}
