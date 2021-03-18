package domain;



import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor{
	private String companyName;
	private CreditCard creditCard;
	private Collection<MonthlyBill> monthlyBills;
	private Collection<Campaign> campaigns;
	
	
	
	//Derivada
	private Integer active;
	
	
	
	
	
	
	
	
	
	
	
	@NotBlank
	@NotNull
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
    @NotNull
	@Valid
	public CreditCard getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	@NotNull
	@Valid
	@OneToMany(mappedBy="sponsor")
	public Collection<MonthlyBill> getMonthlyBills() {
		return monthlyBills;
	}
	public void setMonthlyBills(Collection<MonthlyBill> monthlyBills) {
		this.monthlyBills = monthlyBills;
	}
	@NotNull
	@Valid
	@OneToMany(mappedBy="sponsor")
	public Collection<Campaign> getCampaigns() {
		return campaigns;
	}
	public void setCampaigns(Collection<Campaign> campaigns) {
		this.campaigns = campaigns;
	}
	
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
}
