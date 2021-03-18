package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Endorser extends DomainEntity {
	
	private String name;
	private String url;
	private Curricula curricula;
	
	@Valid 
	@NotNull
	@ManyToOne(optional=true)
	public Curricula getCurricula() {
		return curricula;
	}
	public void setCurricula(Curricula curricula) {
		this.curricula = curricula;
	}
	@NotBlank
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@NotBlank
	@URL
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
