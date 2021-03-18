package controllers.client;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ClientService;
import services.CommentService;
import services.RecipeService;
import controllers.AbstractController;
import domain.Client;
import domain.Comment;


@Controller
@RequestMapping("/recipe/comment")
public class CommentClientController extends AbstractController{
	// Services ---------------------------------------------------------------
	
	@Autowired
	private ClientService clientService;	
	
	@Autowired
	private CommentService commentService;	
	
	@Autowired
	private RecipeService recipeService;	
	
	// Constructors -----------------------------------------------------------
	
	public CommentClientController() {
		super();
	}
	
	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int recipeId) {
		ModelAndView result;
		Comment comment;
		Client client=clientService.findByPrincipal();
		comment = commentService.create(client);
		comment.setRecipe(recipeService.findOne(recipeId));
		result = createEditModelAndView(comment);
		return result;
	}

	// Edition ----------------------------------------------------------------
	
	

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Comment comment, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors()) {
			result = createEditModelAndView(comment);
		} else {
			try {
				clientService.commentar(comment);		
				result = new ModelAndView("redirect:/recipe/display.do?recipeId="+comment.getRecipe().getId());
			} catch (Throwable oops) {
				result = createEditModelAndView(comment, "recipe.commit.error");				
			}
		}

		return result;
	}
		
	
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Comment comment) {
		ModelAndView result;
		result = createEditModelAndView(comment, null);
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Comment comment, String message) {
		ModelAndView result;
		result = new ModelAndView("recipe/comment/create");
		result.addObject("requestURI", "recipe/comment/create.do");
		result.addObject("comment", comment);
		result.addObject("message", message);
		return result;
	}

}


