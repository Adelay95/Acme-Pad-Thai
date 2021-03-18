package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
@Entity
@Access(AccessType.PROPERTY)
public class User extends Client {
    private Collection<Recipe> recipes;
    private Integer numOriginalRecipes;

    @NotNull
	@Valid
    @OneToMany(mappedBy="user")
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
    
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
    @Min(0)
	public Integer getNumOriginalRecipes() {
		return numOriginalRecipes;
	}

	public void setNumOriginalRecipes(Integer numOriginalRecipes) {
		this.numOriginalRecipes = numOriginalRecipes;
	}
	
	
    
}
