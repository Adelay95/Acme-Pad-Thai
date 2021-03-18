package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class MasterClass extends DomainEntity{
	private String title;
	private String description;
	private Cook cook;
	private Collection<LearningMaterial> learningMaterials;
	private Collection<Actor> alums;
	private boolean promote;
	
	//Derivada
	
	
	public boolean isPromote() {
		return promote;
	}
	public void setPromote(boolean promote) {
		this.promote = promote;
	}
	private Integer numText;
	private Integer numPresentation;
	public Integer getNumText() {
		return numText;
	}
	public void setNumText(Integer numText) {
		this.numText = numText;
	}
	public Integer getNumPresentation() {
		return numPresentation;
	}
	public void setNumPresentation(Integer numPresentation) {
		this.numPresentation = numPresentation;
	}
	public Integer getNumVideos() {
		return numVideos;
	}
	public void setNumVideos(Integer numVideos) {
		this.numVideos = numVideos;
	}
	private Integer numVideos;
	

 	@Valid
	@ManyToOne(optional=false)
	public Cook getCook() {
		return cook;
	}
	public void setCook(Cook cook) {
		this.cook = cook;
	}
	

	@Valid
	@ManyToMany(mappedBy="masterClass")
	public Collection<LearningMaterial> getLearningMaterials() {
		return learningMaterials;
	}
	public void setLearningMaterials(Collection<LearningMaterial> learningMaterials) {
		this.learningMaterials = learningMaterials;
	}
	@NotBlank
	@NotNull
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank
	@NotNull
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@NotNull
	@Valid
	@ManyToMany
	public Collection<Actor> getAlums() {
		return alums;
	}
	public void setAlums(Collection<Actor> alums) {
		this.alums = alums;
	}
}
