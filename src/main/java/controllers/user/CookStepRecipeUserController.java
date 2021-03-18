package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CookStepService;
import services.UserService;
import controllers.AbstractController;
import domain.CookStep;

@Controller
@RequestMapping("/recipe/cookstep/user")
public class CookStepRecipeUserController extends AbstractController{
	// Services ---------------------------------------------------------------
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CookStepService cookStepService;
	
	// Constructors ----------------------------------------------------------
	public CookStepRecipeUserController() {
		super();
	}

// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int cookstepId) {
		ModelAndView result;
		CookStep cookStep;
		cookStep = cookStepService.findOne(cookstepId);	
		Assert.notNull(cookStep);
		result = createEditModelAndView(cookStep);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CookStep cookStep, BindingResult binding) {
		ModelAndView result;
	
		if(cookStep.getPicture()==""){
			cookStep.setPicture(null);
		}
		if (binding.hasErrors()) {
			result = createEditModelAndView(cookStep);
		} else {
			try {
				userService.actualizarCookStep(cookStep);			
				result = new ModelAndView("redirect:/recipe/user/edit.do?recipeId="+cookStep.getRecipe().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(cookStep, "recipe.commit.error");				
			}
		}

		return result;
	}
	
	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(CookStep cookStep) {
		ModelAndView result;
		result = createEditModelAndView(cookStep, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(CookStep cookStep, String message) {
		ModelAndView result;
		result = new ModelAndView("recipe/cookstep/edit");
		result.addObject("requestURI", "recipe/cookstep/user/edit.do");
		result.addObject("cookStep", cookStep);
		result.addObject("message", message);
		return result;
	}


}



