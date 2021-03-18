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

import services.CategoryService;
import domain.Category;

@Controller
@RequestMapping("/category")
public class CategoryController extends AbstractController{
	
	@Autowired
	private CategoryService categoryService;
	
	public CategoryController(){
		super();
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Category cat = categoryService.create();
		result = createEditModelAndView(cat);
		return result;
	}
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Category> categories;
		categories=categoryService.allCategory();
		result= new ModelAndView("category/list");
		result.addObject("categories",categories);
		result.addObject("requestURI","category/list.do");
		
		return result;
	}
	@RequestMapping(value= "/listSons", method = RequestMethod.GET)
	public ModelAndView listSons(@RequestParam int categoryId){
		ModelAndView result;
		Collection<Category> categories;
		Category category=categoryService.findOne(categoryId);
		categories=category.getSons();
		result= new ModelAndView("category/list");
		result.addObject("categories",categories);
		result.addObject("requestURI","category/listSons.do");
		result.addObject("nameParent",category.getName());
		
		return result;
	}
	
	@RequestMapping(value= "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int categoryId){
		ModelAndView result;
		Category category;
		category=categoryService.findOne(categoryId);
		Assert.notNull(category);
	    result = createEditModelAndView(category);
		return result;
		
		
	}
	
	@RequestMapping(value= "/setSonAs", method = RequestMethod.GET)
	public ModelAndView lists(@RequestParam int sonId){
		ModelAndView result;
		Collection<Category> categories;
		Category son=categoryService.findOne(sonId);
				
		categories=categoryService.allCategory();
		result= new ModelAndView("category/list");
		result.addObject("categories",categories);
		result.addObject("sonId",sonId);
		result.addObject("son",son);
		result.addObject("requestURI","category/setSonAs.do");
		
		return result;
	}
	@RequestMapping(value= "/quitParent", method = RequestMethod.GET)
	public ModelAndView parent2(@RequestParam int sonId,@RequestParam int parentId){
		ModelAndView result;
		Category son=categoryService.findOne(sonId);
		Category parent=categoryService.findOne(parentId);
		try{
			categoryService.quitarHijoDelPadre(son, parent);
			result=list();
		} catch(Throwable oops){
			result=list();
		   result.addObject("message","category.commit.error");
		}
		
		return result;
	}
	
	@RequestMapping(value= "/setParent", method = RequestMethod.GET)
	public ModelAndView parent(@RequestParam int sonId,@RequestParam int parentId){
		ModelAndView result;
		Category son=categoryService.findOne(sonId);
		Category parent=categoryService.findOne(parentId);
		try{
			categoryService.serHijoDelPadre(son, parent);
			result=list();
		} catch(Throwable oops){
			result=list();
		   result.addObject("message","category.commit.error");
		}
		
		return result;
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST,params="delete")
	public ModelAndView delete(Category category, BindingResult binding){
		ModelAndView result;
		
		try{
			categoryService.delete(category);
			result=new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(category,"category.commit.error");
		}
		return result;
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST,params="save")
	public ModelAndView save(@Valid Category category, BindingResult binding){
		ModelAndView result;
		if(binding.hasErrors()){
			result= createEditModelAndView(category);
		}else{
				try{
					categoryService.save(category);
					result = new ModelAndView("redirect:list.do");}
				catch(Exception oops){
					result=createEditModelAndView(category, "category.commit.error");}
				
			}
			return result;
	}
	
	protected ModelAndView createEditModelAndView(Category category){
		ModelAndView result;
		
		result = createEditModelAndView(category, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Category category, String message){
		ModelAndView result;
		result = new ModelAndView("category/edit");
		result.addObject("category",category);
		result.addObject("requestURI", "category/edit.do");
		result.addObject("message",message);
		return result;
		
		
	}
	
	

}
