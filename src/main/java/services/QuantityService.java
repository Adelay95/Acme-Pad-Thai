package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.QuantityRepository;
import domain.Quantity;
import domain.Recipe;

@Service
@Transactional
public class QuantityService {
	@Autowired
	private QuantityRepository quantityRepository;
//	@Autowired
//	 private RecipeService recipeService;
	
	public QuantityService(){
		super();
	}
	
	public Quantity create(Recipe recipe){
		Quantity res;
		Assert.notNull(recipe);
		res=new Quantity();
		res.setRecipe(recipe);
		return res;
	}
	
	public Quantity save(Quantity quantity){
		Assert.notNull(quantity);
		Quantity res;
		CheckQuantity(quantity);
		res=quantityRepository.save(quantity);
		return res;
	}
	private void CheckQuantity(Quantity res) {
		Quantity asd=quantityRepository.getDuplicateQuantity(res.getIngredient().getId(), res.getRecipe().getId());
		if(asd!=null){
		Assert.isTrue(asd.getId()==res.getId());
		}
	}

	public void delete(Quantity quantity){
		Assert.notNull(quantity);
		Assert.isTrue(quantity.getId()!=0);
		quantityRepository.delete(quantity);
	}
	
	public  Collection<Quantity> allQuantity(){
		Collection<Quantity> res;
		res=quantityRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Quantity findOne(int id){
		Quantity res;
		res=quantityRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

}
