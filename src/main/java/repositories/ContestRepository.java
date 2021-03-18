package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contest;
import domain.Recipe;
@Repository
public interface ContestRepository extends JpaRepository<Contest, Integer> {

	
	

	//Querys
    

	@Query("select c from Contest c")
	Collection<Contest> todosConcursos();
	
	@Query("select c.qualifiedRecipes from Contest c where c.id=?1")
	Collection<Recipe> recetasCalificadasEnConcurso(Integer concurso);
	
	@Query("select c.winnerRecipes from Contest c where c.id=?1")
	Collection<Recipe> recetasGanadorasEnConcurso(Integer concurso);
	

	//Querys
	@Query("select max(c.qualifiedRecipes.size)from Contest c")
	Integer getMaxRecipesContests();
	@Query("select avg(c.qualifiedRecipes.size) from Contest c")
	Double getAvgRecipesContests();
	@Query("select min(c.qualifiedRecipes.size) from Contest c")
	Integer getMinRecipesContests();
	@Query("select c from Contest c where c.qualifiedRecipes.size = (select max(c.qualifiedRecipes.size) from Contest c)")
	Collection<Contest> contestMaxRecipesQualificated();
    @Query("select c from Contest c where c.closingTime<=CURRENT_TIMESTAMP and c.winnerRecipes IS EMPTY")
	Collection<Contest> getAllFinishedContest();
    @Query("select c from Contest c where c.closingTime>=CURRENT_TIMESTAMP and c.openingTime<=CURRENT_TIMESTAMP")
  	Collection<Contest> getAllActiveContest();
    
}
