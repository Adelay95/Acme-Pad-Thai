package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Banner;
import domain.Campaign;

@Service
@Transactional
public class BannerService {
	@Autowired
	private BannerRepository bannerRepository;
	
	public BannerService(){
		super();
	}
	
	public Banner create(Campaign ca){
		Banner res;
		Assert.notNull(ca);
		res=new Banner();
		res.setCampaign(ca);
		res.setNumBannerMes(0);
		
		return res;
	}
	
	public Banner save(Banner banner){
		Assert.notNull(banner);
		Banner res;
		res=bannerRepository.save(banner);
		//Campaign c=res.getCampaign();
		//c.getBanners().add(res);
		//campaignService.save(c);
		return res;
	}
	
	public void delete(Banner campaign){
		Assert.notNull(campaign);
		Assert.isTrue(campaign.getId()!=0);
		
		bannerRepository.delete(campaign);
	}
	
	public  Collection<Banner> allBanners(){
		Collection<Banner> res;
		res=bannerRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Banner findOne(int id){
		Banner res;
		res=bannerRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
}
