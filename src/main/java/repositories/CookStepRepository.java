package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.CookStep;
@Repository
public interface CookStepRepository extends JpaRepository<CookStep, Integer> {

}
