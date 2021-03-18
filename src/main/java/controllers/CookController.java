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

import services.CookService;
import services.LearningMaterialService;
import services.MasterClassService;

import domain.Cook;
import domain.LearningMaterial;
import domain.MasterClass;

@Controller
@RequestMapping("/master-class/cook")
public class CookController {

	@Autowired
	private CookService cookService;
	@Autowired
	private MasterClassService masterClassService;
	@Autowired
	private LearningMaterialService learningMaterialService;
	
	public CookController() {
		super();
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<MasterClass> masterClasses;
        Cook cook = cookService.findByPrincipal();
        masterClasses = cookService.mostrarMasterClassCook(cook.getId());
		result = new ModelAndView("master-class/list");
		result.addObject("requestURI", "master-class/cook/list.do");
		result.addObject("masterClasses", masterClasses);
		result.addObject("actor", cook);
		return result;
	}
	
	@RequestMapping(value = "/add-lm", method = RequestMethod.GET)
	public ModelAndView alburrito(@RequestParam int masterClassId, @RequestParam int lmId){
    ModelAndView result;
		
	    MasterClass m= masterClassService.findOne(masterClassId);
	    LearningMaterial lm= learningMaterialService.findOne(lmId);
	    try{
	 	   masterClassService.addLearningMaterial(m, lm);
	 	   result=new ModelAndView("redirect:list.do");
	 	  }catch(Throwable oops){
	 	   result = createEditModelAndView(m,"message.commit.error");
	 	  }
	
		return result;
		
	}
	
	@RequestMapping(value = "/remove-lm", method = RequestMethod.GET)
	public ModelAndView alburritodelete(@RequestParam int masterClassId, @RequestParam int lmId){
    ModelAndView result;
		
	    MasterClass m= masterClassService.findOne(masterClassId);
	    LearningMaterial lm= learningMaterialService.findOne(lmId);
	    try{
	 	   masterClassService.deleteLearningMaterial(m, lm);
	 	   result=new ModelAndView("redirect:list.do");
	 	  }catch(Throwable oops){
	 	   result = createEditModelAndView(m,"message.commit.error");
	 	  }
	
		return result;
		
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MasterClass masterClass;
		Cook cook=cookService.findByPrincipal();
		masterClass = masterClassService.create(cook);
		result = createEditModelAndView(masterClass);

		return result;
	}
	

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int masterClassId) {
		ModelAndView result;
		MasterClass masterClass;

		masterClass = masterClassService.findOne(masterClassId);		
		Assert.notNull(masterClass);
		result = createEditModelAndView(masterClass);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid MasterClass masterClass, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(masterClass);
		} else {
			try {
				cookService.crearMasterClass(masterClass);	
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(masterClass, "masterClass.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(MasterClass masterClass, BindingResult binding) {
		ModelAndView result;

		try {			
			cookService.borrarMaterClass(masterClass);
			result = new ModelAndView("redirect:list.do");						
		} catch (Throwable oops) {
			result = createEditModelAndView(masterClass, "masterClass.commit.error");
		}

		return result;
	}
	
	protected ModelAndView createEditModelAndView(MasterClass masterClass) {
		ModelAndView result;
		result = createEditModelAndView(masterClass, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(MasterClass masterClass, String message) {
		ModelAndView result;
		result = new ModelAndView("master-class/edit");
		
		result.addObject("requestURI", "master-class/cook/edit.do");
		result.addObject("masterClass", masterClass);
		result.addObject("message", message);
		return result;
	}
}
