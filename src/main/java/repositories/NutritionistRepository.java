package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curricula;
import domain.Nutritionist;
import domain.Property;
import domain.Recipe;
@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Integer> {

	

	@Query("select c.recipes from User c join c.followed r where r.id=?1")
	Collection<Recipe> recetasUsuariosSeguidos(Integer usuario);
	
	@Query("select c from Nutritionist c where c.userAccount.id = ?1")
	Nutritionist findByUserAccountId(int userAccountId);
	@Query("select u.curricula from Nutritionist u where u.id=?1")
	 Collection<Curricula> curriculasDelNutricionista(Integer nutritionist);
//    @Query("select p form Property p where ")
//	Collection<Property> mostrarPropertiesHas(int hasid);
	
	@Query("select p from Property p where p NOT IN(select p from Has h join h.property p where h.ingredient.id=?1)")
	Collection<Property> propertyNoUsed(int idIngre);
}

