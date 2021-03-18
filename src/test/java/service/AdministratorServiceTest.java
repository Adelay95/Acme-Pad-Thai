package service;

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
import services.SponsorService;
import security.Authority;
import security.UserAccount;
import services.AdministratorService;
import services.CategoryService;
import services.ContestService;
import services.MasterClassService;
import services.SpamService;
import services.UserService;
import utilities.AbstractTest;
import domain.Administrator;
import domain.Category;
import domain.Contest;
import domain.Cook;
import domain.MasterClass;
import domain.Spam;
import domain.Sponsor;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml"})
@Transactional

public class AdministratorServiceTest extends AbstractTest{
	
//	@Autowired
//	private AdministratorService administratorService;
//	@Autowired
//	private SpamService spamService;
//	@Autowired
//	private ContestService contestService;
//	@Autowired
//	private CategoryService categoryService;
//	@Autowired
//	private MasterClassService masterClassService;
//	@Autowired
//	private SponsorService sponsorService;
//	@Autowired
//	private UserService userService;
//	
//	@Test
//	public void TestSaveAdministrator(){
//		Administrator admin, saved;
//		UserAccount u1= new UserAccount();
//		Collection<Administrator> admins;
//		Collection<Authority> authorities= new HashSet<Authority>();
//		admin=administratorService.create();
//		admin.setEmailAdress("ejemplo@us.es");
//		admin.setName("Manolo");
//		admin.setSurname("De Prueba");
//		u1.setUsername("Adelay");
//		u1.setPassword("KJIESKA89");
//		Authority asd=new Authority();
//		asd.setAuthority("ADMINISTRATOR");
//		authorities.add(asd);
//		u1.setAuthorities(authorities);
//		admin.setUserAccount(u1);
//		saved=administratorService.save(admin);
//		admins=administratorService.allAdministrator();
//		Assert.isTrue(admins.contains(saved));
//	}
//	@Test
//	public void testCrearSpam(){
//		Spam spam;
//		spam=administratorService.crearSpam();
//		Collection<Spam> spams= spamService.allSpam();
//		Assert.isTrue(spams.contains(spam));
//	}
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testCrearContest(){
//		Contest contest,contest2;
//		contest=contestService.create();
//		Date close=new Date("15/12/2016");
//		Date open=new Date("15/11/2016");
//		contest.setOpeningTime(open);
//		contest.setClosingTime(close);
//		contest.setTitle("Concurso de Prueba");
//		contest2=administratorService.crearContest(contest);
//		Collection<Contest> contests= contestService.allContest();
//		Assert.isTrue(contests.contains(contest2));
//	}
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testModificarContest1(){
//		Contest contest,contest2,contest3;
//		contest=contestService.create();
//		Date close=new Date("15/12/2016");
//		Date open=new Date("15/11/2016");
//		contest.setOpeningTime(open);
//		contest.setClosingTime(close);
//		contest.setTitle("Concurso de Prueba");
//		contest2=administratorService.crearContest(contest);
//		Integer id=contest2.getId();
//		Date close1=new Date("31/12/2016");
//		Date close2=new Date("31/01/2017");
//		
//		contest3=administratorService.modificarContest(
//				id,close1, "Modificación de Prueba", close2);
//		Collection<Contest> contests= contestService.allContest();
//		Assert.isTrue(contests.contains(contest3));
//		Assert.isTrue(contest3.getTitle()=="Modificación de Prueba");
//		Assert.isTrue(contest3.getOpeningTime()==close1);
//		Assert.isTrue(contest3.getClosingTime()==close2);
//	}
//	@SuppressWarnings("deprecation")
//	@Test
//	public void testModificarContest2(){
//		Contest contest,contest2,contest3;
//		contest=contestService.create();
//		Date close=new Date("12/15/2016");
//		Date open=new Date("11/14/2016");
//		contest.setOpeningTime(open);
//		contest.setClosingTime(close);
//		contest.setTitle("Concurso de Prueba");
//		contest2=administratorService.crearContest(contest);
//		Integer id=contest2.getId();
//		Date close1=new Date("12/31/2016");
//		contest3=administratorService.modificarContest(
//				id,open, "Modificación de Prueba", close1);
//		Collection<Contest> contests= contestService.allContest();
//		Assert.isTrue(contests.contains(contest3));
//		Assert.isTrue(contest3.getClosingTime()==close1);
//	}
//	@SuppressWarnings("deprecation")
//	@Test
//	 public void testModificarContest3(){
//	  Contest contest,contest2;
//	  contest=contestService.create();
//	  Date close=new Date("12/15/2016");
//	  Date open=new Date("11/14/2016");
//	  contest.setOpeningTime(open);
//	  contest.setClosingTime(close);
//	  contest.setTitle("Concurso de Prueba");
//	  contest2=administratorService.crearContest(contest);
//	  Integer id=contest2.getId();
//	  Date close1=new Date("12/31/2016");
//	  try{
//	  administratorService.modificarContest(
//	    id,open, "Modificación de Prueba", close1);
//	  }catch (Exception e) {
//	  
//	  }
//	 }
//	 @SuppressWarnings("deprecation")
//	@Test
//	 public void testModificarContest4(){
//	  Contest contest;
//	  contest=contestService.findOne(26);
//	  Date close1=new Date("12/31/2016");
//	  
//	  try{
//	  administratorService.modificarContest(
//	    26,contest.getOpeningTime(), "Modificación de Prueba", close1);
//	  }catch (Exception e) {
//	   
//	  }
//	  
//	 }
//	@SuppressWarnings("deprecation")
//	@Test
//	public void TestBorrarContest(){
//		Contest contest,contest2;
//		contest=contestService.create();
//		Date close=new Date("15/12/2016");
//		Date open=new Date("15/11/2016");
//		contest.setOpeningTime(open);
//		contest.setClosingTime(close);
//		contest.setTitle("Concurso de Prueba");
//		contest2=administratorService.crearContest(contest);
//		Collection<Contest> contests= contestService.allContest();
//		Assert.isTrue(contests.contains(contest2));
//		administratorService.borrarContest(contest2.getId());
//		Collection<Contest> contests2= administratorService.contests();
//		Assert.isTrue(!contests2.contains(contest2));
//	}
//	
//	@Test
//	public void TestCrearCategory(){
//		Category res,saved;
//		res=categoryService.create();
//		res.setName("PESCADO");
//		res.setDescription("Recetas con alto contenido en pescado");
//		saved=administratorService.crearCategory(res);
//		Collection<Category> categories=administratorService.categories();
//		Assert.isTrue(categories.contains(saved));
//		
//	}
//	
//	@Test
//	public void TestBorrarCategory(){
//		Category res,saved;
//		res=categoryService.create();
//		res.setName("PESCADO");
//		res.setDescription("Recetas con alto contenido en pescado");
//		saved=administratorService.crearCategory(res);
//		Collection<Category> categories=administratorService.categories();
//		Assert.isTrue(categories.contains(saved));
//		administratorService.borrarCategory(saved);
//		Collection<Category> categories1=administratorService.categories();
//		Assert.isTrue(!categories1.contains(saved));
//	}
//	@Test
//	public void TestModificarCategory(){
//		Category cat1,cat2,cat3,cat4;
//		cat4=categoryService.findOne(181);
//		cat2=categoryService.findOne(182);
//		cat3=categoryService.findOne(183);
//		Collection<Category> newParents=new HashSet<Category>();
//		Collection<Category> newSons=new HashSet<Category>();
//		newParents.add(cat2);
//		newSons.add(cat3);
//		String tags = ("tag1, tag2");
//		cat1=administratorService.actualizarCategory(181, "PESCADO"
//				, "Recetas con alto contenido en pescado", "http://www.google.com", tags
//				, newSons, newParents, cat4.getRecipes());
//		Collection<Category> categories=administratorService.categories();
//		Assert.isTrue(categories.contains(cat1));
// 		Assert.isTrue(cat1.getName()=="PESCADO");
//    	Assert.isTrue(cat1.getDescription()=="Recetas con alto contenido en pescado");
//		Assert.isTrue(cat1.getPicture()=="http://www.google.com");
//		Assert.isTrue(cat1.getTags()=="tag1, tag2");
//     	Assert.isTrue(cat1.getSons().contains(cat3) && cat1.getSons().size()==1);
//		Assert.isTrue(cat1.getParents().contains(cat2) && cat1.getParents().size()==1);
//	}
////	@Test 
////	public void testPromoteMC(){
////		super.authenticate("admin3");
////		Administrator a = administratorService.findByPrincipal();
////		administratorService.promote(147);
////		MasterClass mc = masterClassService.findOne(147);
////		Assert.isTrue(mc.getAdministrators().contains(a));
////	}
////	
////	@Test 
////	public void testDemoteMC(){
////		super.authenticate("admin");
////		Administrator a = administratorService.findByPrincipal();
////		MasterClass mc = masterClassService.findOne(163);
////		Assert.isTrue(mc.getAdministrators().contains(a));
////		administratorService.depromote(163);
////		MasterClass mc2 = masterClassService.findOne(163);
////		Assert.isTrue(!mc2.getAdministrators().contains(a));
////	}
////	@Test 
////	public void testCalcularGanador(){
////		administratorService.calcularGanador(26);
////		Contest res = contestService.findOne(26);
////		Collection<Contest> colls=administratorService.contests();
////		Assert.isTrue(colls.contains(res));
////		Assert.isTrue(res.getWinnerRecipes().size()==3);
////	}
////	
////	@Test
////	public void testCampañasPorSponsor(){
////		Collection<Double> res=administratorService.campañasPorSponsor();
////		//Assert.isTrue(res.size()==3);
////		Assert.isTrue(res.isEmpty());
////	}
//	
//	@Test
//	public void testRankingCompanyName(){
//		Collection<String> res=administratorService.rankingCompanyName();
//		Assert.isTrue(res.contains("TPT"));
//		Assert.isTrue(res.contains("Overwatch"));
//		Assert.isTrue(res.size()==2);
//	}
//	
//	@Test
//	public void testRankingMonthlyBills(){
//		Collection<String> res = administratorService.rankingMonthlyBills();
//		Assert.isTrue(res.contains("TPT"));
//		Assert.isTrue(res.contains("Overwatch"));
//		Assert.isTrue(res.size()==2);
//	}
//	
////	@Test
////	public void testUnpaidStadisticBills(){
////		Collection<Double> res = administratorService.unpaidStadisticBills();
////		Assert.isTrue(res.size()==1);
////	}
//	
//	@Test
//	public void testSponsorNoActive(){
//		Collection<Sponsor> res= administratorService.sponsorNoActive();
//		  Assert.isTrue(!res.isEmpty());
//		  Assert.isTrue(res.contains(sponsorService.findOne(124)));
//	}
//	
//	@Test
//	public void testCampaignNoActive(){
//		Collection<String> res=administratorService.campaignNoActive();
//		Assert.isTrue(res.size()==1);
//		Assert.isTrue(res.contains("Overwatch"));
//	}
//	
//	@Test
//	public void testCampaign90(){
//		Collection<String> res=administratorService.campaign90();
//		Assert.isTrue(res.size()==1);
//		Assert.isTrue(res.contains("TPT"));
//	}
//	
//	@Test
//	public void testNumMasterClassPromoted(){
//		Integer res=administratorService.numMasterClassPromoted();
//		Assert.isTrue(res==2);
//	}
//	
//	@Test
//	public void testRankingMasterClassPromeoted(){
//		Collection<Cook> res= administratorService.rankingMasterClassPromeoted();
//		Assert.isTrue(res.size()==3);
//	}
//	@Test
//	public void testMaxRecipesUser(){
//		Collection<User> res=administratorService.maxRecipesUser();
//		User este=userService.findOne(16);
//		Assert.isTrue(res.size()==1);
//		Assert.isTrue(res.contains(este));
//	}
//	
//	@Test
//	public void testPopularityListing(){
//		Collection<User> res=administratorService.popularityListing();
//		Assert.isTrue(res.size()==5);
//	}
//	
//	@Test
//	public void testTastesUsers(){
//		Collection<User> res=administratorService.tastesUsers();
//		User u1=userService.findOne(15);
//		User u2=userService.findOne(16);
//		User u3=userService.findOne(12);
//		Assert.isTrue(res.size()==3);
//		Assert.isTrue(res.contains(u2));
//		Assert.isTrue(res.contains(u1));
//		Assert.isTrue(res.contains(u3));
//		
//	}
//	@Test
//	public void testGetContestMaxRecipesQualificated(){
//		Collection<Contest> res=administratorService.getcontestMaxRecipesQualificated();
//		Contest c1=contestService.findOne(26);
//		Contest c2=contestService.findOne(27);
//		Assert.isTrue(res.size()==2);
//		Assert.isTrue(res.contains(c1));
//		Assert.isTrue(res.contains(c2));
//	}
//	
//	@Test
//	public void testMaxSponsor(){
//		Integer res=administratorService.maximoSponsor();
//		Assert.notNull(res==1);
//	}
//	@Test
//	public void testMinSponsor(){
//		Integer res=administratorService.minimoSponsor();
//		Assert.notNull(res==1);
//	}
}
