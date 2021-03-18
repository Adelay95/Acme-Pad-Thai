package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.HasRepository;
import domain.Has;
import domain.Ingredient;
import domain.Property;

@Service
@Transactional
public class HasService {
	@Autowired
	private HasRepository hasRepository;

	@Autowired
	private PropertyService propertyService;
	//
	@Autowired
	private IngredientService ingredientService;

	public HasService() {
		super();
	}

	public Has create(Ingredient ingredient, Property property) {

		Assert.notNull(ingredient);
		Has res;
		res = new Has();
		res.setIngredient(ingredient);
		res.setProperty(property);

		return res;
	}

	public Has save(Has has) {
		Assert.notNull(has);
		checkHas(has);
		Has res;
		res = hasRepository.save(has);
		Ingredient id=has.getIngredient();
		Property p=has.getProperty();
		id.getValue().add(res);
		p.getHas().add(res);
		Ingredient ingre=ingredientService.save(id);
		Property prop=propertyService.save(p);
		res.setIngredient(ingre);
		res.setProperty(prop);
		return res;
	}

	private void checkHas(Has has) {
		Has res=getDuplicateHas(has);
		if(res!=null){
		Assert.isTrue(has.getId()==res.getId());
		}
	}

	public void delete(Has has) {
		Assert.notNull(has);
		Assert.isTrue(has.getId() != 0);
		Ingredient ingre=has.getIngredient();
		Property p=has.getProperty();
		ingre.getValue().remove(has);
		p.getHas().remove(has);
		ingredientService.save(ingre);
		propertyService.save(p);
		hasRepository.delete(has);
	}

	public Collection<Has> allHas() {
		Collection<Has> res;
		res = hasRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Has findOne(int id) {
		Has res;
		res = hasRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Has getDuplicateHas(Has has){
		Has res;
	       res=hasRepository.getDuplicateHas(has.getIngredient().getId(),has.getProperty().getId());
	    return res;
	}
}
