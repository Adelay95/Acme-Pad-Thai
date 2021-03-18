package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Campaign;
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Integer> {

	
	@Query("select c from Campaign c where c.momentStarted<=CURRENT_DATE and c.momentFinished>=CURRENT_DATE and c.star=true and c.numMaxBanner>c.numBanner and c.banners.size>0")
	Collection<Campaign> bannerWelcome();
	@Query("select c from Campaign c where c.momentStarted<=CURRENT_DATE and c.momentFinished>=CURRENT_DATE and c.numMaxBanner>c.numBanner")
	Collection<Campaign> bannerRecipe();
	

    @Query("select c from Campaign c where c.momentFinished>=CURRENT_TIMESTAMP and c.momentStarted<=CURRENT_TIMESTAMP and c.sponsor.id=?1")
  	Collection<Campaign> getAllActiveCampaign(int sponsorId);
}
