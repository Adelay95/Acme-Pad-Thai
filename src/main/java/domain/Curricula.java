package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.OneToMany;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;
@Entity
@Access(AccessType.PROPERTY)
 public class Curricula extends DomainEntity{
   private Nutritionist nutritionist;
   private String photo;
   private String educationSection;
   private String experience;
   private String hobby;
   private Collection<Endorser> endorser;
   
   
   @NotNull
   @Valid
   @OneToMany(mappedBy="curricula")
   public Collection<Endorser> getEndorser() {
	return endorser;
   }

   public void setEndorser(Collection<Endorser> endorser) {
	this.endorser = endorser;
   }
   
   @URL
   @NotNull
   public String getPhoto() {
    return this.photo;
   }
   
   public void setPhoto(String photo) {
    this.photo= photo;
   }
   
   @NotBlank
   public String getEducationSection(){
    return this.educationSection;
   }
   @NotBlank
 public String getExperience() {
  return experience;
 }

 public void setExperience(String experience) {
  this.experience = experience;
 }
 
@NotNull
 public String getHobby() {
  return hobby;
 }

 public void setHobby(String hobby) {
  this.hobby = hobby;
 }

 public void setEducationSection(String educationSection) {
  this.educationSection = educationSection;
 }
 @NotNull
	@Valid
 @ManyToOne(optional=false)
public Nutritionist getNutritionist() {
	return nutritionist;
}

public void setNutritionist(Nutritionist nutritionist) {
	this.nutritionist = nutritionist;
}
   
}