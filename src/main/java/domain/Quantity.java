package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Entity
@Access(AccessType.PROPERTY)
public class Quantity extends DomainEntity {
	private Measure measure;
	private Ingredient ingredient;
	private Recipe recipe;

	@NotNull
	@Valid
	public Measure getMeasure() {
		return measure;
	}
	
	public void setMeasure(Measure measure) {
		this.measure = measure;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Ingredient getIngredient() {
		return ingredient;
	}
	
	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	
	

}
