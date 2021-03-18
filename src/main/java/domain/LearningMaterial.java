package domain;

import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
@Entity
@Access(AccessType.PROPERTY)
public abstract class LearningMaterial extends DomainEntity{
	private String title;
	private String abstracts;
	private String attachment;
	private Collection<MasterClass> masterClass;
	
	
	
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@NotBlank
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	
	@URL
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}

	@NotNull
 	@Valid
	@ManyToMany
	public Collection<MasterClass> getMasterClass() {
		return masterClass;
	}
	
	public void setMasterClass(Collection<MasterClass> masterClass) {
		this.masterClass = masterClass;
	}
	
	
}
