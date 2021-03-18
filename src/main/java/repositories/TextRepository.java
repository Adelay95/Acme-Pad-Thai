package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Text;
@Repository
public interface TextRepository extends JpaRepository<Text, Integer> {
      
	@Query("select t from Text t join t.masterClass m where m.id=?1")
	Collection<Text> textByMasterClass(int mcId);
}
