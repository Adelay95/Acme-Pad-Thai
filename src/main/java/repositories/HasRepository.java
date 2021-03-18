package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Has;
@Repository
public interface HasRepository extends JpaRepository<Has, Integer> {
	@Query("select h from Has h where h.ingredient.id=?1 and h.property.id=?2")
	public Has getDuplicateHas(int ingId,int propId);
}
