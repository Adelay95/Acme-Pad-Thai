package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Contest extends DomainEntity{
	private String title;
	private Date openingTime;
	private Date closingTime;
	private Collection<Recipe> qualifiedRecipes;
	private Collection<Recipe> winnerRecipes;
	private Collection<Recipe> originalRecipes;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getOpeningTime() {
		return openingTime;
	}
	public void setOpeningTime(Date openingTime) {
		this.openingTime = openingTime;
	}
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getClosingTime() {
		return closingTime;
	}
	public void setClosingTime(Date closingTime) {
		this.closingTime = closingTime;
	}
	@NotNull
 	@Valid
	@OneToMany(mappedBy="qualifiedContests")
	public Collection<Recipe> getQualifiedRecipes() {
		return qualifiedRecipes;
	}
	public void setQualifiedRecipes(Collection<Recipe> qualifiedRecipes) {
		this.qualifiedRecipes = qualifiedRecipes;
	}
	@NotNull
 	@Valid
	@OneToMany(mappedBy="winnerContests")
	public Collection<Recipe> getWinnerRecipes() {
		return winnerRecipes;
	}
	public void setWinnerRecipes(Collection<Recipe> winnerRecipes) {
		this.winnerRecipes = winnerRecipes;
	}
	@NotNull
	@ManyToMany
	public Collection<Recipe> getOriginalRecipes() {
		return originalRecipes;
	}
	public void setOriginalRecipes(Collection<Recipe> originalRecipes) {
		this.originalRecipes = originalRecipes;
	}
	
}
