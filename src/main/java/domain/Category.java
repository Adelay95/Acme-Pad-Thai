package domain;

import java.util.Collection;


import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Category extends DomainEntity{
	private String name;
	private String description;
	private String picture;
	private String tags;
	private Collection<Category> sons;
	private Collection<Category> parents;
	private Collection<Recipe> recipes;
	
	@NotNull
 	@Valid
	@ManyToMany
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
	@NotBlank
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@NotNull
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@URL
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	@NotNull
 	@Valid
	@ManyToMany(cascade = CascadeType.PERSIST)
	public Collection<Category> getSons() {
		return sons;
	}
	public void setSons(Collection<Category> sons) {
		this.sons = sons;
	}
	@NotNull
 	@Valid
	@ManyToMany(mappedBy="sons", cascade = CascadeType.PERSIST)
	public Collection<Category> getParents() {
		return parents;
	}
	public void setParents(Collection<Category> parents) {
		this.parents = parents;
	}
	
}
