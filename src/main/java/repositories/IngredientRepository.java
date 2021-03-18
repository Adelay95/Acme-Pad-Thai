package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Ingredient;
@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
	 @Query("select i from Has h join h.ingredient i where h.property.id=?1")
     Collection<Ingredient> ingredientByProperty(int propertyId);
}
