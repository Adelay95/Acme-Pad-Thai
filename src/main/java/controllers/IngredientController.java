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

import services.IngredientService;
import services.NutritionistService;
import domain.Ingredient;

@Controller
@RequestMapping("/ingredient")
public class IngredientController extends AbstractController{
	
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private NutritionistService nutriService;

	public IngredientController(){
		super();
	}
	
	@RequestMapping(value = "/listByProp", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int propertyId) {
		ModelAndView result;
		Collection<Ingredient> ingres= ingredientService.ingredientByProperty(propertyId);
		result= new ModelAndView("ingredient/list");
		result.addObject("ingredients",ingres);
		result.addObject("requestURI","ingredient/listByProp.do?propertyId="+propertyId);
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Ingredient ing = ingredientService.create();
		result = createEditModelAndView(ing);
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Ingredient> ingres;
		ingres=ingredientService.allIngredient();
		result= new ModelAndView("ingredient/list");
		result.addObject("ingredients",ingres);
		result.addObject("requestURI","ingredient/list.do");
		return result;
	}
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int ingredientId){
		ModelAndView result;
		Ingredient ingre;
		ingre=ingredientService.findOne(ingredientId);
		Assert.notNull(ingre);
	    result = createEditModelAndView(ingre);
		return result;
		
		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Ingredient ingredient, BindingResult binding){
		ModelAndView result;
		try{
			nutriService.deleteIngredient(ingredient);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(ingredient,"ingredient.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Ingredient ingredient,  BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(ingredient);
		}else{
				try{
					nutriService.crearIngredient(ingredient);
					result = new ModelAndView("redirect:list.do");}
				catch(Exception oops){
					result=createEditModelAndView(ingredient,"ingredient.commit.error");}
				
			}
			return result;
		
	}
	
	
	protected ModelAndView createEditModelAndView(Ingredient ingredient){
		ModelAndView result;
		
		result = createEditModelAndView(ingredient, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Ingredient ingredient,String message){
		ModelAndView result;
		result = new ModelAndView("ingredient/edit");
		result.addObject("ingredient",ingredient);
		result.addObject("requestURI", "ingredient/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}

