package domain;



import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ManyToOne;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

	private String url;
	private Integer numBannerMes;
	private Campaign campaign;
	
	
	@URL
	@NotBlank
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Min(0)
	public Integer getNumBannerMes() {
		return numBannerMes;
	}
	public void setNumBannerMes(Integer numBannerMes) {
		this.numBannerMes = numBannerMes;
	}
	
	
	@NotNull
	@Valid
	@ManyToOne(optional=false)
	public Campaign getCampaign() {
		return campaign;
	}
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	
	
}
