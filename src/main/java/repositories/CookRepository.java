package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Cook;
import domain.MasterClass;
@Repository
public interface CookRepository extends JpaRepository<Cook, Integer> {
	
	@Query("select c from Cook c where c.userAccount.id = ?1")
	Cook findByUserAccountId(int userAccountId);
	
	@Query("select u.masterClass from Cook u where u.id=?1")
	Collection<MasterClass> masterClassCook(Integer Cook);

	@Query("select c from Cook c join c.masterClass r group by c order by c.numPromotes DESC")
	Collection<Cook> getRankingMasterClassPromeoted();
	
	@Query("select min(c.masterClass.size) from Cook c")
	Integer getMinMasterClassCook();
	@Query("select avg(c.masterClass.size) from Cook c")
	Double getAvgMasterClassCook();
	@Query("select max(c.masterClass.size)from Cook c")
	Integer getMaxMasterClassCook();
	@Query("select stddev(c.masterClass.size) from Cook c")
	Double getDevMasterClassCook();
	@Query("select avg(c.numPromotes) from Cook c")
	Double getAvgCookNumPromoted();
	@Query("select avg(c.masterClass.size-c.numPromotes) from Cook c")
	Double getAvgCookNumDemoted();
	@Query("select count(m) from MasterClass m where m.cook.id=?1 and m.promote=true")
	Integer getNumMCPromoted(int cookID);
}

