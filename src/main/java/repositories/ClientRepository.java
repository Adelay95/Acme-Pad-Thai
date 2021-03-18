package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Client;
import domain.Recipe;
import domain.User;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
	@Query("select c.recipes from User c join c.followed r join c.recipes q where r.id=?1 order by q.lastMomentUpdated DESC")
	Collection<Recipe> recetasClientSeguidos(Integer usuario);
	@Query("select c from Client c where c.userAccount.id = ?1")
	Client findByUserAccountId(int id);
	@Query("select u from User u join u.followed i where i.id=?1")
    Collection<User> getFollowersUser(int clientId);
}
