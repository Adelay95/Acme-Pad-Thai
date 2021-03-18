package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CookStepRepository;
import domain.CookStep;
import domain.Recipe;

@Service
@Transactional
public class CookStepService {
	@Autowired
	private CookStepRepository cookStepRepository;
	
//	@Autowired
//	private RecipeService recipeService;
	
	public CookStepService(){
		super();
	}
	
	public CookStep create(Recipe recipe){
		CookStep res;
		Assert.notNull(recipe);
		res=new CookStep();
		res.setRecipe(recipe);
		return res;
	}
	
	public CookStep save(CookStep cookStep){
		Assert.notNull(cookStep);
		CookStep res;
		res=cookStepRepository.save(cookStep);
		return res;
	}
	public void delete(CookStep cookStep){
		Assert.notNull(cookStep);
		Assert.isTrue(cookStep.getId()!=0);
		cookStepRepository.delete(cookStep);
	}
	
	public  Collection<CookStep> allCookSteps(){
		Collection<CookStep> res;
		res=cookStepRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public CookStep findOne(int id){
		CookStep res;
		res=cookStepRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

}

