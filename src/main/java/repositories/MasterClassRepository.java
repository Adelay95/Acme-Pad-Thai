package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.MasterClass;
@Repository
public interface MasterClassRepository extends JpaRepository<MasterClass, Integer> {

	@Query("select avg(c.numVideos) from MasterClass c")
	Double getAvgVideosMasterClass();
	@Query("select avg(c.numText) from MasterClass c")
	Double getAvgTextMasterClass();
	@Query("select avg(c.numPresentation) from MasterClass c")
	Double getAvgPresentationMasterClass();
	
	@Query("select count(c) from MasterClass c where c.promote = true")
	Integer numMasterClassPromoted();
	
	@Query("select count(t) from MasterClass m join m.learningMaterials t where m.id=?1 and t.class=domain.Text")
	Integer numTextMC(int masterClassId);
	@Query("select count(v) from MasterClass m join m.learningMaterials v where m.id=?1 and v.class=domain.Videos")
	Integer numVideosMC(int masterClassId);
	@Query("select count(p) from MasterClass m join m.learningMaterials p where m.id=?1 and p.class=domain.Presentation")
	Integer numPresentationsMC(int masterClassId);
	
}
