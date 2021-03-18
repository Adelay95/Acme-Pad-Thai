package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CampaignService;
import services.CategoryService;
import services.ClientService;
import services.ContestService;
import services.RecipeService;
import services.UserService;
import domain.Category;
import domain.Client;
import domain.Comment;
import domain.CookStep;
import domain.Recipe;
import domain.User;

@Controller
@RequestMapping("/recipe")
public class RecipeController extends AbstractController{
	
	// Services ---------------------------------------------------------------
			@Autowired
			private CampaignService campaignService;
			
			@Autowired
			private RecipeService recipeService;
			
			@Autowired
			private CategoryService categoryService;
			
			@Autowired
			private ContestService contestService;
			
			@Autowired
			private UserService userService;
			@Autowired
			private ClientService clientService;
	
			// Constructors ----------------------------------------------------------
			public RecipeController() {
				super();
			}
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		
		String search="";
		Collection<Recipe> recipes;
		recipes =recipeService.recetasOriginalesAgrupadasCategoria();
		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/list.do");
		result.addObject("recipes", recipes);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/list-by-author", method = RequestMethod.GET)
	public ModelAndView listUserRecipes(@RequestParam int userId) {
		ModelAndView result;
		String search="";
	
		Collection<Recipe> recipes;
		recipes =userService.recetasOriginalesUsuario(userId);
		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/list-by-author.do");
		result.addObject("recipes", recipes);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/list-contest-winner", method = RequestMethod.GET)
	public ModelAndView listContestWinner(@RequestParam int contestId) {
		ModelAndView result;
		String search="";
	
		Collection<Recipe> recipes;
		recipes =contestService.findOne(contestId).getWinnerRecipes();
		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/list-contest-winner.do");
		result.addObject("recipes", recipes);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/list-contest-qualified", method = RequestMethod.GET)
	public ModelAndView listContestQualified(@RequestParam int contestId) {
		ModelAndView result;
		
		String search="";
		Collection<Recipe> recipes;
		recipes =contestService.findOne(contestId).getQualifiedRecipes();
		
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/list-contest-qualified.do");
		result.addObject("recipes", recipes);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView listSearchRecipes(@RequestParam String search,String searchs) {
		ModelAndView result;
		Collection<Recipe> recipes2;
		recipes2 =recipeService.encontrarPor(search);
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/search.do");
		result.addObject("recipes", recipes2);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView displayRecipe(@RequestParam int recipeId,String message) {
		ModelAndView result;
		Integer likes=recipeService.likesRecipe(recipeId);
		Recipe recipe;
		recipe =recipeService.findOne(recipeId);
		Integer dislikes=recipe.getTastes().size()-likes;
		Collection<Comment> comments;
		comments=recipeService.cometariosOrdenadosReceta(recipeId);
		Collection<CookStep> cookSteps;
		cookSteps=recipe.getCookSteps();
		result = new ModelAndView("recipe/display");
		result.addObject("requestURI", "recipe/display.do");
		result.addObject("recipe", recipe);
		result.addObject("likes", likes);
		if((message!=null)&&(message!="")){
			result.addObject("message", message);
			}
		result.addObject("dislikes", dislikes);
		result.addObject("banner",campaignService.bannerWelcome());
		result.addObject("comments", comments);
		result.addObject("cookSteps", cookSteps);
		return result;
	}
	@RequestMapping(value = "/taste", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int recipeId, @RequestParam Boolean like) {
		ModelAndView result;
		Recipe recipe=recipeService.findOne(recipeId);
			try {
				clientService.gusta(recipe, like);		
				result = new ModelAndView("redirect:display.do?recipeId="+recipeId);
			} catch (Throwable oops) {
				result = new ModelAndView("redirect:display.do?recipeId="+recipeId+"&message=recipe.like.error");				
			}

		return result;
	}
	
	@RequestMapping(value = "/listByIngr", method = RequestMethod.GET)
	public ModelAndView listWithIngredient(@RequestParam int ingredientId) {
		ModelAndView result;
		
		String search="";
		Collection<Recipe> recipeIngre=recipeService.recipesByIngredient(ingredientId);
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI ", "recipe/listByIngr.do");
		result.addObject("recipes", recipeIngre);
		result.addObject("search", search);
		return result;
	}
	
	@RequestMapping(value = "/listByFollowed", method = RequestMethod.GET)
	 public ModelAndView listF() {
	  ModelAndView result;
	  
	  String search="";
	  Collection<Recipe> recetasVacia= new HashSet<Recipe>();
	  Client client=clientService.findByPrincipal();
	  Collection<User> misSeguidores=clientService.getFollowersUser(client.getId());
	  if(misSeguidores.isEmpty()){
	  
	  
		  result = new ModelAndView("recipe/list");
		  result.addObject("requestURI", "recipe/listByFollowed.do");
		  result.addObject("recipes", recetasVacia);
		  result.addObject("search", search);
	  }else{
		  List<Recipe> recipesFol=new ArrayList<Recipe>(recipeService.getRecipeFollowers(misSeguidores));
		  
		  int i=recipesFol.size();
	  
	  if(i<=3){result = new ModelAndView("recipe/list");
	  result.addObject("requestURI", "recipe/listByFollowed.do");
	  result.addObject("recipes", recipesFol);
	  result.addObject("search", search);
	   
	  }else{
	   List<Recipe> defrecipe=recipesFol.subList(0, 3);
	  result = new ModelAndView("recipe/list");
	  result.addObject("requestURI", "recipe/listByFollowed.do");
	  result.addObject("recipes", defrecipe);
	  result.addObject("search", search);
	  }
	  }
	  return result;
	 }
	@RequestMapping(value = "/listByCategory", method = RequestMethod.GET)
	public ModelAndView listWithCategory(@RequestParam int categoryId) {
		ModelAndView result;
		
		String search="";
		Collection<Recipe> recipes2;
		Category cat=categoryService.findOne(categoryId);
		recipes2 =recipeService.recetasPorCategoria(cat);
		result = new ModelAndView("recipe/list");
		result.addObject("requestURI", "recipe/listByCategory.do?CategoryId="+categoryId);
		result.addObject("recipes", recipes2);
		result.addObject("search", search);
		return result;
	}
}
