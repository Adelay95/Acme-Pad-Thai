package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
@Entity
@Access(AccessType.PROPERTY)
public class Taste extends DomainEntity {
	private boolean likee;
	private Client client;
	private Recipe recipe;
	
	@NotNull
	public boolean isLikee() {
		return likee;
	}
	
	public void setLikee(boolean like2) {
		this.likee = like2;
	}

	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
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
