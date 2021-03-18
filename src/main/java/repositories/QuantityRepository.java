package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Quantity;
@Repository
public interface QuantityRepository extends JpaRepository<Quantity, Integer> {
	@Query("select q from Quantity q where q.ingredient.id=?1 and q.recipe.id=?2")
	public Quantity getDuplicateQuantity(int ingId,int recId);
}
