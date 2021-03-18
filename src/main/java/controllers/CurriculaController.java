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
import services.NutritionistService;
import domain.Curricula;
import domain.Nutritionist;

@Controller
@RequestMapping("/curricula")
public class CurriculaController extends AbstractController{
	
	@Autowired
	private NutritionistService nutritionistService;
	@Autowired
	private CurriculaService curService;
	
	public CurriculaController(){
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Nutritionist nutri=nutritionistService.findByPrincipal();
		Curricula cur = curService.create();
		cur.setNutritionist(nutri);
		result = createEditModelAndView(cur);
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curricula> curris;
		Nutritionist nutri=nutritionistService.findByPrincipal();
		curris=nutritionistService.curriculasPorNutricionista(nutri.getId());
		result= new ModelAndView("curricula/list");
		result.addObject("curriculas",curris);
		result.addObject("requestURI","curricula/list.do");
		
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int curriculaId){
		ModelAndView result;
		Curricula cur;
		cur=curService.findOne(curriculaId);
		Assert.notNull(cur);
	    result = createEditModelAndView(cur);
		return result;
		
		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Curricula curricula, BindingResult binding){
		ModelAndView result;
		
		try{
			nutritionistService.borrarCurricula(curricula);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(curricula,"socialIdentity.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Curricula curricula, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(curricula);
		}else{
				try{
					if(curricula.getId()==0){
					nutritionistService.crearCurricula(curricula);
					}else{
					curService.save(curricula);
					}
					result = new ModelAndView("redirect:list.do");}
				catch(Exception oops){
					result=createEditModelAndView(curricula, "actor.commit.error");}
				
			}
			return result;
		
	}
	
	protected ModelAndView createEditModelAndView(Curricula cur){
		ModelAndView result;
		
		result = createEditModelAndView(cur, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Curricula cur, String message){
		ModelAndView result;
		result = new ModelAndView("curricula/edit");
		result.addObject("curricula",cur);
		result.addObject("requestURI", "curricula/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}

