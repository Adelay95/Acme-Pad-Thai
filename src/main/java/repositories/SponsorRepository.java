package repositories;

import java.util.Collection;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;
import domain.Sponsor;
@Repository
public interface SponsorRepository extends JpaRepository<Sponsor, Integer> {

	//Separadas por
	@Query("select avg(c.active) from Sponsor c group by c")
	Double getMedia();
	
	@Query("select count(c) from Campaign c where c.momentFinished>CURRENT_DATE and c.momentStarted<CURRENT_DATE group by c.sponsor having count(c)<=ALL(select count(c) from Campaign c where c.momentFinished>CURRENT_DATE and c.momentStarted<CURRENT_DATE group by c.sponsor)")
	Integer getMinimo();
	@Query("select count(c) from Campaign c where c.momentFinished>CURRENT_DATE and c.momentStarted<CURRENT_DATE group by c.sponsor having count(c)>=ALL(select count(c) from Campaign c where c.momentFinished>CURRENT_DATE and c.momentStarted<CURRENT_DATE group by c.sponsor)")
	Integer getMaximo();
    @Query("select distinct s from MonthlyBill b join b.sponsor s where DATEDIFF(CURRENT_TIMESTAMP,b.momentCreated)>=30 and b.momentPaid=null")
	Collection<Sponsor> sponsorMorosos();
    @Query("select s from Sponsor s where 30>ANY(select DATEDIFF(CURRENT_TIMESTAMP,b.momentCreated) from MonthlyBill b where b.sponsor=s)")
   	Collection<Sponsor> sponsorMorosos2();
    
    @Query("select c from Sponsor c where c.userAccount.id = ?1")
	 Sponsor findByUserAccountId(int userAccountId);
   
	@Query("select c from Campaign c where c.sponsor.id=?1")
	Collection<Campaign> findCampaignsById(int id);

	//Query
	@Query("select max(s.campaigns.size) from Sponsor s")
	Integer getMaxCampaignSponsor();
	@Query("select avg(s.campaigns.size) from Sponsor s")
	Double getAvgCampaignSponsor();
	@Query("select min(s.campaigns.size) from Sponsor s")
	Integer getMinCampaignSponsor();
//	@Query()
//	Collection<Double> getCampaignsActive();
	@Query("select c.sponsor.companyName from Campaign c group by c.sponsor.companyName order by count(c) desc")
	Collection<String> getRankingCompanyName();
	@Query("select  m.sponsor.companyName from MonthlyBill m group by m.sponsor.companyName order by sum(m.cost) desc")
	Collection<String> getRankingByMonthlyBills();
	@Query("select s from Sponsor s where s NOT IN( select c.sponsor from Campaign c where c.sponsor.active=0 AND (c.momentFinished-CURRENT_DATE>300 OR c.momentStarted-CURRENT_DATE>300) group by c.sponsor)")
	 Collection<Sponsor> getSponsorNoActive();
	
	
	
	//Separadas por
	@Query("select avg(c.active) from Sponsor c")
	Double getAvgCampaignSponsorActives();
	
	@Query("select min(c.active) from Sponsor c")
	Integer getMinCampaignSponsorActives();
	@Query("select DISTINCT count(c) from Campaign c where c.momentFinished>CURRENT_DATE and c.momentStarted<CURRENT_DATE group by c.sponsor having count(c)>=ALL(select count(c) from Campaign c where c.momentFinished>CURRENT_DATE and c.momentStarted<CURRENT_DATE group by c.sponsor)")
	Integer getMaxCampaignSponsorActives();
	
}
