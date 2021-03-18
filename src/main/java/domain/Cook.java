package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Cook extends Actor{
		private Collection<MasterClass> masterClass;

		@NotNull
	 	@Valid
	 	@OneToMany(mappedBy="cook")
		public Collection<MasterClass> getMasterClass() {
			return masterClass;
		}
		
		public void setMasterClass(Collection<MasterClass> masterClass) {
			this.masterClass = masterClass;
		}
		
		
		

		//derivada
		private Integer numPromotes;
		
		public Integer getNumPromotes() {
			return numPromotes;
		}

		public void setNumPromotes(Integer numPromotes) {
			this.numPromotes = numPromotes;
		}

}
