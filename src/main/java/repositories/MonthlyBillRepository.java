package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;
import domain.MonthlyBill;
@Repository
public interface MonthlyBillRepository extends JpaRepository<MonthlyBill, Integer> {
	
	@Query("select SUM(c.costBanner*b.numBannerMes)from Campaign c join c.banners b where c.sponsor.id=?1 ")
	Double getCoste(int idSponsor);
	@Query("select b from Campaign c join c.banners b where c.sponsor.id=?1")
	Collection<Banner> dameBanner(int idSponsor);
	@Query("select avg(m.cost) from MonthlyBill m where m.momentPaid is null")
	Double getAvgUnpaidBills();
	@Query("select stddev(m.cost) from MonthlyBill m where m.momentPaid is null")
	Double getDevUnpaidBills();
	
	@Query("select avg(m.cost) from MonthlyBill m where m.momentPaid is not null")
	Double getAvgPaidBills();
	@Query("select stddev(m.cost) from MonthlyBill m where m.momentPaid is not null")
	Double getDevPaidBills();
	
	@Query("select m.sponsor.companyName from MonthlyBill m where m.momentPaid<=CURRENT_DATE group by m.sponsor.companyName having sum(m.cost)<(select avg(m.cost) from MonthlyBill m))")
	Collection<String> getCampaignNoActive();
	@Query("select m.sponsor.companyName from MonthlyBill m where m.momentPaid<=CURRENT_DATE group by m.sponsor.companyName having sum(m.cost)>(select max(m.cost) from MonthlyBill m)*0.9)")
	Collection<String> getCampaign90();
}
