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

import services.IngredientService;
import services.QuantityService;
import services.UserService;
import controllers.AbstractController;
import domain.Ingredient;
import domain.Quantity;
import domain.Unit;


	@Controller
	@RequestMapping("/recipe/quantity/user")
	public class QuantityRecipeUserController extends AbstractController{
		// Services ---------------------------------------------------------------
			
					@Autowired
					private UserService userService;
					
					@Autowired
					private IngredientService ingredientService;
					
					@Autowired
					private QuantityService quantityService;
					
			
					// Constructors ----------------------------------------------------------
					public QuantityRecipeUserController() {
						super();
					}
			
			// Edition ----------------------------------------------------------------
			
					@RequestMapping(value = "/edit", method = RequestMethod.GET)
					public ModelAndView edit(@RequestParam int quantityId) {
						ModelAndView result;
						Quantity quantity;
						quantity = quantityService.findOne(quantityId);	
						Assert.notNull(quantity);
						result = createEditModelAndView(quantity);

						return result;
					}

					@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
					public ModelAndView save(@Valid Quantity quantity, BindingResult binding) {
						ModelAndView result;
					
					
						if (binding.hasErrors()) {
							result = createEditModelAndView(quantity);
						} else {
							try {
								userService.actualizarQuantity(quantity);				
								result = new ModelAndView("redirect:/recipe/user/edit.do?recipeId="+quantity.getRecipe().getId());
							} catch (Throwable oops) {
								result = createEditModelAndView(quantity, "recipe.commit.error");				
							}
						}

						return result;
					}
					
					// Ancillary methods ------------------------------------------------------
					protected ModelAndView createEditModelAndView(Quantity quantity) {
						ModelAndView result;
						result = createEditModelAndView(quantity, null);
						return result;
					}	
					
					protected ModelAndView createEditModelAndView(Quantity quantity, String message) {
						ModelAndView result;
						Collection<String> units=new HashSet<String>();
						for(Unit u:Unit.values()){
							units.add(u.toString());
						}
						Collection<Ingredient> ingredients=ingredientService.allIngredient();
							ingredients.remove(quantity.getIngredient());
						
						result = new ModelAndView("recipe/quantity/edit");
						result.addObject("requestURI", "recipe/quantity/user/edit.do");
						result.addObject("quantity", quantity);
						result.addObject("ingredients", ingredients);
						result.addObject("units", units);
						result.addObject("message", message);
						return result;
					}
			

	}

