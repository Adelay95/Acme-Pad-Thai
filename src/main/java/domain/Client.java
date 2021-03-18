package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@Entity
@Access(AccessType.PROPERTY)
public abstract class Client extends Actor {
          private Collection<Client> followed;
          private Collection<Comment> comments;
          private Collection<Taste> tastes;
		@NotNull
	 	@Valid
		@ManyToMany(cascade= CascadeType.PERSIST)
		public Collection<Client> getFollowed() {
			return followed;
		}
		
		public void setFollowed(Collection<Client> followed) {
			this.followed = followed;
		}
		@NotNull
	 	@Valid
		@OneToMany(mappedBy="client")
		public Collection<Comment> getComments() {
			return comments;
		}
		
		public void setComments(Collection<Comment> comments) {
			this.comments = comments;
		}
		@NotNull
	 	@Valid
		@OneToMany(mappedBy="client")
		public Collection<Taste> getTastes() {
			return tastes;
		}
		
		public void setTastes(Collection<Taste> tastes) {
			this.tastes = tastes;
		}
          
          
}
