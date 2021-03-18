package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TasteRepository;
import domain.Client;
import domain.Recipe;
import domain.Taste;

@Service
@Transactional
public class TasteService {
	@Autowired
	private TasteRepository tasteRepository;
	@Autowired
	private RecipeService recipeService;
	@Autowired
	private ClientService clientService;
	
	public TasteService(){
		super();
	}
	
	public Taste create(Client a){
		Taste res;
		Assert.notNull(a);
		//Assert.notNull(r);
		res=new Taste();
		res.setClient(a);
		//res.setRecipe(r);
		return res;
	}
	
	public Taste save(Taste taste){
		Assert.notNull(taste);
		Taste res;
		Recipe res1=taste.getRecipe();
		Client c1=taste.getClient();
		res=tasteRepository.save(taste);
		c1.getTastes().add(res);
		res1.getTastes().add(res);
		recipeService.save(res1);
		clientService.save(c1);
		
		return res;
	}
		
	
	public void delete(Taste taste){
		Assert.notNull(taste);
		Assert.isTrue(taste.getId()!=0);
		Recipe recipe=taste.getRecipe();
		recipe.getTastes().remove(taste);
		Client client=taste.getClient();
		client.getTastes().remove(taste);
		recipeService.save(recipe);
		clientService.save(client);
		tasteRepository.delete(taste);
	}
	
	public  Collection<Taste> allTaste(){
		Collection<Taste> res;
		res=tasteRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	public Taste findOne(int id){
		Taste res;
		res=tasteRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	public Taste getDuplicateTaste(int clientId, int recipeId){
		Taste res=null;
		res=tasteRepository.getDuplicateTaste(clientId, recipeId);
		return res;
	}
}
