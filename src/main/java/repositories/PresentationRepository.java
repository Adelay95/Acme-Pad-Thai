package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Presentation;
@Repository
public interface PresentationRepository extends JpaRepository<Presentation, Integer> {

	
	@Query("select p from Presentation p join p.masterClass m where m.id=?1")
	Collection<Presentation> presentationsByMasterClass(int mcId);
}
