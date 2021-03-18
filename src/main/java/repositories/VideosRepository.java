package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Videos;
@Repository
public interface VideosRepository extends JpaRepository<Videos, Integer> {

	
	@Query("select v from Videos v join v.masterClass m where m.id=?1")
	Collection<Videos> videosByMasterClass(int mcId);
}
