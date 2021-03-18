package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Property extends DomainEntity{
	private String name;
	private Collection<Has> has;
	
	@NotBlank
	@NotNull
	@Column(unique=true)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@NotNull
	@Valid
	@OneToMany(mappedBy="property")
	public Collection<Has> getHas() {
		return has;
	}
	public void setHas(Collection<Has> has) {
		this.has = has;
	}
	
}
