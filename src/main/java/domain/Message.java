package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity {
	private Date moment;
	private String subject;
	private String body;
	private Priority priority;
	private Folder folder;
	private Actor sender;
	private Actor receiver;
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern="dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}
	
	public void setMoment(Date moment) {
		this.moment = moment;
	}
	
	@NotNull
	@NotBlank
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@NotNull
	@NotBlank
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	@NotNull
	@Valid
	public Priority getPriority() {
		return priority;
	}
	
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	@NotNull
 	@Valid
	@ManyToOne(optional=false)
	public Folder getFolder() {
		return folder;
	}
	
	public void setFolder(Folder folder) {
		this.folder = folder;
	}
	
	@NotNull
 	@Valid
	@ManyToOne(optional=false)
	public Actor getSender() {
		return sender;
	}
	
	public void setSender(Actor sender) {
		this.sender = sender;
	}
	@NotNull
 	@Valid
	@ManyToOne(optional=false)
	public Actor getReceiver() {
		return receiver;
	}
	
	public void setReceiver(Actor receiver) {
		this.receiver = receiver;
	}
	
	

}
