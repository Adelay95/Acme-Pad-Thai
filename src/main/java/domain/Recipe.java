package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Recipe extends DomainEntity {
	private String ticker;
	private String summary;
	private String title;
	private Date momentAuthored;
	private Date lastMomentUpdated;
	private String picture;
	private String hints;
	private Collection<CookStep> cookSteps;
	private Contest qualifiedContests;
	private Contest winnerContests;
	private Collection<Quantity> quantities;
	private Collection<Comment> comments;
	private Collection<Taste> tastes;
	private Collection<Category> categories;
	private User user;
	
//	
//	//Derivado
//	private Integer numTastesTrue;
//	private Integer numTastesFalse;
//	
//	
////	public Integer getNumTastesTrue(){
////		Integer acum=0;
////		for(Taste t:getTastes()){
////			if(t.isLikee()==true)
////				acum++;
////		}
////		numTastesTrue=acum;
////		return numTastesTrue;
////	}
////	public Integer getNumTastesFalse(){
////		numTastesFalse=getTastes().size()-getNumTastesTrue();
////		return numTastesFalse;
////	}
//	
//	public Integer getNumTastesTrue() {
//		return numTastesTrue;
//	}
//
//	public void setNumTastesTrue(Integer numTastesTrue) {
//		this.numTastesTrue = numTastesTrue;
//	}
//
//	public Integer getNumTastesFalse() {
//		return numTastesFalse;
//	}
//
//	public void setNumTastesFalse(Integer numTastesFalse) {
//		this.numTastesFalse = numTastesFalse;
//	}

	@NotNull
	@Pattern(regexp="\\d{2}\\d{2}\\d{2}-\\w{4}")
	@Column(unique=true)
	public String getTicker() {
		return ticker;
	}
	
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	@NotNull
	@NotBlank
	public String getSummary() {
		return summary;
	}
	
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@NotNull
	@NotBlank
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getMomentAuthored() {
		return momentAuthored;
	}
	
	public void setMomentAuthored(Date momentAuthored) {
		this.momentAuthored = momentAuthored;
	}
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getLastMomentUpdated() {
		return lastMomentUpdated;
	}
	
	public void setLastMomentUpdated(Date lastMomentUpdated) {
		this.lastMomentUpdated = lastMomentUpdated;
	}
	
	@URL
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getHints() {
		return hints;
	}
	
	public void setHints(String hints) {
		this.hints = hints;
	}
	@Valid
	@NotNull
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<CookStep> getCookSteps() {
		return cookSteps;
	}
	
	public void setCookSteps(Collection<CookStep> cookSteps) {
		this.cookSteps = cookSteps;
	}
	@Valid
	//@NotEmpty
	@ManyToOne(optional=true)
	public Contest getQualifiedContests() {
		return qualifiedContests;
	}
	
	public void setQualifiedContests(Contest qualifiedContests) {
		this.qualifiedContests = qualifiedContests;
	}
	@Valid
	@ManyToOne(optional=true)
	public Contest getWinnerContests() {
		return winnerContests;
	}

	public void setWinnerContests(Contest winnerContests) {
		this.winnerContests = winnerContests;
	}
	@Valid
   //@NotEmpty
    @ManyToMany(mappedBy="recipes")
	public Collection<Category> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

	@Valid
	@OneToMany(mappedBy="recipe")
	public Collection<Quantity> getQuantities() {
		return quantities;
	}
	
	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}
	@NotNull
	@Valid
	@OneToMany(mappedBy="recipe")
	public Collection<Comment> getComments() {
		return comments;
	}
	
	public void setComments(Collection<Comment> comments) {
		this.comments = comments;
	}
	@NotNull
	@Valid
	@OneToMany(mappedBy="recipe")
	public Collection<Taste> getTastes() {
		return tastes;
	}
	
	public void setTastes(Collection<Taste> tastes) {
		this.tastes = tastes;
	}
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	

}
