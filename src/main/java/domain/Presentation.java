package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends LearningMaterial{
	private String  slideShareNetPath;
	@NotNull
	@URL
	public String getSlideShareNetPath() {
		return slideShareNetPath;
	}

	public void setSlideShareNetPath(String slideShareNetPath) {
		this.slideShareNetPath = slideShareNetPath;
	}
}
