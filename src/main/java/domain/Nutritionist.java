package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
 public class Nutritionist extends Client{

   private Collection<Curricula> curricula;
   
   @NotNull
	@Valid
   @OneToMany(mappedBy="nutritionist")
   public Collection<Curricula> getCurricula() {
    return this.curricula;
   }
   
   public void setCurricula(Collection<Curricula> curricula) {
    this.curricula= curricula;
   }
  

}