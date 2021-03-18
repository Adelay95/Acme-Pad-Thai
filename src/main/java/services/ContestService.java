package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ContestRepository;
import domain.Contest;
import domain.Recipe;

@Service
@Transactional
public class ContestService {
	@Autowired
	private ContestRepository contestRepository;
	
	public ContestService(){
		super();
	}
	
	public Contest create(){
		Contest res;
		Collection<Recipe> qualifiedRecipes=new HashSet<Recipe>();
		Collection<Recipe> winnerRecipes=new HashSet<Recipe>();
		Collection<Recipe> originalRecipes=new HashSet<Recipe>();
		res=new Contest();
		res.setQualifiedRecipes(qualifiedRecipes);
		res.setWinnerRecipes(winnerRecipes);
		res.setOriginalRecipes(originalRecipes);
		return res;
	}
	
	public Contest save(Contest contest){
		Assert.notNull(contest);
		Contest res;
		Assert.notNull(contest.getOpeningTime());
		Assert.notNull(contest.getClosingTime());
		Assert.isTrue(contest.getOpeningTime().before(contest.getClosingTime()));
		res=contestRepository.save(contest);
		return res;
	}
	public void delete(Contest contest){
		Assert.notNull(contest);
		Assert.isTrue(contest.getId()!=0);
		Assert.isTrue(contest.getQualifiedRecipes().isEmpty());
		contestRepository.delete(contest);
	}
	
	public  Collection<Contest> allContest(){
		Collection<Contest> res;
		res=contestRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public  Collection<Contest> allContestactives(){
		Collection<Contest> contest;
		contest=contestRepository.getAllActiveContest();
		Assert.notNull(contest);
		return contest;
	}
	

	  public Collection<Contest> getClosedContest(){
	    	return contestRepository.getAllFinishedContest();
	    }
		
		public Contest findOne(int id){
			Contest res;
			res=contestRepository.findOne(id);
			Assert.notNull(res);
			return res;
		}

		public Collection<Contest> getcontestMaxRecipesQualificated() {
			return contestRepository.contestMaxRecipesQualificated();
		}

		public Integer minRecipesContests() {
			return contestRepository.getMinRecipesContests();
		}
		public Double avgRecipesContests() {
			return contestRepository.getAvgRecipesContests();
		}
		public Integer maxRecipesContests() {
			return contestRepository.getMaxRecipesContests();
		}
	
}
