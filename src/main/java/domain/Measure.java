package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
@Embeddable
@Access(AccessType.PROPERTY)
public class Measure {
       private Unit unit;
       private String value;
       
       public Measure(){
       }

    @NotNull
	public Unit getUnit() {
		return unit;
	}
    @NotNull
    @Pattern(regexp="\\d.\\d{2}")
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
    @NotEmpty
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
       
}
