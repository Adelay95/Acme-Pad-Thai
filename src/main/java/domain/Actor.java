package domain;

import java.util.Collection;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;


import security.UserAccount;
@Entity
@Access(AccessType.PROPERTY)
public abstract class Actor extends DomainEntity {
      private String name;
	  private String surname;
      private String emailAdress;
      private String postalAdress;
      private String phoneNumber;
      private Collection<Message> messageSent;
      private Collection<Message> messageReceived;
      private Collection<Folder> folders;
      private Collection<SocialIdentity> socialIdentities;
      private UserAccount userAccount;
      
    @NotBlank
    public String getName() {
  		return name;
  	}
    
  	public void setName(String name) {
  		this.name = name;
  	}


    @NotBlank
  	 public String getSurname() {
 		return surname;
 	}

 	public void setSurname(String surname) {
 		this.surname = surname;
 	}
 	@NotBlank
    @Email
 	public String getEmailAdress() {
 		return emailAdress;
 	}

 	public void setEmailAdress(String emailAdress) {
 		this.emailAdress = emailAdress;
 	}


 	public String getPostalAdress() {
 		return postalAdress;
 	}

 	public void setPostalAdress(String postalAdress) {
 		this.postalAdress = postalAdress;
 	}
 	
 	@Pattern(regexp = "^([+-]\\d+\\s+)?(\\([0-9]+\\)\\s+)?([\\d\\w\\s-]+)$")
 	public String getPhoneNumber() {
 		return phoneNumber;
 	}

 	public void setPhoneNumber(String phoneNumber) {
 		this.phoneNumber = phoneNumber;
 	}
 	@NotNull
 	@Valid
    @OneToMany(mappedBy="sender")
 	public Collection<Message> getMessageSent() {
 		return messageSent;
 	}

 	public void setMessageSent(Collection<Message> messageSent) {
 		this.messageSent = messageSent;
 	}
 	@NotNull
 	@Valid
 	@OneToMany(mappedBy="receiver")
 	public Collection<Message> getMessageReceived() {
 		return messageReceived;
 	}
 	
 	public void setMessageReceived(Collection<Message> messageReceived) {
 		this.messageReceived = messageReceived;
 	}
 	@NotNull
 	@Valid
 	@OneToMany(mappedBy="actor")
 	public Collection<Folder> getFolders() {
 		return folders;
 	}

 	public void setFolders(Collection<Folder> folders) {
 		this.folders = folders;
 	}
 	
 	@NotNull
 	@Valid
 	@OneToMany(mappedBy="actor")
 	public Collection<SocialIdentity> getSocialIdentities() {
 		return socialIdentities;
 	}

 	public void setSocialIdentities(Collection<SocialIdentity> socialIdentities) {
 		this.socialIdentities = socialIdentities;
 	}


    
 		@NotNull
 		@Valid
      @OneToOne(cascade = CascadeType.ALL, optional = false)
      public UserAccount getUserAccount() {
       return userAccount;
      }

      public void setUserAccount(UserAccount userAccount) {
       this.userAccount = userAccount;
      }
      
    
}
