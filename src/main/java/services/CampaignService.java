package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CampaignRepository;
import domain.Banner;
import domain.Campaign;
import domain.Sponsor;

@Service
@Transactional
public class CampaignService {
	
	@Autowired
	private CampaignRepository campaignRepository;
	@Autowired
	private BannerService bannerService;
	@Autowired
	private SponsorService sponsorService;
	
	public CampaignService(){
		super();
	}
	
	public Campaign create(Sponsor sponsor){
		Campaign res;
		res=new Campaign();
		Collection<Banner> banners= new HashSet<Banner>();
		Assert.notNull(sponsor);
		res.setNumBanner(0);
		res.setCostBanner(0.25);
		res.setSponsor(sponsor);
		res.setBanners(banners);
		return res;
	}
	
	public Campaign save(Campaign campaign){
		Assert.notNull(campaign);
		Campaign res;
		Date currentDate=new Date();
		if(campaign.getId()!=0){
		Assert.isTrue(campaign.getMomentStarted().after(currentDate));
		}
		Assert.isTrue(campaign.getMomentStarted().before(campaign.getMomentFinished()));
		Assert.isTrue(campaign.getNumBanner()<=campaign.getNumMaxBanner());
		
		res=campaignRepository.save(campaign);
		 if(campaign.getId()==0){
			   Sponsor sponsor=campaign.getSponsor();
			   sponsor.getCampaigns().add(res);
			   sponsorService.save(sponsor);
			  }
		return res;
	}
	
	public void delete(Campaign campaign){
		Assert.notNull(campaign);
		Assert.isTrue(campaign.getId()!=0);
		Date currentDate=new Date();
		Assert.isTrue(campaign.getMomentStarted().after(currentDate));
		campaignRepository.delete(campaign);
	}
	
	public  Collection<Campaign> allCampaign(){
		Collection<Campaign> res;
		res=campaignRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Campaign findOne(int id){
		Campaign res;
		res=campaignRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public String bannerWelcome(){
		String res=null;
		Campaign campaign=null;
		Banner baner=null;
		
		
		List<Campaign> campaigns=new ArrayList<Campaign>(campaignRepository.bannerWelcome());
		
		
		if(campaigns.isEmpty()){
			res=null;	
		
		}else{
			Random rand = new Random();
			Random banner= new Random();
			int x = rand.nextInt(campaigns.size());
			campaign=campaigns.get(x);
			List<Banner> banners=new ArrayList<Banner>(campaign.getBanners());
			int y=banner.nextInt(banners.size());
			baner=banners.get(y);
			res=baner.getUrl();
			int m= baner.getNumBannerMes();
			int n=campaign.getNumBanner();
			campaign.setNumBanner(n+1);
			baner.setNumBannerMes(m+1);
			campaignRepository.save(campaign);
			bannerService.save(baner);
		}
		return res;
	}
	public String bannerRecipe(){
		String res=null;
		Campaign campaign=null;
		Banner baner=null;
		
		
		List<Campaign> campaigns=new ArrayList<Campaign>(campaignRepository.bannerRecipe());
		
		
		if(campaigns.isEmpty()){
			res=null;	
		
		}else{
			Random rand = new Random();
			Random banner= new Random();
			int x = rand.nextInt(campaigns.size());
			campaign=campaigns.get(x);
			List<Banner> banners=new ArrayList<Banner>(campaign.getBanners());
			int y=banner.nextInt(banners.size());
			baner=banners.get(y);
			res=baner.getUrl();
			int m= baner.getNumBannerMes();
			int n=campaign.getNumBanner();
			campaign.setNumBanner(n+1);
			baner.setNumBannerMes(m+1);
			campaignRepository.save(campaign);
			bannerService.save(baner);
		}
		return res;
		}
	

	public Collection<Campaign> getAllActiveCampaign(int sponsorId){
		return campaignRepository.getAllActiveCampaign(sponsorId);
	}
}
