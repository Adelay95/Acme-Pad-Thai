package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
	@Query("select c.name, m.title, m.description from MasterClass m join m.cook c")
	Collection<String> informacionMasterClass();
	@Query("select c from Actor c where c.userAccount.id = ?1")
	Actor findByUserAccountId(int id);
}
