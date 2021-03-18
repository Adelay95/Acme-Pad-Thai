package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Recipe;
import domain.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//	@Query("select u from User u")
//	Collection<User>todosUsuarios();
	

	
	//Querys

	
	@Query("select u.recipes from User u where u.id=?1")
	Collection<Recipe> recetasUsuario(Integer usuario);
	
	@Query("select r from Recipe r where r.user.id=?1 and r.qualifiedContests=null and r.winnerContests=null")
	Collection<Recipe> recetasOriginalesUsuario(Integer usuario);
	
	@Query("select u from User u where u.name like ?1")
	Collection<User> usuariosNombre(String nombre);
	
	
	@Query("select c from User c where c.userAccount.id = ?1")
	 User findByUserAccountId(int userAccountId);
	
	//Querys
	@Query("select min(s.numOriginalRecipes) from User s")
	Integer getMinRecipesUser();
	@Query("select avg(s.numOriginalRecipes) from User s")
	Double getAvgRecipesUser();
	@Query("select max(s.numOriginalRecipes) from User s")
	Integer getMaxRecipesUser();
	@Query("select c from User c where c.numOriginalRecipes = (select max(c.numOriginalRecipes) from User c)")
	Collection<User> getUserMaxRecipes();
	@Query("select u from User u order by u.followed.size DESC")
	Collection<User> getPopularityListing();
	@Query("select c from User c join c.recipes r group by c order by avg(r.tastes.size) DESC")
	Collection<User> getTastesUsers();
//	@Query("select i from Ingredient i where i NOT IN(select i from Has h join h.property p where h.ingredient.id=?1)")
//	Collection<Ingredient> ingredientNoUse(int idRecipe);
//	
	
}
