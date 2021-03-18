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

import services.SpamService;

import domain.Spam;

@Controller
@RequestMapping("/spam")
public class SpamController extends AbstractController{
	
	@Autowired
	private SpamService spamService;

	public SpamController(){
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Spam spam = spamService.create();
		result = createEditModelAndView(spam);

		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Spam> spams;
		spams=spamService.allSpam();
		result= new ModelAndView("spam/list");
		result.addObject("spams",spams);
		result.addObject("requestURI","spam/list.do");
		
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int spamId){
		ModelAndView result;
		Spam spam;
		spam=spamService.findOne(spamId);
		Assert.notNull(spam);
	    result = createEditModelAndView(spam);
		return result;
		
		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Spam spam, BindingResult binding){
		ModelAndView result;
		
		try{
			spamService.delete(spam);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(spam,"spam.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Spam spam, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(spam);
		}else{
				try{
					spamService.save(spam);
					result = new ModelAndView("redirect:list.do");}
				catch(Exception oops){
					result=createEditModelAndView(spam, "spam.commit.error");}
				
			}
			return result;
		
	}
	
	protected ModelAndView createEditModelAndView(Spam spam){
		ModelAndView result;
		
		result = createEditModelAndView(spam, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Spam spam, String message){
		ModelAndView result;
		result = new ModelAndView("spam/edit");
		result.addObject("spam",spam);
		result.addObject("requestURI", "spam/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}