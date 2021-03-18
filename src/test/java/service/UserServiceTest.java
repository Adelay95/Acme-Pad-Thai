package service;

import java.util.Collection;
import java.util.HashSet;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.Authority;
import security.UserAccount;
import services.CategoryService;
import services.ClientService;
import services.ContestService;
import services.CookStepService;
import services.IngredientService;
import services.QuantityService;
import services.RecipeService;
import services.UserService;
import utilities.AbstractTest;
import domain.Category;
import domain.Contest;
import domain.CookStep;
import domain.Ingredient;
import domain.Measure;
import domain.Quantity;
import domain.Recipe;
import domain.Taste;
import domain.Unit;
import domain.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml"})
@Transactional

public class UserServiceTest extends AbstractTest{
	
	@Autowired
	private UserService userService;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private CookStepService cookStepService;
	@Autowired
	private QuantityService quantityService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private IngredientService ingredientService;
	@Autowired
	private ContestService contestService;
	@Autowired
	private ClientService clientService;
	
	@Test
	public void TestSaveUser(){
		  User user, saved;
		  UserAccount u1= new UserAccount();
		  Collection<User> users;
		  Collection<Authority> authorities= new HashSet<Authority>();
		  user=userService.create();
		  user.setEmailAdress("ejemplo@us.es");
		  user.setName("Manolo");
		  user.setSurname("De Prueba");
		  u1.setUsername("Adelay");
		  u1.setPassword("KJIESKA89");
		  Authority asd=new Authority();
		  asd.setAuthority("USER");
		  authorities.add(asd);
		  u1.setAuthorities(authorities);
		  user.setUserAccount(u1);
		  saved=userService.save(user);
		  users=userService.allUsers();
		  Assert.isTrue(users.contains(saved));
		 }
	
	@Test
	public void testCrearRecipe(){
		Recipe rec,saved,saved2;
		CookStep ejemplo,ejemplo2;
		Quantity quantity,q2;
		User user=userService.findOne(12);
		rec=recipeService.create(user);
		rec.setTicker("262521-UYHS");
		rec.setSummary("Está bien");
		rec.setTitle("Macarrones");
		rec.setPicture("http://www.google.es");
		ejemplo=cookStepService.create(rec);
		ejemplo.setDescription("Descripcion");
		Ingredient in=ingredientService.findOne(64);
		quantity=quantityService.create(rec);
		quantity.setIngredient(in);
		Category category=categoryService.findOne(181);
		Measure measure=new Measure();
		measure.setUnit(Unit.KILOGRAMS);
		measure.setValue("52.25");
		quantity.setMeasure(measure);
		Collection<Quantity> quantities=new HashSet<Quantity>();
		Collection<Category> categories=new HashSet<Category>();
		Collection<CookStep> cookSteps=new HashSet<CookStep>();
		cookSteps.add(ejemplo);
		categories.add(category);
		quantities.add(quantity);
		rec.setQuantities(quantities);
		rec.setCategories(categories);
		rec.setCookSteps(cookSteps);
		saved=userService.crearReceta(rec);
		ejemplo.setRecipe(saved);
		ejemplo2=cookStepService.save(ejemplo);
		quantity.setRecipe(saved);
		q2=quantityService.save(quantity);
		cookSteps.remove(ejemplo);
		cookSteps.add(ejemplo2);
		quantities.remove(quantity);
		quantities.add(q2);
		saved2=userService.crearReceta(saved);
		category.getRecipes().add(saved2);
		categoryService.save(category);
		Collection<Recipe> recipes=recipeService.allRecipes();
		Assert.isTrue(recipes.contains(saved2));
	}
	
	@Test
	public void testBorrarReceta(){
		Recipe res;
		super.authenticate("user1");
		res= recipeService.findOne(29);
		Collection<Recipe> recipes1= recipeService.allRecipes();
		Assert.isTrue(recipes1.contains(res));
		userService.borrarReceta(res);
		Collection<Recipe> recipes= recipeService.allRecipes();
		Assert.isTrue(!recipes.contains(res));
		super.authenticate(null);
	}
	
	
//	@Test
//	public void testActualizaReceta(){
//		Quantity quantity;
//		Recipe r1;
//		CookStep ejemplo;
//		super.authenticate("user2");
//		r1=recipeService.findOne(44);
//		ejemplo=cookStepService.create(r1);
//		ejemplo.setDescription("Descripcion");
//		Ingredient in=ingredientService.findOne(64);
//		quantity=quantityService.create(r1);
//		quantity.setIngredient(in);
//		Category category=categoryService.findOne(181);
//		Measure measure=new Measure();
//		measure.setUnit(Unit.KILOGRAMS);
//		measure.setValue("52.25");
//		quantity.setMeasure(measure);
//		Collection<Quantity> quantities=new HashSet<Quantity>();
//		Collection<Category> categories=new HashSet<Category>();
//		Collection<CookStep> cookSteps=new HashSet<CookStep>();
//		cookSteps.add(ejemplo);
//		categories.add(category);
//		quantities.add(quantity);
//		userService.actualizarReceta(44, "Está bien", "Macarrones", "http://www.google.es", 
//				r1.getHints(), categories, quantities, cookSteps, r1.getTastes());
//		Collection<Recipe> receta=recipeService.allRecipes();
//		Assert.isTrue(receta.contains(r1));
//		Assert.isTrue(r1.getCategories().size()==1 && r1.getCategories().contains(category));
//		Assert.isTrue(r1.getTitle()=="Macarrones");
//		super.authenticate(null);
//	}
	
//	@Test
//	public void testCalificarReceta(){
//		Recipe res,res2,res3;
//		Contest res1;
//		Taste t1,t2,t3,t4,t5;
//		super.authenticate("user1");
//		User u=userService.findOne(20);
//		User u1=userService.findOne(12);
//		User u2=userService.findOne(15);
//		User u3=userService.findOne(16);
//		User u4=userService.findOne(18);
//		res= recipeService.findOne(29);
//		t1=clientService.gusta(u);
//		t1.setLikee(true);
//		t1.setRecipe(res);
//		userService.gustarReceta(t1);
//		t2=clientService.gusta(u1);
//		t2.setLikee(true);
//		t2.setRecipe(res);
//		userService.gustarReceta(t2);
//		t3=clientService.gusta(u2);
//		t3.setLikee(true);
//		t3.setRecipe(res);
//		userService.gustarReceta(t3);
//		t4=clientService.gusta(u3);
//		t4.setLikee(true);
//		t4.setRecipe(res);
//		userService.gustarReceta(t4);
//		t5=clientService.gusta(u4);
//		t5.setLikee(true);
//		t5.setRecipe(res);
//		userService.gustarReceta(t5);
//		res1=contestService.findOne(28);
//		res3=userService.calificarReceta(29, 28);
//		res2= recipeService.findOne(29);
//		Assert.isTrue(!res2.getTastes().isEmpty());
//		Assert.isTrue(res1.getQualifiedRecipes().contains(res3));
//		super.authenticate(null);
//	}
//	

}
