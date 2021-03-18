package services;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RecipeRepository;
import domain.Category;
import domain.Comment;
import domain.CookStep;
import domain.Ingredient;
import domain.Quantity;
import domain.Recipe;
import domain.Taste;
import domain.User;

@Service
@Transactional
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
//	
	@Autowired
	private CookStepService cookStepService;
//	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private QuantityService quantityService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private TasteService tasteService;
	
	public RecipeService(){
		super();
	}
	public Recipe create(User user){

		Assert.notNull(user);
//		Assert.notNull(cookSteps);
//		Assert.notEmpty(cookSteps);
//		Assert.notNull(quantity);
//		Assert.notEmpty(quantity);
//		Assert.notNull(category);
//		Assert.notEmpty(category);
		Recipe res;
		Date moment;
		Collection<CookStep> cookSteps=new HashSet<CookStep>();
		Collection<Quantity> quantity=new HashSet<Quantity>();
		Collection<Category> category=new HashSet<Category>();
		Collection<Taste> tastes=new HashSet<Taste>();
		Collection<Comment> comments=new HashSet<Comment>();
		moment = new Date(System.currentTimeMillis() - 1000);
		res= new Recipe();
		res.setMomentAuthored(moment);
		res.setLastMomentUpdated(moment);
		res.setUser(user);
		res.setCategories(category);
		res.setQuantities(quantity);
		res.setCookSteps(cookSteps);
		res.setTastes(tastes);
		res.setComments(comments);
		res.setTicker(this.generaTicker());
		//CookStep cookstep=cookStepService.create(res);
		
		
		return res;
	}
	private String generaCadena(){
		  String cadenaAleatoria = "";
		  int longitud=4;
		  Random r = new Random();
		  int i = 0;
		  while ( i < longitud){
		  char c = (char)r.nextInt(255);
		  if ((c >='A' && c <='z' && Character.isLetter(c)) ){
		  cadenaAleatoria += c;
		  i ++;
		  }
		  }
		  return cadenaAleatoria;
		  }
		 
		 private String generaNumero(){
		  Date date =new Date();
		  DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");
		  String convertido = fecha.format(date);
		  
		  String[] campos= convertido.split("/");
		  String año= campos[0].trim().substring(2, 4);
		  String mes= campos[1].trim();
		  String dia= campos[2].trim();
		  
		  String res=año+mes+dia;
		  return res;
		 }
		 public String generaTicker(){
			  String res= generaNumero()+"-"+generaCadena();
			  return res;
			 }
		 
	public Recipe save(Recipe recipe){
		  Assert.notNull(recipe);
		  //Assert.isTrue(recipe.getCategories().size()>0);
		  Recipe res;
		  User user;
		  Date moment;
		  moment = new Date(System.currentTimeMillis() - 1000); 
		  recipe.setLastMomentUpdated(moment);
		  if(recipe.getId()==0){
			  recipe.setMomentAuthored(moment);
		  }
		  if(recipe.getId()!=0){
		  Assert.isTrue(recipe.getQualifiedContests()==null);
		  }
		  res=recipeRepository.save(recipe);
		  user=res.getUser();
		  user.setNumOriginalRecipes(getTheOnlyOneRecipe(user.getId()));
		  userService.save(user);
		  return res;
	}
	 public Recipe saveWinnerRecipe(Recipe recipe){
		    Assert.notNull(recipe);
		    Recipe res,res1;
		    res1=findOne(recipe.getId());
		    res1.setQualifiedContests(recipe.getQualifiedContests());
		    res1.setWinnerContests(recipe.getWinnerContests());
		    res=recipeRepository.save(res1);
		    return res;
		 }


	public void delete(Recipe recipe){
		Assert.notNull(recipe);
		Assert.isTrue(recipe.getId()!=0);
		for(Taste t: recipe.getTastes()){
			tasteService.delete(t);
		}
		for(Comment c:recipe.getComments()){
			commentService.delete(c);
		}
		
		recipeRepository.delete(recipe);
	}
	public Collection<Recipe> recetasOrdenandasUpdated(){
		return recipeRepository.recetasOrdenadasUpdated();
	}
	
	public  Collection<Recipe> allRecipes(){
		Collection<Recipe> res;
		res=recipeRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Recipe findOne(int id){
		Recipe res;
		res=recipeRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public Collection<Comment> cometariosOrdenadosReceta(int recipeId){
		Collection<Comment> res;
		res=recipeRepository.cometariosOrdenadosReceta(recipeId);
		return res;
	}
	public User autorReceta(Integer receta){
		Assert.notNull(receta);
		return recipeRepository.autorReceta(receta);
	}
	public Collection<Recipe> recetasAgrupadasCategoria(){
		return recipeRepository.recetasAgrupadasCategoria();
	}
	public Collection<Recipe> recetasOriginalesAgrupadasCategoria() {
		return recipeRepository.recetasOriginalesAgrupadasCategoria();
	}
	public Collection<Recipe> encontrarPor(String palabra){
		Assert.notNull(palabra);
		String buscar="%"+palabra+"%";
		return recipeRepository.encontrarPor(buscar);
	}


	//QUERY
	
    public Collection<Recipe> recipesByIngredient(int ingrId){
    	return recipeRepository.recipesByIngredient(ingrId);
    }
	public Double avgQuantitiesRecipes() {
		return recipeRepository.getAvgQuantitiesRecipes();
	}
	public Double devQuantitiesRecipes() {
		return recipeRepository.getDevQuantitiesRecipes();
	}
	public Double avgStepsRecipes() {
		return recipeRepository.getAvgStepsRecipes();
	}
	public Double devStepsRecipes() {
		return recipeRepository.getDevStepsRecipes();
	}
	
	public Collection<Recipe> recetasPorCategoria(Category cat) {
		return recipeRepository.getRecipesByCategory(cat.getId());
	}
	
	     public Collection<Ingredient> ingredientsNoRecipe(int recipeId) {
		  return recipeRepository.getIngredientsNoRecipe(recipeId);
		 }
		 public Collection<Category> categoriesNoRecipe(int recipeId) {
		  return recipeRepository.getCategoriesNoRecipe(recipeId);
		 }
		 public Integer likesRecipe(int recipeId) {
		  return recipeRepository.getLikesRecipe(recipeId);
		 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//SeToty
	
	
	
	
	
	
	public Recipe createCopy(int id){
		  Recipe copy;
		  Recipe copy1;
		  Recipe recipe;
		  recipe=findOne(id);
		   copy=create(recipe.getUser());
		   copy.setHints(recipe.getHints());
		   copy.setLastMomentUpdated(recipe.getLastMomentUpdated());
		   copy.setPicture(recipe.getPicture());
		   copy.setSummary(recipe.getSummary());
		   copy.setTitle(recipe.getTitle());
		   copy.setMomentAuthored(recipe.getMomentAuthored());
		   copy1=save(copy);
		   Recipe copy2=cambiarCookStep(recipe.getCookSteps(),copy1);
	       Recipe copy3=cambiarQuantity(recipe.getQuantities(),copy2);
		   Recipe copy4=cambiarCategory(recipe.getCategories(),copy3);
		   Recipe copy5=cambiarTaste(recipe.getTastes(),copy4);
		   Recipe copy6=cambiarComment(recipe.getComments(),copy5);
		   Recipe copy7=save(copy6);
		  return copy7;
		 }
		 private Recipe cambiarComment(Collection<Comment> comments, Recipe copy5) {
			 Recipe res=copy5;
			  Comment ahore=null;
			  for(Comment c:comments){
			   Comment asd=commentService.create(c.getClient());
			   asd.setCreationDate(c.getCreationDate());
			   asd.setStars(c.getStars());
			   asd.setText(c.getText());
			   asd.setTittle(c.getTittle());
			   asd.setRecipe(res);
			   ahore=commentService.save(asd);
			   res.getComments().add(ahore);
			   save(res);
			   
			  }
			  return res;
	}
		private Recipe cambiarTaste(Collection<Taste> tastes, Recipe copy4) {
			 Recipe res=copy4;
			  Taste ahore=null;
			  for(Taste t:tastes){
			   Taste asd=tasteService.create(t.getClient());
			   asd.setLikee(t.isLikee());
			   asd.setRecipe(res);
			   ahore=tasteService.save(asd);
			   res.getTastes().add(ahore);
			   save(res);
			   
			  }
			  return res;
	}
		private Recipe cambiarQuantity(
		   Collection<Quantity> quantities, Recipe copy) {
		  Recipe res=copy;
		  Quantity ahore=null;
		  for(Quantity q:quantities){
		   Quantity asd=quantityService.create(copy);
		   asd.setIngredient(q.getIngredient());
		   asd.setMeasure(q.getMeasure());
		   asd.setRecipe(res);
		   ahore=quantityService.save(asd);
		   res.getQuantities().add(ahore);
		   save(res);
		   
		  }
		  return res;
		 }
		 private Recipe cambiarCookStep(Collection<CookStep> cookSteps,Recipe recipe) {
		  Recipe res=recipe;
		  CookStep ahore = null;
		  for(CookStep s:cookSteps){
		   CookStep asd=cookStepService.create(recipe);
		   asd.setDescription(s.getDescription());
		   asd.setHints(s.getHints());
		   asd.setPicture(s.getPicture());
		   asd.setRecipe(res);
		   ahore=cookStepService.save(asd);
		   res.getCookSteps().add(ahore);
		   save(res);
		  
		  }
		  
		  return res;
		 }
		 
		 private Recipe cambiarCategory(Collection<Category> categories,Recipe recipe) {
		  Recipe res=recipe;
		  Category ahore=null;
		  for(Category s:categories){
			  s.getRecipes().add(res);
			  ahore = categoryService.save(s);
			  res.getCategories().add(ahore);
			  save(res);
		   
		  }
		  
		  return res;
		 }
	
	public Integer getTheOnlyOneRecipe(int userId){
		return recipeRepository.getTheOnlyOneRecipe(userId);
	}
	
	public Collection<Recipe> getRecipeFollowers(Collection<User> followers){
		return recipeRepository.recipesByFollower(followers);
	}
	
	
	
	
	
	
	
	
	
}
