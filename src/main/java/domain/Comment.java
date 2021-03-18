package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Comment extends DomainEntity {
	
	private String tittle;
	private String text;
	private double stars;
	private Date creationDate;
    private Client client;
    private Recipe recipe;
    
    @NotBlank
	public String getTittle() {
		return tittle;
	}
    
	public void setTittle(String tittle) {
		this.tittle = tittle;
	}
    @NotBlank
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	@NotNull
    @Min(0)
	public double getStars() {
		return stars;
	}
	
	public void setStars(double stars) {
		this.stars = stars;
	}
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
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
