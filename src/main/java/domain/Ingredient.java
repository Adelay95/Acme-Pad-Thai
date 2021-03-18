package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
@Entity
@Access(AccessType.PROPERTY)
public class Ingredient extends DomainEntity {
	private String description;
	private String name;
	private String picture;
	private Collection<Has> value;
	private Collection<Quantity> quantities;
	
	@NotBlank
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotBlank
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@URL
	public String getPicture() {
		return picture;
	}
	
	public void setPicture(String picture) {
		this.picture = picture;
	}

	
 	@Valid
	@NotNull
	@OneToMany(mappedBy="ingredient")
	public Collection<Has> getValue() {
		return value;
	}
	
	public void setValue(Collection<Has> value) {
		this.value = value;
	}
	@NotNull
 	@Valid
	@OneToMany(mappedBy="ingredient")
	public Collection<Quantity> getQuantities() {
		return quantities;
	}
	
	public void setQuantities(Collection<Quantity> quantities) {
		this.quantities = quantities;
	}

}
