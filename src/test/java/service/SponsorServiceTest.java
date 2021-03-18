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

import security.Authority;
import security.UserAccount;
import services.CampaignService;
import services.MonthlyBillService;
import services.SponsorService;
import utilities.AbstractTest;
import domain.Campaign;
import domain.CreditCard;
import domain.MonthlyBill;
import domain.Sponsor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
"classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml"})
@Transactional
public class SponsorServiceTest extends AbstractTest{
	//Service under test
//		@Autowired
//		private SponsorService sponsorService;
//		
//		@Autowired
//		private CampaignService campaignService;
//		
//		@Autowired
//		private MonthlyBillService monthlyBillService;
//		
//		
//	//Test
//		@Test
//		public void TestSaveSponsor(){
//			Sponsor sponsor, saved;
//			UserAccount u1= new UserAccount();
//			Collection<Sponsor> sponsors;
//			Collection<Authority> authorities= new HashSet<Authority>();
//			sponsor=sponsorService.create();
//			sponsor.setEmailAdress("ejemplo@us.es");
//			sponsor.setName("Pepito");
//			sponsor.setSurname("Perez");
//			sponsor.setCompanyName("TPT");
//			CreditCard cc=new CreditCard();
//			cc.setBrandName("BRAND");
//			cc.setExpirationMonth(1);
//			cc.setExpirationYear(8653);
//			cc.setHolderName("HOLDER");
//			cc.setNumber("1234567890123456");
//			cc.setcVV(123);
//			sponsor.setCreditCard(cc);
//			u1.setUsername("Pepito");
//			u1.setPassword("OlaKeAse");
//			Authority asd=new Authority();
//			asd.setAuthority("SPONSOR");
//			authorities.add(asd);
//			u1.setAuthorities(authorities);
//			sponsor.setUserAccount(u1);
//			saved=sponsorService.save(sponsor);
//			sponsors=sponsorService.allSponsors();
//			Assert.isTrue(sponsors.contains(saved));
//		}
//		
//			@SuppressWarnings("deprecation")
//			@Test
//			public void TestActualizaCampaign(){
//				super.authenticate("sponsor2");
//				Campaign camp,saved;
//				Date started=new Date("01/06/2017");
//				Date end=new Date("06/06/2017");
//				saved=sponsorService.actualizaCampaign(131, started, end, 3, 9, true, 0.25);
//				camp=campaignService.findOne(saved.getId());
//				Assert.isTrue(camp.getMomentStarted().equals(started));
//				Assert.isTrue(camp.getMomentFinished().equals(end));
//				Assert.isTrue(camp.getNumBanner()==3);
//				Assert.isTrue(camp.getNumMaxBanner()==9);
//				Assert.isTrue(camp.getStar()==true);
//				Assert.isTrue(camp.getCostBanner()==0.25);
//				super.authenticate(null);
//			}
//			@SuppressWarnings("deprecation")
//			@Test
//			public void TestActualizaCampaignBadDate(){
//				super.authenticate("sponsor2");
//				Date started=new Date("03/06/2016");
//				Date end=new Date("06/06/2017");
//				try{
//				sponsorService.actualizaCampaign(131, started, end, 3, 9, true, 0.25);
//				}catch(Exception e){}
//				super.authenticate(null);
//			}
//			@Test
//			public void TestDeleteCampaign(){
//				super.authenticate("sponsor2");
//				Campaign camp;
//				Collection<Campaign> campaigns;
//				camp=campaignService.findOne(131);
//				sponsorService.borrarCampaign(camp);
//				campaigns=campaignService.allCampaign();
//				Assert.isTrue(!campaigns.contains(camp));
//				super.authenticate(null);
//			}
//			@Test
//			public void TestDeleteCampaignBadSponsor(){
//				super.authenticate("sponsor1");
//				Campaign camp;
//				Collection<Campaign> campaigns;
//				camp=campaignService.findOne(131);
//				campaigns=campaignService.allCampaign();
//				try{
//				sponsorService.borrarCampaign(camp);
//				Assert.isTrue(campaigns.contains(camp));
//				}catch(Exception e){}
//				
//				super.authenticate(null);
//			}
//			@Test
//			public void TestChangeCreditCard(){
//				super.authenticate("sponsor1");
//				Sponsor ponsor;
//				sponsorService.changeCreditCard("Nuevo Holder","Nuevo Brand", "6543210987654321", 02, 3000, 473);
//				ponsor=sponsorService.findByPrincipal();
//				Assert.isTrue(ponsor.getCreditCard().getHolderName().equals("Nuevo Holder"));
//				Assert.isTrue(ponsor.getCreditCard().getBrandName().equals("Nuevo Brand"));
//				Assert.isTrue(ponsor.getCreditCard().getNumber().equals("6543210987654321"));
//				Assert.isTrue(ponsor.getCreditCard().getExpirationMonth()==2);
//				Assert.isTrue(ponsor.getCreditCard().getExpirationYear()==3000);
//				Assert.isTrue(ponsor.getCreditCard().getcVV()==473);
//				super.authenticate(null);
//			}
//			@Test
//			public void TestChangeBadCreditCard(){
//				super.authenticate("sponsor1");
//				try{
//				sponsorService.changeCreditCard("Nuevo Holder","Nuevo Brand", "6547654321", 02, 3000, 21473);
//				}catch(Exception e){}
//				super.authenticate(null);
//			
//			}
//			@Test
//			public void TestPayMonthlyBills(){
//				super.authenticate("sponsor1");
//				MonthlyBill facture,res;
//				facture=monthlyBillService.findOne(136);
//				sponsorService.payMonthlyBills(facture);
//				res=monthlyBillService.findOne(136);
//				Date hoy= new Date();
//				Assert.isTrue(res.getMomentPaid().before(hoy));
//				super.authenticate(null);
//				
//			}
//			@Test
//			public void TestPayMonthlyBillsBadSponsor(){
//				super.authenticate("sponsor2");
//				MonthlyBill facture;
//				facture=monthlyBillService.findOne(136);
//				try{
//				sponsorService.payMonthlyBills(facture);
//				}catch(Exception e){}
//				super.authenticate(null);
//				
//			}
//			@SuppressWarnings("deprecation")
//			@Test
//			public void TestCountCampaignsActives(){
//				Campaign c,res;
//				Integer a;
//				Date started=new Date("03/06/2016");
//				Date end=new Date("06/06/2017");
//				super.authenticate("sponsor2");
//				Sponsor sponsor,sres;
//				sponsor=sponsorService.findByPrincipal();
//				a=sponsor.getActive();
//				c=campaignService.create(sponsor);
//				c.setMomentStarted(started);
//				c.setMomentFinished(end);
//				c.setNumBanner(4);
//				c.setNumMaxBanner(8);
//				c.setStar(true);
//				res=campaignService.save(c);
//				sponsor.getCampaigns().add(res);
//				sres=sponsorService.save(sponsor);
//				Assert.isTrue(sres.getActive()==a+1);
//				super.authenticate(null);
//			}
//			@SuppressWarnings("deprecation")
//			@Test
//			public void TestCrearCampaign(){
//				super.authenticate("sponsor2");
//				Sponsor sponsor,sponsor2;
//				sponsor=sponsorService.findByPrincipal();
//				Campaign camp,saved;
//				Date started=new Date("03/06/2016");
//				Date end=new Date("06/06/2017");
//				camp=campaignService.create(sponsor);
//				camp.setMomentStarted(started);
//				camp.setMomentFinished(end);
//				camp.setNumBanner(2);
//				camp.setNumMaxBanner(22);
//				camp.setStar(true);
//				saved=campaignService.save(camp);
//				sponsor.getCampaigns().add(saved);
//				sponsor2=sponsorService.findByPrincipal();
//				Assert.isTrue(sponsor2.getCampaigns().contains(saved));
//				super.authenticate(null);
//			
//			}
//			@SuppressWarnings("deprecation")
//			@Test
//			public void TestCrearBadCampaign(){
//				super.authenticate("sponsor2");
//				Sponsor sponsor,sponsor2;
//				sponsor=sponsorService.findByPrincipal();
//				Campaign camp,saved;
//				Date started=new Date("03/06/2017");
//				Date end=new Date("06/06/2016");
//				camp=campaignService.create(sponsor);
//				camp.setMomentStarted(started);
//				camp.setMomentFinished(end);
//				camp.setNumBanner(30);
//				camp.setNumMaxBanner(22);
//				camp.setStar(true);
//				try{
//				saved=campaignService.save(camp);
//				sponsor.getCampaigns().add(saved);
//				sponsor2=sponsorService.findByPrincipal();
//				Assert.isTrue(!sponsor2.getCampaigns().contains(saved));
//				}catch(Exception e){}
//			
//				super.authenticate(null);
//			
//			}
}
