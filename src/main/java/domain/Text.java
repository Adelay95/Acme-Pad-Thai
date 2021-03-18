package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Text extends LearningMaterial{
	private String htmlText;
	@NotBlank
	public String getHtmlText() {
		return htmlText;
	}

	public void setHtmlText(String htmlText) {
		this.htmlText = htmlText;
	}

//	@NotBlank
//	
//	public String getHTMLText() {
//		return htmlText;
//	}
//
//	public void setHTMLText(String htmlText) {
//		this.htmlText = htmlText;
//	}

	
}
