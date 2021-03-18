package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Access(AccessType.PROPERTY)
public class Videos extends LearningMaterial{
	private String youtubeIdentifier;
	
	@NotBlank
	@NotNull
	public String getYoutubeIdentifier() {
		return youtubeIdentifier;
	}

	public void setYoutubeIdentifier(String youtubeIdentifier) {
		this.youtubeIdentifier = youtubeIdentifier;
	}
	
	

	
}
