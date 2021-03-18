package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import services.ClientService;
import services.CurriculaService;
import services.HasService;
import services.IngredientService;
import services.NutritionistService;
import services.PropertyService;
import services.QuantityService;
import services.RecipeService;
import services.UserService;
import utilities.AbstractTest;
import domain.Comment;
import domain.Curricula;
import domain.Has;
import domain.Ingredient;
import domain.Nutritionist;
import domain.Property;
import domain.Quantity;
import domain.Recipe;
import domain.Taste;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml"})
@Transactional
public class NutritionistServiceTest extends AbstractTest {

//	 @Autowired
//	 private NutritionistService nutriService;
//	 @Autowired
//	 private CurriculaService curriculaService;
//	 @Autowired
//	 private PropertyService propertyService;
//	 @Autowired
//	 private UserService userService;
//	 @Autowired
//	 private RecipeService recipeService;
//	 @Autowired
//	 private ClientService clientService;
//	 @Autowired
//	 private IngredientService ingredientService;
//	 @Autowired
//	 private HasService hasService;
//	 @Autowired
//	 private QuantityService quantityService;
//	 
//	 @Test
//	 public void TestCreateCurricula(){
//		    super.authenticate("nutri1");
//			Curricula res= curriculaService.create();
//			Curricula mec;
//			String hobbys ="Futbol";
//			res.setHobby(hobbys);
//			res.setPhoto("http://google.es");
//			res.setEducationSection("Oskita");
//			res.setExperience("ninguna");
//			res.setNumReferences(20);
//			mec=nutriService.crearCurricula(res);
//		//	Assert.notNull(mec);
//			Collection<Curricula> curricula2= curriculaService.allCurricula();
//			Assert.isTrue(curricula2.contains(mec));
//			super.authenticate(null);
//	   }
//	 
//	 @Test
//	 public void TestDeleteCurricula(){
//		    super.authenticate("nutri1");
//			Curricula c= curriculaService.findOne(5);
//		//	Assert.notNull(mec);
//			nutriService.borrarCurricula(c);
//			Collection<Curricula> curricula2= curriculaService.allCurricula();
//			Assert.isTrue(!curricula2.contains(c));
//			super.authenticate(null);
//	   }
//	 
//	 @Test
//	 public void TestActualizarCurricula(){
//		    super.authenticate("nutri1");
//			Curricula c= curriculaService.findOne(5);
//		//	Assert.notNull(mec);
//			String hobbys ="Futbol";
//			nutriService.actualizarCurricula(5, "http://xxxx.com", "flequillo del higueras", "alguna", hobbys, 16);
//			Assert.isTrue(c.getPhoto().equals("http://xxxx.com"));
//			Assert.isTrue(c.getHobby().equals(hobbys));
//			super.authenticate(null);
//	   }
//	 
//	 @Test
//	 public void TestCreateIngredient(){
//		    super.authenticate("nutri1");
//			Ingredient res= ingredientService.create();
//			Ingredient ing;
//			res.setDescription("delicioso");
//			res.setName("arrocito");
//			res.setPicture("http://HiguerasElGuapo.com");
//			Collection<Has> has1= new ArrayList<>();
//			Has value= hasService.findOne(108);
//			has1.add(value);
//			res.setValue(has1);
//			Collection<Quantity> qua1= new ArrayList<>();
//			Quantity qua= quantityService.findOne(75);
//			qua1.add(qua);
//			res.setQuantities(qua1);
//			ing=nutriService.crearIngredient(res);
//		//	Assert.notNull(mec);
//			Collection<Ingredient> ing2= ingredientService.allIngredient();
//			Assert.isTrue(ing2.contains(ing));
//			super.authenticate(null);
//	   }
//	 
//	 
////	 @Test
////	 public void TestDeleteIngredient(){
////		    super.authenticate("nutri1");
////			Ingredient c= ingredientService.findOne(64);
////			Collection<Quantity> q= new ArrayList<>();
////			c.setQuantities(q);
////			Ingredient saved=ingredientService.save(c);
////			System.out.println(saved);
////			
////			nutriService.deleteIngredient(saved);
////			
////			Collection<Ingredient> ingredi2= ingredientService.allIngredient();
////			System.out.println(ingredi2);
////			Assert.isTrue(!ingredi2.contains(saved));
////			super.authenticate(null);
////	   }
//	 
////	 @Test
////	 public void TestActualizarIngredient(){
////		    super.authenticate("nutri1");
////		    Ingredient p= ingredientService.findOne(63);
////		    Collection<Has> has =new ArrayList<Has>();
////		    Has value= hasService.findOne(114);
////			has.add(value);
////			Collection<Quantity> qua1= new ArrayList<>();
////			Quantity qua= quantityService.findOne(81);
////			qua1.add(qua);
////			nutriService.modificarIngredient(63, "esta rico oye", "arrocillos", "http://hola.com", p.getValue(), p.getQuantities());
////			Assert.isTrue(p.getDescription().equals("esta rico oye"));
////			Assert.isTrue(p.getName().equals("arrocillos"));
////			super.authenticate(null);
////	   }
//	 
//	 @Test
//	 public void TestCreateProperty(){
//		    super.authenticate("nutri1");
//			Property res= propertyService.create();
//			Property mec;
//			res.setName("whatefuck");
//			mec=nutriService.crearProperty(res);
//		//	Assert.notNull(mec);
//			Collection<Property> property2= propertyService.allProperty();
//			Assert.isTrue(property2.contains(mec));
//			super.authenticate(null);
//	   }
//	 
//	 @Test
//	 public void TestDeleteProperty(){
//		    super.authenticate("nutri1");
//			Property p= propertyService.findOne(104);
//		//	Assert.notNull(mec);
//			nutriService.deleteProperty(p);
//			Collection<Property> property2= propertyService.allProperty();
//			Assert.isTrue(!property2.contains(p));
//			super.authenticate(null);
//	   }
//	 
//	 @Test
//	 public void TestActualizarProperty(){
//		    super.authenticate("nutri1");
//		    Property p= propertyService.findOne(104);
//		//	Assert.notNull(mec);
//		    Collection<Has> has =new ArrayList<Has>();
//			nutriService.modificarProperty(104, "shurmano", has );
//			Assert.isTrue(p.getHas().equals(has));
//			Assert.isTrue(p.getName().equals("shurmano"));
//			super.authenticate(null);
//	   }
//	 
//	 @Test
//	 public void TestSeguir(){
//		 super.authenticate("nutri1");
//		 Nutritionist n= nutriService.findByPrincipal();
//		 User s= userService.findOne(18);
//		 nutriService.seguir(s);
//		 Assert.isTrue(s.getFollowed().contains(n));
//		 super.authenticate(null);
//	   }
//	 
//	 @Test
//	 public void TestDejarSeguir(){
//		 super.authenticate("nutri1");
//		 Nutritionist n= nutriService.findByPrincipal();
//		 Nutritionist s= nutriService.findOne(3);
//		 nutriService.dejarDeSeguir(s);
//		 Assert.isTrue(!s.getFollowed().contains(n));
//		 super.authenticate(null);
//	   }
//	 
////	 @Test
////	  public void TestComentar(){
////	   super.authenticate("nutri1");
////	   Nutritionist n= nutriService.findByPrincipal();
////	   Comment saved;
////	   Comment c= clientService.comentar(n);
////	   @SuppressWarnings("deprecation")
////	  Date d= new Date("12/12/2012");
////	   c.setCreationDate(d);
////	   Recipe r= recipeService.findOne(29);
////	   c.setRecipe(r);
////	   c.setStars(5.5);
////	   c.setText("Maemia chiquillo");
////	   c.setTittle("Higueras el Flequi");
////	   saved=nutriService.comentar(c);
////	   Nutritionist n1= nutriService.findByPrincipal();
////	   Assert.isTrue(n1.getComments().contains(saved));
////	   super.authenticate(null);
////	    }
////	  
//	 // @Test
////	  public void TestTaste(){
////	   super.authenticate("nutri1");
////	   Nutritionist n= nutriService.findByPrincipal();
////	   Taste saved;
////	   Taste t= clientService.gusta(n);
////	   t.setLikee(true);
////	   Recipe r= recipeService.findOne(29);
////	   t.setRecipe(r);
////	   saved=nutriService.gustarReceta(t);
////	   Nutritionist n1= nutriService.findByPrincipal();
////	   Assert.isTrue(n1.getTastes().contains(saved));
////	   super.authenticate(null);
////	    }
//	 
////	 @Test
////	 public void TestSaveNutritionist(){
////	    Nutritionist nutri, saved;
////	    UserAccount u1= new UserAccount();
////	    Collection<Nutritionist> nutris;
////	    Collection<Authority> authorities= new HashSet<Authority>();
////	    nutri=nutriService.create();
////	    nutri.setEmailAdress("ejemploDeNutri@us.es");
////	    nutri.setName("Pepe");
////	    nutri.setSurname("El Papafrita");
////	    u1.setUsername("Papita69");
////	    u1.setPassword("69Papita");
////	    Authority asd=new Authority();
////	    asd.setAuthority("NUTRITIONIST");
////	    authorities.add(asd);
////	    u1.setAuthorities(authorities);
////	    nutri.setUserAccount(u1);
////	    saved=nutriService.save(nutri);
////	    nutris=nutriService.allNutritionist();
////	    Assert.isTrue(nutris.contains(saved));
////	   }
}
