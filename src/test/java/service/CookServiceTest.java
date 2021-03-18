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
import services.ActorService;
import services.CookService;
import services.MasterClassService;
import services.NutritionistService;
import utilities.AbstractTest;
import domain.Cook;
import domain.LearningMaterial;
import domain.MasterClass;
import domain.Nutritionist;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml"})
@Transactional
public class CookServiceTest extends AbstractTest {
//Service under test
	@Autowired
	private CookService cookService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private MasterClassService masterClassService;
	@Autowired
	private NutritionistService nutriService;
	
//Test
	@Test
	public void TestSaveCook(){
		Cook cook, saved;
		UserAccount u1= new UserAccount();
		Collection<Cook> cooks;
		Collection<Authority> authorities= new HashSet<Authority>();
		cook=cookService.create();
		cook.setEmailAdress("ejemplo@us.es");
		cook.setName("Manolo");
		cook.setSurname("De Prueba");
		u1.setUsername("Adelay");
		u1.setPassword("KJIESKA89");
		Authority asd=new Authority();
		asd.setAuthority("COOK");
		authorities.add(asd);
		u1.setAuthorities(authorities);
		cook.setUserAccount(u1);
		saved=cookService.save(cook);
		cooks=cookService.allCooks();
		Assert.isTrue(cooks.contains(saved));
	}
	
	@Test
	public void TestUnSaveCook(){
		Cook cook;
		UserAccount u1= new UserAccount();
		Collection<Cook> cooks;
		Collection<Authority> authorities= new HashSet<Authority>();
		cook=cookService.create();
		cook.setEmailAdress("ejemplo@us.es");
		cook.setName("Manolo");
		cook.setSurname("De Prueba");
		u1.setUsername("Adelay");
		u1.setPassword("KJIESKA89");
		Authority asd=new Authority();
		asd.setAuthority("COOK");
		authorities.add(asd);
		u1.setAuthorities(authorities);
		cook.setUserAccount(u1);
		cookService.save(cook);
		cooks=cookService.allCooks();
		
		Assert.isTrue(!cooks.contains(cook));
		
	}
	
	@Test
	public void testDeleteCook(){
		Cook cook, saved;
		UserAccount u1= new UserAccount();
		Collection<Cook> cooks;
		Collection<Authority> authorities= new HashSet<Authority>();
		cook=cookService.create();
		cook.setEmailAdress("ejemplo@us.es");
		cook.setName("Manolo");
		cook.setSurname("De Prueba");
		u1.setUsername("Adelay");
		u1.setPassword("KJIESKA89");
		Authority asd=new Authority();
		asd.setAuthority("COOK");
		authorities.add(asd);
		u1.setAuthorities(authorities);
		cook.setUserAccount(u1);
		saved=cookService.save(cook);
		cookService.delete(saved);
		cooks=cookService.allCooks();
		Assert.isTrue(!cooks.contains(saved));
	}
	@Test
	public void BorrarMasterClass(){
		MasterClass m= masterClassService.findOne(147);
		Nutritionist n1=nutriService.findOne(2);
		actorService.registerMC(m, n1);
		Assert.isTrue(m.getAlums().contains(n1));
		
		super.authenticate("cooks2");
		
		cookService.borrarMaterClass(m);
		Assert.isTrue(!masterClassService.allMasterClass().contains(m));
		Assert.isTrue(n1.getMessageReceived().size()>1);
		super.authenticate(null);
	}
	@Test
	public void CrearMasterClass(){
		
		super.authenticate("cooks2");
		MasterClass res=masterClassService.create(cookService.findByPrincipal());
		Collection<LearningMaterial> learningMaterials;
		learningMaterials=masterClassService.findOne(147).getLearningMaterials();
		res.setLearningMaterials(learningMaterials);
		res.setDescription("oao");
		res.setTitle("asdadda");
		MasterClass save= cookService.crearMasterClass(res);
		Assert.isTrue(masterClassService.allMasterClass().contains(save));
		Assert.isTrue(cookService.findByPrincipal().getMasterClass().contains(save));
		Assert.isTrue(save.getCook().equals(cookService.findByPrincipal()));
		super.authenticate(null);
	}
	
//	@Test
//	public void ActualizarMC(){
//		super.authenticate("cooks2");
//		cookService.findByPrincipal();
//		MasterClass hi=masterClassService.findOne(147);
//		cookService.actualizarMasterrClass(147, "hi", "es mu bonita", hi.getCook(), hi.getLearningMaterials(), hi.getAdministrators(), hi.getAlums());
//		Assert.isTrue(masterClassService.findOne(147).getDescription().equals("es mu bonita"));
//		super.authenticate(null);
//	}
	
	
}
