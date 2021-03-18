package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Category;
import domain.Comment;
import domain.Ingredient;
import domain.Recipe;
import domain.User;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

	@Query("select r.user from Recipe r where r.id=?1")
	User autorReceta(Integer receta);
	@Query("select c from Recipe r join r.comments c where r.id=?1 order by c.creationDate desc")
	Collection<Comment> cometariosOrdenadosReceta(int recipeId);
	@Query(" select distinct r from Category c join c.recipes r")
	Collection<Recipe> recetasAgrupadasCategoria();
	@Query("select r from Recipe r where ( r.ticker like ?1 or r.summary like ?1 or r.title like ?1 ) and r.qualifiedContests=null")
	Collection<Recipe> encontrarPor(String palabra);
	@Query("select avg(r.cookSteps.size) from Recipe r where r.qualifiedContests=null and r.winnerContests=null")
	Double getAvgStepsRecipes();
	@Query("select stddev(r.cookSteps.size) from Recipe r where r.qualifiedContests=null and r.winnerContests=null")
	Double getDevStepsRecipes();
	@Query("select avg(r.quantities.size) from Recipe r where r.qualifiedContests=null and r.winnerContests=null")
	Double getAvgQuantitiesRecipes();
	@Query("select stddev(r.quantities.size) from Recipe r where r.qualifiedContests=null and r.winnerContests=null")
	Double getDevQuantitiesRecipes();
	@Query(" select distinct r from Category c join c.recipes r where r.qualifiedContests=null and r.winnerContests=null")
	Collection<Recipe> recetasOriginalesAgrupadasCategoria();
	@Query(" select r from Recipe r where r.qualifiedContests=null and r.winnerContests=null order by r.lastMomentUpdated DESC")
	Collection<Recipe> recetasOrdenadasUpdated();
	@Query("select r from Category c join c.recipes r where c.id=?1")
	Collection<Recipe> getRecipesByCategory(int id);
	 @Query("select i from Ingredient i where i NOT IN (select q.ingredient from Recipe r join r.quantities q where r.id=?1)")
	 Collection<Ingredient> getIngredientsNoRecipe(int recipeId);
	 
	 @Query("select c from Category c where c NOT IN (select c from Recipe r join r.categories c where r.id=?1)")
	 Collection<Category> getCategoriesNoRecipe(int recipeId);
	 
	 @Query("select count(t) from Recipe r join r.tastes t where r.id=?1 and t.likee=true")
	 Integer getLikesRecipe(int recipeId);
	 @Query(" select count(r) from User c join c.recipes r where c.id=?1 and r.qualifiedContests=null and r.winnerContests=null")
	 Integer getTheOnlyOneRecipe(int userId);
	 @Query("select r from Quantity q join q.recipe r where q.ingredient.id=?1")
	 Collection<Recipe> recipesByIngredient(int ingrId);
	 @Query("select r from Recipe r where r.qualifiedContests=null and r.user IN ?1 order by r.lastMomentUpdated DESC")
	 Collection<Recipe> recipesByFollower(Collection<User> followers);
}
