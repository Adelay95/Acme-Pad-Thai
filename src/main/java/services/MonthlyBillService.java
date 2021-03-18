package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MonthlyBillRepository;
import domain.Banner;
import domain.MonthlyBill;
import domain.Sponsor;

@Service
@Transactional
public class MonthlyBillService {
	@Autowired
	private MonthlyBillRepository monthlyBillRepository;
	@Autowired
	private BannerService bannerService;
	@Autowired
	private SponsorService sponsorService;
	
	public MonthlyBillService(){
		super();
	}
	

	public MonthlyBill create(Sponsor sponsor){
		MonthlyBill res;
		Date created;
		res=new MonthlyBill();
		Assert.notNull(sponsor);
		String descripcion="Factura de los banners mostrado en Acme-Pad-Thai";
		created = new Date(System.currentTimeMillis() - 1000);		
		res.setMomentCreated(created);
		res.setSponsor(sponsor);
		res.setCost(calculaCost1(sponsor));
		res.setMomentPaid(null);
		res.setDescription(descripcion);
		return res;
	}
	

	
	
	
	private Double calculaCost1(Sponsor sponsor){
		Collection<Banner> bans= monthlyBillRepository.dameBanner(sponsor.getId());
		
		Double res=monthlyBillRepository.getCoste(sponsor.getId());
		for(Banner b: bans){
			b.setNumBannerMes(0);
			bannerService.save(b);
		}
		return res;
	}
	
	
	
	public MonthlyBill save(MonthlyBill monthlyBill){
		Assert.notNull(monthlyBill);
		MonthlyBill res;	
		res=monthlyBillRepository.save(monthlyBill);
		Sponsor ss=res.getSponsor();
		ss.getMonthlyBills().add(res);
		sponsorService.save(ss);
		return res;
	}
	
	public void delete(MonthlyBill monthlyBill){
		Assert.notNull(monthlyBill);
		Assert.isTrue(monthlyBill.getId()!=0);
		monthlyBillRepository.delete(monthlyBill);
	}
	
	public  Collection<MonthlyBill> allCampaign(){
		Collection<MonthlyBill> res;
		res=monthlyBillRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public MonthlyBill findOne(int id){
		MonthlyBill res;
		res=monthlyBillRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	//QUERY
	

	public Collection<String> campaignNoActive(){
		return monthlyBillRepository.getCampaignNoActive();
	}
	
	public Collection<String> campaign90(){
		return monthlyBillRepository.getCampaign90();
	}
	
	public Double AvgPaidBills(){
		return monthlyBillRepository.getAvgPaidBills();
	}
	public Double AvgUnpaidBills(){
		return monthlyBillRepository.getAvgUnpaidBills();
	}
	public Double DevPaidBills(){
		return monthlyBillRepository.getDevPaidBills();
	}
	public Double DevUnpaidBills(){
		return monthlyBillRepository.getDevUnpaidBills();
	}

}
