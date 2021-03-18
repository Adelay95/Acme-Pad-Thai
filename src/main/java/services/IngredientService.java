package services;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.IngredientRepository;
import domain.Has;
import domain.Ingredient;
import domain.Quantity;

@Service
@Transactional
public class IngredientService {
	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	 private HasService hasService;
	
	public IngredientService(){
		super();
	}
	
	public Ingredient create(){
		Collection<Has> has;
		has=new HashSet<Has>();
		Collection<Quantity> q=new HashSet<Quantity>();
		Ingredient res;
		res=new Ingredient();
		res.setValue(has);
		res.setQuantities(q);
		return res;
	}
	
	public Ingredient save(Ingredient ingredient){
		Assert.notNull(ingredient);
		Ingredient res;
		res=ingredientRepository.save(ingredient);
		return res;
	}
	public void delete(Ingredient ingredient){
		Assert.notNull(ingredient);
		Assert.isTrue(ingredient.getId()!=0);
		Assert.isTrue(ingredient.getQuantities().isEmpty());
		for(Has h:ingredient.getValue()){
			hasService.delete(h);
		}
		ingredientRepository.delete(ingredient);
	}
	
	public  Collection<Ingredient> allIngredient(){
		Collection<Ingredient> res;
		res=ingredientRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Ingredient findOne(int id){
		Ingredient res;
		res=ingredientRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Collection<Ingredient> ingredientByProperty(int propertyId){
		return ingredientRepository.ingredientByProperty(propertyId);
	}
}
