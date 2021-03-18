package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SponsorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Banner;
import domain.Campaign;
import domain.CreditCard;
import domain.Folder;
import domain.Message;
import domain.MonthlyBill;
import domain.SocialIdentity;
import domain.Sponsor;


@Service
@Transactional
public class SponsorService {
	
	@Autowired
	private SponsorRepository sponsorRepository;
	
	@Autowired
	private CampaignService campaignService;
	@Autowired
	private MonthlyBillService monthlyBillService;
	@Autowired
	private ActorService actorService;
	@Autowired
	private BannerService bannerService;
	
	
	public SponsorService(){
		super();
	}
	
	
	public Sponsor create(){
		  Sponsor res;
		  CreditCard creditCard=new CreditCard();
		  Collection<Campaign> campaigns= new ArrayList<Campaign>();
		  Collection<MonthlyBill> monthlyBills= new ArrayList<MonthlyBill>();
		  Collection<Folder> folders= new ArrayList<Folder>();
		  Collection<SocialIdentity> socialIdentities=new HashSet<SocialIdentity>();
		  Collection<Message> messageSent=new HashSet<Message>();
		  Collection<Message> messageReceived=new HashSet<Message>();
		  UserAccount userAccount=new UserAccount();
			Authority aut=new Authority();
			aut.setAuthority("SPONSOR");
			Collection<Authority> authorities=new HashSet<Authority>();
			authorities.add(aut);
			userAccount.setAuthorities(authorities);
			
		  res=new Sponsor();
		  res.setUserAccount(userAccount);
		  res.setCreditCard(creditCard);
		  res.setSocialIdentities(socialIdentities);
		  res.setMessageReceived(messageReceived);
		  res.setMessageSent(messageSent);
		  res.setCampaigns(campaigns);
		  res.setMonthlyBills(monthlyBills);
		  res.setFolders(folders);
		  return res;
		  
		 }
		 
		 public Sponsor save(Sponsor sponsor){
		  Assert.notNull(sponsor);
		  Sponsor res;
		  Assert.notNull(sponsor.getCompanyName());
		  sponsor.setActive(campaignService.getAllActiveCampaign(sponsor.getId()).size());
		  res=sponsorRepository.save(sponsor);
		  if(res.getFolders().isEmpty()){
		   Collection<Folder> folder= actorService.createFolderss(res);
		         res.setFolders(folder);
		         res=sponsorRepository.save(res);
		  }
		  return res;
		 }
		 
	public void delete(Sponsor sponsor){
		Assert.notNull(sponsor);
		Assert.isTrue(sponsor.getId()!=0);
		sponsorRepository.delete(sponsor);
	}
	
	public  Collection<Sponsor> allSponsors(){
		Collection<Sponsor> res;
		res=sponsorRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Sponsor findOne(int id){
		Sponsor res;
		res=sponsorRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Sponsor findByPrincipal(){
		Sponsor res;
		UserAccount userAccount;
		userAccount=LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res=findByUserAccount(userAccount);
		Assert.notNull(res);
		return res;
	}


	private Sponsor findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Sponsor res;
		res=sponsorRepository.findByUserAccountId(userAccount.getId());
		return res;
	}
	
	public Campaign crearCampaign(Campaign c){
		Campaign res;
		Sponsor sponsor;
		Assert.notNull(c);
		sponsor=this.findByPrincipal();
		c.setSponsor(sponsor);
		res=campaignService.save(c);
		sponsor.getCampaigns().add(res);
		save(sponsor);
		return res;
	}
	public Collection<Campaign> campaignList(){
		Sponsor sponsor;
		sponsor=this.findByPrincipal();
		Collection<Campaign> res;
		res=sponsorRepository.findCampaignsById(sponsor.getId());
		return res;
	}
	
	
	public void borrarCampaign(Campaign campaign){
		Assert.notNull(campaign);
		Date currentDate=new Date();
		Sponsor sponsor;
		sponsor=findByPrincipal();
		Assert.isTrue(campaign.getSponsor().equals(sponsor));
		Assert.isTrue(campaign.getMomentStarted().after(currentDate));
		borrarBanners(campaign);
		sponsor.getCampaigns().remove(campaign);
		campaignService.delete(campaign);
		save(sponsor);
	}
	
	private void borrarBanners(Campaign campaign) {
		Collection<Banner> banners=campaign.getBanners();
		for(Banner b:banners){
			bannerService.delete(b);
		}
		
	}


	public Collection<Sponsor> sponsorMorosos(){
		return sponsorRepository.sponsorMorosos();
	}
	public Collection<Sponsor> sponsorMorosos2(){
		return sponsorRepository.sponsorMorosos2();
	}
	
	public CreditCard changeCreditCard(String holderName, String brandName, String number,
	int expirationMonth, int expirationYear,int cVV){
		Sponsor sponsor;
		sponsor=findByPrincipal();
		CreditCard creditCard=sponsor.getCreditCard();
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setcVV(cVV);
		creditCard.setNumber(number);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		sponsor.setCreditCard(creditCard);
		save(sponsor);
		return creditCard;		
	}
	
	public void payMonthlyBills(MonthlyBill facture){
		Sponsor res;
		Assert.notNull(facture);
		res=findByPrincipal();
		Assert.notNull(res);
		Assert.isTrue(facture.getSponsor().equals(res));
		
		Date now=new Date(System.currentTimeMillis() - 1000);
			if(facture.getMomentPaid()== null){
				facture.setMomentPaid(now);
				monthlyBillService.save(facture);
			}
		}
	
	//QUERY
	

	public Collection<String> getRankingCompanyName2(){
		return sponsorRepository.getRankingCompanyName();
	}
	
	public Collection<String> getRankingMonthlyBills2(){
		return sponsorRepository.getRankingByMonthlyBills();
	}
	
	public Collection<Sponsor> getSponsorNoActive2(){
		return sponsorRepository.getSponsorNoActive();
	}

	public Integer maxCampaignSponsor() {
		return sponsorRepository.getMaxCampaignSponsor();
	}
	public Integer minCampaignSponsor() {
		return sponsorRepository.getMinCampaignSponsor();
	}
	public Double avgCampaignSponsor() {
		return sponsorRepository.getAvgCampaignSponsor();
	}
	public Integer maxCampaignSponsorActives() {
		return sponsorRepository.getMaxCampaignSponsorActives();
	}
	public Integer minCampaignSponsorActives() {
		return sponsorRepository.getMinCampaignSponsorActives();
	}
	public Double avgCampaignSponsorActives() {
		return sponsorRepository.getAvgCampaignSponsorActives();
	}
	

}
