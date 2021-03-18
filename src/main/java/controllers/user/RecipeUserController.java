package controllers.user;

import java.util.Collection;
import java.util.HashSet;

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
import services.ContestService;
import services.CookStepService;
import services.QuantityService;
import services.RecipeService;
import services.UserService;
import controllers.AbstractController;
import domain.Category;
import domain.Contest;
import domain.CookStep;
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;
import domain.Unit;
import domain.User;


@Controller
@RequestMapping("/recipe/user")
public class RecipeUserController extends AbstractController{
	// Services ---------------------------------------------------------------
		
				@Autowired
				private UserService userService;
				
				@Autowired
				private RecipeService recipeService;
				
				@Autowired
				private QuantityService quantityService;
				
	
				@Autowired
				private CookStepService cookStepService;
				
				@Autowired
				private CategoryService categoryService;
				
				@Autowired
				private ContestService contestService;
		// Constructors ----------------------------------------------------------
				public RecipeUserController() {
					super();
				}
		// Listing ----------------------------------------------------------------
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list(String message) {
			ModelAndView result;
			String search="";
		
			Collection<Recipe> recipes;
			recipes =userService.recetasOriginalesUsuario(userService.findByPrincipal().getId());
			
			
			result = new ModelAndView("recipe/list");
			result.addObject("requestURI", "recipe/user/list.do");
			result.addObject("recipes", recipes);
			if((message!=null)&&(message!="")){
			result.addObject("message", message);
			}
			result.addObject("search", search);
			return result;
		}
		
		// Edition ----------------------------------------------------------------
		
				@RequestMapping(value = "/edit", method = RequestMethod.GET)
				public ModelAndView edit(@RequestParam int recipeId) {
					ModelAndView result;
					Recipe recipe;
					recipe = recipeService.findOne(recipeId);	
					Assert.notNull(recipe);
					Quantity quantity=quantityService.create(recipe);
					CookStep cookStep=cookStepService.create(recipe);
					result = createEditModelAndView(recipe,quantity,cookStep);

					return result;
				}

				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
				public ModelAndView save(@Valid Recipe recipe, BindingResult binding) {
					ModelAndView result;
					if(recipe.getPicture()==""){
						recipe.setPicture(null);
					}
					Quantity quantity=quantityService.create(recipe);
					CookStep cookStep=cookStepService.create(recipe);
					if (binding.hasErrors()) {
						result = createEditModelAndView(recipe,quantity,cookStep);
					} else {
						try {
							userService.crearReceta(recipe);				
							result = new ModelAndView("redirect:list.do");
						} catch (Throwable oops) {
							result = createEditModelAndView(recipe,quantity,cookStep, "recipe.commit.error");				
						}
					}

					return result;
				}
				
						
				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
				public ModelAndView delete(Recipe recipe, BindingResult binding) {
					ModelAndView result;
					Quantity quantity=quantityService.create(recipe);
					CookStep cookStep=cookStepService.create(recipe);
					try {			
						userService.borrarReceta(recipe);
						result = new ModelAndView("redirect:list.do");						
					} catch (Throwable oops) {
						result = createEditModelAndView(recipe,quantity,cookStep, "recipe.commit.error");
					}

					return result;
				}
				
				//CREAR RECETA
				@RequestMapping(value = "/create", method = RequestMethod.GET)
				public ModelAndView create() {
					ModelAndView result;
					Recipe recipe;
					User user=userService.findByPrincipal();
					recipe = recipeService.create(user);	
					Assert.notNull(recipe);
					result = createCreateModelAndViewRecipe(recipe);

					return result;
				}
				
				@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
				public ModelAndView saveCreate(@Valid Recipe recipe, BindingResult binding,int idcategory) {
					if(recipe.getPicture()==""){
						recipe.setPicture(null);
					}
					ModelAndView result;
					Category category=categoryService.findOne(idcategory);
					Collection<Category> categories=new HashSet<Category>();
					categories.add(category);
					recipe.setCategories(categories);
					Recipe saved;
					if (binding.hasErrors()) {
						result = createCreateModelAndViewRecipe(recipe);
					} else {
						try {
							saved=recipeService.save(recipe);
							category.getRecipes().add(saved);
							categoryService.save(category);
							result = new ModelAndView("redirect:edit.do?recipeId="+saved.getId());
						} catch (Throwable oops) {
							result = createCreateEditModelAndViewRecipe(recipe, "recipe.commit.error");				
						}
					}

					return result;
				}
				//INGREDIENTES
				
				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "saveingredient")
				public ModelAndView saveIngredient(@Valid Quantity quantity, BindingResult binding) {
					ModelAndView result;
					CookStep cookStep=cookStepService.create(quantity.getRecipe());
					if (binding.hasErrors()) {
						result = createEditModelAndView(quantity.getRecipe(),quantity,cookStep);
					} else {
						try {
							userService.addIngrediente(quantity);		
							result = new ModelAndView("redirect:edit.do?recipeId="+quantity.getRecipe().getId());
						} catch (Throwable oops) {
							result = createEditModelAndView(quantity.getRecipe(),quantity,cookStep, "recipe.commit.error");				
						}
					}

					return result;
				}
				
				
				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deleteingredient")
				public ModelAndView deleteIngredient(int idingredient) {
					ModelAndView result;
					Quantity quantity=quantityService.findOne(idingredient);
					Recipe recipe=quantity.getRecipe();
					CookStep cookStep=cookStepService.create(recipe);
					try {			
						userService.quitarIngredienteReceta(quantity);
						result = new ModelAndView("redirect:edit.do?recipeId="+recipe.getId());						
					} catch (Throwable oops) {
						result = createEditModelAndView(recipe,quantity,cookStep, "recipe.commit.error");
					}

					return result;
				}
		//COOKSTEP
				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "savecookstep")
				public ModelAndView saveCookStep(@Valid CookStep cookStep, BindingResult binding) {
					ModelAndView result;
					Recipe recipe=cookStep.getRecipe();
					Quantity quantity=quantityService.create(recipe);
					if(cookStep.getPicture()==""){
						cookStep.setPicture(null);
					}
					if (binding.hasErrors()) {
						result = createEditModelAndView(recipe,quantity,cookStep);
					} else {
						try {
							userService.addCookStep(cookStep);		
							result = new ModelAndView("redirect:edit.do?recipeId="+quantity.getRecipe().getId());
						} catch (Throwable oops) {
							result = createEditModelAndView(recipe,quantity,cookStep, "recipe.commit.error");				
						}
					}

					return result;
				}
				
				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deletecookstep")
				public ModelAndView deleteCookStep(int idcookstep) {
					ModelAndView result;
					CookStep cookStep=cookStepService.findOne(idcookstep);
					Recipe recipe=cookStep.getRecipe();
					Quantity quantity=quantityService.create(recipe);
					try {			
						userService.quitarCookStepReceta(cookStep);
						result = new ModelAndView("redirect:edit.do?recipeId="+recipe.getId());						
					} catch (Throwable oops) {
						result = createEditModelAndView(recipe,quantity,cookStep, "recipe.commit.error");
					}

					return result;
				}
		//CATEGORY
				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "savecategory")
				public ModelAndView addCategory(int idcategory, int idrecipe) {
					ModelAndView result;
					Category category=categoryService.findOne(idcategory);
					Recipe recipe=recipeService.findOne(idrecipe);
					Quantity quantity=quantityService.create(recipe);
					CookStep cookStep=cookStepService.create(recipe);
					try {			
						userService.addCategoriaReceta(category, recipe);
						result = new ModelAndView("redirect:edit.do?recipeId="+recipe.getId());						
					} catch (Throwable oops) {
						result = createEditModelAndView(recipe,quantity,cookStep, "recipe.commit.error");
					}

					return result;
				}
				
				@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "deletecategory")
				public ModelAndView deleteCategory(int idcategory, int idrecipe) {
					ModelAndView result;
					Category category=categoryService.findOne(idcategory);
					Recipe recipe=recipeService.findOne(idrecipe);
					Quantity quantity=quantityService.create(recipe);
					CookStep cookStep=cookStepService.create(recipe);
					try {			
						userService.quitarCategoriaReceta(category, recipe);
						result = new ModelAndView("redirect:edit.do?recipeId="+recipe.getId());						
					} catch (Throwable oops) {
						result = createEditModelAndView(recipe,quantity,cookStep, "recipe.commit.error");
					}

					return result;
				}
		//QUALIFIED
				@RequestMapping(value = "/qualified", method = RequestMethod.GET)
				public ModelAndView qualifiedList(@RequestParam int recipeId) {
					ModelAndView result;
					Recipe recipe=recipeService.findOne(recipeId);
					Collection<Contest> contests;
					
					try {			
						userService.CheckRecetaPosible(recipe);
						contests =contestService.allContestactives();
						result = new ModelAndView("recipe/qualified");
						result.addObject("requestURI", "recipe/user/qualified.do");
						result.addObject("recipe", recipe);
						result.addObject("contests", contests);					
					} catch (Throwable oops) {
						result = new ModelAndView("redirect:list.do?message=recipe.qualified.error");	
					}
					
					
					
					return result;
				}
				
				@RequestMapping(value = "/savequalified", method = RequestMethod.GET)
				public ModelAndView qualifiedSave(@RequestParam int recipeId, @RequestParam int contestId) {
					ModelAndView result;
					try {			
						userService.calificarReceta(recipeId, contestId);
						result = new ModelAndView("redirect:list.do");			
					} catch (Throwable oops) {
						result = new ModelAndView("redirect:list.do?message=recipe.qualified.error");	
					}
					
					
					
					return result;
				}
				
		// Ancillary methods ------------------------------------------------------
				
				protected ModelAndView createEditModelAndView(Recipe recipe,Quantity quantity, CookStep cookStep) {
					ModelAndView result;
					result = createEditModelAndView(recipe,quantity,cookStep, null);
					return result;
				}	
				
				protected ModelAndView createEditModelAndView(Recipe recipe,Quantity quantity, CookStep cookStep, String message) {
					ModelAndView result;
					Collection<String> units=new HashSet<String>();
					for(Unit u:Unit.values()){
						units.add(u.toString());
					}
					Collection<Ingredient> ingredients=recipeService.ingredientsNoRecipe(recipe.getId());
				    Collection<Category> addcategories=recipeService.categoriesNoRecipe(recipe.getId());
					result = new ModelAndView("recipe/edit");
					result.addObject("requestURI", "recipe/user/edit.do");
					result.addObject("recipe", recipe);
					result.addObject("ingredients", ingredients);
					result.addObject("units", units);
					result.addObject("quantity", quantity);
					result.addObject("cookStep", cookStep);
					result.addObject("addcategories", addcategories);
					result.addObject("cookSteps", recipe.getCookSteps());
					result.addObject("message", message);
					return result;
				}
				
				protected ModelAndView createCreateModelAndViewRecipe(Recipe recipe) {
					ModelAndView result;
					result = createCreateEditModelAndViewRecipe(recipe, null);
					return result;
				}	
				
				protected ModelAndView createCreateEditModelAndViewRecipe(Recipe recipe, String message) {
					ModelAndView result;
					Collection<Category> addcategories=categoryService.allCategory();
					result = new ModelAndView("recipe/create");
					result.addObject("requestURI", "recipe/user/create.do");
					result.addObject("recipe", recipe);
					result.addObject("addcategories", addcategories);
					result.addObject("message", message);
					return result;
				}
		
}
