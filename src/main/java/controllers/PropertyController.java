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

import services.HasService;
import services.IngredientService;
import services.NutritionistService;
import services.PropertyService;
import domain.Has;
import domain.Ingredient;
import domain.Property;

@Controller
@RequestMapping("/property")
public class PropertyController extends AbstractController{
	
	@Autowired
	private NutritionistService nutritionistService;
	@Autowired
	private PropertyService propertyService;
	@Autowired
	private HasService hasService;
	@Autowired
	private IngredientService ingredientService;
	
	public PropertyController(){
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Property property = propertyService.create();
		result = createEditModelAndView(property);
		return result;
	}
	
	@RequestMapping(value = "/createHas", method = RequestMethod.GET)
	public ModelAndView create2(@RequestParam int ingredientId) {
		ModelAndView result;
		Ingredient ingre=ingredientService.findOne(ingredientId);
		Has has=hasService.create(ingre,null);
		result = createHasModelAndView(has);
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Property> properties;
		properties=nutritionistService.mostrarTodasPorpertys();
		result= new ModelAndView("property/list");
		result.addObject("properties",properties);
		result.addObject("requestURI","property/list.do");
		
		return result;
	}
	
	@RequestMapping(value = "/listHas", method = RequestMethod.GET)
	public ModelAndView list2(@RequestParam int ingredientId) {
		ModelAndView result;
		Collection<Has> hass;
		
		Ingredient ingre=ingredientService.findOne(ingredientId);
		hass=ingre.getValue();
		result= new ModelAndView("property/listHas");
		result.addObject("hass",hass);
		result.addObject("ingredientId",ingredientId);
		result.addObject("requestURI","property/listHas.do");
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int propertyId){
		ModelAndView result;
		Property property;
		property=propertyService.findOne(propertyId);
		Assert.notNull(property);
	    result = createEditModelAndView(property);
		return result;
		
		
	}
	@RequestMapping(value= "/editHas", method = RequestMethod.GET)
	public ModelAndView edit2(@RequestParam int HasId){
		ModelAndView result;
		Has has;
		has=hasService.findOne(HasId);
		Assert.notNull(has);
	    result = createHasModelAndView(has);
		return result;
		
		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Property property, BindingResult binding){
		ModelAndView result;
		
		try{
			nutritionistService.deleteProperty(property);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(property,"property.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/editHas", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Has has, BindingResult binding){
		ModelAndView result;
		
		try{
			hasService.delete(has);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createHasModelAndView(has,"property.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Property property, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(property);
		}else{
				try{
					nutritionistService.crearProperty(property);
					result = new ModelAndView("redirect:list.do");}
				catch(Exception oops){
					result=createEditModelAndView(property, "property.commit.error");}
				
			}
			return result;
		
	}
	@RequestMapping(value="/editHas", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Has has, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createHasModelAndView(has);
		}else{
				try{
					hasService.save(has);
					result = new ModelAndView("redirect:listHas.do?ingredientId="+has.getIngredient().getId());}
				catch(Exception oops){
					result=createHasModelAndView(has, "property.commit.error");}
				
			}
			return result;
		
	}
	
	protected ModelAndView createEditModelAndView(Property property){
		ModelAndView result;
		
		result = createEditModelAndView(property, null);
		
		return result;
	}
	
	protected ModelAndView createHasModelAndView(Has has){
		ModelAndView result;
		
		result = createHasModelAndView(has, null);
		
		return result;
	}
	
	protected ModelAndView createHasModelAndView(Has has,String message){
		ModelAndView result;
		result = new ModelAndView("property/editHas");
		Collection<Property> properties=nutritionistService.propertyNoUse(has.getIngredient().getId());
		if(has.getId()!=0){
			properties.add(has.getProperty());
		}
		result.addObject("has",has);
		result.addObject("properties",properties);
		result.addObject("requestURI", "property/editHas.do");
		result.addObject("message",message);
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Property property, String message){
		ModelAndView result;
		result = new ModelAndView("property/edit");
		result.addObject("property",property);
		result.addObject("requestURI", "property/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}
