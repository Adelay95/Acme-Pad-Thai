package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Campaign extends DomainEntity{
	private Date momentStarted;
	private Date momentFinished;
	private int numBanner;
	private int numMaxBanner;
	private boolean star;
	private double costBanner;
	private Sponsor sponsor;
	private Collection<Banner> banners;
	

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getMomentStarted() {
		return momentStarted;
	}
	
	public void setMomentStarted(Date momentStarted) {
		this.momentStarted = momentStarted;
	}
	
	@Temporal(TemporalType.DATE)
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	public Date getMomentFinished() {
		return momentFinished;
	}
	public void setMomentFinished(Date momentFinished) {
		this.momentFinished = momentFinished;
	}
	
	@Min(0)
	@NotNull
	public int getNumBanner() {
		return numBanner;
	}
	
	public void setNumBanner(int numBanner) {
		this.numBanner = numBanner;
	}
	
	@Min(0)
	@NotNull
	public int getNumMaxBanner() {
		return numMaxBanner;
	}
	
	public void setNumMaxBanner(int numMaxBanner) {
		this.numMaxBanner = numMaxBanner;
	}

	@NotNull
	public boolean getStar() {
		return star;
	}
	public void setStar(boolean star) {
		this.star = star;
	}
	
	@Min(0)
	@NotNull
	public double getCostBanner() {
		return costBanner;
	}

	public void setCostBanner(double costBanner) {
		this.costBanner = costBanner;
	}
	
	@NotNull
 	@Valid
	@ManyToOne(optional=false)
	public Sponsor getSponsor() {
		return sponsor;
	}
	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}

	
	@NotNull
	@Valid
	@OneToMany(mappedBy="campaign")
	public Collection<Banner> getBanners() {
		return banners;
	}

	public void setBanners(Collection<Banner> banners) {
		this.banners = banners;
	}
	
}
