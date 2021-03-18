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

import services.CurriculaService;
import services.EndorserService;
import domain.Curricula;
import domain.Endorser;


@Controller
@RequestMapping("/endorser")
public class EndorserController {

	@Autowired
	private EndorserService endorserService;
	@Autowired
	private CurriculaService curriculaService;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int curriculaId) {
		ModelAndView result;
		Curricula curri= curriculaService.findOne(curriculaId);
		Endorser endorser = endorserService.create(curri);
		result = createEditModelAndView(endorser);
		result.addObject("curri",curri);
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int curriculaId) {
		ModelAndView result;
		Collection<Endorser> endorser ;
		endorser=endorserService.getEndorser(curriculaId);
		result= new ModelAndView("endorser/list");
		result.addObject("endorser",endorser);
		result.addObject("curriculaId",curriculaId);
		result.addObject("requestURI","endorser/list.do");
		
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int endorserId){
		ModelAndView result;
		Endorser endorser;
		endorser=endorserService.findOne(endorserId);
		Assert.notNull(endorser);
	    result = createEditModelAndView(endorser);
		return result;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Endorser endorser, BindingResult binding){
		ModelAndView result;
		try{
			endorserService.delete(endorser);
			result=new ModelAndView("redirect:/curricula/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(endorser,"endorser.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Endorser endorser, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(endorser);
		}else{
				try{
					endorserService.save(endorser);
					result = new ModelAndView("redirect:/curricula/list.do");}
				catch(Exception oops){
					result=createEditModelAndView(endorser, "endorser.commit.error");}
				
			}
			return result;
	}
	
	protected ModelAndView createEditModelAndView(Endorser endorser){
		ModelAndView result;
		
		result = createEditModelAndView(endorser, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Endorser endorser, String message){
		ModelAndView result;
		result = new ModelAndView("endorser/edit");
		result.addObject("endorser",endorser);
		result.addObject("requestURI", "endorser/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
}
